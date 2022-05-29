package com.hachikuji.server.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.hachikuji.core.domain.model.DataTable;
import com.hachikuji.core.service.BaseService;
import com.hachikuji.core.utils.StringUtils;
import com.hachikuji.frame.generate.entity.Comment;
import com.hachikuji.frame.generate.entity.DiscussPost;
import com.hachikuji.frame.generate.service.CommentService;
import com.hachikuji.frame.generate.service.DiscussPostService;
import com.hachikuji.frame.security.utils.SecurityUtils;
import com.hachikuji.server.constant.NormalConstants;
import com.hachikuji.server.filter.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 评论服务类
 * **********
 * 评论 entityType 代表评论回复的类型
 * entityId 表示评论回复的类型的 ID，如 Type = Post 为帖子 id / Type = Comment 为回复 id
 * targetId 表示评论目标用户 ID
 *
 * @author hachikuji
 */
@Service
public class SysCommentService extends BaseService {

    @Autowired
    private CommentService commentService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private DiscussPostService discussPostService;

    public List<Comment> getCommentByEntity(int entityType, int entityId) {

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entity_type", entityType)
                .eq("entity_id", entityId);
        return commentService.list(queryWrapper);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addComment(Integer entityType, Integer entityId, Integer targetId, String content) {

        if (!StringUtils.isNotEmpty(content))
            throw new IllegalArgumentException("参数不能为空！");

        // 得到登录用户的 userId，未登录过滤器直接会过滤
        int userId = userService.getSystemUserId();

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setEntityType(entityType);
        comment.setEntityId(entityId);
        comment.setTargetId(targetId);
        comment.setContent(sensitiveFilter.filter(content));
        comment.setCreateTime(LocalDateTime.now());

        if (entityType == NormalConstants.ENTITY_TYPE_POST) {
            Integer count = discussPostService.getById(entityId).getCommentCount();
            UpdateWrapper<DiscussPost> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("comment_count", count + 1).eq("id", entityId);
            discussPostService.update(updateWrapper);
        }

        return commentService.save(comment);
    }

    public Map<String, DataTable> pageCommentInDiscussPost(int entityId) {

        return getDataTableMap("comment", getCommentInfoByEntity(NormalConstants.ENTITY_TYPE_POST, entityId, true));

    }


    public DataTable getCommentInfoByEntity(int entityType, int entityId, boolean isPage) {

        List<Map<String, Object>> rows = new ArrayList<>();

        // 启动分页
        if (isPage)
            startPage();
        // 查询文章评论
        List<Comment> contentComments = getCommentByEntity(entityType, entityId);
        long total = getDataTablePage(contentComments).getTotal();

        // 再查询
        for (Comment c : contentComments) {

            Map<String, Object> comment = new HashMap<>();

            comment.put("info", c);
            comment.put("user", userService.getUserInfo(c.getUserId()));
            if (entityType == NormalConstants.ENTITY_TYPE_POST)
                comment.put("reply", getCommentInfoByEntity(NormalConstants.ENTITY_TYPE_COMMENT, c.getId(), false));

            rows.add(comment);
        }

        return getDataTablePage(rows, total);
    }


}

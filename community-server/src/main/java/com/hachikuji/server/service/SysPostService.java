package com.hachikuji.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hachikuji.core.domain.model.DataTable;
import com.hachikuji.core.service.BaseService;
import com.hachikuji.frame.generate.entity.Comment;
import com.hachikuji.frame.generate.entity.DiscussPost;
import com.hachikuji.frame.generate.entity.User;
import com.hachikuji.frame.generate.service.CommentService;
import com.hachikuji.frame.generate.service.DiscussPostService;
import com.hachikuji.frame.generate.service.UserService;
import com.hachikuji.frame.security.utils.SecurityUtils;
import com.hachikuji.server.constant.NormalConstants;
import com.hachikuji.server.domain.UserInfoBody;
import com.hachikuji.server.filter.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SysPostService extends BaseService {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private CommentService commentService;

    public String addContent(String title, String content) {

        // 得到登录用户的 userId，未登录过滤器直接会过滤
        int userId = SecurityUtils.getLoginUser().getUser().getId();

        DiscussPost post = new DiscussPost();
        post.setTitle(sensitiveFilter.filter(title));
        post.setContent(sensitiveFilter.filter(content));
        post.setCreateTime(LocalDateTime.now());

        return "发送成功！";

    }

    /**
     * 评论 entityType 代表评论回复的类型
     * entityId 表示评论回复的类型的 ID
     * targetId 表示评论指向的评论 ID
     */

    public LocalDateTime addComment(Integer targetId, Integer discussPostId, String content) {

        // 得到登录用户的 userId，未登录过滤器直接会过滤
        int userId = SecurityUtils.getLoginUser().getUser().getId();

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setEntityType(NormalConstants.ENTITY_TYPE_COMMENT);
        comment.setEntityType(discussPostId);
        comment.setTargetId(targetId);
        comment.setContent(sensitiveFilter.filter(content));
        comment.setCreateTime(LocalDateTime.now());

        return comment.getCreateTime();
    }

    public String getContentById(Integer discussPostId) {

        return discussPostService.getById(discussPostId).getContent();

    }

    /**
     * @return "content of discuss post"
     * "comment list page": comment 1 -> comment 2 and user
     */
    public Map<String, Object> getPostPageById(Integer id) {

        Map<String, Object> result = new HashMap<>();

        result.put("content", discussPostService.getById(id).getContent());
        result.put("comment", getCommentInfoByEntity(NormalConstants.ENTITY_TYPE_POST, id, true));

        return result;
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

            User user = userService.getById(c.getUserId());
            UserInfoBody userInfo = new UserInfoBody();
            userInfo.setUsername(user.getUsername());
            userInfo.setHeaderUrl(user.getHeaderUrl());

            comment.put("detail", c);
            comment.put("user", userInfo);
            if (entityType == NormalConstants.ENTITY_TYPE_POST)
                comment.put("reply", getCommentInfoByEntity(NormalConstants.ENTITY_TYPE_COMMENT, c.getId(), false));

            rows.add(comment);
        }

        return getDataTablePage(rows, total);
    }

    public List<Comment> getCommentByEntity(int entityType, int entityId) {

        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("entity_type", entityType);
        queryWrapper.eq("entity_id", entityId);
        return commentService.list(queryWrapper);

    }

}

package com.hachikuji.server.service;


import com.hachikuji.core.domain.model.DataTable;
import com.hachikuji.core.service.BaseService;
import com.hachikuji.frame.generate.entity.DiscussPost;
import com.hachikuji.frame.generate.service.DiscussPostService;
import com.hachikuji.frame.security.utils.SecurityUtils;
import com.hachikuji.server.domain.PostInfoBody;
import com.hachikuji.server.filter.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SysDiscussPostService extends BaseService {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private SysUserService userService;


    public String addContent(String title, String content) {

        // 得到登录用户的 userId，未登录过滤器直接会过滤
        int userId = userService.getSystemUserId();

        DiscussPost post = new DiscussPost();
        post.setUserId(userId);
        post.setTitle(sensitiveFilter.filter(title));
        post.setContent(sensitiveFilter.filter(content));
        post.setCreateTime(LocalDateTime.now());

        return "发送成功！";

    }


    public Map<String, DataTable> pagePostInfo() {

        Map<String, DataTable> result = new HashMap<>();

        //启动分页
        startPage();
        List<DiscussPost> posts = discussPostService.list();
        long total = getDataTablePage(posts).getTotal();

        List<Object> rows = new ArrayList<>();
        //再查询
        for (DiscussPost p : posts) {

            Map<String, Object> object = new HashMap<>();
            PostInfoBody body = new PostInfoBody();

            body.setDiscussPostId(p.getId());
            body.setTitle(p.getTitle());
            body.setCreateTime(p.getCreateTime());
            body.setScore(p.getScore());
            body.setCommentCount(p.getCommentCount());

            object.put("info", body);
            object.put("user",userService.getUserInfo(p.getUserId()));

            rows.add(object);
        }

        result.put("discussPost",getDataTablePage(rows, total));
        return result;
    }


    public String getContentById(Integer id) {
        return discussPostService.getById(id).getContent();
    }

}

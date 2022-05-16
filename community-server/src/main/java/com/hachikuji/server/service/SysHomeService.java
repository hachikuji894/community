package com.hachikuji.server.service;

import com.hachikuji.core.domain.model.DataTable;
import com.hachikuji.core.service.BaseService;
import com.hachikuji.frame.generate.entity.DiscussPost;
import com.hachikuji.frame.generate.service.DiscussPostService;
import com.hachikuji.server.domain.HomeInfoBody;
import com.hachikuji.frame.generate.entity.User;
import com.hachikuji.frame.generate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SysHomeService extends BaseService {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    public DataTable getHomePage(){

        List<HomeInfoBody> rows = new ArrayList<>();

        //启动分页
        startPage();
        List<DiscussPost> posts = discussPostService.list();

        long total = getDataTablePage(posts).getTotal();

        //再查询
        for(DiscussPost p:posts){

            HomeInfoBody body = new HomeInfoBody();

            body.setDiscussPostId(p.getId());
            body.setTitle(p.getTitle());
            body.setCreateTime(p.getCreateTime());
            body.setScore(p.getScore());
            body.setCommentCount(p.getCommentCount());

            User u = userService.getById(p.getUserId());

            body.setHeaderUrl(u.getHeaderUrl());
            body.setUsername(u.getUsername());

            rows.add(body);

        }
        return getDataTablePage(rows,total);

    }


}

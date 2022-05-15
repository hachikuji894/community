package com.hachikuji.server.service;

import com.hachikuji.frame.security.utils.SecurityUtils;
import com.hachikuji.server.model.UserInfoBody;
import com.hachikuji.frame.generate.entity.User;
import com.hachikuji.frame.generate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    UserService userService;

    public UserInfoBody getUserInfo(){

        User user  = SecurityUtils.getLoginUser().getUser();
        UserInfoBody body  = new UserInfoBody();
        body.setName(user.getUsername());
        body.setAvatar(user.getHeaderUrl());

        return body;
    }

    public User getUser(){
        return SecurityUtils.getLoginUser().getUser();
    }


}

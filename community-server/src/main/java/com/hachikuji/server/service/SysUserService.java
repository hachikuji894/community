package com.hachikuji.server.service;

import com.hachikuji.frame.security.utils.SecurityUtils;
import com.hachikuji.server.domain.UserInfoBody;
import com.hachikuji.frame.generate.entity.User;
import com.hachikuji.frame.generate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserService {

    @Autowired
    UserService userService;

    /**
     * 无参数默认放回本用户的信息
     * @return 用户 DO
     */

    public UserInfoBody getSystemUserInfo() {

        User user = SecurityUtils.getLoginUser().getUser();
        return new UserInfoBody(user.getUsername(), user.getHeaderUrl());
    }


    public UserInfoBody getUserInfo(Integer id) {

        User user = userService.getById(id);
        return new UserInfoBody(user.getUsername(), user.getHeaderUrl());
    }


    public User getSystemUser() {
        return SecurityUtils.getLoginUser().getUser();
    }

    public Integer getSystemUserId() {
        return SecurityUtils.getLoginUser().getUser().getId();
    }

}

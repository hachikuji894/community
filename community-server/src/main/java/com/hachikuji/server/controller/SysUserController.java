package com.hachikuji.server.controller;

import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    SysUserService userService;

    /**
     * 请求获得全部用户信息
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo(){

        return AjaxResult.success(userService.getUserInfo());

    }
}

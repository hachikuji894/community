package com.hachikuji.server.controller;

import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    SysUserService userService;

    /**
     *
     * @return 用户信息
     */
    @GetMapping("/getSystemInfo")
    public AjaxResult getSystemInfo(){

        return AjaxResult.success(userService.getSystemUserInfo());

    }

    @GetMapping("/getInfo/id/{id}")
    public AjaxResult getInfo(@PathVariable Integer id){

        return AjaxResult.success(userService.getUserInfo(id));

    }
}

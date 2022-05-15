package com.hachikuji.server.controller;

import com.hachikuji.core.constant.Constants;
import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.model.LoginBody;
import com.hachikuji.server.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody body){

        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(body.getUsername(),body.getPassword(), body.getCode(), body.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }




}

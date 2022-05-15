package com.hachikuji.server.controller;

import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.model.RegisterBody;
import com.hachikuji.server.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 注册控制器
 * @author hachikuji
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/register")
public class SysRegisterController {

    @Autowired
    private SysRegisterService registerService;

    /**
     * 注册账户
     *
     * @param registerBody 注册包装类
     * @return 注册得到 id
     */
    @PostMapping("")
    public AjaxResult register(@RequestBody RegisterBody registerBody) {

        int id = registerService.register(registerBody.getUsername(), registerBody.getPassword(), registerBody.getEmail());
        return AjaxResult.success(id);

    }

    /**
     * 判断账号或邮箱是否已经存在
     *
     * @param key 属性
     * @param value 值
     * @return true or false
     */
    @GetMapping("/exist/{key}/{value}")
    public AjaxResult exist(@PathVariable("key") String key, @PathVariable("value") String value) {

        return AjaxResult.success(registerService.exist(key, value));

    }

    /**
     * 查看账户的激活状态
     *
     * @param id 用户 id
     * @return 状态信息
     */
    @GetMapping("/status/id/{id}")
    public AjaxResult status(@PathVariable("id") int id) {

        String message = registerService.status(id);

        return AjaxResult.success(message);

    }

    /**
     * 激活账户
     *
     * @param id 用户 id
     * @param activationCode 用户 激活码
     * @return 激活信息
     */
    @GetMapping("/activation/id/{id}/activationCode/{activationCode}")
    public AjaxResult activate(@PathVariable("id") int id, @PathVariable("activationCode") String activationCode) {

        String message = registerService.activation(id, activationCode);

        return AjaxResult.success(message);

    }

}
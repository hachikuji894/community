package com.hachikuji.server.controller;

import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.constant.NormalConstants;
import com.hachikuji.server.domain.RegisterBody;
import com.hachikuji.server.service.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 注册控制器
 *
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
     * @param registerBody 注册信息
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
     * @param key   属性（账号 or 邮箱）
     * @param value 值
     * @return true or false
     */
    @GetMapping("/exist/{key}/{value}")
    public AjaxResult exist(@PathVariable String key, @PathVariable String value) {

        return AjaxResult.success(registerService.exist(key, value));

    }

    /**
     * 查看账户的激活状态
     *
     * @param id 用户 id
     * @return 状态信息
     */
    @GetMapping("/status/id/{id}")
    public AjaxResult status(@PathVariable int id) {

        return registerService.getStatusById(id) == NormalConstants.ACTIVATION_UNDO ?
                AjaxResult.success("注册成功，我们已经向您的邮箱发送了一封激活邮件，请尽快激活！") :
                AjaxResult.success("该账号已经被激活，请登录！");
    }

    /**
     * 激活账户
     *
     * @param id             用户 id
     * @param activationCode 用户 激活码
     * @return 激活反馈信息
     */
    @GetMapping("/activate/id/{id}/code/{code}")
    public AjaxResult activate(@PathVariable("id") int id, @PathVariable("code") String activationCode) {

        String message = registerService.activate(id, activationCode);

        return AjaxResult.success(message);

    }

}

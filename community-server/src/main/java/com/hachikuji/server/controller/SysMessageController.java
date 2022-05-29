package com.hachikuji.server.controller;


import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.service.SysMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 消息控制器
 *
 * @author hachikuji
 * @since 2021-5-16
 */
@RestController
@RequestMapping("/message")
public class SysMessageController {

    @Autowired
    SysMessageService messageService;

    @PostMapping("/add")
    public AjaxResult add(@RequestParam int id, @RequestParam String message) {
        return messageService.addMassage(id, message) ?
                AjaxResult.success("发送消息成功！") : AjaxResult.error();
    }

    @PutMapping("/update/id/{id}")
    public AjaxResult updateByToId(@PathVariable int id) {

        return messageService.updateMassageStateByToId(id) ?
                AjaxResult.success("读取状态已更新！") : AjaxResult.error();

    }


    @GetMapping("/chat/page")
    public AjaxResult pageChat() {

        return AjaxResult.success(messageService.pageChat());

    }

    @GetMapping("/page/id/{toId}")
    public AjaxResult pageMessage(@PathVariable Integer toId) {

        return AjaxResult.success(messageService.pageMessageByToId(toId));

    }

}

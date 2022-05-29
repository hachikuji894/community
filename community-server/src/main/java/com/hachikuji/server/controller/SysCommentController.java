package com.hachikuji.server.controller;

import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.constant.NormalConstants;
import com.hachikuji.server.service.SysCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class SysCommentController {

    @Autowired
    private SysCommentService commentService;

    /**
     * 创建评论
     *
     * @param entityType 评论类型
     * @param entityId   目标 id，帖子 or 回复
     * @param targetId   目标用户 id
     * @param content    评论内容
     * @return 是否成功
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestParam Integer entityType, @RequestParam Integer entityId,
                                 @RequestParam Integer targetId, @RequestParam String content) {

        return AjaxResult.success(commentService.addComment(entityType, entityId, targetId, content));

    }

    /**
     * 分页查询评论
     *
     * @param id 回复类型的 ID
     * @return 结果
     */
    @GetMapping("/page/id/{id}")
    public AjaxResult page(@PathVariable Integer id) {
        return AjaxResult.success(commentService.pageCommentInDiscussPost(id));
    }
}

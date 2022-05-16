package com.hachikuji.server.controller;


import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.service.SysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 讨论贴控制器
 *
 * @author hachikuji
 * @since 2022-5-16
 */
@RestController
@RequestMapping("/post")
public class SysPostController {

    @Autowired
    private SysPostService postService;

    /**
     * 创建帖子
     *
     * @param title   标题
     * @param content 文章
     * @return 提示信息
     */
    @PostMapping("/add/content")
    public AjaxResult addContent(@RequestParam String title, @RequestParam String content) {

        String message = postService.addContent(title, content);
        return AjaxResult.success(message);

    }

    /**
     * 创建评论
     *
     * @param targetId      目标 id
     * @param discussPostId 帖子 id
     * @param content       评论内容
     * @return 创建时间
     */
    @PostMapping("/add/comment")
    public AjaxResult addComment(@RequestParam Integer targetId, @RequestParam Integer discussPostId, @RequestParam String content) {

        return AjaxResult.success(postService.addComment(targetId, discussPostId, content));

    }

    /**
     * 查询帖子内容 + 分页查询评论
     *
     * @param id 帖子 id
     * @return 内容 DiscussPost CommentList
     */
    @GetMapping("/pageInfo/id/{id}")
    public AjaxResult pageInfo(@PathVariable Integer id) {
        return AjaxResult.success(postService.getPostPageById(id));
    }

    /**
     * 分页查询评论
     *
     * @param id 回复类型的 ID
     * @return 结果
     */
    @GetMapping("/page/id/{id}")
    public AjaxResult page(@PathVariable Integer id) {
        return AjaxResult.success();
    }


}

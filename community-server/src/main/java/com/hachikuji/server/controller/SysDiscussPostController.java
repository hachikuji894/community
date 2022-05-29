package com.hachikuji.server.controller;


import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.service.SysDiscussPostService;
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
public class SysDiscussPostController {

    @Autowired
    private SysDiscussPostService postService;

    /**
     * 创建帖子
     *
     * @param title   标题
     * @param content 文章
     * @return 提示信息
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestParam String title, @RequestParam String content) {

        String message = postService.addContent(title, content);
        return AjaxResult.success(message);

    }

    @GetMapping("/pageInfo")
    public AjaxResult pageInfo(){

        return AjaxResult.success(postService.pagePostInfo());

    }

    /**
     * 查询帖子内容
     *
     * @param id 帖子 id
     * @return content
     */
    @GetMapping("/getContent/id/{id}")
    public AjaxResult getContent(@PathVariable Integer id) {
        return AjaxResult.success(postService.getContentById(id));
    }
}

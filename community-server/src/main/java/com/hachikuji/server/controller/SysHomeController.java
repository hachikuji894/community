package com.hachikuji.server.controller;


import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.server.service.SysHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 首页控制器
 *
 * @author hachikuji
 * @since 2021-11-29
 */
@RestController
@RequestMapping("/home")
public class SysHomeController{

    @Autowired
    private SysHomeService homeService;

    @GetMapping("/list")
    public AjaxResult getHomeInfoList(){

        return AjaxResult.success(homeService.getHomeDataTablePage());

    }

}

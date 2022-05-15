package com.hachikuji.frame.generate.service.impl;

import com.hachikuji.frame.generate.mapper.DiscussPostMapper;
import com.hachikuji.frame.generate.service.DiscussPostService;
import com.hachikuji.frame.generate.entity.DiscussPost;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author hachikuji
 * @since 2021-11-29
 */
@Service
public class DiscussPostServiceImpl extends ServiceImpl<DiscussPostMapper, DiscussPost> implements DiscussPostService {
}

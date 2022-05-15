package com.hachikuji.frame.generate.service.impl;

import com.hachikuji.frame.generate.entity.Comment;
import com.hachikuji.frame.generate.mapper.CommentMapper;
import com.hachikuji.frame.generate.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author hachikuji
 * @since 2021-11-29
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}

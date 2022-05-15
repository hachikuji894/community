package com.hachikuji.frame.generate.service.impl;

import com.hachikuji.frame.generate.entity.User;
import com.hachikuji.frame.generate.service.UserService;
import com.hachikuji.frame.generate.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


/**
 * @author hachikuji
 * @since 2021-11-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

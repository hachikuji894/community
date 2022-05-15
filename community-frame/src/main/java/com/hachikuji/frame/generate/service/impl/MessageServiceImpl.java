package com.hachikuji.frame.generate.service.impl;

import com.hachikuji.frame.generate.service.MessageService;
import com.hachikuji.frame.generate.entity.Message;
import com.hachikuji.frame.generate.mapper.MessageMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author hachikuji
 * @since 2021-11-29
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

}

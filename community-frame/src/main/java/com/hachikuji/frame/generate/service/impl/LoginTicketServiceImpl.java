package com.hachikuji.frame.generate.service.impl;

import com.hachikuji.frame.generate.service.LoginTicketService;
import com.hachikuji.frame.generate.entity.LoginTicket;
import com.hachikuji.frame.generate.mapper.LoginTicketMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author hachikuji
 * @since 2021-11-29
 */
@Service
public class LoginTicketServiceImpl extends ServiceImpl<LoginTicketMapper, LoginTicket> implements LoginTicketService {

}

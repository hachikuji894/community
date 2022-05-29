package com.hachikuji.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hachikuji.core.constant.Constants;
import com.hachikuji.frame.generate.entity.LoginTicket;
import com.hachikuji.frame.generate.service.LoginTicketService;
import com.hachikuji.frame.security.model.LoginUser;
import com.hachikuji.frame.security.service.TokenService;
import com.hachikuji.frame.tool.RedisCache;
import com.hachikuji.server.exception.CaptchaException;
import com.hachikuji.server.exception.CaptchaExpireException;
import com.hachikuji.core.exception.ServiceException;
import com.hachikuji.frame.generate.entity.User;
import com.hachikuji.server.constant.NormalConstants;
import com.hachikuji.frame.generate.service.UserService;
import com.hachikuji.server.exception.UserPasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 登录服务类
 *
 * @author hachikuji
 *
 */
@Service
public class SysLoginService{

    @Autowired
    private LoginTicketService loginTicketService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;


    public String login(String username, String password, String code, String uuid) {

        validateCaptcha(code, uuid);

        Authentication authentication;

        password = createPassword(username,password);

        try
        {
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                throw new UserPasswordNotMatchException();
            }
            else
            {
                throw new ServiceException(e.getMessage());
            }
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        String token = tokenService.createToken(loginUser);
        recordLoginInfo(token,loginUser.getUserId());

        return token;
    }

    /**
     * 校验验证码
     *
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
            throw new CaptchaExpireException();

        if (!code.equalsIgnoreCase(captcha))
            throw new CaptchaException();

    }

    /**
     * 记录登录信息
     * @param token 用户登录凭证
     * @param userId 用户ID
     */
    public void recordLoginInfo(String token,Integer userId)
    {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        loginTicket.setTicket(token);
        loginTicket.setStatus(NormalConstants.TICKET_VALID);
        loginTicket.setExpired(LocalDateTime.now());

        loginTicketService.save(loginTicket);
    }

   public String createPassword(String username,String password){

       QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("username", username);
       User user = userService.getOne(queryWrapper);

       String randomSalt = user.getSalt();

       return DigestUtils.md5DigestAsHex((randomSalt+password).getBytes());
   }

}

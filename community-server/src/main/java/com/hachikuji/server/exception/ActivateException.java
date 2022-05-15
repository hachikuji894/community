package com.hachikuji.server.exception;

/**
 * 激活码错误异常类
 *
 * @author ruoyi
 */
public class ActivateException extends UserException{

    private static final long serialVersionUID = 1L;

    public ActivateException()
    {
        super("user.activate.error", null);
    }


}

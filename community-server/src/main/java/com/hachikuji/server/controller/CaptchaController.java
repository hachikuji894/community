package com.hachikuji.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hachikuji.core.constant.Constants;
import com.hachikuji.core.domain.AjaxResult;
import com.hachikuji.core.utils.IdUtils;
import com.hachikuji.core.utils.sign.Base64;
import com.hachikuji.frame.tool.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/captcha")
    public AjaxResult getCaptchaImage(){

        AjaxResult ajax = AjaxResult.success();

        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String code = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(code);

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }


}

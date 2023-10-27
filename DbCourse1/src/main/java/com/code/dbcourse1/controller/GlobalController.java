package com.code.dbcourse1.controller;

import com.code.dbcourse1.pojo.CheckCode;
import com.code.dbcourse1.pojo.ResponseException;
import com.code.dbcourse1.pojo.User;
import com.code.dbcourse1.service.GlobalService;
import com.code.dbcourse1.util.ImgUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.util.UUID;

@RestController
public class GlobalController {

    @Resource
    private GlobalService globalService;
    @Resource
    private DefaultKaptcha captchaProducer;

    /**
     * 登录
     * @return 成功返回对应用户 失败返回 null
     */
    @PostMapping("/login")
    public User login(@RequestParam String name, @RequestParam String password){
        return globalService.login(name, password);
    }


    /**
     * 注册
     * @param role teacher|student
     * @return 注册成功 true
     */
    @PostMapping("/register")
    public boolean register(
            @RequestParam String name,
            @RequestParam String password,
            @RequestParam String role,
            @RequestParam String checkCodeId,
            @RequestParam String checkCode,
            String sex,
            @RequestParam(defaultValue = "0", required = false) int age,
            String tel,
            String email,
            HttpServletRequest req
    ){
        // 验证验证码是否正确
        Object checkCodeS = req.getServletContext().getAttribute(checkCodeId);
        if(!checkCode.equals(checkCodeS)){
            throw new ResponseException("验证码输入错误", false);
        }
        return globalService.register(name, password, role, sex, age, tel, email);
    }

    @GetMapping("/checkCode")
    public CheckCode checkCode(HttpServletRequest req){
        // 生成验证码
        String verifyCode = captchaProducer.createText();
        BufferedImage verifyImage = captchaProducer.createImage(verifyCode);
        // 验证码对象
        CheckCode checkCode = new CheckCode();
        checkCode.setCheckCodeId(UUID.randomUUID().toString());
        checkCode.setImage(ImgUtils.toBytes(verifyImage));
        // 保存验证码到servletContext 后续验证需要
        req.getServletContext().setAttribute(checkCode.getCheckCodeId(), verifyCode);
        return checkCode;
    }

}

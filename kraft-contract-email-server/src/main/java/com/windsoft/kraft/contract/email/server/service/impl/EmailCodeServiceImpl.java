package com.windsoft.kraft.contract.email.server.service.impl;

import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.email.server.service.EmailCodeService;
import com.windsoft.kraft.contract.email.server.utils.RedisUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Setter
@Service
public class EmailCodeServiceImpl implements EmailCodeService {

    @Value("${spring.mail.username}")
    private String sender;
    @Value("${spring.msg.limit.captcha}")
    private Long captchaLimit;
    private final String EMAIL_KEY = "email:code:";

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 简单文本邮件
     */
    @Override
    public void send(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("kc登录验证码");
        // 邮件的内容
        message.setText(contentBuild(code, 60L));
        // 设置接收者邮箱
        message.setTo(to);
        // 设置发送者邮箱
        message.setFrom(sender);
        // 发送
        mailSender.send(message);
        saveCode(to, code,60L);
    }

    /**
     * 验证码邮件
     */
    @Override
    public JsonResult sendCaptcha(String user, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // 设置邮件主题
            message.setSubject("KraftContract验证码");
            // 邮件的内容
            message.setText(captchaContentBuild(code));
            // 设置接收者邮箱
            message.setTo(user);
            // 设置发送者邮箱
            message.setFrom(sender);
            // 发送
            mailSender.send(message);
            saveCode(user, code, captchaLimit);
            return JsonResult.success();
        }catch (Exception e){
            return JsonResult.error("服务出现未知错误");
        }

    }

    /**
     * 生成邮箱信息
     */
    private String contentBuild(String code, Long expireIn) {
        String sb = "您的验证码:" +
                code +
                ",有效时效:" +
                expireIn;
        return sb;
    }
    private String captchaContentBuild(String code) {
        String sb = "您的验证码:" +
                code +
                ",有效时效:" +
                captchaLimit/60 + "分钟";
        return sb;
    }

    private void saveCode(String to, String code, Long limitTime){
        StringBuffer bf = new StringBuffer(EMAIL_KEY);
        String key = bf.append(CommonUtils.md5(to)).toString();
        redisUtil.set(key, code, limitTime);
    }

}
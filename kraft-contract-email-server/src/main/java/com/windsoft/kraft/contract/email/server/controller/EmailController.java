package com.windsoft.kraft.contract.email.server.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.email.server.generator.impl.EmailCodeGenerator;
import com.windsoft.kraft.contract.email.server.service.EmailCodeService;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("email")
public class EmailController {

    @Resource
    private EmailCodeService emailCodeService;
    @Resource
    private EmailCodeGenerator emailCodeGenerator;

    /**
     * 邮箱验证码接口
     */
    @GetMapping("/captcha/{email}")
    public JsonResult createCaptcha(@PathVariable String email) throws ServletRequestBindingException {
        // 发送短信
        return emailCodeService.sendCaptcha(email, emailCodeGenerator.getCode());
    }
}

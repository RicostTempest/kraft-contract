package com.windsoft.kraft.contract.email.server.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;

public interface EmailCodeService {
    /**
     * 发送验证码到目标邮箱
     *
     * @param to 目标邮箱
     * @param code  验证码
     */
    void send(String to, String code);
    JsonResult sendCaptcha(String user, String code);
}

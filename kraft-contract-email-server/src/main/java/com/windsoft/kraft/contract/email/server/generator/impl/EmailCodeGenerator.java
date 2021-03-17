package com.windsoft.kraft.contract.email.server.generator.impl;

import com.windsoft.kraft.contract.common.utils.RandomUtils;
import com.windsoft.kraft.contract.email.server.generator.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class EmailCodeGenerator implements ValidateCodeGenerator {
    @Override
    public String getCode() {
        return RandomUtils.getCaptcha();
    }
}

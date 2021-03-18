package com.windsoft.kraft.contract.server.message.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.MessageContent;

public interface MessageService {
    public JsonResult addMessage(Long sendId, Long[] acceptId, MessageContent content);
}

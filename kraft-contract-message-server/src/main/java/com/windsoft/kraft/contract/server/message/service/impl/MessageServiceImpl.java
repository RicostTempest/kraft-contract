package com.windsoft.kraft.contract.server.message.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Message;
import com.windsoft.kraft.contract.mybatis.domain.MessageContent;
import com.windsoft.kraft.contract.server.message.mapper.MessageContentMapper;
import com.windsoft.kraft.contract.server.message.mapper.MessageMapper;
import com.windsoft.kraft.contract.server.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;
    @Autowired
    MessageContentMapper messageContentMapper;

    @Override
    public JsonResult addMessage(Long sendId, Long[] acceptId, MessageContent content) {
        Long contentId = addContent(content);
        Message message = new Message();
        message.setSendId(sendId);
        message.setContentId(contentId);
        for (int i = 0; i < acceptId.length; i++) {
            message.setAcceptId(acceptId[i]);
            messageMapper.insert(message);
        }
        return JsonResult.success();
    }

    public Long addContent(MessageContent content) {
        messageContentMapper.insert(content);
        return content.getId();
    }
}

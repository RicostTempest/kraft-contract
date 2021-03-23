package com.windsoft.kraft.contract.server.message.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Message;
import com.windsoft.kraft.contract.mybatis.domain.MessageContent;
import com.windsoft.kraft.contract.server.message.mapper.MessageContentMapper;
import com.windsoft.kraft.contract.server.message.mapper.MessageMapper;
import com.windsoft.kraft.contract.server.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;
    @Autowired
    MessageContentMapper messageContentMapper;

    @Override
    public JsonResult findMessage(Long id) {
        return JsonResult.success(messageMapper.selectMessageById(id));
    }

    @Override
    public JsonResult addMessage(Long sendId, Long[] acceptId, MessageContent content) {
        Long contentId = addContent(content);
        Message message = new Message();
        message.setSendId(sendId);
        message.setContentId(contentId);
        for (int i = 0; i < acceptId.length; i++) {
            message.setAcceptId(acceptId[i]);
            message.setId(null);
            messageMapper.insert(message);
        }
        return JsonResult.success();
    }

    @Override
    public JsonResult findContent(Long id) {
        MessageContent content = messageContentMapper.selectByPrimaryKey(id);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("content",content.getContent());
        return JsonResult.success(data);
    }

    @Override
    public JsonResult updateStatus(Long id) {
        if (messageMapper.updateStatus(id)){
            return JsonResult.success();
        }
        return JsonResult.error("消息不存在");
    }

    public Long addContent(MessageContent content) {
        messageContentMapper.insert(content);
        return content.getId();
    }
}

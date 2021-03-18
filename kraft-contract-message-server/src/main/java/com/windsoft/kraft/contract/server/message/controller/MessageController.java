package com.windsoft.kraft.contract.server.message.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.MessageContent;
import com.windsoft.kraft.contract.server.message.dto.MessageDto;
import com.windsoft.kraft.contract.server.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("user")
    public JsonResult sendPerson(@RequestBody MessageDto messageDto){
        MessageContent content = new MessageContent();
        content.setTitle(messageDto.getTitle());
        content.setContent(messageDto.getContent());
        content.setType((byte) messageDto.getType());
        return messageService.addMessage(messageDto.getSendId()
                ,messageDto.getAcceptId()
                ,content);
    }
}

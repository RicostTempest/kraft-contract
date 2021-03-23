package com.windsoft.kraft.contract.server.message.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.MessageContent;
import com.windsoft.kraft.contract.server.message.dto.MessageDto;
import com.windsoft.kraft.contract.server.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("send")
    public JsonResult sendPerson(@RequestBody MessageDto messageDto){
        MessageContent content = new MessageContent();
        content.setTitle(messageDto.getTitle());
        content.setContent(messageDto.getContent());
        content.setType((byte) messageDto.getType());
        return messageService.addMessage(messageDto.getSendId()
                ,messageDto.getAcceptId()
                ,content);
    }

    @GetMapping("all/{userId}")
    public JsonResult allMessage(@PathVariable("userId") Long id){
        return messageService.findMessage(id);
    }

    @GetMapping("content/{contentId}")
    public JsonResult findContent(@PathVariable("contentId") Long id){
        return messageService.findContent(id);
    }

    @PutMapping("read/{messageId}")
    public JsonResult readMessage(@PathVariable("messageId") Long id){
        return messageService.updateStatus(id);
    }
}

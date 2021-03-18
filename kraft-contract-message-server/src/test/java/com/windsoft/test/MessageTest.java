package com.windsoft.test;

import com.windsoft.kraft.contract.mybatis.domain.Message;
import com.windsoft.kraft.contract.mybatis.domain.MessageContent;
import com.windsoft.kraft.contract.server.message.MessageServerApplication;
import com.windsoft.kraft.contract.server.message.mapper.MessageContentMapper;
import com.windsoft.kraft.contract.server.message.mapper.MessageMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageServerApplication.class)
public class MessageTest {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    MessageContentMapper messageContentMapper;

    Logger logger = Logger.getLogger("com.xiya.test.LogDemo");

    @Test
    public void messageTest(){
        Message message = new Message();
        message.setSendId(1L);
        message.setAcceptId(2L);

        MessageContent content = new MessageContent();
        content.setContent("2345");
        content.setTitle("test");
        content.setType((byte) 1);

        messageContentMapper.insert(content);
        Long id =content.getId();
        message.setContentId(id);
        messageMapper.insert(message);
        logger.info("testend");
    }

}

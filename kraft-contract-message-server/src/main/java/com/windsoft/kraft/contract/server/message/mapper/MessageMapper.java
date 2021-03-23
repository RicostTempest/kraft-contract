package com.windsoft.kraft.contract.server.message.mapper;

import com.windsoft.kraft.contract.mybatis.domain.Message;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;
import com.windsoft.kraft.contract.server.message.dto.MessageInfoDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper extends BaseMapper<Message> {
    public List<MessageInfoDto> selectMessageById(@Param("id") Long id);
    public boolean updateStatus(@Param("id") Long id);
}
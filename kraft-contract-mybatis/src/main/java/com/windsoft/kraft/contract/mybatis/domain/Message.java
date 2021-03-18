package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_message")
public class Message extends BaseEntity {
    /**
     * 发送信息的用户id
     */
    @Column(name = "send_id")
    private Long sendId;

    /**
     * 接受者的id
     */
    @Column(name = "accept_id")
    private Long acceptId;

    /**
     * 是否已读
1：未读，0：已读
     */
    @Column(name = "`status`", insertable = false)
    private Byte status;

    /**
     * 内容id
     */
    @Column(name = "content_id")
    private Long contentId;

    private MessageContent content;
}
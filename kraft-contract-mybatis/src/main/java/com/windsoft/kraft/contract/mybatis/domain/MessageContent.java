package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_message_content")
public class MessageContent extends BaseEntity {
    @Column(name = "content")
    private String content;

    /**
     * 类型
1：私信，2：公告，3：通知
     */
    @Column(name = "`type`")
    private Byte type;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;
}
package com.windsoft.kraft.contract.mybatis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_invoice")
public class Invoice extends BaseEntity {
    @Column(name = "`name`")
    private String name;

    @Column(name = "invoice_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date invoiceDate;

    @Column(name = "resource_id")
    private Long resourceId;

    /**
     * 发票代码
     */
    @Column(name = "invoice_code")
    private String invoiceCode;

    /**
     * 发票号码
     */
    @Column(name = "invoice_number")
    private String invoiceNumber;

    /**
     * 提供发票的用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 是否已经报销 1：已报销 0：未报销
     */
    @Column(name = "is_reimburse",insertable = false)
    private Byte isReimburse;

    /**
     * 金额
     */
    @Column(name = "`value`")
    private BigDecimal value;
}
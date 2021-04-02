package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_reimburse_sheet")
public class ReimburseSheet extends BaseEntity {
    /**
     * 项目名称
     */
    @Column(name = "project_id")
    private Long projectId;

    /**
     * 总金额
     */
    @Column(name = "amount")
    private BigDecimal amount;
}
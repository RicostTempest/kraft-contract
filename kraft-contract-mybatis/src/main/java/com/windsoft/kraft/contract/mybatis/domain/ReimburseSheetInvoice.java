package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "kc_reimburse_sheet_invoice")
public class ReimburseSheetInvoice extends BaseEntity {
    /**
     * 报销单id
     */
    @Column(name = "reimburse_sheet_id")
    private Long reimburseSheetId;

    /**
     * 发票id 可为空
     */
    @Column(name = "invoice_id")
    private Long invoiceId;
}
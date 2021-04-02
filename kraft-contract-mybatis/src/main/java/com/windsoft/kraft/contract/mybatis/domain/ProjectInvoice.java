package com.windsoft.kraft.contract.mybatis.domain;

import com.windsoft.kraft.contract.mybatis.entity.BaseEntity;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "kc_project_invoice")
public class ProjectInvoice extends BaseEntity {
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "project_id")
    private Long projectId;
}
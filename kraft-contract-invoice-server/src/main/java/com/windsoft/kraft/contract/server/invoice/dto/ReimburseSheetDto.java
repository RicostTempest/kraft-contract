package com.windsoft.kraft.contract.server.invoice.dto;

import com.windsoft.kraft.contract.mybatis.domain.Invoice;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ReimburseSheetDto implements Serializable {
    private Long projectId;
    private BigDecimal amount;
    private Invoice[] invoices;
}

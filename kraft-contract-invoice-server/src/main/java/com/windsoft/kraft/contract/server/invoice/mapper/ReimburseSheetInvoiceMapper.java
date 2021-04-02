package com.windsoft.kraft.contract.server.invoice.mapper;

import com.windsoft.kraft.contract.mybatis.domain.Invoice;
import com.windsoft.kraft.contract.mybatis.domain.ReimburseSheetInvoice;
import com.windsoft.kraft.contract.mybatis.mapper.BaseMapper;

import java.util.List;

public interface ReimburseSheetInvoiceMapper extends BaseMapper<ReimburseSheetInvoice> {
    List<Invoice> selectInvoiceBySheetId(Long id);
}
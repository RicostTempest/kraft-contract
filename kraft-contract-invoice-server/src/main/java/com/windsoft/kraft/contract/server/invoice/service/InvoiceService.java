package com.windsoft.kraft.contract.server.invoice.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.ReimburseSheet;
import com.windsoft.kraft.contract.server.invoice.dto.ReimburseSheetDto;

public interface InvoiceService {
    public JsonResult initSheet(ReimburseSheetDto dto);
    public JsonResult getSheetInfo(Long id);
    public JsonResult updateFunding(ReimburseSheet sheet);
    public JsonResult removeInvoiceSheet(Long sheetId,Long invoiceId);
    public JsonResult addInvoice(Long sheetId, ReimburseSheetDto dto);
}

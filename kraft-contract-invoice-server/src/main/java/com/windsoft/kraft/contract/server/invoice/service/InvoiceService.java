package com.windsoft.kraft.contract.server.invoice.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.invoice.dto.ReimburseSheetDto;

public interface InvoiceService {
    public JsonResult initSheet(ReimburseSheetDto dto);
    public JsonResult getSheetInfo(Long id);
}

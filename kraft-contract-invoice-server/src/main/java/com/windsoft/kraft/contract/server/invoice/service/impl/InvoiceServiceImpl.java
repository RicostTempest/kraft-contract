package com.windsoft.kraft.contract.server.invoice.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Invoice;
import com.windsoft.kraft.contract.mybatis.domain.ProjectInvoice;
import com.windsoft.kraft.contract.mybatis.domain.ReimburseSheet;
import com.windsoft.kraft.contract.mybatis.domain.ReimburseSheetInvoice;
import com.windsoft.kraft.contract.server.invoice.dto.ReimburseSheetDto;
import com.windsoft.kraft.contract.server.invoice.mapper.InvoiceMapper;
import com.windsoft.kraft.contract.server.invoice.mapper.ProjectInvoiceMapper;
import com.windsoft.kraft.contract.server.invoice.mapper.ReimburseSheetInvoiceMapper;
import com.windsoft.kraft.contract.server.invoice.mapper.ReimburseSheetMapper;
import com.windsoft.kraft.contract.server.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceMapper invoiceMapper;
    @Autowired
    private ReimburseSheetMapper reimburseSheetMapper;
    @Autowired
    private ReimburseSheetInvoiceMapper reimburseSheetInvoiceMapper;
    @Autowired
    private ProjectInvoiceMapper projectInvoiceMapper;

    @Override
    public JsonResult initSheet(ReimburseSheetDto dto) {
        ReimburseSheet reimburseSheet = new ReimburseSheet();
        reimburseSheet.setAmount(dto.getAmount());
        reimburseSheet.setProjectId(dto.getProjectId());
        reimburseSheetMapper.insert(reimburseSheet);
        ReimburseSheetInvoice reimburseSheetInvoice = new ReimburseSheetInvoice();
        reimburseSheetInvoice.setReimburseSheetId(reimburseSheet.getId());
        ProjectInvoice projectInvoice = new ProjectInvoice();
        projectInvoice.setProjectId(dto.getProjectId());
        for (int i = 0; i < dto.getInvoices().length; i++) {
            invoiceMapper.insert(dto.getInvoices()[i]);
            reimburseSheetInvoice.setId(null);
            reimburseSheetInvoice.setInvoiceId(dto.getInvoices()[i].getId());
            reimburseSheetInvoiceMapper.insert(reimburseSheetInvoice);
            projectInvoice.setId(null);
            projectInvoice.setInvoiceId(dto.getInvoices()[i].getId());
            projectInvoiceMapper.insert(projectInvoice);
        }
        return JsonResult.success(reimburseSheet.getId());
    }

    @Override
    public JsonResult getSheetInfo(Long id) {
        ReimburseSheet sheet = reimburseSheetMapper.selectByPrimaryKey(id);
        List<Invoice> invoiceList = reimburseSheetInvoiceMapper.selectInvoiceBySheetId(id);
        ReimburseSheetDto reimburseSheetDto = new ReimburseSheetDto();
        reimburseSheetDto.setAmount(sheet.getAmount());
        reimburseSheetDto.setProjectId(sheet.getProjectId());
        reimburseSheetDto.setInvoices(invoiceList.toArray(new Invoice[0]));
        return JsonResult.success(reimburseSheetDto);
    }
}

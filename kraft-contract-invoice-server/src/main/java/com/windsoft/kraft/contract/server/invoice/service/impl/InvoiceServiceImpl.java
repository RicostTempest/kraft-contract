package com.windsoft.kraft.contract.server.invoice.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.*;
import com.windsoft.kraft.contract.server.invoice.dto.ReimburseSheetDto;
import com.windsoft.kraft.contract.server.invoice.mapper.InvoiceMapper;
import com.windsoft.kraft.contract.server.invoice.mapper.ProjectInvoiceMapper;
import com.windsoft.kraft.contract.server.invoice.mapper.ReimburseSheetInvoiceMapper;
import com.windsoft.kraft.contract.server.invoice.mapper.ReimburseSheetMapper;
import com.windsoft.kraft.contract.server.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

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

    @Override
    public JsonResult updateFunding(ReimburseSheet sheet) {
        reimburseSheetMapper.updateFunding(sheet);
        return JsonResult.success();
    }

    @Override
    public JsonResult removeInvoiceSheet(Long sheetId, Long invoiceId) {
        Condition condition = new Condition(ReimburseSheetInvoice.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("reimburseSheetId", sheetId)
                .andEqualTo("invoiceId", invoiceId);
        reimburseSheetInvoiceMapper.deleteByCondition(condition);
        return JsonResult.success();
    }

    @Override
    public JsonResult addInvoice(Long sheetId, ReimburseSheetDto dto) {
        ReimburseSheetInvoice reimburseSheetInvoice = new ReimburseSheetInvoice();
        reimburseSheetInvoice.setReimburseSheetId(sheetId);
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
        ReimburseSheet reimburseSheet = reimburseSheetMapper.selectByPrimaryKey(sheetId);
        reimburseSheet.setAmount(dto.getAmount());
        reimburseSheetMapper.updateByPrimaryKey(reimburseSheet);
        return JsonResult.success();
    }
}

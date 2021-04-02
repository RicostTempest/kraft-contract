package com.windsoft.test;

import com.windsoft.kraft.contract.common.utils.CommonUtils;
import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.domain.Invoice;
import com.windsoft.kraft.contract.server.invoice.InvoiceServiceApplication;
import com.windsoft.kraft.contract.server.invoice.feign.ProjectUserServer;
import com.windsoft.kraft.contract.server.invoice.mapper.ReimburseSheetInvoiceMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvoiceServiceApplication.class)
public class InvoiceTest {
    @Autowired
    private ProjectUserServer projectUserServer;
    @Autowired
    private ReimburseSheetInvoiceMapper reimburseSheetInvoiceMapper;

    @Test
    public void testProjectUser(){
        JsonResult result = projectUserServer.findUserInProject(1L);
        List<Long> ids = new ArrayList<>();
        List<Map> users = (List<Map>) result.getData();
        users.forEach(item->{
            if ((int)item.get("permission") == 2){
                ids.add(Long.valueOf(item.get("id").toString()));
            }
        });
        String string = CommonUtils.longArrayToString( ids.toArray(new Long[0]), ",");
        System.out.println(string);
    }

    @Test
    public void testSelectInvoiceBySheetId(){
        List<Invoice> invoiceList = reimburseSheetInvoiceMapper.selectInvoiceBySheetId(5L);
        System.out.println(invoiceList.toArray(new Invoice[0]).length);
    }
}

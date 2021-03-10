package com.windsoft.kraft.contract.server.menu.service;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.service.BaseService;
import com.windsoft.kraft.contract.mybatis.domain.Menu;

import java.util.List;

public interface MenuService extends BaseService<Menu> {
    public JsonResult<List<Menu>> getMenuList();
}

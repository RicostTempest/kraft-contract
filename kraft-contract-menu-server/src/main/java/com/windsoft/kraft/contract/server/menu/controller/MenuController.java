package com.windsoft.kraft.contract.server.menu.controller;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.server.menu.domain.Menu;
import com.windsoft.kraft.contract.server.menu.query.MenuQuery;
import com.windsoft.kraft.contract.server.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("index")
    public JsonResult index(){
        return menuService.getMenuList();
    }

    @GetMapping("find")
    public JsonResult find(MenuQuery menuQuery){
        return menuService.getList(menuQuery);
    }

    @PostMapping("add")
    public JsonResult add(@RequestBody Menu entity){
        return menuService.add(entity);
    }

    @PutMapping("edit")
    public JsonResult edit(@RequestBody Menu entity){
        return menuService.update(entity);
    }

    @DeleteMapping("delete/{menuId}")
    public JsonResult delete(@PathVariable("menuId") Long menuId){
        return menuService.deleteById(menuId);
    }

}

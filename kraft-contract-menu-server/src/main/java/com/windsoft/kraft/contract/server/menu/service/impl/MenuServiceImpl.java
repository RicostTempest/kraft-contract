package com.windsoft.kraft.contract.server.menu.service.impl;

import com.windsoft.kraft.contract.common.utils.JsonResult;
import com.windsoft.kraft.contract.mybatis.query.BaseQuery;
import com.windsoft.kraft.contract.mybatis.service.impl.BaseServiceImpl;
import com.windsoft.kraft.contract.server.menu.domain.Menu;
import com.windsoft.kraft.contract.server.menu.service.MenuService;
import com.windsoft.kraft.contract.server.menu.mapper.MenuMapper;
import com.windsoft.kraft.contract.server.menu.query.MenuQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public JsonResult<List<Menu>> getList(BaseQuery query) {
        MenuQuery menuQuery = (MenuQuery) query;
        Condition condition = new Condition(Menu.class);
        Example.Criteria criteria = condition.createCriteria();
        // 菜单名称
        if (!StringUtils.isEmpty(menuQuery.getTitle())) {
            criteria.andLike("title","%"+menuQuery.getTitle()+"%");
        }
        condition.orderBy("sort").asc();
        List<Menu> menuList = menuMapper.selectByCondition(condition);
        return JsonResult.success(menuList);
    }

    /**
     * 获取根目录
     * @return
     */
    @Override
    public JsonResult getMenuList() {
        List<Menu> menuList = null;
        menuList = getChildrenMenuAll(0L);
        return JsonResult.success(menuList);
    }

    /**
     * 根据父级ID获取子级菜单
     *
     * @param pid 上级ID
     * @return
     */
    public List<Menu> getChildrenMenuAll(Long pid) {
        Condition condition = new Condition(Menu.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("pid", pid);
        criteria.andEqualTo("status", 1);
        // 只取菜单一级
        criteria.andEqualTo("type", 0);
        condition.orderBy("sort").asc();
        List<Menu> menuList = menuMapper.selectByCondition(condition);
        if (!menuList.isEmpty()) {
            menuList.forEach(item -> {
                List<Menu> childrenList = getChildrenMenuAll(item.getId());
                item.setChildren(childrenList);
            });
        }
        return menuList;
    }
}

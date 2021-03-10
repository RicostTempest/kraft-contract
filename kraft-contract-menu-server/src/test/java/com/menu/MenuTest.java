package com.menu;

import com.windsoft.kraft.contract.server.menu.MenuServerApplication;
import com.windsoft.kraft.contract.server.menu.domain.Menu;
import com.windsoft.kraft.contract.server.menu.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MenuServerApplication.class)
public class MenuTest {
    @Autowired
    private MenuService menuService;

    @Test
    public void getMenu(){
        List<Menu> menuList = menuService.getMenuList().getData();
        System.out.println(menuList);
    }
}

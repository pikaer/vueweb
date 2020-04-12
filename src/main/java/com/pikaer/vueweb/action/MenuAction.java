package com.pikaer.vueweb.action;

import com.pikaer.vueweb.entity.system.MenuInfo;
import com.pikaer.vueweb.entity.utils.BaseResponse;
import com.pikaer.vueweb.service.impl.MenuBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuAction {

    @Autowired
    private MenuBiz menuBiz;

    @RequestMapping(value = "/getMenuList")
    public BaseResponse<List<MenuInfo>> getMenuList(){
        return menuBiz.getMenuList();
    }
}

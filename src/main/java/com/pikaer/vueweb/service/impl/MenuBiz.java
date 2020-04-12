package com.pikaer.vueweb.service.impl;


import com.pikaer.vueweb.dal.frameworkdb.dao.IMenuInfoDao;
import com.pikaer.vueweb.entity.system.MenuInfo;
import com.pikaer.vueweb.entity.utils.BaseResponse;
import com.pikaer.vueweb.entity.utils.ResponseCodeEnum;
import com.pikaer.vueweb.service.IMenuBiz;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuBiz implements IMenuBiz {

    @Autowired
    private IMenuInfoDao menuInfoDao;

    @Override
    public BaseResponse<List<MenuInfo>> getMenuList() {
        BaseResponse<List<MenuInfo>> response=new BaseResponse<>();
        List<MenuInfo> menuList = new ArrayList<>();
        menuList.add(getSysMenu());
        menuList.add(getLogMenu());
        response.setCodeAndMsg(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMsg());
        response.setData(menuList);
        return response;
    }

    private MenuInfo getSysMenu(){
        MenuInfo systemMenu = new MenuInfo(100, "系统管理", "", "fa fa-wrench");
        MenuInfo usersSubMenu = new MenuInfo(101, "用户管理", "users", "fa fa-user");
        MenuInfo rolesSubMenu = new MenuInfo(102, "角色管理", "roles", "fa fa fa-id-card");
        MenuInfo rightsSubMenu = new MenuInfo(103, "权限管理", "rights", "fa fa-cog");
        systemMenu.setSubMenuList(Lists.newArrayList(usersSubMenu, rolesSubMenu, rightsSubMenu));
        return systemMenu;
    }

    private MenuInfo getLogMenu(){
        MenuInfo logMenu = new MenuInfo(200, "日志管理", "", "fa fa-cogs");
        MenuInfo systemLog = new MenuInfo(201, "系统日志", "systemlog", "fa fa-bug");
        MenuInfo serviceLog = new MenuInfo(202, "接口调用", "servicelog", "fa fa fa-line-chart");
        logMenu.setSubMenuList(Lists.newArrayList(systemLog, serviceLog));
        return logMenu;
    }

}

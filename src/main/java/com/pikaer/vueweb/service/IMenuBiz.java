package com.pikaer.vueweb.service;

import com.pikaer.vueweb.entity.system.MenuInfo;
import com.pikaer.vueweb.entity.utils.BaseResponse;

import java.util.List;

public interface IMenuBiz {
    BaseResponse<List<MenuInfo>>getMenuList();
}

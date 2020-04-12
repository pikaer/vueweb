package com.pikaer.vueweb.service;

import com.pikaer.vueweb.dal.frameworkdb.entity.MenuInfoEntity;
import com.pikaer.vueweb.entity.system.MenuInfo;
import com.pikaer.vueweb.entity.system.UpdateIndexRequest;
import com.pikaer.vueweb.entity.utils.BaseResponse;

import java.util.List;

public interface IMenuBiz {

    BaseResponse<List<MenuInfo>>getMenuList();

    BaseResponse insert(MenuInfoEntity entity);

    BaseResponse delete(int id);

    BaseResponse updateIndex(UpdateIndexRequest request);
}

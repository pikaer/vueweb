package com.pikaer.vueweb.dal.frameworkdb.dao;

import com.pikaer.vueweb.dal.frameworkdb.entity.MenuInfoEntity;

import java.util.List;

public interface IMenuInfoDao {
    List<MenuInfoEntity> getMenuList();
}
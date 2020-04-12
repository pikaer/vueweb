package com.pikaer.vueweb.dal.frameworkdb.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pikaer.vueweb.dal.base.SqlSessionBase;
import com.pikaer.vueweb.dal.config.DalConst;
import com.pikaer.vueweb.dal.config.MapperConst;
import com.pikaer.vueweb.dal.frameworkdb.dao.IMenuInfoDao;
import com.pikaer.vueweb.dal.frameworkdb.entity.MenuInfoEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuInfoDao extends SqlSessionBase<MenuInfoDao> implements IMenuInfoDao {

    @Override
    protected TypeReference<MenuInfoDao> getTypeReference() {
        return new TypeReference<>() {};
    }

    @Override
    protected String getDbName() {
        return DalConst.FRAMEWORKDB;
    }

    @Override
    public String getMapperNameSpace() {
        return MapperConst.MenuInfoMapper;
    }

    @Override
    public List<MenuInfoEntity> getMenuList() {
        SqlSession sqlSession = openSqlSession(true);
        List<MenuInfoEntity> menuList=sqlSession.selectList(getMapperName("selectMenuList"));
        closeSession(sqlSession);
        return menuList;
    }
}

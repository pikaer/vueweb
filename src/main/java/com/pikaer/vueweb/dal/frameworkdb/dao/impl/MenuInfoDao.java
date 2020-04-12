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
        try(SqlSession sqlSession = openSqlSession(true)){
            List<MenuInfoEntity> menuList=sqlSession.selectList(getMapperName("selectMenuList"));
            closeSession(sqlSession);
            return menuList;
        }catch (Exception ex){
            //todo log
            return null;
        }
    }

    @Override
    public MenuInfoEntity getMenuInfoById(int id) {
        try(SqlSession sqlSession = openSqlSession(true)){
            MenuInfoEntity menu=sqlSession.selectOne(getMapperName("getMenuInfoById"),id);
            closeSession(sqlSession);
            return menu;
        }catch (Exception ex){
            //todo log
            return null;
        }
    }

    @Override
    public boolean insert(MenuInfoEntity entity) {
        try(SqlSession sqlSession = openSqlSession(true)){
            int count=sqlSession.insert(getMapperName("insertMenu"),entity);
            closeSession(sqlSession);
            return count>0;
        }catch (Exception ex){
            //todo log
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try(SqlSession sqlSession = openSqlSession(true)){
            int count=sqlSession.delete(getMapperName("deleteMenu"),id);
            closeSession(sqlSession);
            return count>0;
        }catch (Exception ex){
            //todo log
            return false;
        }
    }

    @Override
    public boolean updateIndex(int id,int index) {
        try(SqlSession sqlSession = openSqlSession(true)){
            MenuInfoEntity entity=new MenuInfoEntity();
            entity.setId(id);
            entity.setIndex(index);
            int count=sqlSession.update(getMapperName("updateIndex"),entity);
            closeSession(sqlSession);
            return count>0;
        }catch (Exception ex){
            //todo log
            return false;
        }
    }
}

package com.pikaer.vueweb.dal.frameworkdb.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.pikaer.vueweb.dal.base.SqlSessionBase;
import com.pikaer.vueweb.dal.config.DalConst;
import com.pikaer.vueweb.dal.config.MapperConst;
import com.pikaer.vueweb.dal.frameworkdb.dao.IUserInfoDao;
import com.pikaer.vueweb.dal.frameworkdb.entity.UserInfoEntity;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDao extends SqlSessionBase<UserInfoDao> implements IUserInfoDao {
    @Override
    protected TypeReference<UserInfoDao> getTypeReference() {
        return new TypeReference<>() {};
    }

    @Override
    protected String getDbName() {
        return DalConst.FRAMEWORKDB;
    }

    @Override
    public String getMapperNameSpace() {
        return MapperConst.UserInfoMapper;
    }

    @Override
    public UserInfoEntity selectUserById(){
        try(SqlSession sqlSession = openSqlSession(true)){
            UserInfoEntity userInfo=sqlSession.selectOne(getMapperName("getUserInfoByUid"));
            closeSession(sqlSession);
            return userInfo;
        }catch (Exception ex){
            return null;
        }
    }


}

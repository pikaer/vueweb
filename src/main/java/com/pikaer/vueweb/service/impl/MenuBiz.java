package com.pikaer.vueweb.service.impl;


import com.pikaer.vueweb.dal.frameworkdb.dao.IMenuInfoDao;
import com.pikaer.vueweb.dal.frameworkdb.entity.MenuInfoEntity;
import com.pikaer.vueweb.entity.system.MenuInfo;
import com.pikaer.vueweb.entity.system.UpdateIndexRequest;
import com.pikaer.vueweb.entity.utils.BaseResponse;
import com.pikaer.vueweb.entity.utils.ResponseCodeEnum;
import com.pikaer.vueweb.service.IMenuBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuBiz implements IMenuBiz {

    @Autowired
    private IMenuInfoDao menuInfoDao;

    @Override
    public BaseResponse<List<MenuInfo>> getMenuList() {
        BaseResponse<List<MenuInfo>> response=new BaseResponse<>();
        response.setCodeAndMsg(ResponseCodeEnum.SUCCESS);
        try{
            List<MenuInfoEntity> entityList=menuInfoDao.getMenuList();
            if(CollectionUtils.isEmpty(entityList)){
                return response;
            }
            List<MenuInfoEntity> parentList=entityList.stream().filter(a->a.getParentId()==0).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(parentList)){
                return response;
            }
            parentList.sort(Comparator.comparing(MenuInfoEntity::getIndex));
            response.setData(new ArrayList<>());
            for(MenuInfoEntity menu:parentList){
                MenuInfo result=new MenuInfo(menu.getId(),menu.getIndex(),menu.getName(),menu.getPath(),menu.getIcon());
                result.setCanUp(canUp(menu,entityList));
                result.setCanDown(canDown(menu,entityList));
                List<MenuInfoEntity> childList=entityList.stream().filter(a->a.getParentId()==menu.getId()).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(childList)){
                    childList.sort(Comparator.comparing(MenuInfoEntity::getIndex));
                    result.setSubMenuList(new ArrayList<>());
                    for(MenuInfoEntity childMenu:childList){
                        MenuInfo child=new MenuInfo(childMenu.getId(),childMenu.getIndex(),childMenu.getName(),childMenu.getPath(),childMenu.getIcon());
                        child.setCanUp(canUp(childMenu,entityList));
                        child.setCanDown(canDown(childMenu,entityList));
                        result.getSubMenuList().add(child);
                    }
                }
                response.getData().add(result);
            }
        }catch (Exception ex){
            response.setCodeAndMsg(ResponseCodeEnum.OTHER_ERROR);
            // todo log
        }
        return response;
    }

    private boolean canDown(MenuInfoEntity entity,List<MenuInfoEntity>entityList){
        MenuInfoEntity change=entityList.stream().filter(a->a.getParentId()==entity.getParentId()&&a.getIndex()>entity.getIndex()).findFirst().orElse(null);
        if(change!=null){
            return true;
        }
        return false;
    }

    private boolean canUp(MenuInfoEntity entity,List<MenuInfoEntity>entityList){
        MenuInfoEntity change=entityList.stream().filter(a->a.getParentId()==entity.getParentId()&&a.getIndex()<entity.getIndex()).findFirst().orElse(null);
        if(change!=null){
            return true;
        }
        return false;
    }

    @Override
    public BaseResponse insert(MenuInfoEntity entity) {
        BaseResponse response=new BaseResponse<>();
        response.setCodeAndMsg(ResponseCodeEnum.SUCCESS);
        try{
            boolean success=menuInfoDao.insert(entity);
            if(!success){
                response.setCodeAndMsg(ResponseCodeEnum.INSERT_FAIL.getCode(),"添加失败");
            }
        }catch (Exception ex){
            response.setCodeAndMsg(ResponseCodeEnum.OTHER_ERROR);
            // todo log
        }
        return response;
    }

    @Override
    public BaseResponse delete(int id) {
        BaseResponse response=new BaseResponse<>();
        response.setCodeAndMsg(ResponseCodeEnum.SUCCESS);
        try{
            boolean success=menuInfoDao.delete(id);
            if(!success){
                response.setCodeAndMsg(ResponseCodeEnum.DELETE_FAIL.getCode(),"添加失败");
            }
        }catch (Exception ex){
            response.setCodeAndMsg(ResponseCodeEnum.OTHER_ERROR);
            // todo log
        }
        return response;
    }

    @Override
    public BaseResponse updateIndex(UpdateIndexRequest request) {
        BaseResponse response=new BaseResponse<>();
        response.setCodeAndMsg(ResponseCodeEnum.SUCCESS);
        try{
            List<MenuInfoEntity> entityList=menuInfoDao.getMenuList();
            if(CollectionUtils.isEmpty(entityList)){
                response.setCode(ResponseCodeEnum.UPDATE_FAIL);
                response.setMsg("修改失败");
                return response;
            }
            MenuInfoEntity entity=entityList.stream().filter(a->a.getId()==request.getId()).findFirst().orElse(null);
            if(entity==null){
                response.setCode(ResponseCodeEnum.UPDATE_FAIL);
                response.setMsg("修改失败,数据不存在");
                return response;
            }
            List<MenuInfoEntity> changeList;
            if(request.getType()==1){
                //上移
                changeList=entityList.stream().filter(a->a.getIndex() < entity.getIndex()&&a.getParentId()==entity.getParentId()).collect(Collectors.toList());
                changeList.sort(Comparator.comparing(MenuInfoEntity::getIndex).reversed());
            }else {
                //下移
                changeList=entityList.stream().filter(a->a.getIndex() > entity.getIndex()&&a.getParentId()==entity.getParentId()).collect(Collectors.toList());
                changeList.sort(Comparator.comparing(MenuInfoEntity::getIndex));
            }
            if(CollectionUtils.isEmpty(changeList)){
                response.setCode(ResponseCodeEnum.UPDATE_FAIL);
                response.setMsg("修改失败");
                return response;
            }
            menuInfoDao.updateIndex(entity.getId(),changeList.get(0).getIndex());
            menuInfoDao.updateIndex(changeList.get(0).getId(),entity.getIndex());
        }catch (Exception ex){
            response.setCodeAndMsg(ResponseCodeEnum.OTHER_ERROR);
            // todo log
        }
        return response;
    }
}

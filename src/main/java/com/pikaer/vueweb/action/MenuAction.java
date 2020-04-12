package com.pikaer.vueweb.action;

import com.pikaer.vueweb.dal.frameworkdb.entity.MenuInfoEntity;
import com.pikaer.vueweb.entity.system.MenuInfo;
import com.pikaer.vueweb.entity.system.UpdateIndexRequest;
import com.pikaer.vueweb.entity.utils.BaseResponse;
import com.pikaer.vueweb.service.IMenuBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class MenuAction {

    @Autowired
    private IMenuBiz menuBiz;

    @RequestMapping(value = "/getMenuList")
    public BaseResponse<List<MenuInfo>> getMenuList(){
        return menuBiz.getMenuList();
    }

    @RequestMapping(value = "/insert")
    public BaseResponse insert(@RequestBody MenuInfoEntity entity){
        return menuBiz.insert(entity);
    }

    @RequestMapping(value = "/updateIndex")
    public BaseResponse updateIndex(@RequestBody UpdateIndexRequest request){
        return menuBiz.updateIndex(request);
    }

    @RequestMapping(value = "/delete")
    public BaseResponse delete(@RequestBody MenuInfoEntity entity){
        return menuBiz.delete(entity.getId());
    }
}

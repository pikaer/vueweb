package com.pikaer.vueweb.entity.system;


import java.util.List;

/**
 * 菜单信息
 */
public class MenuInfo {
    /**
     * 唯一标识
     */
    private int id;

    /**
     * 用以排序
     */
    private int index;

    /**
     * 描述名
     */
    private String name;

    /**
     * 路径
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    private List<MenuInfo> subMenuList;

    public MenuInfo(int id, String name, String path, String icon) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public MenuInfo(int id,int index, String name, String path, String icon) {
        this.id = id;
        this.index=index;
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<MenuInfo> getSubMenuList() {
        return subMenuList;
    }

    public void setSubMenuList(List<MenuInfo> subMenuList) {
        this.subMenuList = subMenuList;
    }
}
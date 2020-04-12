<template>
    <el-container class="home-container">
        <!-- 侧边栏 -->
        <el-aside :width="isCollapse?'64px':'200px'">
            <!-- 侧边栏菜单区域 -->
            <!-- background-color背景色 -->
            <!-- text-color文本颜色 -->
            <!-- active-text-color激活的颜色 -->
            <!-- unique-opened表示只保持一个子菜单的展开，也可以写成:unique-opened="true" -->
            <!-- collapse是否水平折叠收起菜单,默认为false -->
            <!-- collapse-transition关闭折叠动画 -->
            <!-- router使用 vue-router 的模式，启用该模式会在激活导航时以 index 作为 path 进行路由跳转 -->
            <!-- default-active 当前激活菜单的 index-->
            <el-menu
                    background-color="#333744"
                    text-color="#fff"
                    active-text-color="#409EFF"
                    unique-opened
                    :collapse="isCollapse"
                    :collapse-transition="false"
                    router
                    :default-active="activePath"
            >
                <!-- 一级菜单 -->
                <el-submenu :index="item.id + ''" v-for="item in menuList" :key="item.id">
                    <!-- 一级菜单的模版区域 -->
                    <template slot="title">
                        <el-row>
                            <el-col :span="4">
                                <!-- 图标 -->
                                <i :class="item.icon"></i>
                            </el-col>
                            <el-col :span="20">
                                <!-- 文本 -->
                                <span>{{item.name}}</span>
                            </el-col>
                        </el-row>
                    </template>
                    <!-- 二级菜单 -->
                    <!-- index属性代表路由路径 -->
                    <el-menu-item
                            :index="'/' + subItem.path"
                            v-for="subItem in item.subMenuList"
                            :key="subItem.id"
                            @click="saveNavState('/' + subItem.path)"
                    >
                        <!-- 二级菜单的模版区域 -->
                        <template slot="title">
                            <el-row>
                                <el-col :span="5">
                                    <!-- 图标 -->
                                    <i :class="subItem.icon"></i>
                                </el-col>
                                <el-col :span="19">
                                    <!-- 文本 -->
                                    <span>{{subItem.name}}</span>
                                </el-col>
                            </el-row>
                        </template>
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <!-- 头部 -->
            <el-header>
                <!-- 折叠和展开菜单 -->
                <el-button type="primary" size="small" class="fa fa-bars" @click="toggleCollapse"></el-button>
            </el-header>
            <!-- 分割线 -->
            <el-divider></el-divider>
            <!-- 主体 -->
            <el-main>
                <!-- 路由占位符 -->
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        data() {
            return {
                // 控制菜单折叠和展开
                isCollapse: false,
                // 被激活的链接
                activePath: '',
                // 菜单
                menuList: []
            }
        },
        methods: {
            // 控制菜单折叠和展开
            toggleCollapse() {
                this.isCollapse = !this.isCollapse
            },
            // 保存菜单栏激活链接
            saveNavState(activePath) {
                // 保存到sessionStorage
                window.sessionStorage.setItem('activePath', activePath)
                // 设置激活链接
                this.activePath = activePath
            },
            // 获取菜单列表
            getMenuList() {
                var that = this
                //  { data: response }表示把响应json中的data解析出来，并且重命名为response
                this.$http.get('menu/getMenuList').then(function({ data: response }) {
                    if (response.code === 0) {
                        // axios回调函数中的this已经改变，无法访问到data中的数据，解决：把this保存起来，回调函数中直接使用保存的this即可
                        that.menuList = response.data
                    } else {
                        that.$message.error(response.msg)
                    }
                })
            }
        },
        created() {
            // 获取菜单
            this.getMenuList()
            // 初始化激活链接
            this.activePath = window.sessionStorage.getItem('activePath')
        }
    }
</script>

<style lang="less" scoped>
    // 设置容器高度
    .home-container {
        height: 100%;
    }

    // 头部样式
    .el-header {
        background-color: #eaedf1;
        display: flex;
        align-items: center;
        padding-left: 10px;
    }

    // 侧边栏样式
    .el-aside {
        background-color: #333744;
        // 消除菜单栏右侧边框
        .el-menu {
            border-right: none;
        }
    }

    // 主体区域样式
    .el-main {
        background-color: #eaedf1;
    }

    // 设置分割线外边距
    .el-divider {
        margin: 0;
    }
</style>

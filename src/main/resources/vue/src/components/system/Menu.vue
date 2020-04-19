<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>菜单管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片 -->
    <el-card class="box-card">
      <el-table
        :data="menuList"
        style="width: 100%;margin-bottom: 20px;"
        row-key="id"
        border
        default-expand-all
        :tree-props="{children: 'subMenuList', hasChildren: 'hasChildren'}"
      >
        <el-table-column prop="name" label="菜单名" width="180">
          <template slot-scope="scope">
            <i :class="scope.row.icon">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{scope.row.name}}</i>
          </template>
        </el-table-column>
        <el-table-column prop="path" label="路径" width="180"></el-table-column>
        <el-table-column prop="icon" label="图标" width="180"></el-table-column>
        <el-table-column label="操作" width="275">
          <template slot="header">
            <el-button type="success" size="mini">插入一条</el-button>
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="toUp(scope.row.id)" :disabled="!scope.row.canUp">
              <i class="fa fa-arrow-up"></i>
            </el-button>
            <el-button size="mini" @click="todown(scope.row.id)" :disabled="!scope.row.canDown">
              <i class="fa fa-arrow-down"></i>
            </el-button>
            <el-button size="mini" @click="handleEdit(indexMethod(scope.$index), scope.row)">编辑</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(indexMethod(scope.$index), scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 菜单
      menuList: []
    };
  },
  methods: {
    // 获取菜单列表
    getMenuList() {
      var that = this;
      //  { data: response }表示把响应json中的data解析出来，并且重命名为response
      this.$http.get("menu/getMenuList").then(function({ data: response }) {
        if (response.code === 0) {
          // axios回调函数中的this已经改变，无法访问到data中的数据，解决：把this保存起来，回调函数中直接使用保存的this即可
          that.menuList = response.data;
        } else {
          that.$message.error(response.msg);
        }
      });
    },
    toUp(id) {
      this.updateIndex(id, 1);
    },
    todown(id) {
      this.updateIndex(id, 2);
    },
    updateIndex(id, type) {
      var that = this;
      this.$http
        .post("menu/updateIndex", { id: id, type: type })
        .then(function({ data: response }) {
          if (response.code === 0) {
            that.getMenuList();
          } else {
            that.$message.error(response.msg);
          }
        });
    }
  },
  created() {
    // 获取菜单
    this.getMenuList();
  }
};
</script>

<style lang="less" scoped>
</style>

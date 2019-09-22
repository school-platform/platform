<template>
  <el-container style="height: 700px; border: 1px solid #eee;width:66%;margin:0px auto;">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-s-fold"></i>账号管理
          </template>
          <el-menu-item index="1-1">
            <router-link to="/manager/student">学生账号</router-link>
          </el-menu-item>
          <el-menu-item index="1-2">
            <router-link to="/manager/organization">社团账号</router-link>
          </el-menu-item>
        </el-submenu>
        <el-menu-item index="2">
          <template slot="title">
            <i class="el-icon-basketball"></i>
            <router-link to="/manager/approval">活动管理</router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="3">
          <template slot="title">
            <i class="el-icon-message"></i>
            <router-link to="/manager/news">发布消息</router-link>
          </template>
        </el-menu-item>
        <el-menu-item index="4">
          <template slot="title">
            <i class="el-icon-lock"></i>
            <router-link to="/manager/passmod">修改密码</router-link>
          </template>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <router-view></router-view>
    </el-container>
  </el-container>
</template>


<script>
export default {
  data() {
    const item = {
      date: "2016-05-02",
      name: "王小虎",
      address: "上海市普陀区金沙江路 1518 弄"
    };
    return {
      tableData: Array(20).fill(item)
    };
  }
};
</script>

<style>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>

<style scoped>
a {
  color: #333;
}
</style>


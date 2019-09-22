<template>
  <el-container style="height: 667px; border: 1px solid #eee;width:66%;margin:0px auto;">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu default-active="1" class="el-menu-vertical-demo">
        <el-menu-item index="1">
          <i class="el-icon-user-solid"></i>
          <span slot="title">
            <router-link to="/organization/information">我的资料</router-link>
          </span>
        </el-menu-item>

        <el-menu-item index="2">
          <i class="el-icon-s-promotion"></i>
          <span slot="title">
            <router-link to="/organization/vip">会员管理</router-link>
          </span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-basketball"></i>
          <span slot="title">
            <router-link to="/organization/activity">活动管理</router-link>
          </span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-message"></i>
          <span slot="title">
            <router-link to="/organization/news">我的消息</router-link>
          </span>
        </el-menu-item>
        <el-menu-item index="5">
          <i class="el-icon-setting"></i>
          <span slot="title">
            <router-link to="/organization/passmod">修改密码</router-link>
          </span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <router-view></router-view>
    </el-container>
  </el-container>
</template>


<script>
export default {};
</script>

<style scoped>
a {
  color: black;
}
</style>

<style>
.el-main {
  padding: 0px 0px !important;
}
.el-header,
.el-footer {
  background-color: aliceblue;
}
.el-pagination{
  padding:0px 0px !important;
}
</style>



<template>
  <el-container style="height: 700px; border: 1px solid #eee;width:66%;margin:0px auto;">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu default-active="1" class="el-menu-vertical-demo">
        <el-menu-item index="1">
          <i class="el-icon-user-solid"></i>
          <span slot="title">
            <router-link to="/student/information">我的资料</router-link>
          </span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-basketball"></i>
          <span slot="title"><router-link to="/student/organization">我的社团</router-link></span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-message"></i>
          <span slot="title"><router-link to="/student/news">我的消息</router-link></span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-s-data"></i>
          <span slot="title"><router-link to="/student/statistics">学分统计</router-link></span>
        </el-menu-item>
        <el-menu-item index="5">
          <i class="el-icon-setting"></i>
          <span slot="title"><router-link to="/student/passmod">修改密码</router-link></span>
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
a{
  color:black;
}
</style>


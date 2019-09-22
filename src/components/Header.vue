<template>
  <div id="nav">
    <div class="container">
      <div class="nav-content">
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="green"
        >
          <el-menu-item index="6">
            <img src="../assets/logo1.png" style="width:150px;height:50px;" />
          </el-menu-item>
          <el-menu-item index="1">
            <router-link to="/">主页</router-link>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">用户中心</template>
            <el-menu-item index="2-1">
              <router-link to="/manager">管理中心</router-link>
            </el-menu-item>
            <el-menu-item index="2-2">
              <router-link to="/student">学生中心</router-link>
            </el-menu-item>
            <el-menu-item index="2-3">
              <router-link to="/organization">社团中心</router-link>
            </el-menu-item>
          </el-submenu>
          <el-menu-item index="3">
            <el-input
              placeholder="请输入活动或社团名称"
              prefix-icon="el-icon-search"
              v-model="input2"
              size="small"
            ></el-input>
          </el-menu-item>
          <el-menu-item v-if="islogin()==null" index="4" style="float:right">
            <router-link to="/login">登陆</router-link>
          </el-menu-item>
          <el-menu-item v-else index="4" style="float:right">
            <el-dropdown>
              <span class="el-dropdown-link">
                <el-button type="text" @click="jump()">{{islogin()}}</el-button>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item>退出登陆</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-menu-item>
        </el-menu>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  watch: { $route: "fresh" },
  data() {
    return {
      activeIndex: "1",
      input2: ""
    };
  },
  methods: {
    islogin() {
      return sessionStorage.getItem("username");
    },
    jump() {
      let type = sessionStorage.getItem("type");
      if (type == "student") {
        this.$router.push("/student");
      } else if (type == "organization") {
        this.$router.push("/organization");
      } else if (type == "manager") {
        this.$router.push("/manager");
      }
    },
    fresh(){
      console.log("shuaxin")
      this.$forceUpdate()
    }
  }
};
</script>

<style scoped>
#nav {
  width: 100%;
  margin: 0 auto;
  margin-bottom: 10px;
  background: #545c64;
}
.nav-content {
  width: 100%;
  height: 60px;
  margin: 0 auto;
}
</style>

<template>
  <el-container>
    <el-header>
      <el-page-header @back="goBack" content="详情页面" style="line-height:60px;"></el-page-header>
    </el-header>

    <el-main style="height:605px;">
      <el-tabs type="border-card" :stretch="true">
        <el-tab-pane>
          <span slot="label">
            <router-link to="/organization/detail/content">活动详情</router-link>
          </span>
          <router-view></router-view>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <router-link to="/organization/detail/apply">报名管理</router-link>
          </span>
          <router-view></router-view>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <router-link to="/organization/detail/sign">签到管理</router-link>
          </span>
          <router-view></router-view>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <router-link to="/organization/detail/grade">评分管理</router-link>
          </span>
          <router-view></router-view>
        </el-tab-pane>
        <el-tab-pane>
          <span slot="label">
            <router-link to="/organization/detail/comment">评论管理</router-link>
          </span>
          <router-view></router-view>
        </el-tab-pane>
      </el-tabs>
    </el-main>
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
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        }
      ]
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1); //返回上一层
    }
  }
};
</script>

<style scoped>
.el-main {
  padding: 0px;
  overflow: hidden !important;
}
a{
  color: #409eff;
}
</style>

<style>
.el-tabs__content {
     padding: 0px !important;
}
</style>


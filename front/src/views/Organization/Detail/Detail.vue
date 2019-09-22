<template>
  <el-container>
    <el-header>
      <el-page-header @back="goBack" content="详情页面" style="line-height:60px;"></el-page-header>
    </el-header>

    <el-tabs :stretch="true">
      <el-tab-pane>
        <span slot="label">
          <router-link :to="{path:'/organization/detail/content',query:{act_id:this.$route.query.act_id}}">活动详情</router-link>
        </span>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label">
          <router-link :to="{path:'/organization/detail/apply',query:{act_id:this.$route.query.act_id}}">报名管理</router-link>
        </span>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label">
          <router-link :to="{path:'/organization/detail/sign',query:{act_id:this.$route.query.act_id}}">签到管理</router-link>
        </span>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label">
          <router-link :to="{path:'/organization/detail/grade',query:{act_id:this.$route.query.act_id}}">评分管理</router-link>
        </span>
      </el-tab-pane>
      <el-tab-pane>
        <span slot="label">
          <router-link :to="{path:'/organization/detail/comment',query:{act_id:this.$route.query.act_id}}">评论管理</router-link>
        </span>
      </el-tab-pane>
    </el-tabs>
    <router-view></router-view>
  </el-container>
</template>

<script>
export default {
  methods: {
    goBack() {
      this.$router.go(-1); //返回上一层
    }
  }
};
</script>

<style scoped>
a {
  color: #409eff;
}
</style>

<style>
.el-tabs__content {
  padding: 0px !important;
}
</style>


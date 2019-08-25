<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">我的消息</span>
      <el-dropdown @command="handleCommand">
        <i class="el-icon-setting" style="margin-right: 5px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="delete">一键删除</el-dropdown-item>
          <el-dropdown-item command="ready">一键已读</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>伍方健</span>
    </el-header>

    <el-main>
      <el-collapse
        @change="handleChange"
        class="infinite-list"
        v-infinite-scroll="load"
        accordion
      >
        <el-collapse-item
          v-for="i in count"
          class="infinite-list-item"
          :key="i"
          title="系统消息"
        >消息{{ i }}</el-collapse-item>
      </el-collapse>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      count: 7
    };
  },
  methods: {
    load() {
      //this.count += 2;
    },
    handleChange(val) {
      console.log(val);
    },
    handleCommand(command) {}
  }
};
</script>

<style scoped>
.el-main {
  padding: 0px 0px;
  text-align: left !important;
}
</style>
<style>
.el-collapse-item__header,.el-collapse-item__wrap{
  padding-left: 15px;
}
</style>



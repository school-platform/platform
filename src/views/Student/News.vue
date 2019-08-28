<template>
  <el-container>
    <el-header style="font-size: 18px">
      <div style="float:left">消息管理</div>
      <el-dropdown @command="handleCommand" style="float:right">
        <i class="el-icon-setting" style="margin-right: 5px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="delete">一键删除</el-dropdown-item>
          <el-dropdown-item command="ready">一键已读</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-header>

    <el-main>
      <el-collapse
        @change="handleChange"
        class="infinite-list"
        style=" height:100%;"
        v-infinite-scroll="load"
        accordion
      >
        <el-collapse-item
          v-for="(i,index) in message"
          class="infinite-list-item"
          :key="i.id"
          :name="index"
        >
          <template slot="title">
            <i class="el-icon-user"></i>
            <span style="margin-right:20px;width:150px;">{{i.sender}}</span>
            <i class="el-icon-time"></i>
            <span style="margin-right:20px;">{{i.time}}</span>
            <span v-if="!i.isread">未读</span>
            <span v-else>已读</span>
          </template>
          {{ i.content }}
          <br />
          <el-button
            type="warning"
            plain
            size="mini"
            style="float:right;margin-bottom:10px;margin-right:5px;"
            @click="dele(i.id)"
          >删除</el-button>
        </el-collapse-item>
      </el-collapse>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      count: 5,
      message: [
        {
          id: "11111111",
          sender: "管理员",
          isread: false,
          time: "1000-1-1",
          content: "hello world!"
        },
        {
          id: "2222222",
          sender: "开源软件学会",
          isread: true,
          time: "2019-3-4",
          content: "hello world!"
        }
      ]
    };
  },
  methods: {
    load() {
      //this.count += 2;
    },
    handleChange(val) {
      if (typeof val == "number" && !this.message[val].isread) {
        console.log(this.message[val].id);
      }
    },
    handleCommand(command) {
      if (command == "delete") {
      } else if (command == "read") {
      }
    },
    dele: function(message) {
      console.log(message);
    }
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
.el-collapse-item__header,
.el-collapse-item__wrap {
  padding-left: 15px;
}
</style>



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
            <span style="margin-right:20px;">{{i.sendTime}}</span>
            <span v-if="!i.isread">未读</span>
            <span v-else>已读</span>
            <el-button type="text" size="mini" @click="dele(i.id,index)" style="margin-left:20px;">删除</el-button>
          </template>
          {{ i.text }}
          <br />
        </el-collapse-item>
      </el-collapse>
    </el-main>
  </el-container>
</template>

<script>
export default {
  created: function() {
    axios({
      url: "/api/student/getnews",
      method: "post",
      params: { stu_id: "16111205000" }
    }).then(res => {
      console.log(res);
      if (res.data.state == 200) {
        console.log(res.data);
        this.message = res.data.data;
      } else {
        this.$notify.error({
          title: "错误",
          message: res.data.msg
        });
      }
    });
  },
  data() {
    return {
      count: 5,
      message: []
    };
  },
  methods: {
    load() {
      //this.count += 2;
    },
    handleChange(val) {
      if (typeof val == "number" && !this.message[val].isread) {
        console.log(this.message[val].id);
        axios({
          url: "/api/student/readnews",
          method: "post",
          params: { stu_id: "16111205000", msg_id: this.message[val].id }
        }).then(res => {
          console.log(res);
          if (res.data.state == 200) {
            console.log(res.data.msg);
          } else {
            this.$notify.error({
              title: "错误",
              message: res.data.msg
            });
          }
        });
      }

      axios({
        url: "/api/student/getnews",
        method: "post",
        params: { stu_id: "16111205000" }
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          console.log(res.data);
          this.message = res.data.data;
        } else {
          this.$notify.error({
            title: "错误",
            message: res.data.msg
          });
        }
      });
    },
    handleCommand(command) {
      if (command == "delete") {
        axios({
          url: "/api/islogin",
          method: "post",
          params: {}
        }).then(res => {
          alert(res.data);
        });
      } else if (command == "read") {
      }
    },
    dele: function(id, index) {
      axios({
        url: "/api/student/deletenews",
        method: "post",
        params: { stu_id: "16111205000", msg_id: id }
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          this.$notify({
            title: "操作成功",
            message: res.data.msg,
            type: "success"
          });

          this.message.splice(index, 1);
        } else {
          this.$notify.error({
            title: "错误",
            message: res.data.msg
          });
        }
      });
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



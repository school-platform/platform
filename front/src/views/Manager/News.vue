<template>
  <el-container>
    <el-header style="font-size: 18px;float:18px;text-align:left;">
      <span>消息管理</span>
    </el-header>

    <el-main>
      <el-form ref="form" :model="form" label-width="80px" style="margin:20px 20px;">
        <el-input
          type="textarea"
          placeholder="请输入内容"
          v-model="form.text"
          rows="10"
          style="margin:10px 0px;"
        ></el-input>
        <el-row>
          <el-col :span="12">
              <el-checkbox label="学生" v-model="form.isstudent"></el-checkbox>
              <el-checkbox label="社团" v-model="form.isorgan"></el-checkbox>
          </el-col>
          <el-col :span="12">
            <el-button type="primary" size="mini" @click="handleChange">发送</el-button>
          </el-col>
        </el-row>
      </el-form>
      <el-collapse
        class="infinite-list"
        v-infinite-scroll="load"
        style="margin:20px 20px;"
        accordion
      >
        <el-collapse-item v-for="m in message" key="m.id">
          <template slot="title">
            <i class="header-icon el-icon-time">{{m.time}}</i>
          </template>
          <div>{{m.text}}</div>
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
      form: {
        isstudent: false,
        isorgan: false,
        text: ""
      },
      message: [
        {
          id: "21341",
          time: "2015-1-1",
          text: "这是一个好消息!"
        }
      ]
    };
  },
  methods: {
    load() {
      //this.count += 2;
    },
    handleChange() {
      console.log(this.form);
      axios({
        url: "/api/admin/publishMessage",
        method: "post",
        params: this.form
      }).then(res => {
        if (res.data.state == 200) {
          this.$message({
            message: res.data.msg,
            type: "success"
          });
        } else {
          this.$message.error(res.data.msg);
        }
      });
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
.el-collapse-item__header,
.el-collapse-item__wrap {
  padding-left: 15px;
}
</style>



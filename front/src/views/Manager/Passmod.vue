<template>
  <el-container>
    <el-header style="text-align: left; font-size: 18px">修改密码</el-header>

    <el-main>
      <el-form
        :model="ruleForm"
        status-icon
        label-width="100px"
        class="demo-ruleForm container"
        style="margin-top:100px;"
      >
        <el-form-item label="原密码" prop="oldpass">
          <el-input v-model.number="ruleForm.old_pass"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password">
          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="post()">提交</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        password: "",
        checkPass: "",
        old_pass: "",
        admin_acc: "admin"
      }
    };
  },
  methods: {
    post() {
      axios({
        url: "/api/admin/upPass",
        method: "post",
        params: this.ruleForm
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
    }
  }
};
</script>

<style scoped>
h3 {
  text-align: left;
}
.container {
  width: 50%;
}
.clearfix span {
  font-size: 18px;
  float: left;
  line-height: 36px;
}
</style>



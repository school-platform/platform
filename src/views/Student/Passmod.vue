<template>
  <el-container>
    <el-header style="text-align: left; font-size: 18px">修改密码</el-header>

    <el-main>
      <el-form :model="ruleForm" status-icon label-width="100px" class="demo-ruleForm container" style="margin-top:100px;">
        <el-form-item label="原密码" prop="oldpass">
          <el-input v-model.number="ruleForm.oldpass"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newpass">
          <el-input type="password" v-model="ruleForm.newpass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">提交</el-button>
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
        newpass: "",
        checkPass: "",
        oldpass: ""
      }
    };
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



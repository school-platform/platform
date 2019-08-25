<template>
  <div class="login">
    <el-form
      :model="ruleForm2"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-container"
    >
      <h3 class="title">系统登录</h3>
      <el-form-item>
        <el-input type="text" auto-complete="off" placeholder="账号" prefix-icon="el-icon-user-solid"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" auto-complete="off" placeholder="密码" prefix-icon="el-icon-key"></el-input>
      </el-form-item>
      <el-form-item  class="inputbar">
        <el-input
          class="log-input"
          placeholder="验证码"
          prefix-icon="el-icon-lock"
          @keydown.enter.native="alert('denglu')"
        >
        <template slot="append"><div  @click="createCode()" class="check" title="换一张">{{checkCode}}</div></template>
        </el-input>
      </el-form-item>
      <el-checkbox checked class="remember">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      logining: false,
      ruleForm2: {
        account: "admin",
        checkPass: "123456"
      },
      checked: true,
      checkCode:'1234'
    };
  },
  methods:{
      createCode() {
      let code = "";
      let codeLength = 4; //验证码的长度  
      let random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
        'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数  
      for(let i = 0; i < codeLength; i++) { //循环操作  
        let index = Math.floor(Math.random() * 36); //取得随机数的索引（0~35）  
        code += random[index]; //根据索引取得随机数加到code上  
      }
      this.checkCode = code; //把code值赋给验证码  
    }
  }
};
</script>

<style scoped>
.login-container {
  /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
  -webkit-border-radius: 5px;
  border-radius: 5px;
  -moz-border-radius: 5px;
  background-clip: padding-box;
  margin: 0px auto;
  width: 250px;
  padding: 35px 35px 15px 35px;
  background: #ffffff4a;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.title {
  margin: 0px auto 40px auto;
  text-align: left;
  color: #67717b;
}
.remember {
  margin: 0px 0px 35px 0px;
  float: left;
}
.login {
  padding-top: 150px;
  margin-top: -10px;
  height: 540px;
  background: url("../assets/login.jpg") no-repeat;
  background-size: 100%;
}
.el-checkbox__label,
.el-checkbox {
  color: rgb(255, 255, 255) !important;
}
.check{
  letter-spacing: 5px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
}
</style>
<template>
  <div class="login">
    <el-form
      :model="form"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-container"
    >
      <h3 class="title">系统登录</h3>
      <el-form-item>
        <el-input
          type="text"
          v-model="form.userid"
          auto-complete="off"
          placeholder="账号"
          prefix-icon="el-icon-user-solid"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          type="password"
          v-model="form.password"
          auto-complete="off"
          placeholder="密码"
          prefix-icon="el-icon-key"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-radio-group v-model="form.type">
          <el-radio label="student">学生</el-radio>
          <el-radio label="organization">社团</el-radio>
          <el-radio label="manager">管理员</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item class="inputbar">
        <el-input
          class="log-input"
          placeholder="验证码"
          prefix-icon="el-icon-lock"
          @keydown.enter.native="alert('denglu')"
          v-model="input"
        >
          <template slot="append">
            <div @click="createCode(null)" class="check" title="换一张">{{this.checkCode}}</div>
          </template>
        </el-input>
      </el-form-item>

      <el-checkbox checked class="remember">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%;" @click="login()">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  created(){
    var _this=this;
    this.$options.methods.createCode(_this);
  },
  data() {
    return {
      logining: false,
      form: {
        userid: "",
        password: "",
        type: "student"
      },
      checked: true,
      checkCode: "1234",
      input:""
    };
  },
  methods: {
    createCode(_this) {
      if(_this==null){
        _this=this;
      }
      let code = "";
      let codeLength = 4; //验证码的长度
      let random = new Array(
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z"
      ); //随机数
      for (let i = 0; i < codeLength; i++) {
        //循环操作
        let index = Math.floor(Math.random() * 36); //取得随机数的索引（0~35）
        code += random[index]; //根据索引取得随机数加到code上
      }
      _this.checkCode = code; //把code值赋给验证码
    },
    login() {
      // if(this.input!=this.checkCode){
      //   this.$message.error("验证码错误");
      //   return;
      // }
      axios({
        url: "/api/login",
        method: "post",
        params: this.form
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          sessionStorage.setItem("username", res.data.data.username);
          sessionStorage.setItem("type", res.data.data.type);
          sessionStorage.setItem("user_id", res.data.data.userid);
          this.$message({
            message: "恭喜你,登陆成功！"+res.data.data.username,
            type: "success"
          });
          this.$router.push('/hotactivity');
        } else {
          this.$message.error(res.data.msg);
        }
      });
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
.check {
  letter-spacing: 5px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
}
</style>
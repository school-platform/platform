<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown @command="handleCommand">
        <i class="el-icon-setting" style="margin-right: 5px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="alterphone">修改手机号</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>伍方健</span>
    </el-header>

    <el-main>
      <el-container>
        <el-aside width="200px">
          <el-upload
            class="avatar-uploader"
            action="/api/upload"
            :show-file-list="false"
            :http-request="upload"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <img src="../../assets/avatar.jpg" />
          </el-upload>
        </el-aside>
        <el-main>
          <ul>
            <li>姓名：{{user.name}}</li>
            <li>学号：{{user.id}}</li>
            <li>性别：{{user.sex}}</li>
            <li>出生：{{user.birth}}</li>
            <li>年级：{{user.grade}}</li>
            <li>学院：{{user.institute}}</li>
            <li>专业：{{user.speciality}}</li>
            <li>手机号：{{user.phone}}</li>
          </ul>
        </el-main>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: "",
      user: {
        id: "16111205119",
        name: "伍方健",
        sex: "男",
        birth: "1999-1-1",
        grade: "2016",
        institute: "计算机与信息",
        speciality: "软件工程",
        phone: "17855354884"
      }
    };
  },
  methods: {
    handleCommand(command) {
      this.$prompt("请输入手机号", "修改手机号", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^[0-9]{11}$/,
        inputErrorMessage: "手机号格式不正确"
      }).then(({ value }) => {
        this.$message({
          type: "success",
          message: "你的手机号是: " + value
        });
      });
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传头像图片只能是 JPG 格式!");
      }
      if (!isLt2M) {
        this.$message.error("上传头像图片大小不能超过 2MB!");
      }
      return isJPG && isLt2M;
    },
    upload(f) {
      this.axios.defaults.headers.post["Content-Type"] = "mutipart/form-data";
      let param = new FormData();
      console.log('开始初始化')
      param.append("file", f.file);
      param.append("name","伍方健");
      this.axios
        .post(f.action, param)
        .then(response => {
          alert("上传成功");
        })
        .catch(({ err }) => {
          console.log(err);
          f.onError();
        });
    }
  }
};
</script>

<style scoped>
.el-main {
  line-height: 50px;
  text-align: left;
  padding-top: 0px;
}
.el-main .el-container {
  margin-top: 20px;
}
.el-main {
  overflow: hidden;
}
</style>







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
            action="https://jsonplaceholder.typicode.com/posts/"
            :show-file-list="false"
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
        birth:"1999-1-1",
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
        inputPattern: /./,
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
    }
  }
};
</script>

<style scoped>
.el-main{
  line-height: 50px;
  text-align: left;
  padding-top:0px;
}
.el-main .el-container{
  margin-top: 20px;
}
.el-main{
  overflow: hidden;
}
</style>


<style>
.el-header,
.el-footer {
  /* background-color: #b3c0d1; */
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
  height: 100%;
  width:200px;
}

.el-main {
  /* background-color: #e9eef3; */
  color: #333;
  text-align: center;
}

.el-container {
  margin-bottom: 40px;
  height: 100%;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 50%;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  height: 178px;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>







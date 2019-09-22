<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">我的资料</span>
      <el-dropdown @command="handleCommand">
        <i class="el-icon-setting" style="margin-right: 5px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="save">保存修改</el-dropdown-item>
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
        <el-main style="overflow:scroll;">
          <ul>
            <li>社团ID：</li>
            <li>
              <el-input v-model="user.orgid" placeholder="请输入内容" disabled></el-input>
            </li>
            <li>社团名称：</li>
            <li>
              <el-input v-model="user.org_name" placeholder="请输入内容" disabled></el-input>
            </li>
            <li>建立时间：</li>
            <li>
              <el-input v-model="user.foundtime" placeholder="请输入内容" disabled></el-input>
            </li>
            <li>所属学院：</li>
            <li>
              <el-input v-model="user.col_name" placeholder="请输入内容" disabled></el-input>
            </li>
            <li>团长姓名：</li>
            <li>
              <el-input v-model="user.leadername" placeholder="请输入内容" disabled></el-input>
            </li>
            <li>联系方式：</li>
            <li>
              <el-input v-model="user.leadercontact" placeholder="请输入内容"></el-input>
            </li>
            
            <li>简介：</li>
            <li>
              <el-input type="textarea" :rows="8" placeholder="请输入内容" v-model="user.message"></el-input>
            </li>
          </ul>
        </el-main>
      </el-container>
    </el-main>
  </el-container>
</template>

<script>
export default {
  created: function() {
    axios({
      url: "/api/organ/getinfo",
      method: "post",
      params: {org_id:"17718260001"}
    }).then(res => {
      console.log(res.data);
      this.user=res.data.data[0];
    });
  },
  data() {
    return {
      imageUrl: "",
      user: {
        orgid: "19374882221",
        name: "开源软件协会",
        foundtime: "1992-1-1",
        message:
          "开源软件协会是计算机与信息学院的社团，一直发布与计算机相关的活动，欢迎大家的参加！",
        college: "计算机与信息",
        leadername: "伍方健",
        leadercontact: "18566958552"
      }
    };
  },
  methods: {
    handleCommand(command) {},
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
.el-main {
  line-height: 50px;
  text-align: left;
  padding-top: 0px;
  padding-right: 60px !important;
}
.el-main .el-container {
  margin-top: 20px;
}
.el-main {
  overflow: hidden;
}
</style>









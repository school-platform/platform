<template>
  <div id="activity">
    <div class="container">
      <div class="header">
        <img src="../assets/ai.jpg" />
      </div>

      <div class="body">
        <el-tabs v-model="activeName" :stretch="true">
          <el-tab-pane label="活动详情" name="first">
            <ul>
              <li>活动名称：</li>
              <li>{{act_info.name}}</li>
              <li>举办方：</li>
              <li>{{act_info.postorg}}</li>
              <li>报名时间：</li>
              <li>{{act_info.starttime}}-{{act_info.endtime}}</li>
              <li>活动类型：</li>
              <li>{{act_info.type}}</li>
              <li>活动说明：</li>
              <li>{{act_info.message}}</li>
              <li>奖项设置：</li>
              <li></li>
              <li>参赛类别：</li>
              <li v-if="act_info.isteam">团队赛</li>
              <li v-else>个人赛</li>
              <li>参赛标准：</li>
              <li>{{act_info.tmin}}-{{act_info.tmax}}人</li>
            </ul>
          </el-tab-pane>
          <el-tab-pane label="奖项设置" name="third">奖项设置</el-tab-pane>
          <el-tab-pane label="全部评论" name="second">
            <div class="comment">
              <div class="left img">
                <img src="../assets/avatar.jpg" alt="等等">
              </div>
              <div class="left">
                <div class="return">那个男人：<span>还可以！</span></div>
                <div class="parent">回复： @那个男人</div>
                <div class="time">1999-1-1 2:20</div>
              </div>
            </div>
          </el-tab-pane>
          
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: "first",
      act_info: {
        id: 1,
        name: "计算机设计大赛",
        postorg: "开源软件协会",
        img: "",
        starttime: "1999-1-1",
        type: "专业竞赛",
        isteam: true,
        message: "",
        endtime: "2000-1-1",
        max: "100",
        tmin: "3",
        tmax: "5"
      }
    };
  }
};
</script>

<style scoped>
a {
  color: black;
}
ul > li {
  line-height: 2em;
  text-align: left;
}
.comment{
  margin:20px auto;
  padding: 20px auto;
  border: 1px solid black;
}
.left{
  float: left;
  text-align: left;
}
.comment::after{
  content: "";
  clear: both;
  display: block;
}
.img img{
  width: 50px;
  height: 50px;
  border-radius: 50% !important;
}
</style>

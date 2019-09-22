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
              <li>{{act_info.org_name}}</li>
              <li>报名时间：</li>
              <li>{{act_info.starttime}}-{{act_info.endtime}}</li>
              <li>活动类型：</li>
              <li>{{act_info.type}}</li>
              <li>活动说明：</li>
              <li>{{act_info.message}}</li>
              <li>奖项设置：</li>
              <el-table :data="act_info.score" style="width: 100%">
                <el-table-column prop="level" label="奖项名" width="180"></el-table-column>
                <el-table-column prop="type" label="模块" width="180"></el-table-column>
                <el-table-column prop="score" label="分值"></el-table-column>
                <el-table-column prop="max" label="人数"></el-table-column>
              </el-table>
              <li>参赛类别：</li>
              <li v-if="act_info.isteam">团队赛-团队人数：{{act_info.tmin}}-{{act_info.tmax}}人</li>
              <li v-else>个人赛</li>
              <template v-if="act_info.isteam&&islogin()&&part!=null"></template>
              <template v-else-if="act_info.isteam&&islogin()&&part==null">
                <el-button type="primary">创建团队</el-button>
                <el-button type="primary">加入团队</el-button>
              </template>
              <template v-else-if="act_info.isteam&&!islogin()">
                <el-button type="primary">创建团队</el-button>
                <el-button type="primary">加入团队</el-button>
              </template>
              <template v-else-if="!act_info.isteam&&islogin()&&part!=null">
                <el-button type="primary" disabled>已报名</el-button>
              </template>
              <template v-else-if="!act_info.isteam&&islogin()&&part==null">
                <el-button type="primary">报名</el-button>
              </template>
              <template v-else-if="!act_info.isteam&&!islogin()">
                <el-button type="primary">报名</el-button>
              </template>
            </ul>
          </el-tab-pane>
          <el-tab-pane label="获奖公示" name="third">
            <el-table :data="award" style="width: 100%;height:500px;overflow:auto;">
              <el-table-column prop="studentid" label="学号" width="180"></el-table-column>
              <el-table-column prop="name" label="姓名" width="180"></el-table-column>
              <el-table-column prop="type" label="模块"></el-table-column>
              <el-table-column prop="level" label="奖项名"></el-table-column>
              <el-table-column prop="score" label="分值"></el-table-column>
            </el-table>
            <el-button v-if="!islasta" type="text" @click="getmore">加载更多</el-button>
            <el-button v-else type="text">————人家也是有底线的————</el-button>
          </el-tab-pane>
          <el-tab-pane label="全部评论" name="second">
            <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto;height:500px;">
              <li v-for="i in comments" class="infinite-list-item">
                <div class="comment">
                  <div class="left img">
                    <img src="../assets/avatar.jpg" alt="等等" />
                  </div>
                  <div class="left">
                    <div class="return">
                      {{i.studentid}}：
                      <span>{{i.text}}</span>
                    </div>
                    <div class="parent">回复： @{{i.parent_id}}</div>
                    <div class="parent">{{i.parentText}}</div>
                    <div class="time">{{i.time}}</div>
                  </div>
                </div>
              </li>
            </ul>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  created() {
    console.log(this.$route.query.act_id);
    var _this = this;
    axios({
      url: "/api/getActInfo",
      method: "post",
      params: { act_id: this.$route.query.act_id }
    }).then(res => {
      console.log(res);
      if (res.data.state == 200) {
        this.act_info = res.data.data;
      } else {
        this.$message.error(res.data.msg);
      }
    });

    this.$options.methods.getmorecom(0, 10, _this);
    this.$options.methods.getmoreaward(0, 10, _this);

    if (sessionStorage.getItem("user_id") != null) {
      axios({
        url: "/api/student/getPartInfo",
        method: "post",
        params: { act_id: this.$route.query.act_id ,stu_id:sessionStorage.getItem("user_id")}
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          this.part=res.data.data[0];
        }
      });
    }
  },
  data() {
    return {
      activeName: "first",
      act_info: {},
      award: [],
      islasta: false,
      islastc: false,
      comments: [],
      part: null
    };
  },
  methods: {
    islogin() {
      return sessionStorage.getItem("username");
    },
    getmore() {
      var _this = this;
      this.$options.methods.getmoreaward(this.award.length, 10, _this);
    },
    load() {
      var _this = this;
      //this.$options.methods.getmorecom(this.comments.length,5,_this);
    },
    getmorecom(now, num, _this) {
      axios({
        url: "/api/getComments",
        method: "post",
        params: { act_id: _this.$route.query.act_id, num: num, now: now }
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          let list = res.data.data.list;
          list.forEach(item => {
            _this.comments.push(item);
          });
          _this.islastc = res.data.data.islast;
        } else {
          this.$message.error(res.data.msg);
        }
      });
    },
    getmoreaward(now, num, _this) {
      axios({
        url: "/api/getRewardList",
        method: "post",
        params: { act_id: _this.$route.query.act_id, num: num, now: now }
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          let list = res.data.data.data;
          list.forEach(item => {
            _this.award.push(item);
          });
          _this.islasta = res.data.data.islast;
        } else {
          this.$message.error(res.data.msg);
        }
      });
    }
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
.comment {
  margin: 20px auto;
  padding: 20px auto;
  border: 1px solid black;
  border-radius: 5px;
}
.left {
  float: left;
  text-align: left;
}
.comment::after {
  content: "";
  clear: both;
  display: block;
}
.img img {
  width: 50px;
  height: 50px;
  border-radius: 50% !important;
}
</style>

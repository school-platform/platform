<template>
  <div id="hotactivity">
    <div class="row">
      <el-row>
        <el-col :span="12" v-for="item in tableData" :key="item.id">
          <div class="act-item">
            <router-link to="/activity">
              <div class="item-img">
                <img src="../../assets/ai.jpg" />
              </div>
            </router-link>
            <div class="item-detail">
              <p>{{item.name}}</p>
              <p>{{item.starttime}} - {{item.endtime}}</p>
            </div>
            <hr />
            <div class="person">
              <span>{{item.num}}</span>
              <i class="el-icon-user-solid"></i>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-button v-if="!islast" type="text" @click="getmore()">获取更多</el-button>
    <el-button v-else type="text">——————我也是有底线的——————</el-button>
  </div>
</template>

<script>
export default {
  created: function() {
    var _this = this;
    this.$options.methods.post(0, 6, _this);
  },
  data() {
    return {
      tableData: [],
      islast: false
    };
  },
  methods: {
    getmore() {
      var data = { now: this.tableData.length, num: 6 };
      console.log(data);
      axios({
        url: "/api/newAct",
        method: "post",
        params: data
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          var newdata = res.data.data.data;
          newdata.forEach(item => {
            this.tableData.push(item)
          });
          this.islast = res.data.data.islast;
        } else {
          this.$message.error(res.data.msg);
        }
      });
    },
    post(now, num, _this) {
      axios({
        url: "/api/newAct",
        method: "post",
        params: { now: now, num: num }
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          _this.tableData = res.data.data.data;
          _this.islast = res.data.data.islast;
        } else {
          this.$message.error(res.data.msg);
        }
      });
    }
  }
};
</script>


<template>
  <div id="hotactivity">
    <div class="row">
      <el-row>
        <el-col :span="12" v-for="item in acts" :key="item.id">
          <router-link to="/activity">
            <div class="act-item">
              <div class="item-img">
                <img src="../../assets/ai.jpg" />
              </div>
              <div class="item-detail">
                <p>计算机设计大赛</p>
                <p>2019年1月1日 - 2020年1月1日</p>
              </div>
              <hr />
              <div class="person">
                <span>12333</span>
                <i class="el-icon-user-solid"></i>
              </div>
            </div>
          </router-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "hot",
  data() {
    return {
      acts: [
        { id: "1", age: 12 },
        { id: "2", age: 13 },
        { id: "3", age: 14 },
        { id: "4", age: 14 },
        { id: "5", age: 14 },
        { id: "6", age: 14 }
      ]
    };
  }
};
</script>

<style scoped>
.act-item {
  height: 375px;
  margin: 10px auto;
  background: rgb(255, 255, 255);
  border-radius: 0px;
}
.el-col:nth-child(2n) .act-item {
  margin-left: 9px;
}
.el-col:nth-child(2n + 1) .act-item {
  margin-right: 9px;
}
.item-detail {
  text-align: left;
  border-left: 5px solid green;
}
.person {
  margin-right: 10px;
}

.person * {
  float: right;
  margin-right: 5px;
}
.person::after {
  content: "";
  clear: both;
}
a{
  color:black;
}
</style>

<template>
  <div class="container">
    <div id="carous">
      <div class="block">
        <el-carousel height="500px">
          <el-carousel-item v-for="item in 4" :key="item">
            <img src="../../assets/ai.jpg" />
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
    <div id="tab">
      <el-tabs v-model="activeName" :stretch="true">
        <el-tab-pane name="first">
          <span slot="label">
            <router-link to="/hotActivity">热门活动</router-link>
          </span>
        </el-tab-pane>
        <el-tab-pane name="second">
          <span slot="label">
            <router-link to="/newActivity">最新活动</router-link>
          </span>
        </el-tab-pane>
        <el-tab-pane name="third">
          <span slot="label">
            <router-link to="/myActivity">我的活动</router-link>
          </span>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="tab-item">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: "first"
    };
  },
  methods: {}
};
</script>

<style scoped>
#tab {
  margin: 0 auto;
}
div.con {
  height: 100px;
}
a {
  color: #000;
  text-decoration: none;
}

#carous {
  margin-bottom: 20px;
}
.block {
  margin: 0 auto;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 400px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
a{
  color:black;
}
</style>

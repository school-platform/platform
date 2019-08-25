<template>
<el-container>
    <el-header style="text-align: right; font-size: 12px">
    </el-header>

    <el-main>
      <div id="tab">
      <el-tabs v-model="activeName" :stretch="true">
        <el-tab-pane name="first">
          <span slot="label">A模块 1.0</span>
        </el-tab-pane>
        <el-tab-pane name="second">
          <span slot="label">B模块 2.0</span>
        </el-tab-pane>
        <el-tab-pane name="third">
          <span slot="label">C模块 3.0</span>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div class="tab-item">
      <ul
        class="infinite-list"
        v-infinite-scroll="load"
        style="overflow:scroll;overflow-x:hidden;height:100%;"
      >
        <li v-for="i in count" class="infinite-list-item" :key="i">
          <el-container>
            <el-aside width="420px">
              <img src="../../assets/ai.jpg" class="avatar" />
            </el-aside>
            <el-main>
              <ul>
                <li>活动名称：计算机设计大赛</li>
                <li>发布单位：开源软件协会</li>
                <li>发布时间：2019/1/1</li>
                <li>活动时间：2019/1/2-2019/3/4</li>
              </ul>
            </el-main>
          </el-container>
        </li>
      </ul>
    </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      count: 10,
      activeName: "first"
    };
  },
  methods: {
    load() {
      //  this.count += 2;
    },
    handleChange(val) {
      console.log(val);
    }
  }
};
</script>

<style scoped>
img {
  margin:5px 10px;
  width: 400px;
  height: 250px;
}

.tab-item {
  height: 530px;
}
h3 {
  text-align: left;
}
.clearfix span {
  font-size: 18px;
  float: left;
  line-height: 36px;
}

.el-aside {
  /* background-color: #D3DCE6; */
  color: #333;
  text-align: center;
}

li .el-main {
  /* background-color: #E9EEF3; */
  color: #333;
  text-align: left;
  line-height: 2em;
}

.el-container {
  border-top: 1px solid #e9eef3;
  border-bottom: 1px solid #e9eef3;
}
</style>



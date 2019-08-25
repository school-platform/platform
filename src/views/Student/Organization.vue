<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px"></el-header>

    <el-main>
      <ul
        class="infinite-list"
        v-infinite-scroll="load"
        style="overflow:scroll;overflow-x:hidden;height:100%;"
      >
        <li v-for="i in count" class="infinite-list-item" :key="i">
          <el-container>
            <el-aside width="200px">
              <img src="../../assets/avatar.jpg" class="avatar" />
            </el-aside>
            <el-main>
              <ul>
                <li>社团名：开源软件协会</li>
                <li>简介：开源软件协会</li>
              </ul>
            </el-main>
          </el-container>
        </li>
      </ul>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      count: 10
    };
  },
  methods: {
    load() {
      // this.count += 2;
    },
    handleChange(val) {
      console.log(val);
    }
  }
};
</script>

<style scoped>
img {
  margin-left: auto;
  margin-right: auto;
  border-radius: 50%;
  position: relative; /*设置position属性*/
  top: 50%; /*偏移*/
}
li .el-main{
line-height: 2em;
text-align: left;
padding-top:0px;
}
li .el-container{
  border-top: 1px solid gray;
  border-bottom: 1px solid gray;
  padding: 10px 0px;
  margin-bottom: 10px;
}
</style>




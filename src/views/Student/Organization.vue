<template>
  <el-container>
    <el-header style="text-align: left; font-size: 18px">我的社团</el-header>

    <el-main>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="社团名称"></el-table-column>
        <el-table-column prop="foundtime" label="建立时间"></el-table-column>
        <el-table-column prop="college" label="隶属学院"></el-table-column>
        <el-table-column prop="leadername" label="会长"></el-table-column>
        <el-table-column prop="leadercontact" label="联系方式"></el-table-column>
        <el-table-column prop="jointime" label="入会时间"></el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    const item = {
      name:"开源软件协会",
      foundtime:"1999-11-11",
      college:"计算机与信息学院",
      leadername:"伍方健",
      leadercontact:"17855354884",
      jointime:"2016"
    };
    return {
      tableData: Array(10).fill(item)
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




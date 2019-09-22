<template>
  <el-container>
    <el-header style="text-align: left; font-size: 18px">我的社团</el-header>

    <el-main>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="name" label="社团名称"></el-table-column>
        <el-table-column prop="foundTime" label="建立时间"></el-table-column>
        <el-table-column prop="collegeName" label="隶属学院"></el-table-column>
        <el-table-column prop="leaderName" label="会长"></el-table-column>
        <el-table-column prop="contact" label="联系方式"></el-table-column>
        <el-table-column prop="joinTime" label="入会时间"></el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
export default {
  created: function() {
    axios({
      url: "/api/student/getorgs",
      method: "post",
      params: { stu_id: "16111205000" }
    }).then(res => {
      console.log(res);
      if (res.data.state == 200) {
        console.log(res.data);
        this.tableData = res.data.data;
      } else {
        this.$notify.error({
          title: "错误",
          message: res.data.msg
        });
      }
    });
  },
  data() {
    const item = {
      name: "开源软件协会",
      foundtime: "1999-11-11",
      collegeName: "计算机与信息学院",
      leadername: "伍方健",
      contact: "17855354884",
      joinTime: "2016"
    };
    return {
      tableData:[]
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




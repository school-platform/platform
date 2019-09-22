<template>
  <el-container style="height:550px;">
    <el-main>
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="studentid" label="ID"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="grade" label="年级"></el-table-column>
        <el-table-column prop="college" label="学院"></el-table-column>
        <el-table-column prop="major" label="专业"></el-table-column>
        <el-table-column prop="phone" label="手机"></el-table-column>
        <el-table-column prop="jointime" label="加入时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button v-if="scope.row.checktime==''" type="primary" size="mini">签到</el-button>
            <el-button v-else type="primary" disabled size="mini">已签到</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
    <el-footer>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="pageSize"
        layout="sizes, prev, pager, next"
        :total="count"
      ></el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
export default {
  created: function() {
    console.log(this.$route.query.act_id);
    var _this=this;
    this.$options.methods.post(1, 10,_this);
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 10,
      count: 0
    };
  },

  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.currentPage = 1;
      var _this = this;
      this.$options.methods.post(1, val,_this);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage=val;
      var _this=this;
      this.$options.methods.post(val, this.pageSize,_this);
    },
    handleClick(index, row) {
      console.log(index, row);
    },
    post:function(page, num,_this) {
      axios({
        url: "/api/organ/getSignList",
        method: "post",
        params: { act_id: 51, num: num, page: page }
      }).then(res => {
        _this.tableData = res.data.data.list;
        _this.count = res.data.data.count;
      });
    }
  }
};
</script>

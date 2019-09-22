<template>
  <el-container style="height:550px;">
    <el-main>
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="time" label="时间"></el-table-column>
        <el-table-column prop="text" label="内容" width="500"></el-table-column>
        <el-table-column label="操作" fixed="right" width="100">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleClick(scope.row)">删除</el-button>
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
    var _this = this;
    this.$options.methods.post(1, 10, _this);
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      count: 0,
      pageSize: 10
    };
  },

  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.currentPage = 1;
      var _this = this;
      this.$options.methods.post(1, val, _this);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      var _this = this;
      this.$options.methods.post(val, this.pageSize, _this);
    },
    handleClick(index) {
      axios({
        url: "/api/organ/deleteComment",
        method: "post",
        params: { com_id: this.tableData[index].id }
      }).then(res => {
        if (res.data.state == 200) {
          this.$message({
            message: res.data.msg,
            type: "success"
          });
          this.tableData.splice(index, 1);
        }else{
          this.$message.error(res.data.msg);
        }
      });
    },
    post: function(page, num, _this) {
      axios({
        url: "/api/organ/getCommentList",
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

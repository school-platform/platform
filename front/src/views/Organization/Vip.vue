<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">会员管理</span>

      <el-button
        type="primary"
        style="margin-right: 5px"
        icon="el-icon-circle-plus-outline"
        size="small"
        @click="addvip"
      >新增</el-button>
      <el-input
        placeholder="请输入姓名或学号"
        v-model="input"
        size="small"
        class="input-with-select"
        style="width:250px;margin-left:10px;"
      >
        <el-button slot="append" icon="el-icon-search"></el-button>
      </el-input>
    </el-header>
    <el-main>
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="stu_id" label="学号"></el-table-column>
        <el-table-column prop="stu_name" label="姓名"></el-table-column>
        <el-table-column prop="grade" label="年级"></el-table-column>
        <el-table-column prop="college" label="学院"></el-table-column>
        <el-table-column prop="major" label="专业"></el-table-column>
        <el-table-column prop="jointime" label="加入时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
        :page-size="page_size"
        layout="sizes, prev, pager, next"
        :total="count"
      ></el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
export default {
  created: function() {
    axios({
      url: "/api/organ/getVipList",
      method: "post",
      params: { org_id: "17718260001", page: 1, num: 10 }
    }).then(res => {
      console.log(res);
      if (res.data.state == 200) {
        console.log(res.data);
        this.tableData = res.data.data.list;
        this.count = res.data.data.count_num;
      }
    });
  },
  data() {
    return {
      tableData: [],
      multipleSelection: [],
      currentPage: 1,
      count: 100,
      page_size:10,
      input: "",
      select: ""
    };
  },

  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.page_size=val;
      this.currentPage=1;
      axios({
        url: "/api/organ/getVipList",
        method: "post",
        params: { org_id: "17718260001", page: 1, num: val }
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          console.log(res.data);
          this.tableData = res.data.data.list;
          this.count = res.data.data.count_num;
        }
      });
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      axios({
        url: "/api/organ/getVipList",
        method: "post",
        params: { org_id: "17718260001", page: val, num: this.page_size }
      }).then(res => {
        console.log(res);
        if (res.data.state == 200) {
          console.log(res.data);
          this.tableData = res.data.data.list;
          this.count = res.data.data.count_num;
        }
      });
    },
    handleDelete(index, row) {
      console.log(this.tableData[index].stu_id);
      this.$confirm("此操作将删除该会员, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          axios({
            url: "/api/organ/deleteVip",
            method: "post",
            params: {
              org_id: "17718260001",
              stu_id: this.tableData[index].stu_id
            }
          }).then(res => {
            if (res.data.state == 200) {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
            } else {
              this.$message.error(res.data.msg);
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    addvip: function() {
      this.$prompt("请输入学生ID", "添加会员", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        inputPattern: /^[0-9]{11}$/,
        inputErrorMessage: "ID格式不正确"
      }).then(({ value }) => {
        axios({
          url: "/api/organ/addVip",
          method: "post",
          params: { org_id: "17718260001", stu_id: value }
        }).then(res => {
          console.log(res);
          if (res.data.state == 200) {
            console.log(res.data);
            this.$message({
              type: "success",
              message: res.data.msg
            });
          } else {
            this.$message.error(res.data.msg);
          }
        });
      });
    }
  }
};
</script>
<style>
.cell {
  line-height: 27px !important;
}
</style>

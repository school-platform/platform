<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">会员管理</span>
      <el-button type="danger" style="margin-right: 5px" icon="el-icon-delete" size="small">删除</el-button>
      <el-button
        type="primary"
        style="margin-right: 5px"
        icon="el-icon-circle-plus-outline"
        size="small"
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
      <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="id" label="学号"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="sex" label="性别"></el-table-column>
        <el-table-column prop="grade" label="年级"></el-table-column>
        <el-table-column prop="college" label="学院"></el-table-column>
        <el-table-column prop="major" label="专业"></el-table-column>
        <el-table-column prop="jointime" label="加入时间"></el-table-column>
      </el-table>
    </el-main>
    <el-footer>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-sizes="[10, 20, 50, 100]"
        :page-size="10"
        layout="sizes, prev, pager, next"
        :total="100"
      ></el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
export default {
  data() {
    const item = {
      id: "16111205119",
      name: "伍方健",
      sex: "男",
      grade: "2016",
      college: "计算机与信息",
      major: "软件工程",
      jointime: "2017-1-1"
    };
    return {
      tableData: Array(10).fill(item),
      multipleSelection: [],
      currentPage: 1,
      input: "",
      select: ""
    };
  },

  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    }
  }
};
</script>
<style>
.cell { 
    line-height: 27px !important;
}
</style>

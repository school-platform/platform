<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">活动管理</span>
      <el-input
        placeholder="请输入关键字"
        v-model="input"
        size="small"
        class="input-with-select"
        style="width:300px;margin-right:20px;"
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
        <el-table-column prop="name" label="活动名称"></el-table-column>
        <el-table-column prop="posttime" label="提交时间"></el-table-column>
        <el-table-column prop="extime" label="审核时间"></el-table-column>
        <el-table-column prop="result" label="状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleClick(scope.$index, scope.row)"><a href="/organization/detail">详细信息</a></el-button>
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
      name: "软件设计大赛",
      posttime: "2019-1-1",
      extime: "2019-2-2",
      result: "未通过"
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
    },
    handleClick(index, row) {
      console.log(index, row);
    }
  }
};
</script>

<style>

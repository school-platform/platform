<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">社团账号管理</span>
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
      >
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="name" label="社团名称"></el-table-column>
        <el-table-column prop="foundtime" label="建立时间"></el-table-column>
        <el-table-column prop="leadername" label="团长名"></el-table-column>
        <el-table-column prop="leadercontact" label="联系方式"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleClick(scope.$index, scope.row)"><a href="/organization/detail">修改</a></el-button>
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
      id:"16111205119",
      name: "开源软件协会",
      foundtime:"2019-1-1",
      leadername:"伍方健",
      leadercontact:"17855354884",
      password:"16111205119"
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


<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">学生账号管理</span>
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
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="grade" label="年级"></el-table-column>
        <el-table-column prop="college" label="学院"></el-table-column>
        <el-table-column prop="major" label="专业"></el-table-column>
        <el-table-column prop="birthday" label="生日"></el-table-column>
        <el-table-column prop="phone" label="手机"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="text" @click="dialogTableVisible = true">修改</el-button>
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
    <el-dialog title="修改信息" :visible.sync="dialogTableVisible">
      <el-form ref="form" :model="tableData[0]" label-width="80px">
        <el-form-item label="姓名">
          <el-input v-model="tableData[0].name"></el-input>
        </el-form-item>
      </el-form>
    </el-dialog>
  </el-container>
</template>

<script>
export default {
  data() {
    const item = {
      id: "16111205119",
      name: "伍方健",
      grade: "2019",
      college: "计算机与信息",
      major: "软件工程",
      birthday: "1999-11-26",
      phone: "17855354884",
      password: "16111205119"
    };
    return {
      tableData: Array(10).fill(item),
      multipleSelection: [],
      currentPage: 1,
      input: "",
      select: "",
      dialogTableVisible: false
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

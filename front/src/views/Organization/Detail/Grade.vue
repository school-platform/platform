<template>
  <el-container style="height:550px;">
    <el-main>
      <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="grade" label="年级"></el-table-column>
        <el-table-column prop="college" label="学院"></el-table-column>
        <el-table-column prop="major" label="专业"></el-table-column>
        <el-table-column prop="phone" label="手机"></el-table-column>
        <el-table-column prop="checktime" label="签到时间"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-select placeholder="选择奖项" size="mini">
              <el-option
                v-for="item in score"
                :key="item.level"
                :label="item.level"
                :value="item.level"
              ></el-option>
            </el-select>
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
      id: "16111205119",
      name: "伍方健",
      grade: "2019",
      college: "计算机与信息",
      major: "软件工程",
      phone: "17855354884",
      posttime: "1999-11-11",
      jointime: "2000-1-1",
      checktime: "2001-1-1"
    };
    return {
      tableData: Array(10).fill(item),
      currentPage: 1,
      score: [
          {
            level: "参与奖",
            score: 0.2,
            type: "A"
          },
          {
            level: "优秀奖",
            score: 0.2,
            type: "A"
          }
        ]
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

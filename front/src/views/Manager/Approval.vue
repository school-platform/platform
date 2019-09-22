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
      >
        <el-table-column prop="act_name" label="活动名称"></el-table-column>
        <el-table-column prop="org_name" label="社团名称"></el-table-column>
        <el-table-column prop="posttime" label="提交时间"></el-table-column>

        <el-table-column label="审核时间">
          <template slot-scope="scope">
            <span v-if="scope.row.extime!=''">{{scope.row.extime}}</span>
            <span v-else>待审核</span>
          </template>
        </el-table-column>

        <el-table-column label="审核时间">
          <template slot-scope="scope">
            <el-popover
              v-if="scope.row.extime!=''&&scope.row.ispass"
              placement="right-start"
              title="批注:"
              width="200"
              trigger="hover"
              :content="scope.row.result"
            >
              <el-button size="mini" type="primary" slot="reference">已批准</el-button>
            </el-popover>
            <el-popover
              v-if="scope.row.extime!=''&&!scope.row.ispass"
              placement="right-start"
              title="批注:"
              width="200"
              trigger="hover"
              :content="scope.row.result"
            >
              <el-button size="mini" type="danger" slot="reference">已驳回</el-button>
            </el-popover>
            <el-button
              v-if="scope.row.extime==''"
              size="mini"
              type="primary"
              @click="dialogFormVisible=true"
            >审核</el-button>
          </template>
        </el-table-column>

        <el-table-column label="查看">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="look()">详细信息</el-button>
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
    <el-dialog title="修改信息" :visible.sync="dialogFormVisible" width="30%" style="text-align:left">
      <el-input
        type="textarea"
        :autosize="{ minRows: 6, maxRows: 10}"
        rows="6"
        placeholder="请输入内容"
        v-model="result"
      ></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button type="danger" @click="dialogFormVisible = false">驳回</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">批准</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
export default {
  created: function() {
    var _this = this;
    this.$options.methods.post(1, 10, _this);
  },
  data() {
    return {
      tableData: [
      ],
      currentPage: 1,
      input: "",
      result: "",
      dialogFormVisible: false,
      count:0,
      pageSize:10
    };
  },

  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
      var _this = this;
      this.$options.methods.post(1, val, _this);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
      var _this = this;
      this.$options.methods.post(val, this.pageSize, _this);
    },
    handleClick(index, row) {
      console.log(index, row);
    },
    look(){
      this.$router.push("/manager/detail");
    },
    post(page, num, _this) {
      axios({
        url: "/api/admin/getAllExamine",
        method: "post",
        params: { page: page, num: num }
      }).then(res => {
        console.log(res.data);
        _this.tableData = res.data.data.list;
        _this.count = res.data.data.count;
      });
    }
  }
};
</script>

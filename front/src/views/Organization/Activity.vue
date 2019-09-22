<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">活动管理</span>
      <el-button
        type="primary"
        style="margin-right: 5px"
        icon="el-icon-circle-plus-outline"
        size="small"
        @click="publish()"
      >发布活动</el-button>
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
        <el-table-column prop="name" label="活动名称"></el-table-column>
        <el-table-column prop="posttime" label="提交时间"></el-table-column>
        <el-table-column label="审核时间">
          <template slot-scope="scope">
            <span v-if="scope.row.extime!=''">{{scope.row.extime}}</span>
            <span v-else>--</span>
          </template>
        </el-table-column>
        <el-table-column label="状态">
          <template slot-scope="scope">
            <span v-if="scope.row.extime==''">待审核</span>
            <span v-else-if="scope.row.result==''">已通过</span>
            <span v-else>未通过</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="handleClick(scope.$index, scope.row)">详细信息</el-button>
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
        :total="total"
      ></el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
export default {
  created: function() {
    var _this=this;
    this.$options.methods.post(1, 10, _this);
  },
  data() {
    return {
      tableData: [],
      currentPage: 1,
      total:0,
      pageSize:10,
      input: ""
    };
  },

  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.currentPage=1;
      this.pageSize=val;
      var _this = this;
      this.$options.methods.post(1, val, _this);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage=val;
      var _this = this;
      this.$options.methods.post(val, this.pageSize, _this);
    },
    handleClick(index, row) {
      console.log(index, row);
      this.$router.push({
        path: "/organization/detail",
        query: { act_id: this.tableData[index].id }
      });
    },
    publish() {
      this.$router.push("/organization/publish");
    },
    post(page,num,_this) {
      axios({
        url: "/api/organ/getActList",
        method: "post",
        params: { org_id: "17718260001", page: page, num: num }
      }).then(res => {
        console.log(res.data);
        _this.tableData = res.data.data.list;
        _this.total=res.data.data.count;
      });
    }
  }
};
</script>

<style>

<template>
  <el-container>
    <el-header style="text-align: left; font-size: 18px">学分统计</el-header>

    <el-main>
      <div id="tab">
        <el-tabs v-model="editableTabsValue" :stretch="true" @tab-click="handleClick">
          <el-tab-pane
            v-for="(item,index) in editableTabs"
            :key="item.index"
            :label="item.type"
            :title="item.type"
          ></el-tab-pane>
        </el-tabs>
      </div>
      <div class="tab-main">
        <el-table style="width: 100%" :data="tableData">
          <el-table-column prop="act_name" label="活动名称"></el-table-column>
          <el-table-column prop="act_time" label="开始时间"></el-table-column>
          <el-table-column prop="post_time" label="发布时间"></el-table-column>
          <el-table-column prop="check_time" label="签到时间"></el-table-column>
          <el-table-column prop="reward" label="获奖名"></el-table-column>
          <el-table-column prop="score" label="分值"></el-table-column>
        </el-table>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  created: function() {
    axios({
      url: "/api/student/getScoreInfo",
      method: "post",
      params: {stu_id:"16111205000"}
    }).then(res => {
      if (res.data.state == 200) {
        this.editableTabs=res.data.data;
        this.tableData=res.data.data[0].si_list;
      } else {
        this.$notify.error({
          title: "错误",
          message: res.data.msg
        });
      }
    });
  },
  data() {
    return {
      editableTabsValue: "0",
      editableTabs: [
      ],
      tableData:[],
      tabIndex: 0,
    };
  },
  methods: {
    addTab(tabtitle) {
      let newTabName = ++this.tabIndex + "";
      this.editableTabs.push({
        title: tabtitle
      });
      this.editableTabsValue = newTabName;
    },
    handleClick(tab, event) {
      this.tableData=this.editableTabs[tab.index].si_list;
    }
  }
};
</script>




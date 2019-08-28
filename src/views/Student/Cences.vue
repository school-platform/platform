<template>
  <el-container>
    <el-header style="text-align: left; font-size: 18px">学分统计</el-header>

    <el-main>
      <div id="tab">
        <el-tabs v-model="editableTabsValue" :stretch="true" @tab-click="handleClick">
          <el-tab-pane
            v-for="(item) in editableTabs"
            :key="item.name"
            :label="item.title"
          ></el-tab-pane>
        </el-tabs>
      </div>
      <div class="tab-main">
        <el-table :data="tableData" style="width: 100%">
          <el-table-column prop="name" label="活动名称"></el-table-column>
          <el-table-column prop="starttime" label="开始时间"></el-table-column>
          <el-table-column prop="posttime" label="报名时间"></el-table-column>
          <el-table-column prop="signtime" label="签到时间"></el-table-column>
          <el-table-column prop="rewordtime" label="获奖时间"></el-table-column>
          <el-table-column prop="reword" label="获奖名"></el-table-column>
          <el-table-column prop="score" label="分值"></el-table-column>
        </el-table>
      </div>
    </el-main>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      editableTabsValue: "0",
      editableTabs: [
        {
          title: "A",
          table: [
            {
              name: "计算机设计大赛",
              starttime: "1999-11-11",
              posttime: "1999-1-1",
              signtime: "2000-1-1",
              rewordtime: "2000-1-1",
              reword: "一等奖",
              score: "1.0"
            }
          ]
        },
        {
          title: "B",
          table: [
            {
              name: "ACM设计大赛",
              starttime: "1999-11-11",
              posttime: "1999-1-1",
              signtime: "2000-1-1",
              rewordtime: "2000-1-1",
              reword: "一等奖",
              score: "1.0"
            }
          ]
        }
      ],
      tabIndex: 2,
      tableData: [
        {
          name: "计算机设计大赛",
          starttime: "1999-11-11",
          posttime: "1999-1-1",
          signtime: "2000-1-1",
          rewordtime: "2000-1-1",
          reword: "一等奖",
          score: "1.0"
        }
      ]
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
      this.tableData=this.editableTabs[tab.index].table;
    }
  }
};
</script>



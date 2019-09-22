<template>
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <span style="float:left;font-size:16px;">社团账号管理</span>
      <el-button
        type="primary"
        style="margin-right: 5px"
        icon="el-icon-circle-plus-outline"
        size="small"
        @click="dialogFormVisible2=true"
      >添加社团</el-button>
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
      <el-table :data="tableData" tooltip-effect="dark" style="width: 100%">
        <el-table-column prop="orgid" label="ID"></el-table-column>
        <el-table-column prop="name" label="姓名"></el-table-column>
        <el-table-column prop="col_name" label="学院"></el-table-column>
        <el-table-column prop="foundtime" label="创建时间"></el-table-column>
        <el-table-column prop="leadername" label="团长"></el-table-column>
        <el-table-column prop="leadercontact" label="联系方式"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="text"
              @click="dialogFormVisible1 = true,form1=tableData[scope.$index]"
            >修改</el-button>
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
    <el-dialog title="修改信息" :visible.sync="dialogFormVisible1" width="25%" style="text-align:left">
      <el-form ref="form1" :model="form1" label-width="80px">
        <el-form-item label="ID">
          <el-input v-model="form1.orgid" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form1.name"></el-input>
        </el-form-item>
        <el-form-item label="学院">
          <el-select v-model="form1.collegeid" placeholder="请选择">
            <el-option
              v-for="item in college"
              :key="item.collegeid"
              :label="item.name"
              :value="item.collegeid"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="form1.foundtime"
            value-format="yyyy/MM/dd"
            type="date"
            placeholder="选择日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="团长">
          <el-input v-model="form1.leadername"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form1.leadercontact"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible1 = false,update()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加社团" :visible.sync="dialogFormVisible2" width="25%" style="text-align:left">
      <el-form ref="form" :model="form2" label-width="80px">
        <el-form-item label="ID">
          <el-input v-model="form2.orgid"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form2.name"></el-input>
        </el-form-item>
        <el-form-item label="学院">
          <el-select v-model="form2.collegeid" placeholder="请选择">
            <el-option
              v-for="item in college"
              :key="item.collegeid"
              :label="item.name"
              :value="item.collegeid"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="创建时间">
          <el-date-picker
            v-model="form2.foundtime"
            value-format="yyyy/MM/dd"
            type="date"
            placeholder="选择日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="团长">
          <el-input v-model="form2.leadername"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form2.leadercontact"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible2 = false,add()">确 定</el-button>
      </div>
    </el-dialog>
  </el-container>
</template>

<script>
export default {
  created: function() {
    var _this = this;
    this.$options.methods.post(1, 10, _this);
    axios({
      url: "/api/getAllCollege",
      method: "post",
      params: {}
    }).then(res => {
      console.log(res.data);
      this.college = res.data.data;
    });
  },
  data() {
    return {
      tableData: [],
      form1: {
        img: "",
        password: "17718260001",
        foundtime: "2019/10/10",
        leadername: "SilverRice",
        leadercontact: "15055412599",
        collegeid: null,
        name: "社团1",
        id: 1,
        col_name: "学院0",
        message: "",
        orgid: "17718260001"
      },
      form2: {
        img: "",
        password: "",
        foundtime: "",
        leadername: "",
        leadercontact: "",
        collegeid: null,
        name: "",
        id: 1,
        col_name: "",
        message: "",
        orgid: ""
      },
      currentPage: 1,
      input: "",
      dialogFormVisible1: false,
      dialogFormVisible2: false,
      college: [],
      pageSize: 10,
      count: 10
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
    add() {
      // axios({
      //   headers: {
      //     "Content-Type": "application/json;charset=UTF-8"
      //   },
      //   url: "/api/admin/addOrgnization",
      //   method: "post",
      //   data: JSON.stringify(this.form2)
      // }).then(res => {
      //   console.log(res.data);
      // });
      console.log(this.form2);
    },
    update() {
      axios({
        headers: {
          "Content-Type": "application/json;charset=UTF-8"
        },
        url: "/api/admin/modifyStudent",
        method: "post",
        data: JSON.stringify(this.form1)
      }).then(res => {
        console.log(res.data);
      });
      console.log(form1);
    },
    post(page, num, _this) {
      axios({
        url: "/api/admin/getAllOrgnization",
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

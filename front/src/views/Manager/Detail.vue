<template>
  <el-container>
    <el-header>
      <el-page-header @back="goBack" content="活动发布" style="line-height:60px;"></el-page-header>
      
    </el-header>
    <el-main style="overflow-y:scroll;text-align:center;">
      <el-form ref="form" :model="form" label-width="100px" style="width:90%;padding-top:20px;">
        <el-form-item label="活动名称">
          <el-col :span="10">
            <el-input v-model="form.name" size="mini"></el-input>
          </el-col>
          <el-col :span="8">
            <el-select v-model="form.type" placeholder="活动类型" size="mini">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="活动时间">
          <el-col :span="5">
            <el-date-picker
              size="mini"
              type="date"
              placeholder="选择开始日期"
              v-model="form.starttime"
              style="width: 100%;"
            ></el-date-picker>
          </el-col>
          <el-col class="line" :span="1">-</el-col>
          <el-col :span="5">
            <el-date-picker
              type="date"
              size="mini"
              placeholder="选择报名截止日期"
              v-model="form.endtime"
              style="width: 100%;"
            ></el-date-picker>
          </el-col>
        </el-form-item>

        <el-form-item label="活动图片" style="text-align:left;">
          <img :src="imgurl" style="width:100%;height:300px;" />
          <input @change="getImgBase()" type="file"/>
        </el-form-item>
        <el-form-item label="活动描述">
          <el-input
            type="textarea"
            placeholder="请输入内容"
            v-model="form.message"
            rows="10"
            show-word-limit
          ></el-input>
        </el-form-item>
        <el-form-item label="参赛标准">
          <el-col class="line" :span="3">最大参数人数</el-col>
          <el-col :span="3">
            <el-input v-model="form.max" placeholder="请输入最大人数" size="mini"></el-input>
          </el-col>
          <el-col :span="18">
            <el-switch
              v-model="form.isteam"
              active-color="#13ce66"
              active-text="团队赛"
              inactive-text="个人赛"
            ></el-switch>
          </el-col>

          <div v-show="form.isteam">
            <el-col class="line" :span="2">团队人数</el-col>
            <el-col :span="5">
              <el-input-number
                v-model="form.tmin"
                size="mini"
                controls-position="right"
                :min="1"
                :max="20"
              ></el-input-number>
            </el-col>
            <el-col class="line" :span="1">-</el-col>
            <el-col :span="5">
              <el-input-number
                v-model="form.tmax"
                size="mini"
                controls-position="right"
                :min="1"
                :max="20"
              ></el-input-number>
            </el-col>
          </div>
        </el-form-item>
        <el-form-item label="奖项设置" style="text-align:left">
          <el-row v-for="(item,index) in form.score" :key="index">
            <el-col :span="4">
              <el-input v-model="item.level" placeholder size="mini"></el-input>
            </el-col>
            <el-col :span="4">
              <el-select v-model="item.type" placeholder="请选择" size="mini">
                <el-option v-for="p in part" :key="p.value" :label="p.label" :value="p.value"></el-option>
              </el-select>
            </el-col>
            <el-col :span="4">
              <el-input v-model="item.score" placeholder size="mini"></el-input>
            </el-col>
            <el-col :span="4">
              <el-button type="danger" icon="el-icon-delete" size="mini" circle @click="del(index)"></el-button>
            </el-col>
          </el-row>
          <el-button type="primary" round size="mini" @click="add()">添加</el-button>
        </el-form-item>
        <el-button type="primary" size="mini" style="float:right;margin-bottom:20px;">提交审核</el-button>
      </el-form>
    </el-main>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      form: {
        name: "计算机设计大赛",
        starttime: "",
        endtime: "",
        type: "",
        isteam: false,
        message: "",
        tmax: 5,
        tmin: 3,
        max: 100,
        score: [
          {
            level: "参与奖",
            score: 0.2,
            type: "A"
          },
          {
            level: "参与奖",
            score: 0.2,
            type: "A"
          }
        ]
      },
      imgurl: "",
      options: [
        {
          value: "学科竞赛",
          label: "学科竞赛"
        },
        {
          value: "文体竞赛",
          label: "文体竞赛"
        }
      ],
      part: [
        {
          value: "A",
          label: "A"
        },
        {
          value: "B",
          label: "B"
        }
      ]
    };
  },
  methods: {
    goBack() {
      this.$router.go(-1); //返回上一层
    },
    getImgBase() {
      let event = event || window.event;
      let file = event.target.files[0];
      let dataURL = window.URL.createObjectURL(file);
      this.imgurl = dataURL;
    },
    add() {
      let sc = {
        level: "参与奖",
        score: 0,
        type: this.part[0].value
      };
      this.form.score.push(sc);
    },
    del(index) {
      this.form.score.splice(index, 1);
    }
  }
};
</script>










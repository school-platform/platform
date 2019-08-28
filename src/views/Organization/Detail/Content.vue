<template>
  <el-form ref="form" :model="form" label-width="80px" style="width:500px;">
    <el-form-item label="活动名称">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="活动时间">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="活动图片">
      <img :src="imgurl"  style="width:300px;height:300px;" />
      <input @change="getImgBase()" type="file" />
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      form: {
        name:"计算机设计大赛",
        starttime:"2001-1-1-",
        endtime:"2001-2-3",
        type:"学科竞赛",
        isteam:true,
        message:"实打实大苏打",
        tmax:5,
        tmin:3,
        max:100,
        score:[{
          level:"参与奖",
          score:0.2,
          type:"A"
        }]
      },
      imgurl: ""
    };
  },
  methods: {
    getImgBase() {
      let event = event || window.event;
      let file = event.target.files[0];
      let dataURL = window.URL.createObjectURL(file);
      this.imgurl=dataURL;
    }
  }
};
</script>










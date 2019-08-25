<template>
  <div id="app">
    <app-header></app-header>
    <router-view></router-view>
    <app-footer></app-footer>

    <el-backtop :right="240" :bottom="100" title="回到顶部"></el-backtop>
  </div>
</template>

<script>
import Header from "./components/Header";
import Footer from "./components/Footer";

export default {
  name: "app",
  components: {
    "appHeader":Header,
    "appFooter":Footer
  },
  data() {
    return {};
  }
};
</script>



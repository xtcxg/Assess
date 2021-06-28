<template>
  <el-container class="container">
    <el-header style="background: rgba(255,140,0,0.76)">
      <Title/>
    </el-header>
    <el-main style="background: bisque">
      <el-row type="flex" justify="center">
<!--        <el-col :span="2" style="background: gainsboro;width: auto">-->
<!--          left-->
<!--        </el-col>-->
        <el-col :span="20" style="min-width: 1200px;height: 100%">
          <Portal/>
        </el-col>
<!--        <el-col :span="2" style="background: gold;width: auto">-->
<!--          right-->
<!--        </el-col>-->
      </el-row>

    </el-main>
  </el-container>

</template>

<script>
import Portal from './components/Portal.vue';
import Title from "@/components/Title";

export default {
  name: 'app',
  components: {
    Portal,
    Title
  }
}
</script>

<style>
.app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
.container {
  /*width: 1200px;*/
  min-height: 500px;
  min-width: 1200px;
  /*display: block;*/
  /*position: relative;*/
  text-align: left;

  background: aquamarine;
  /*left: 50%;*/
  /*transform: translate(-50%, 0%);*/
}
</style>

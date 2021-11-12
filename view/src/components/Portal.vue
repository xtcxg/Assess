<template>
  <div id="app" class="app">
    <el-input class="search-input" size="medium" id="search" v-model="searchData" >
      <template #suffix>
        <i class="el-icon-error el-input__icon" @click="searchData = ''"></i>
      </template>
    </el-input> <el-button type="primary" icon="el-icon-search" size="medium" @click="search()" circle/>
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: "Portal.vue",
  setup(){
    return{
      searchData:ref("")
    }
  },
  data(){
    return{
    }
  },
  methods:{
    search() {
      // console.log(this.addrs.product.selectAll);
      // const data = await fetch(
      //     'https://hn.algolia.com/api/v1/search?query=vue'
      // ).then(rsp => rsp.json())
      // console.log(data);

      let params = {
        page:0,
        size:20,
        data: "lenvo"
      }
      this.$axios.post(this.addrs.product.selectAll,params,)
          .then(res => {
            console.log(res.data);
          })
          .catch(error => {
            console.log(error);
          });
    }
  }
})
</script>

<style scoped>
.app{
  text-align: left;
  background: antiquewhite;
}
.search-input {
  width: 220px;
  border-radius: 30px;
}
</style>
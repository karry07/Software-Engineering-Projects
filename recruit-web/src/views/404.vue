<!--404出错界面-->
<template>
  <div class="container">
    <img class="backgroud" src="@/assets/img/404error.png" alt>
    <div class="text">你的页面飞出去了</div>
    <el-button  class="text" @click="back()" type="text" icon="el-icon-back">返回</el-button>
  </div>
</template>

<script>
export default {
  methods:{
    back () {
      // 判断是否有上一页，如果有则返回上一页，否则返回首页
      window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
    }
  }
};
</script>

<style lang="scss" scoped>
.container {
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  text-align: center;
  .backgroud {
    width: 500px;
    height: 400px;
    margin-top: 6%;
  }
  .text {
    font-size: 24px;
    font-weight: bold;
    color: #7ac9e7;
    margin-top: 10px;
  }
}
</style>


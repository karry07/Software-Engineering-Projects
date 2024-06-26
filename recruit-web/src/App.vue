<template>
  <div id="app" class="">
<!--    根据当前路由渲染不同的组件，文件无需修改-->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "app",
};
</script>

<style lang="scss" scoped>
*{
  padding: 0;
  margin:0;
}
html,body{
  width: 100%;
  height:100%;
}
#app{
  height:100%;
}
</style>

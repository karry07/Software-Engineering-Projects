<!--面包屑导航-->
<template>
  <el-breadcrumb class="breadcrumbs" separator="/">
    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
  </el-breadcrumb>
</template>
<script>
export default {};
</script>
<style lang="scss" scoped>
.breadcrumbs {
  padding: 20px;
  background: #ffffff;
}
</style>

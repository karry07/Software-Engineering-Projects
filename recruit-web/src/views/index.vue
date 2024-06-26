<!--我们的后台主体框架-->
<template>
  <el-container>
<!--    页面头部-->
    <index-header></index-header>
    <el-container>
<!--      页面侧栏-->
      <index-aside></index-aside>
<!--      页面主体内容-->
      <index-main></index-main>
    </el-container>
  </el-container>
</template>
<script>
import IndexHeader from '@/components/index/IndexHeader'
import IndexAside from '@/components/index/IndexAsideStatic'
import IndexMain from '@/components/index/IndexMain'

export default {
  components: {
    IndexAside,
    IndexHeader,
    IndexMain
  }
}
</script>

<style lang="scss" scoped>
// 铺满全屏
.el-container {
  position: absolute;
  width: 100%;
  top: 0;
  left: 0;
  bottom: 0;
}
</style>

<!--刚进入的首页-->
<template>
  <div class="content">
    <el-col :span="15">
      <div class="box">
        <div class="text main-text">欢迎来到 {{ this.$project.projectName }}</div>
        <div class="fake-rating">
          <span class="label">用户满意度：</span>
          <span class="star">⭐⭐⭐⭐⭐</span>
        </div>
      </div>
    </el-col>

    <el-col :span="13">
      <div class="box">
        <div class="notice">
          <div class="title">公告栏</div>
          <ul>
            <li>平台用户累积突破10万</li>
            <li>设计者：高铠炜 李凯玄 王天宇 赵新苑</li>
          </ul>
        </div>
      </div>
    </el-col>

  </div>

</template>

<script>
import router from '@/router/router-static';

export default {
  mounted() {
    this.init();
  },
  methods: {
    init() {
      if (this.$storage.get('Token')) {
        this.$http({
          url: `${this.$storage.get('sessionTable')}/session`,
          method: 'get',
        }).then(({data}) => {
          if (data && data.code !== 0) {
            router.push({name: 'login'});
          }
        });
      } else {
        router.push({name: 'login'});
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.content {
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  min-height: 500px;
  text-align: center;
  background-image: url('../assets/img/homebackground.jpeg');
  background-size: cover;
  position: relative;
  justify-content: space-between;

  .header {
    width: 100%;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.8);
  }

  .box {
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.8);
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
    text-align: center;
  }

  .main-text {
    font-size: 38px;
    font-weight: bold;
    margin-top: 8%;
  }

  .fake-rating {
    margin-top: 20px;
    font-size: 20px;
  }

  .fake-rating .label {
    font-weight: bold;
  }

  .fake-rating .star {
    color: #f1a009; /* 黄色 */

  }

  .notice {
    text-align: left;
  }

  .notice .title {
    font-size: 30px;
    font-weight: bold;
    margin-bottom: 8px;
  }

  .notice ul {
    list-style-type: none;
    padding-left: 0;
  }

  .notice ul li {
    font-size: 30px;
    margin-bottom: 20px;
  }
}
</style>

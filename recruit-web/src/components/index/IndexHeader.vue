<!--页头部-->
<template>
  <el-header>
    <el-menu mode="horizontal">
      <div class="fl title">{{ this.$project.projectName }}</div>
      <div class="fr" style="display:flex;">
        <el-submenu index="1">
          <template slot="title">设置</template>
          <el-menu-item index="1-1">
            <div @click="openDialog">修改账号密码</div>
          </el-menu-item>
        </el-submenu>
        <el-menu-item index="3">
          <div>{{ this.$storage.get('role') }} {{ this.$storage.get('adminName') }}</div>
        </el-menu-item>
        <el-menu-item @click="onLogout" index="2">
          <div class="logout">退出登录</div>
        </el-menu-item>
      </div>
      <el-dialog title="修改密码" :visible.sync="dialogVisible" width="40%" append-to-body>
        <el-form ref="ruleForm" :model="ruleForm" label-width="80px">
          <el-form-item label="原密码">
            <el-input v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item label="新密码">
            <el-input v-model="ruleForm.newpassword"></el-input>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input v-model="ruleForm.repassword"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
      </el-dialog>
    </el-menu>

  </el-header>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      ruleForm: {},
      currentSidebarName: '首页'
    };
  },
  mounted() {

  },
  methods: {
    onLogout() {
      this.$storage.remove("Token");
      this.$router.replace({name: "login"});
    },
    onUpdateHandler() {
      this;
    },
    openDialog() {
      this.dialogVisible = true;
    }
  }
};
</script>
<style lang="scss" scoped>
.el-header .fr {
  float: right;
}

.el-header .fl {
  float: left;
}

.el-header {
  width: 100%;
  color: #333;
  text-align: center;
  line-height: 60px;
  padding: 0;
  z-index: 99;
}

.logo {
  width: 60px;
  height: 60px;
  margin-left: 70px;
}

.avator {
  width: 40px;
  height: 40px;
  background: #ffffff;
  border-radius: 50%;
}

.title {
  background: linear-gradient(to right, red, blue);
  -webkit-background-clip: text;
  color: transparent;
  position: relative;
  display: inline-block;
  font-size: 30px;
}

.title::before {
  content: "\f1fc"; /* Font Awesome icon */
  font-family: "Font Awesome 5 Free";
  position: absolute;
  left: -20px;
}

.title:hover {
  animation: shake 0.5s;
  animation-iteration-count: infinite;
}

@keyframes shake {
  0% {
    transform: translate(1px, 1px) rotate(0deg);
  }
  10% {
    transform: translate(-1px, -2px) rotate(-1deg);
  }
  20% {
    transform: translate(-3px, 0px) rotate(1deg);
  }
  30% {
    transform: translate(3px, 2px) rotate(0deg);
  }
  40% {
    transform: translate(1px, -1px) rotate(1deg);
  }
  50% {
    transform: translate(-1px, 2px) rotate(-1deg);
  }
  60% {
    transform: translate(-3px, 1px) rotate(0deg);
  }
  70% {
    transform: translate(3px, 1px) rotate(-1deg);
  }
  80% {
    transform: translate(-1px, -1px) rotate(1deg);
  }
  90% {
    transform: translate(1px, 2px) rotate(0deg);
  }
  100% {
    transform: translate(1px, -2px) rotate(-1deg);
  }
}

.logout {
  font-size: 18px;
  color: white;
  background-color: #f1ae8a;
  border: 1px solid lightblue;
  //padding: 10px;
}

.logout:hover {
  background-color: #f1d87b;
}

.el-menu-item {
  padding: 0 !important;
  width: 150px !important;
}

.el-button {
  background-color: #f1ae8a;
  color: white;
}

.el-submenu {
  width: 120px;
}
</style>

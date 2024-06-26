<!--后台登录页面-->
<template>
  <div>
    <img class="bg" src="@/assets/img/loginbackground.png">
    <el-form :model="rulesForm" :rules="rules" ref="rulesForm" class="login-form">
      <h1 class="title">在线简历投递平台后台管理系统登录</h1>
      <hr />
      <el-form-item label="账号" prop="username">
        <el-input type="text" v-model="rulesForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="rulesForm.password"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="secureword">
        <div class="input-and-code">
          <el-input
              type="text"
              v-model="inputCode"
              placeholder="请输入您的验证码"
          />
          <div @click="refreshCode()">
            <!--验证码组件-->
            <SecurityCode :identifyCode="identifyCode"></SecurityCode>
          </div>
        </div>
      </el-form-item>

      <el-form-item label="角色" prop="role">
        <el-select v-model="rulesForm.role" placeholder="请选择角色">
          <el-option
              v-for="item in menus"
              :key="item.roleName"
              :label="item.roleName"
              :value="item.roleName">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item class="register-links">
        <p class="register-prompt">没有账号？立刻注册</p>
        <a class="register-link" href="javascript:void(0)" @click="register('generaluser')">求职者注册</a>
        <a class="register-link" href="javascript:void(0)" @click="register('companyinfo')">企业注册</a>
      </el-form-item>
      <el-button @click="login()" class="btn-login" type="primary">登录</el-button>
    </el-form>
  </div>
</template>
<script>
import menu from '@/utils/menu'
import SecurityCode from "@/components/Sidentify.vue";


export default {
  data() {
    return {
      identifyCodeType: "1234567890",
      identifyCode: "",
      inputCode: "", //text框输入的验证码
      rulesForm: {
        username: "",
        password: "",
        role: ""
      },
      menus: [],
      tableName: "",
      rules: {
        username: [{required: true, message: "请输入账号", trigger: "blur"}],
        password: [{required: true, message: "请输入密码", trigger: "blur"}],
        role: [{required: true, message: "请选择角色", trigger: "blur"}]
      }
    };
  },
  components: {
    SecurityCode
  },
  mounted() {
    let menus = menu.list();
    this.menus = menus;
  },
  methods: {
    //验证码规则
    getSubmitData() {
      if (this.inputCode == "") {
        alert("请输入验证码");
        return false;
      }
      if (this.identifyCode !== this.inputCode) {
        this.inputCode = "";
        this.refreshCode();
        alert("请输入正确的验证码!");
        return false;
      } else {
        // this.$message({
        //   message: "验证码验证成功",
        //   type: "success",
        // });
        return true;
      }
    },
    //验证码
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    },
    //初始化验证码
    refreshCode() {
      this.identifyCode = ""; //输入框置空
      this.makeCode(this.identifyCodeType, 4); //验证码长度为4
    },
    //随机切换验证码
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode +=
            this.identifyCodeType[
                this.randomNum(0, this.identifyCodeType.length)
                ];
      }
      // console.log(this.identifyCode);
    },
    // 注册
    register(tableName) {
      this.$storage.set("loginTable", tableName);
      this.$router.push({path: '/register'})
    },
    // 登录
    login() {
      this.$refs["rulesForm"].validate(valid => {
        let menus = this.menus;
        for (let i = 0; i < menus.length; i++) {
          if (menus[i].roleName == this.rulesForm.role) {
            this.tableName = menus[i].tableName
          }
        }
        // 登录验证
        if (valid) {
          if (!this.getSubmitData()) {
            return;
          }
          this.$http({
            url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
            method: "post"
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$storage.set("Token", data.token);
              this.$storage.set("role", this.rulesForm.role);
              this.$storage.set("sessionTable", this.tableName);
              this.$storage.set("adminName", this.rulesForm.username);
              this.$router.replace({path: "/index/"});
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
hr {
  border: none;
  border-top: 1px solid #ccc; /* 设置颜色 */
  margin: 2px 0; /* 设置上下边距 */
}
.title {
  font-size: 22px;
  font-weight: bold;
  color: #11d53f;
  font-family: 'KaiTi';
  text-align: center;
}

.input-and-code {
  display: flex;
  align-items: center;
}

.input-and-code > div {
  margin-left: 10px;
}

.role-selection {
  margin: 20px 0;
  background-color: #f2f2f2;
  padding: 10px;
  border-radius: 5px;
}

.role-radio {
  display: block;
  margin: 10px 0;
  font-size: 18px;
  color: #333;
}

.register-links {
  margin: 20px 0;
  background-color: #e9effa;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
   text-align: center;
}

.register-link {
  display: inline-block;
  margin-right: 10px;
  font-size: 18px;
  color: #007BFF;
  text-decoration: none;
  transition: color 0.3s ease;

}

.register-link:hover {
  color: #0056b3;
}
.register-prompt {
  font-size: 10px;
  color: #666;
  text-align: center;
  margin-top: 10px;
}
</style>
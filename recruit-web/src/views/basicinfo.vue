<!--基本信息页面-->
<template>
  <div id="app">
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      label-width="80px"
      class="login-form1"
    >
      <h1 class="title">个人信息修改</h1>
      <el-col :span="24">
        <el-form-item v-if="flag=='generaluser'" label="姓名" prop="fullname">
          <el-input v-model="ruleForm.fullname" placeholder="姓名" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='generaluser'" label="用户名" prop="username">
          <el-input v-model="ruleForm.username" placeholder="用户名" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='generaluser'" label="性别" prop="gender">
          <el-select v-model="ruleForm.gender" placeholder="请选择性别">
            <el-option v-for="(item, index) in generalusergenderOptions" :key="index" :label="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='generaluser'" label="电话" prop="phone">
          <el-input v-model="ruleForm.phone" placeholder="电话" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='generaluser'" label="邮箱" prop="email">
          <el-input v-model="ruleForm.email" placeholder="邮箱" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='companyinfo'" label="企业编号" prop="companynumber">
          <el-input v-model="ruleForm.companynumber" placeholder="企业编号" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='companyinfo'" label="企业名称" prop="companyname">
          <el-input v-model="ruleForm.companyname" placeholder="企业名称" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='companyinfo'" label="负责人" prop="personincharge">
          <el-input v-model="ruleForm.personincharge" placeholder="负责人" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='companyinfo'" label="联系电话" prop="contactphone">
          <el-input v-model="ruleForm.contactphone" placeholder="联系电话" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='companyinfo'" label="企业邮箱" prop="companyemail">
          <el-input v-model="ruleForm.companyemail" placeholder="企业邮箱" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-col :span="24">
        <el-form-item v-if="flag=='companyinfo'" label="企业介绍" prop="companyintroduction">
          <el-input v-model="ruleForm.companyintroduction" placeholder="企业介绍" clearable></el-input>
        </el-form-item>
      </el-col>
      <el-form-item v-if="flag=='users'" label="用户名" prop="username">
        <el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
      </el-form-item>
      <el-col :span="24">
        <el-form-item>
          <el-button type="primary" @click="onUpdateHandler">修 改</el-button>
        </el-form-item>
      </el-col>
    </el-form>
  </div>
</template>

<script>
// 校验
import { isEmail, isMobile} from "@/utils/validate";

export default {
  data() {
    return {
      ruleForm: {},
      flag: '',
      usersFlag: false,
      generalusergenderOptions: [],
    };
  },
  mounted() {
    var table = this.$storage.get("sessionTable");
    this.flag = table;
    this.$http({
      url: `${this.$storage.get("sessionTable")}/session`,
      method: "get"
    }).then(({data}) => {
      if (data && data.code === 0) {
        this.ruleForm = data.data;
      } else {
        this.$message.error(data.msg);
      }
    });
    this.generalusergenderOptions = "男,女".split(',')
  },
  methods: {
    onUpdateHandler() {
      if ((!this.ruleForm.username) && 'generaluser' == this.flag) {
        this.$message.error('用户名不能为空');
        return
      }
      if ((!this.ruleForm.fullname) && 'generaluser' == this.flag) {
        this.$message.error('姓名不能为空');
        return
      }
      if ('generaluser' == this.flag && this.ruleForm.phone && (!isMobile(this.ruleForm.phone))) {
        this.$message.error(`电话应输入电话格式`);
        return
      }
      if ('companyinfo' == this.flag && this.ruleForm.contactphone && (!isMobile(this.ruleForm.contactphone))) {
        this.$message.error(`联系电话应输入电话格式`);
        return
      }
      if ('companyinfo' == this.flag && this.ruleForm.companyemail && (!isEmail(this.ruleForm.companyemail))) {
        this.$message.error(`企业邮箱应输入邮箱格式`);
        return
      }
      this.$http({
        url: `${this.$storage.get("sessionTable")}/update`,
        method: "post",
        data: this.ruleForm
      }).then(({data}) => {
        // 如果服务器返回成功，更新 localStorage 中的 adminName
        localStorage.setItem('adminName', this.ruleForm.username);
        if (data && data.code === 0) {
          this.$message({
            message: "修改信息成功",
            type: "success",
            duration: 1500,
            onClose: () => {
              this.$router.go(0);
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
body, html {
  height: 100%;
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

#app {
  background-image: url('../assets/img/basicinfobackground.png');
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.login-form {
  background: rgba(255, 255, 255, 0.9);
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input, .el-select, .el-button {
  width: 100%;
  border-radius: 5px;
}

.el-input {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
}

.el-input:hover {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.el-button {
  margin-top: 10px;
}

.title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
}
.login-form1 {
position: absolute;
top: 50%;
right: 50%;
transform: translate(73%, -45%);
width: 30%;
padding: 20px;
// height: 50%;
font-size: 18px;
font-weight: bold;
background: #FFF
}
</style>

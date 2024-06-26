<!--注册界面-->
<template>
  <div>
    <img class="bg" src="@/assets/img/loginbackground.png">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" class="login-form">
      <h1 class="title">在线简历投递平台后台管理系统注册</h1>
      <el-form-item v-if="tableName=='generaluser'" label="用户名" prop="generaluserusername">
        <el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='generaluser'" label="姓名" prop="generaluserfullname">
        <el-input v-model="ruleForm.fullname" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='generaluser'" label="密码" prop="generaluserpassword">
        <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName == 'generaluser'" label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="ruleForm.confirmPassword" placeholder="请确认密码"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='generaluser'" label="电话" prop="generaluserphone">
        <el-input v-model="ruleForm.phone" placeholder="请输入电话"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='generaluser'" label="邮箱" prop="generaluseremail">
        <el-input v-model="ruleForm.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='companyinfo'" label="企业账号" prop="companyinfocompanynumber">
        <el-input v-model="ruleForm.companynumber" placeholder="请输入企业账号"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='companyinfo'" label="密码" prop="companyinfopassword">
        <el-input type="password" v-model="ruleForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName == 'companyinfo'" label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="ruleForm.confirmPassword1" placeholder="请确认密码"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='companyinfo'" label="企业名称" prop="companyinfocompanyname">
        <el-input v-model="ruleForm.companyname" placeholder="请输入企业名称"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='companyinfo'" label="负责人" prop="companyinfopersonincharge">
        <el-input v-model="ruleForm.personincharge" placeholder="请输入负责人"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='companyinfo'" label="联系电话" prop="companyinfocontactphone">
        <el-input v-model="ruleForm.contactphone" placeholder="请输入联系电话"></el-input>
      </el-form-item>
      <el-form-item v-if="tableName=='companyinfo'" label="企业邮箱" prop="companyinfocompanyemail">
        <el-input v-model="ruleForm.companyemail" placeholder="请输入企业邮箱"></el-input>
      </el-form-item>
      <el-button @click="login()" class="btn-login" type="primary">注册</el-button>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      ruleForm: {
        password: "",
        confirmPassword: "",
         confirmPassword1: ""
      },
      tableName: "",
      rules: {},
    };
  },
  mounted() {
    let table = this.$storage.get("loginTable");
    this.tableName = table;
  },
  methods: {
    // 获取time
    getUUID() {
      return new Date().getTime();
    },
    // 注册
    login() {
      if ((!this.ruleForm.username) && `generaluser` == this.tableName) {
        this.$message.error(`用户名不能为空`);
        return
      }
      if ((!this.ruleForm.fullname) && `generaluser` == this.tableName) {
        this.$message.error(`姓名不能为空`);
        return
      }
      if ((!this.ruleForm.password) && `generaluser` == this.tableName) {
        this.$message.error(`密码不能为空`);
        return
      }
      if (this.ruleForm.password !== this.ruleForm.confirmPassword && `generaluser` == this.tableName) {
        this.$message.error('两次输入的密码不一致');
        return;
      }
      if (`generaluser` == this.tableName && this.ruleForm.phone && (!this.$validate.isMobile(this.ruleForm.phone))) {
        this.$message.error(`电话应输入电话格式`);
        return
      }
      if ((!this.ruleForm.companynumber) && `companyinfo` == this.tableName) {
        this.$message.error(`企业用户名不能为空`);
        return
      }
      if ((!this.ruleForm.companyname) && `companyinfo` == this.tableName) {
        this.$message.error(`企业名称不能为空`);
        return
      }
      if (this.ruleForm.password !== this.ruleForm.confirmPassword1 && `companyinfo` == this.tableName) {
        this.$message.error('两次输入的密码不一致');
        return;
      }
      if (`companyinfo` == this.tableName && this.ruleForm.contactphone && (!this.$validate.isMobile(this.ruleForm.contactphone))) {
        this.$message.error(`联系电话应输入电话格式`);
        return
      }
      if (`companyinfo` == this.tableName && this.ruleForm.companyemail && (!this.$validate.isEmail(this.ruleForm.companyemail))) {
        this.$message.error(`企业邮箱应输入邮件格式`);
        return
      }
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: `${this.tableName}/register`,
            method: "post",
            data: this.ruleForm
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$router.replace({path: "/login"});
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
.title {
  font-size: 24px;
  font-weight: bold;
  color: #a80dda;
  font-family: 'KaiTi';
  text-align: center;
}

</style>

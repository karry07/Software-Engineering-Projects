<template>
  <div>
    <el-form
        class="detail-form-content"
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="80px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="企业编号" prop="companynumber">
            <el-input v-model="ruleForm.companynumber"
                      placeholder="企业编号" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.companynumber" label="企业编号" prop="companynumber">
              <el-input v-model="ruleForm.companynumber"
                        placeholder="企业编号" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="密码" prop="password">
            <el-input v-model="ruleForm.password"
                      placeholder="密码" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.password" label="密码" prop="password">
              <el-input v-model="ruleForm.password"
                        placeholder="密码" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="企业名称" prop="companyname">
            <el-input v-model="ruleForm.companyname"
                      placeholder="企业名称" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.companyname" label="企业名称" prop="companyname">
              <el-input v-model="ruleForm.companyname"
                        placeholder="企业名称" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="负责人" prop="personincharge">
            <el-input v-model="ruleForm.personincharge"
                      placeholder="负责人" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.personincharge" label="负责人" prop="personincharge">
              <el-input v-model="ruleForm.personincharge"
                        placeholder="负责人" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="联系电话" prop="contactphone">
            <el-input v-model="ruleForm.contactphone"
                      placeholder="联系电话" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.contactphone" label="联系电话" prop="contactphone">
              <el-input v-model="ruleForm.contactphone"
                        placeholder="联系电话" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="企业邮箱" prop="companyemail">
            <el-input v-model="ruleForm.companyemail"
                      placeholder="企业邮箱" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.companyemail" label="企业邮箱" prop="companyemail">
              <el-input v-model="ruleForm.companyemail"
                        placeholder="企业邮箱" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="企业介绍" prop="companyintroduction">
            <el-input
                style="min-width: 200px; max-width: 600px;"
                type="textarea"
                :rows="8"
                placeholder="企业介绍"
                v-model="ruleForm.companyintroduction">
            </el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.companyintroduction" label="企业介绍" prop="companyintroduction">
              <span>{{ ruleForm.companyintroduction }}</span>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button v-if="type!='info'" type="primary" @click="onSubmit">提交</el-button>
        <el-button v-if="type!='info'" @click="back()">取消</el-button>
        <el-button v-if="type=='info'" @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
// 数字，邮件，手机，url，身份证校验
import {isNumber, isIntNumer, isEmail, isMobile, isURL, checkIdCard} from "@/utils/validate";

export default {
  data() {
    var validateIdCard = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!checkIdCard(value)) {
        callback(new Error("请输入正确的身份证号码"));
      } else {
        callback();
      }
    };
    var validateUrl = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isURL(value)) {
        callback(new Error("请输入正确的URL地址"));
      } else {
        callback();
      }
    };
    var validatePhone = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isMobile(value)) {
        callback(new Error("请输入正确的电话号码"));
      } else {
        callback();
      }
    };
    var validateEmail = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isEmail(value)) {
        callback(new Error("请输入正确的邮箱地址"));
      } else {
        callback();
      }
    };
    var validateNumber = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isNumber(value)) {
        callback(new Error("请输入数字"));
      } else {
        callback();
      }
    };
    var validateIntNumber = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isIntNumer(value)) {
        callback(new Error("请输入整数"));
      } else {
        callback();
      }
    };
    return {
      id: '',
      type: '',
      ruleForm: {
        companynumber: '',
        password: '',
        companyname: '',
        personincharge: '',
        contactphone: '',
        companyemail: '',
        companyintroduction: '',
      },
      rules: {
        companynumber: [],
        password: [],
        companyname: [],
        personincharge: [],
        contactphone: [
          {validator: validatePhone, trigger: 'blur'},
        ],
        companyemail: [
          {validator: validateEmail, trigger: 'blur'},
        ],
        companyintroduction: [],
      }
    };
  },
  props: ["parent"],
  computed: {},
  methods: {
    // 下载
    download(file) {
      window.open(`${file}`)
    },
    // 初始化
    init(id, type) {
      if (id) {
        this.id = id;
        this.type = type;
      }
      if (this.type == 'info' || this.type == 'else') {
        this.info(id);
      } else if (this.type == 'cross') {
        var obj = this.$storage.getObj('crossObj');
        for (var o in obj) {
          if (o == 'companynumber') {
            this.ruleForm.companynumber = obj[o];
            continue;
          }
          if (o == 'password') {
            this.ruleForm.password = obj[o];
            continue;
          }
          if (o == 'companyname') {
            this.ruleForm.companyname = obj[o];
            continue;
          }
          if (o == 'personincharge') {
            this.ruleForm.personincharge = obj[o];
            continue;
          }
          if (o == 'contactphone') {
            this.ruleForm.contactphone = obj[o];
            continue;
          }
          if (o == 'companyemail') {
            this.ruleForm.companyemail = obj[o];
            continue;
          }
          if (o == 'companyintroduction') {
            this.ruleForm.companyintroduction = obj[o];
            continue;
          }
        }
      }
    },
    // 多级联动参数
    info(id) {
      this.$http({
        url: `companyinfo/info/${id}`,
        method: "get"
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.ruleForm = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    // 提交
    onSubmit() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: `companyinfo/${!this.ruleForm.id ? "save" : "update"}`,
            method: "post",
            data: this.ruleForm
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.parent.showFlag = true;
                  this.parent.addOrUpdateFlag = false;
                  this.parent.qiyexinxiCrossAddOrUpdateFlag = false;
                  this.parent.search();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        }
      });
    },
    // 获取uuid
    getUUID() {
      return new Date().getTime();
    },
    // 返回
    back() {
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.qiyexinxiCrossAddOrUpdateFlag = false;
    },
  }
};
</script>
<style lang="scss" scoped>
.editor {
  height: 500px;
}
</style>

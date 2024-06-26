<template>
  <div>
    <el-form
        class="detail-form-content"
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="80px"
    >
    <!-- 企业编号、企业名称、负责人、联系电话、招聘岗位、用户名、姓名、电话、简历、是否审核、审核回复 -->
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
          <el-form-item v-if="type!='info'" label="招聘岗位" prop="position">
            <el-input v-model="ruleForm.position"
                      placeholder="招聘岗位" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.position" label="招聘岗位" prop="position">
              <el-input v-model="ruleForm.position"
                        placeholder="招聘岗位" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="用户名" prop="username">
            <el-input v-model="ruleForm.username"
                      placeholder="用户名" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.username" label="用户名" prop="username">
              <el-input v-model="ruleForm.username"
                        placeholder="用户名" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="姓名" prop="fullname">
            <el-input v-model="ruleForm.fullname"
                      placeholder="姓名" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.fullname" label="姓名" prop="fullname">
              <el-input v-model="ruleForm.fullname"
                        placeholder="姓名" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="电话" prop="phone">
            <el-input v-model="ruleForm.phone"
                      placeholder="电话" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.phone" label="电话" prop="phone">
              <el-input v-model="ruleForm.phone"
                        placeholder="电话" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="简历" prop="resume">
            <file-upload
                tip="点击上传简历"
                action="file/upload"
                :limit="1"
                :multiple="true"
                :fileUrls="ruleForm.resume?ruleForm.resume:''"
                @change="jianliUploadChange"
            ></file-upload>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.resume" label="简历" prop="resume">
              <el-button type="text" size="small" @click="download(ruleForm.resume)">下载</el-button>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <!-- 按钮-->
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
        companyname: '',
        personincharge: '',
        contactphone: '',
        position: '',
        username: '',
        fullname: '',
        phone: '',
        resume: '',
        ifaudit: '',
        auditresponse: '',
      },
      rules: {
        companynumber: [],
        companyname: [],
        personincharge: [],
        contactphone: [],
        position: [],
        username: [],
        fullname: [],
        phone: [],
        resume: [],
        ifaudit: [],
        auditresponse: [],
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
          if (o == 'position') {
            this.ruleForm.position = obj[o];
            continue;
          }
          if (o == 'username') {
            this.ruleForm.username = obj[o];
            continue;
          }
          if (o == 'fullname') {
            this.ruleForm.fullname = obj[o];
            continue;
          }
          if (o == 'phone') {
            this.ruleForm.phone = obj[o];
            continue;
          }
          if (o == 'resume') {
            this.ruleForm.resume = obj[o];
            continue;
          }
          if (o == 'ifaudit') {
            this.ruleForm.ifaudit = obj[o];
            continue;
          }
          if (o == 'auditresponse') {
            this.ruleForm.auditresponse = obj[o];
            continue;
          }
        }
      }
      // 获取用户信息
      this.$http({
        url: `${this.$storage.get('sessionTable')}/session`,
        method: "get"
      }).then(({data}) => {
        if (data && data.code === 0) {
          var json = data.data;
          this.ruleForm.username = json.username
          this.ruleForm.fullname = json.fullname
          this.ruleForm.phone = json.phone
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    // 多级联动参数
    info(id) {
      this.$http({
        url: `application/info/${id}`,
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
            url: `application/${!this.ruleForm.id ? "save" : "update"}`,
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
                  this.parent.yingpinxinxiCrossAddOrUpdateFlag = false;
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
      this.parent.yingpinxinxiCrossAddOrUpdateFlag = false;
    },
    jianliUploadChange(fileUrls) {
      this.ruleForm.resume = fileUrls;
    },
  }
};
</script>
<style lang="scss" scoped>
.editor {
  height: 500px;
}
</style>

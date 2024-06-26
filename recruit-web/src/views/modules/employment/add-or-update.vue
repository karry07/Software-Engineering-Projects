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
          <el-form-item v-if="type!='info'" label="企业账号" prop="companynumber">
            <el-input v-model="ruleForm.companynumber"
                      placeholder="企业账号" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.companynumber" label="企业账号" prop="companynumber">
              <el-input v-model="ruleForm.companynumber"
                        placeholder="企业账号" readonly></el-input>
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
          <el-form-item v-if="type!='info'" label="招聘人数" prop="recruitnum">
            <el-input v-model="ruleForm.recruitnum"
                      placeholder="招聘人数" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.recruitnum" label="招聘人数" prop="recruitnum">
              <el-input v-model="ruleForm.recruitnum"
                        placeholder="招聘人数" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="岗位类别" prop="jobcategory">
            <el-select v-model="ruleForm.jobcategory" placeholder="请选择岗位类别">
              <el-option
                  v-for="(item,index) in jobcategoryOptions"
                  v-bind:key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.jobcategory" label="岗位类别" prop="jobcategory">
              <el-input v-model="ruleForm.jobcategory"
                        placeholder="岗位类别" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="学历" prop="education">
            <el-select v-model="ruleForm.education" placeholder="请选择学历">
              <el-option
                  v-for="(item,index) in xueliOptions"
                  v-bind:key="index"
                  :label="item"
                  :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.education" label="学历" prop="education">
              <el-input v-model="ruleForm.education"
                        placeholder="学历" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="图片" prop="picture">
            <file-upload
                tip="点击上传图片"
                action="file/upload"
                :limit="3"
                :multiple="true"
                :fileUrls="ruleForm.picture?ruleForm.picture:''"
                @change="tupianUploadChange"
            ></file-upload>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.picture" label="图片" prop="picture">
              <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.picture.split(',')"
                   :src="item" width="100" height="100">
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="岗位要求" prop="demand">
            <el-input
                style="min-width: 200px; max-width: 600px;"
                type="textarea"
                :rows="8"
                placeholder="岗位要求"
                v-model="ruleForm.demand">
            </el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.demand" label="岗位要求" prop="demand">
              <span>{{ ruleForm.demand }}</span>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="工作职责" prop="duty">
            <editor
                style="min-width: 200px; max-width: 600px;"
                v-model="ruleForm.duty"
                class="editor"
                action="file/upload">
            </editor>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.duty" label="工作职责" prop="duty">
              <span v-html="ruleForm.duty"></span>
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
        companyname: '',
        personincharge: '',
        contactphone: '',
        position: '',
        recruitnum: '',
        jobcategory: '',
        education: '',
        picture: '',
        demand: '',
        duty: '',
        thumbsupnum: '',
        crazilynum: '',
      },
      jobcategoryOptions: [],
      xueliOptions: [],
      rules: {
        companynumber: [],
        companyname: [],
        personincharge: [],
        contactphone: [],
        position: [],
        recruitnum: [],
        jobcategory: [],
        education: [],
        picture: [],
        demand: [],
        duty: [],
        thumbsupnum: [
          {validator: validateIntNumber, trigger: 'blur'},
        ],
        crazilynum: [
          {validator: validateIntNumber, trigger: 'blur'},
        ],
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
          if (o == 'recruitnum') {
            this.ruleForm.recruitnum = obj[o];
            continue;
          }
          if (o == 'jobcategory') {
            this.ruleForm.jobcategory = obj[o];
            continue;
          }
          if (o == 'education') {
            this.ruleForm.education = obj[o];
            continue;
          }
          if (o == 'picture') {
            this.ruleForm.picture = obj[o];
            continue;
          }
          if (o == 'demand') {
            this.ruleForm.demand = obj[o];
            continue;
          }
          if (o == 'duty') {
            this.ruleForm.duty = obj[o];
            continue;
          }
          if (o == 'thumbsupnum') {
            this.ruleForm.thumbsupnum = obj[o];
            continue;
          }
          if (o == 'crazilynum') {
            this.ruleForm.crazilynum = obj[o];
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
          this.ruleForm.companynumber = json.companynumber
          this.ruleForm.companyname = json.companyname
          this.ruleForm.personincharge = json.personincharge
          this.ruleForm.contactphone = json.contactphone
        } else {
          this.$message.error(data.msg);
        }
      });
      this.$http({
        url: `option/job_category/jobcategory`,
        method: "get"
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.jobcategoryOptions = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });

      this.$http({
        url: `option/educationInfo/education`,
        method: "get"
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.xueliOptions = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });

    },
    // 多级联动参数
    info(id) {
      this.$http({
        url: `employment/info/${id}`,
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
            url: `employment/${!this.ruleForm.id ? "save" : "update"}`,
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
                  this.parent.zhaopinxinxiCrossAddOrUpdateFlag = false;
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
      this.parent.zhaopinxinxiCrossAddOrUpdateFlag = false;
    },
    tupianUploadChange(fileUrls) {
      this.ruleForm.picture = fileUrls;
    },
  }
};
</script>
<style lang="scss" scoped>
.editor {
  height: 500px;
}
</style>

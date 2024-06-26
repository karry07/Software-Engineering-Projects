<template>
  <!--简历导出-->
  <div>
    <el-form
        class="detail-form-content"
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="80px"
    >
      <el-row>
        <el-col :span="24">
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
        <el-col :span="24">
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
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="性别" prop="gender">
            <el-input v-model="ruleForm.gender"
                      placeholder="性别" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.gender" label="性别" prop="gender">
              <el-input v-model="ruleForm.gender"
                        placeholder="性别" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
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
          <el-form-item v-if="type!='info'" label="照片" prop="photo">
            <file-upload
                tip="点击上传照片"
                action="file/upload"
                :limit="3"
                :multiple="true"
                :fileUrls="ruleForm.photo?ruleForm.photo:''"
                @change="photoUploadChange"
            ></file-upload>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.photo" label="照片" prop="photo">
              <img style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.photo.split(',')"
                   :src="item" width="100" height="100">
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
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
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="简历" prop="resume">
            <file-upload
                tip="点击上传简历"
                action="file/upload"
                :limit="1"
                :multiple="true"
                :fileUrls="ruleForm.resume?ruleForm.resume:''"
                @change="resumeUploadChange"
            ></file-upload>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.resume" label="简历" prop="resume">
              <el-button type="text" size="small" @click="download(ruleForm.resume)">下载</el-button>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="工作经历" prop="workexperience">
            <el-input
                style="min-width: 200px; max-width: 600px;"
                type="textarea"
                :rows="8"
                placeholder="工作经历"
                v-model="ruleForm.workexperience">
            </el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.workexperience" label="工作经历" prop="workexperience">
              <span>{{ ruleForm.workexperience }}</span>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="个人基本情况" prop="personalinfo">
            <editor
                style="min-width: 200px; max-width: 600px;"
                v-model="ruleForm.personalinfo"
                class="editor"
                action="file/upload">
            </editor>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.personalinfo" label="个人基本情况" prop="personalinfo">
              <span v-html="ruleForm.personalinfo"></span>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <!--三个按钮-->
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
import pdfExport from "../jobseekerinfo/export-pdf";
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
        username: '',
        fullname: '',
        gender: '',
        phone: '',
        photo: '',
        education: '',
        jobcategory: '',
        resume: '',
        workexperience: '',
        personalinfo: '',
        thumbsupnum: '',
        crazilynum: '',
      },
      xueliOptions: [],
      jobcategoryOptions: [],
      rules: {
        username: [],
        fullname: [],
        gender: [],
        phone: [],
        photo: [],
        education: [],
        jobcategory: [],
        resume: [],
        workexperience: [],
        personalinfo: [],
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
          if (o == 'username') {
            this.ruleForm.username = obj[o];
            continue;
          }
          if (o == 'fullname') {
            this.ruleForm.fullname = obj[o];
            continue;
          }
          if (o == 'gender') {
            this.ruleForm.gender = obj[o];
            continue;
          }
          if (o == 'phone') {
            this.ruleForm.phone = obj[o];
            continue;
          }
          if (o == 'photo') {
            this.ruleForm.photo = obj[o];
            continue;
          }
          if (o == 'education') {
            this.ruleForm.education = obj[o];
            continue;
          }
          if (o == 'jobcategory') {
            this.ruleForm.jobcategory = obj[o];
            continue;
          }
          if (o == 'resume') {
            this.ruleForm.resume = obj[o];
            continue;
          }
          if (o == 'workexperience') {
            this.ruleForm.workexperience = obj[o];
            continue;
          }
          if (o == 'personalinfo') {
            this.ruleForm.personalinfo = obj[o];
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
          this.ruleForm.username = json.username
          this.ruleForm.fullname = json.fullname
          this.ruleForm.gender = json.gender
          this.ruleForm.phone = json.phone
        } else {
          this.$message.error(data.msg);
        }
      });
      this.$http({
        url: `option/educationinfo/education`,
        method: "get"
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.xueliOptions = data.data;
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

    },
    // 多级联动参数
    info(id) {
      this.$http({
        url: `jobseekerinfo/info/${id}`,
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
            url: `jobseekerinfo/${!this.ruleForm.id ? "save" : "update"}`,
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
                  this.parent.qiuzhizhexinxiCrossAddOrUpdateFlag = false;
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
      this.parent.qiuzhizhexinxiCrossAddOrUpdateFlag = false;
    },
    photoUploadChange(fileUrls) {
      this.ruleForm.photo = fileUrls;
    },
    resumeUploadChange(fileUrls) {
      this.ruleForm.resume = fileUrls;
    },
    exportResumePdf() {
    const elementId = "downResume"; // ID of the resume section
    const htmlTitle = "简历.pdf";
    pdfExport.exportPdf(elementId, htmlTitle);
  }
  }
};
</script>
<style lang="scss" scoped>
.editor {
  height: 500px;
}
</style>

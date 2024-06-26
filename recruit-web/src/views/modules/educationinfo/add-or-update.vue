<template>
  <div class="main-content">
    <el-form
      class="detail-form-content"
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="80px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="学历" prop="education">
            <el-input v-model="ruleForm.education" placeholder="学历" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.education" label="学历" prop="education">
              <el-input v-model="ruleForm.education" placeholder="学历" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="毕业院校" prop="institution">
            <el-input v-model="ruleForm.institution" placeholder="毕业院校" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.institution" label="毕业院校" prop="institution">
              <el-input v-model="ruleForm.institution" placeholder="毕业院校" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="专业领域" prop="fieldofstudy">
            <el-input v-model="ruleForm.fieldofstudy" placeholder="专业领域" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.fieldofstudy" label="专业领域" prop="fieldofstudy">
              <el-input v-model="ruleForm.fieldofstudy" placeholder="专业领域" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-form-item class="button-group">
        <el-button v-if="type!='info'" type="primary" @click="onSubmit">提交</el-button>
        <el-button v-if="type!='info'" @click="back">取消</el-button>
        <el-button v-if="type=='info'" @click="back">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { isNumber, isIntNumer, isEmail, isMobile, isURL, checkIdCard } from "@/utils/validate";

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
        education: '',
        institution: '',
        fieldofstudy: ''
      },
      rules: {
        education: [],
        institution: [],
        fieldofstudy: []
      }
    };
  },
  props: ["parent"],
  methods: {
    download(file) {
      window.open(`${file}`)
    },
    init(id, type) {
      if (id) {
        this.id = id;
        this.type = type;
      }
      if (this.type === 'info' || this.type === 'else') {
        this.info(id);
      } else if (this.type === 'cross') {
        var obj = this.$storage.getObj('crossObj');
        for (var o in obj) {
          if (o === 'education') {
            this.ruleForm.education = obj[o];
            continue;
          }
        }
      }
    },
    info(id) {
      this.$http({
        url: `educationinfo/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.ruleForm = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    onSubmit() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: `educationinfo/${!this.ruleForm.id ? "save" : "update"}`,
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
                  this.parent.educationInfoCrossAddOrUpdateFlag = false;
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
    getUUID() {
      return new Date().getTime();
    },
    back() {
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.educationInfoCrossAddOrUpdateFlag = false;
    },
  }
};
</script>

<style lang="scss" scoped>
.main-content {
  background: rgba(255, 255, 255, 0.9);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 20px auto;
}

.detail-form-content {
  background: #f9f9f9;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.el-form-item {
  margin-bottom: 20px;
}

.el-input {
  border-radius: 5px;
}

.button-group {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.el-button {
  border-radius: 20px;
  padding: 8px 20px;
}

.el-col {
  padding: 0 10px;
}
</style>

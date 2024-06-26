<template>
  <div>
    <!-- 详情表单 -->
    <el-form
        class="detail-form-content"
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="80px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="关联表id" prop="refid">
            <el-input v-model="ruleForm.refid"
                      placeholder="关联表id" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.refid" label="关联表id" prop="refid">
              <el-input v-model="ruleForm.refid"
                        placeholder="关联表id" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="评论内容" prop="content">
            <el-input v-model="ruleForm.content"
                      placeholder="评论内容" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.content" label="评论内容" prop="content">
              <el-input v-model="ruleForm.content"
                        placeholder="评论内容" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item  v-if="type!='info'"  label="userid" prop="userid">
            <el-input v-model="ruleForm.userid"
                      placeholder="userid" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.userid" label="userid" prop="userid">
              <el-input v-model="ruleForm.userid"
                        placeholder="userid" readonly></el-input>
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
        refid: '',
        content: '',
        userid: '',
      },
      rules: {
        refid: [
          {required: true, message: '关联表id不能为空', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '评论内容不能为空', trigger: 'blur'},
        ],
        userid: [
          {required: true, message: '用户id不能为空', trigger: 'blur'},
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
          if (o == 'refid') {
            this.ruleForm.refid = obj[o];
            continue;
          }
          if (o == 'content') {
            this.ruleForm.content = obj[o];
            continue;
          }
          if (o == 'userid') {
            this.ruleForm.userid = obj[o];
            continue;
          }
        }
      }
    },
    // 多级联动参数
    info(id) {
      this.$http({
        url: `job_seeker_comments/info/${id}`,
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
            url: `job_seeker_comments/${!this.ruleForm.id ? "save" : "update"}`,
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
                  this.parent.job_seeker_commentsCrossAddOrUpdateFlag = false;
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
      this.parent.job_seeker_commentsCrossAddOrUpdateFlag = false;
    },
  }
};
</script>
<style lang="scss" scoped>
.editor {
  height: 500px;
}
@keyframes custom-animation {
  0% { opacity: 0; transform: scale(0); }
  50% { opacity: 1; transform: scale(1.2); }
  100% { opacity: 1; transform: scale(1); }
}

.main-content {
  background-image: url('../../../assets/img/commentbackground.png'); /*背景图片路径*/
  background-size: cover;
  background-position: center;
  padding: 20px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  animation: custom-animation 2s ease-in-out;
}

.detail-form-content {
  background-color: rgba(255, 255, 255, 0.8);   /*表单背景色*/
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.el-form-item {
  text-align: center; /*表单项文字居中*/
}

.el-row {
  display: flex;
  justify-content: center;  /*行内元素居中*/
}

.el-col {
  margin: 10px;
}

.el-input {
  width: 100%;
}

.primary-button {
  background-color: #67c23a;
  border-color: #67c23a;
  color: white;
  border-radius: 20px;
  padding: 10px 20px;
  transition: all 0.3s ease;
}

.primary-button:hover {
  background-color: #5cb85c;
  border-color: #5cb85c;
}

.danger-button {
  background-color: #ff6b6b;
  border-color: #ff6b6b;
  color: white;
  border-radius: 20px;
  padding: 10px 20px;
  transition: all 0.3s ease;
}

.danger-button:hover {
  background-color: #ff4d4d;
  border-color: #ff4d4d;
}

.action-button {
  color: #409EFF;
}

.action-button:hover {
  color: #66b1ff;
}
</style>

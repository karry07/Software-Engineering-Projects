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
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="用户id" prop="userid">
            <el-input v-model="ruleForm.userid"
                      placeholder="用户id" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.userid" label="用户id" prop="userid">
              <el-input v-model="ruleForm.userid"
                        placeholder="用户id" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="帖子标题" prop="title">
            <el-input v-model="ruleForm.title"
                      placeholder="帖子标题" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.title" label="帖子标题" prop="title">
              <el-input v-model="ruleForm.title"
                        placeholder="帖子标题" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type !== 'info'" label="帖子内容" prop="content">
            <editor
              style="min-width: 200px; max-width: 600px;"
              v-model="ruleForm.content"
              class="editor"
              action="file/upload"
            ></editor>
          </el-form-item>
          <div v-else>
            <el-form-item label="帖子内容">
              <div class="content-display content-box content-display1" v-html="ruleForm.content"></div>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-col :span="24">
        <el-form-item v-if="type!='info'" label="点赞数" prop="thumbsupnum">
          <el-input-number v-model="ruleForm.thumbsupnum"
                           controls-position="right"
                           :min="0"
                           placeholder="点赞数"></el-input-number>
        </el-form-item>
        <div v-else>
          <el-form-item v-if="ruleForm.thumbsupnum" label="点赞数" prop="thumbsupnum">
            <el-input v-model="ruleForm.thumbsupnum"
                      placeholder="点赞数" readonly></el-input>
          </el-form-item>
        </div>
      </el-col>
      <el-form-item>
        <el-button v-if="type!='info'" type="primary" @click="onSubmit">提交</el-button>
        <el-button v-if="type!='info'" @click="back()">取消</el-button>
        <el-button v-if="type=='info'" @click="back()">返回</el-button>
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
        content: '',
        title: '',
        userid: '',
        thumbsupnum: '',
      },
      rules: {
        title: [
          { required: true, message: '帖子标题不能为空', trigger: 'blur' },
        ],
        userid: [
          { required: true, message: '用户id不能为空', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '帖子内容不能为空', trigger: 'blur' },
        ],
        thumbsupnum: [
          { required: true, message: '点赞数不能为空', trigger: 'blur' },
        ],
      }
    };
  },
  props: ["parent"],
  computed: {},
  methods: {
    download(file) {
      window.open(`${file}`);
    },
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
          if (o == 'userid') {
            this.ruleForm.userid = obj[o];
            continue;
          }
          if (o == 'title') {
            this.ruleForm.title = obj[o];
            continue;
          }
          if (o == 'content') {
            this.ruleForm.content = obj[o];
            continue;
          }

          if (o == 'thumbsupnum') {
            this.ruleForm.thumbsupnum = obj[o];
            continue;
          }
        }
      }
    },
    info(id) {
      this.$http({
        url: `forum/info/${id}`,
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
            url: `forum/${!this.ruleForm.id ? "save" : "update"}`,
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
                  this.parent.forumCrossAddOrUpdateFlag = false;
                  this.parent.search();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          }).catch(error => {
            this.$message.error('提交失败，请检查网络或稍后再试');
            console.error(error);
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
      this.parent.forumCrossAddOrUpdateFlag = false;
    },
  }
};
</script>

<style lang="scss" scoped>
.editor {
  height: 500px;
}

.content-display1 {
  padding: 20px;
  margin: 10px;
  font-size: 18px;
  color: #333;
  background-color: #f9f9f9;
  border-radius: 5px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
</style>

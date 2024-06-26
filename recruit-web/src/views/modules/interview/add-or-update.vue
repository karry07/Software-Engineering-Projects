<template>
  <div>
    <!-- 面试信息 -->
    <el-form
        class="detail-form-content"
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="80px"
    >
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type != 'info'" label="面试编号" prop="id">
            <el-input v-model="ruleForm.id" placeholder="面试编号" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.id" label="面试编号" prop="id">
              <el-input v-model="ruleForm.id" placeholder="面试编号" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type != 'info'" label="面试日期" prop="interviewtime">
            <el-date-picker v-model="ruleForm.interviewtime" type="date" placeholder="选择日期"></el-date-picker>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.interviewtime" label="面试日期" prop="interviewtime">
              <el-date-picker v-model="ruleForm.interviewtime" type="date" placeholder="选择日期" readonly></el-date-picker>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type != 'info'" label="面试官" prop="interviewer">
            <el-input v-model="ruleForm.interviewer" placeholder="面试官" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.interviewer" label="面试官" prop="interviewer">
              <el-input v-model="ruleForm.interviewer" placeholder="面试官" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
                <el-col :span="24">
          <el-form-item v-if="type != 'info'" label="面试地点" prop="location">
            <el-input v-model="ruleForm.location" placeholder="面试地点" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.location" label="面试地点" prop="location">
              <el-input v-model="ruleForm.location" placeholder="面试地点" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type != 'info'" label="面试状态" prop="status">
            <el-select v-model="ruleForm.status" placeholder="请选择面试状态">
              <el-option
                v-for="item in statusOptions"
                :key="item"
                :label="item"
                :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.status" label="面试状态" prop="status">
              <el-input v-model="ruleForm.status" placeholder="面试状态" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type != 'info'" label="反馈" prop="feedback">
            <el-input v-model="ruleForm.feedback" placeholder="反馈" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.feedback" label="反馈" prop="feedback">
              <el-input v-model="ruleForm.feedback" placeholder="反馈" readonly></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button v-if="type != 'info'" type="primary" @click="onSubmit">提交</el-button>
        <el-button v-if="type != 'info'" @click="back()">取消</el-button>
        <el-button v-if="type == 'info'" @click="back()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
// 数字，邮件，手机，url，身份证校验
import {isNumber, isEmail, isMobile, isURL, checkIdCard} from "@/utils/validate";

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
      } else if (!isNumber(value)) {
        callback(new Error("请输入整数"));
      } else {
        callback();
      }
    };
    return {
      id: '',
      type: '',
      ruleForm: {
        id: '',
        interviewtime: '',
        interviewer: '',
        feedback: '',
        location:'',
        status:''
      },
      statusOptions: ['scheduled', 'completed', 'cancelled'],
      rules: {
        id: [],
        interviewtime: [],
        interviewer: [],
        feedback: [],
        location:[],
        status:[]
      }
    };
  },
  props: ["parent"],
  computed: {},
  methods: {
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
          if (o == 'id') {
            this.ruleForm.id = obj[o];
            continue;
          }
          if (o == 'interviewtime') {
            this.ruleForm.interviewtime = obj[o];
            continue;
          }
          if (o == 'interviewer') {
            this.ruleForm.interviewer = obj[o];
            continue;
          }
          if (o == 'location') {
            this.ruleForm.location = obj[o];
            continue;
          }
          if (o == 'status') {
            this.ruleForm.status = obj[o];
            continue;
          }
          if (o == 'feedback') {
            this.ruleForm.feedback = obj[o];
            continue;
          }
        }
      }
    },
    // 获取面试信息
    info(id) {
      this.$http({
        url: `interview/info/${id}`,
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
            url: `interview/${!this.ruleForm.id ? "save" : "update"}`,
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
    // 返回
    back() {
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
    },
  }
};
</script>

<style lang="scss" scoped>
.editor {
  height: 500px;
}
</style>

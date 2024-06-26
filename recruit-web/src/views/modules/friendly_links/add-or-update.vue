<template>
  <div>
    <!-- 添加或修改 -->
    <el-form
        class="detail-form-content"
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="80px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="网站名称" prop="webname">
            <el-input
                v-model="ruleForm.webname"
                placeholder="网站名称"
                clearable
            ></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.webname" label="网站名称" prop="webname" >
              <el-input
                  v-model="ruleForm.webname"
                  placeholder="网站名称"
                  readonly
              ></el-input>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="logo" prop="logo">
            <file-upload
                tip="点击上传logo"
                action="file/upload"
                :limit="3"
                :multiple="true"
                :fileUrls="ruleForm.logo ? ruleForm.logo : ''"
                @change="logoUploadChange"
            ></file-upload>
          </el-form-item>
          <div v-else style="width: 400px; height: 100px; overflow: hidden;">
            <el-form-item v-if="ruleForm.logo" label="logo" prop="logo">
              <div style="width: 200px; height: 100px;">
                <img
                    v-bind:key="index"
                    v-for="(item, index) in ruleForm.logo.split(',')"
                    :src="item"
                    width="100%"
                    height="100%"
                    style="object-fit: contain; margin-right:20px;"
                >
              </div>
            </el-form-item>
          </div>
        </el-col>
        <el-col :span="12">
          <el-form-item v-if="type!='info'" label="网址" prop="website">
            <el-input
                v-model="ruleForm.website"
                placeholder="网址"
                clearable
            ></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.website" label="网址" prop="website">
              <el-input
                  v-model="ruleForm.website"
                  placeholder="网址"
                  readonly
              ></el-input>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-if="type!='info'" label="网站介绍" prop="webinfo" >
            <editor
                style="min-width: 200px; max-width: 600px;"
                v-model="ruleForm.webinfo"
                class="editor"
                action="file/upload"
            ></editor>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.webinfo" label="网站介绍" prop="webinfo" >
              <span class="webinfo-item" v-html="ruleForm.webinfo"></span>
            </el-form-item>
          </div>
        </el-col>
      </el-row>
      <!-- 三个按钮 -->
      <el-form-item>
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
    const validateIdCard = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!checkIdCard(value)) {
        callback(new Error("请输入正确的身份证号码"));
      } else {
        callback();
      }
    };

    const validateUrl = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isURL(value)) {
        callback(new Error("请输入正确的URL地址"));
      } else {
        callback();
      }
    };

    const validatePhone = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isMobile(value)) {
        callback(new Error("请输入正确的电话号码"));
      } else {
        callback();
      }
    };

    const validateEmail = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isEmail(value)) {
        callback(new Error("请输入正确的邮箱地址"));
      } else {
        callback();
      }
    };

    const validateNumber = (rule, value, callback) => {
      if (!value) {
        callback();
      } else if (!isNumber(value)) {
        callback(new Error("请输入数字"));
      } else {
        callback();
      }
    };

    const validateIntNumber = (rule, value, callback) => {
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
        webname: '',
        logo: '',
        website: '',
        webinfo: '',
      },
      rules: {
        webname: [],
        logo: [],
        website: [],
        webinfo: [],
      }
    };
  },
  props: ["parent"],
  computed: {},
  methods: {
    download(file) {//下载
      window.open(`${file}`);
    },
    init(id, type) {//初始化网站信息
      if (id) {
        this.id = id;
        this.type = type;
      }
      if (this.type == 'info' || this.type == 'else') {
        this.info(id);
      } else if (this.type == 'cross') {
        const obj = this.$storage.getObj('crossObj');
        for (const o in obj) {
          if (o == 'webname') {
            this.ruleForm.webname = obj[o];
            continue;
          }
          if (o == 'logo') {
            this.ruleForm.logo = obj[o];
            continue;
          }
          if (o == 'website') {
            this.ruleForm.website = obj[o];
            continue;
          }
          if (o == 'webinfo') {
            this.ruleForm.webinfo = obj[o];
            continue;
          }
        }
      }
    },
    info(id) {//详情
      this.$http({
        url: `friendly_links/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.ruleForm = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    onSubmit() {//提交
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.$http({
            url: `friendly_links/${!this.ruleForm.id ? "save" : "update"}`,
            method: "post",
            data: this.ruleForm
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 1500,
                onClose: () => {
                  this.parent.showFlag = true;
                  this.parent.addOrUpdateFlag = false;
                  this.parent.friendly_linksCrossAddOrUpdateFlag = false;
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
    getUUID() {//获取uuid
      return new Date().getTime();
    },
    back() {//返回
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.friendly_linksCrossAddOrUpdateFlag = false;
    },
    logoUploadChange(fileUrls) {
      this.ruleForm.logo = fileUrls;
    },
  }
};
</script>

<style lang="scss" scoped>
.webinfo-item {
  display: flex;  /* 弹性布局 */
  align-items: center;  /* 垂直居中 */
  justify-content: center;  /* 水平居中 */
  padding: 10px;
  border: 1px solid #e0e0e0;  /* 边框 */
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 阴影 */
  max-width: 700px;
  width: 100%;
  box-sizing: border-box; /* 盒模型 */
}

.editor {
  height: 500px;
}
</style>

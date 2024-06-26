<!--新闻管理的添加更新页面-->
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
          <el-form-item v-if="type!='info'" label="标题" prop="title">
            <el-input v-model="ruleForm.title"
                      placeholder="标题" clearable></el-input>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.title" label="标题" prop="title">
              <el-input v-model="ruleForm.title"
                        placeholder="标题" readonly></el-input>
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
                @change="pictureUploadChange"
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
          <el-form-item v-if="type!='info'" label="内容" prop="content">
            <editor
                style="min-width: 200px; max-width: 600px;"
                v-model="ruleForm.content"
                class="editor"
                action="file/upload">
            </editor>
          </el-form-item>
          <div v-else>
            <el-form-item v-if="ruleForm.content" label="内容" prop="content">
              <span v-html="ruleForm.content"></span>
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
export default {
  data() {
    return {
      id: '',
      type: '',
      ruleForm: {
        title: '',
        picture: '',
        content: '',
      },
      rules: {
        title: [
          {required: true, message: '标题不能为空', trigger: 'blur'},
        ],
        picture: [
          {required: true, message: '图片不能为空', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '内容不能为空', trigger: 'blur'},
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
          if (o == 'title') {
            this.ruleForm.title = obj[o];
            continue;
          }
          if (o == 'picture') {
            this.ruleForm.picture = obj[o];
            continue;
          }
          if (o == 'content') {
            this.ruleForm.content = obj[o];
            continue;
          }
        }
      }
    },
    // 多级联动参数
    info(id) {
      this.$http({
        url: `news/info/${id}`,
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
            url: `news/${!this.ruleForm.id ? "save" : "update"}`,
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
                  this.parent.newsCrossAddOrUpdateFlag = false;
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
    // 获取time
    getUUID() {
      return new Date().getTime();
    },
    // 返回
    back() {
      this.parent.showFlag = true;
      this.parent.addOrUpdateFlag = false;
      this.parent.newsCrossAddOrUpdateFlag = false;
    },
    // 图片上传
    pictureUploadChange(fileUrls) {
      this.ruleForm.picture = fileUrls;
    },
  }
};
</script>

<style lang="scss" scoped>
.news-detail {
  background-color: #fff5e1;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* 阴影 */
}

.detail-form-content {
  background: rgba(255, 255, 255, 0.9);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.image-gallery {  /* 图片展示 */
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.news-image {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 5px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}

.editor {
  height: 500px;
}

.content-display {
  padding: 10px;
  background: rgba(255, 245, 230, 0.9);
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.content-box {
  border: 2px solid #ffcc99;
  padding: 20px;
  border-radius: 10px;  /* 圆角 */
  background: rgba(255, 245, 230, 0.9);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}
</style>

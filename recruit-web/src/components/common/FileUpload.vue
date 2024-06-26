<!-- 上传文件组件 -->
<template>
  <div>
    <el-upload
      ref="upload"
      :action="getActionUrl"
      list-type="picture-card"
      :multiple="multiple"
      :limit="limit"
      :headers="myHeaders"
      :file-list="fileList"
      :on-exceed="handleExceed"
      :on-preview="handleUploadPreview"
      :on-remove="handleRemove"
      :on-success="handleUploadSuccess"
      :on-error="handleUploadErr"
      :before-upload="handleBeforeUpload"
      accept=".jpg,.jpeg,.png,.pdf"
    >
      <i class="el-icon-plus"></i>
      <div slot="tip" class="el-upload__tip" style="color:#838fa1;">{{ tip }}</div>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible" size="tiny" append-to-body>
      <img width="100%" :src="dialogImageUrl" alt>
    </el-dialog>
  </div>
</template>

<script>
import storage from "@/utils/storage";
import base from "@/utils/base";

export default {
  data() {
    return {
      dialogVisible: false,
      dialogImageUrl: "",
      fileList: [],
      fileUrlList: [],
      myHeaders: {}
    };
  },
  props: ["tip", "action", "limit", "multiple", "fileUrls"],
  mounted() {
    this.init();
    this.myHeaders = {
      'Token': storage.get("Token")
    };
  },
  watch: {
    fileUrls: function(val) {
      this.init();
    }
  },
  computed: {
    getActionUrl() {
      const url = `/${this.$base.name}/` + this.action;
    return url;
    }
  },
  methods: {
    init() {
      if (this.fileUrls) {
        this.fileUrlList = this.fileUrls.split(",");
        let fileArray = [];
        this.fileUrlList.forEach((item, index) => {
          let file = {
            name: index,
            url: item
          };
          fileArray.push(file);
        });
        this.setFileList(fileArray);
      }
    },
    handleBeforeUpload(file) {
      const isAllowedType = ['image/jpeg', 'image/png', 'application/pdf'].includes(file.type);
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isAllowedType) {
        this.$message.error('上传文件只能是 JPG, PNG 或 PDF 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传文件大小不能超过 2MB!');
      }
      return isAllowedType && isLt2M;
    },
    handleUploadSuccess(res, file, fileList) {
      //console.log('Upload Success Response:', res);
      if (res && res.code === 0) {
        fileList[fileList.length - 1]["url"] = this.$base.url + "upload/" + file.response.file;
        this.setFileList(fileList);
        this.$emit("change", this.fileUrlList.join(","));
      } else {
        this.$message.error(res.msg);
      }
    },
    handleUploadErr() {
      this.$message.error("文件上传失败");
    },
    handleRemove(file, fileList) {
      this.setFileList(fileList);
      this.$emit("change", this.fileUrlList.join(","));
    },
    handleUploadPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleExceed() {
      this.$message.warning(`最多上传 ${this.limit} 张图片`);
    },
    setFileList(fileList) {
      let fileArray = [];
      let fileUrlArray = [];
      let token = storage.get("token");
      fileList.forEach((item) => {
        let url = item.url.split("?")[0];
        let file = {
          name: item.name,
          url: url + "?token=" + token
        };
        fileArray.push(file);
        fileUrlArray.push(url);
      });
      this.fileList = fileArray;
      this.fileUrlList = fileUrlArray;
    }
  }
};
</script>

<style lang="scss" scoped>
</style>

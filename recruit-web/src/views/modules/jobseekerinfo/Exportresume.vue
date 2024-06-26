<template>
  <div>
    <!-- 简历显示区域 -->
    <div id="pdfResume" class="resume-container">
      <h1>简历</h1>
      <el-row>
        <el-col :span="24" class="photo-container user-photo-right" >
          <img :src="ruleForm.photo" alt="User Photo" class="user-photo" />
        </el-col>
        <el-col :span="12">
          <p><strong>用户名：</strong>{{ ruleForm.username }}</p>
        </el-col>
        <el-col :span="12">
          <p><strong>姓名：</strong>{{ ruleForm.fullname }}</p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <p><strong>性别：</strong>{{ ruleForm.gender }}</p>
        </el-col>
        <el-col :span="12">
          <p><strong>电话：</strong>{{ ruleForm.phone }}</p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <p><strong>学历：</strong>{{ ruleForm.education }}</p>
        </el-col>
        <el-col :span="12">
          <p><strong>岗位类别：</strong>{{ ruleForm.jobcategory }}</p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <p><strong>政治面貌：</strong>{{ ruleForm.politicalstatus }}</p>
        </el-col>
        <el-col :span="12">
          <p><strong>年龄：</strong>{{ ruleForm.age }}</p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <p><strong>籍贯：</strong>{{ ruleForm.birthplace }}</p>
        </el-col>
        <el-col :span="12">
          <p><strong>民族：</strong>{{ ruleForm.ethnicity }}</p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <p><strong>身高：</strong>{{ ruleForm.height }}</p>
        </el-col>
        <el-col :span="12">
          <p><strong>邮箱：</strong>{{ ruleForm.email }}</p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <p><strong>工作经历：</strong>{{ ruleForm.workexperience }}</p>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="24">
          <p><strong>个人基本情况：</strong></p>
          <div v-html="ruleForm.personalinfo"></div>
        </el-col>
      </el-row>
    </div>
    <!-- 导出PDF按钮 -->
    <el-button type="primary" @click="exportResumePdf">导出PDF简历</el-button>
    <el-button @click="back()">返回</el-button>
  </div>
</template>

<script>
import pdfExport from "../jobseekerinfo/export-pdf";

export default {
  data() {
    return {
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
        birthday: '',
        age: '',
        birthplace: '',
        ethnicity: '',
        height: '',
        email: '',
        politicalstatus: '',
      },
    };
  },
  props: ["parent"],
  methods: {
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
          if (o == 'birthday') {
            this.ruleForm.birthday = obj[o];
            continue;
          }
          if (o == 'age') {
            this.ruleForm.age = obj[o];
            continue;
          }
          if (o == 'birthplace') {
            this.ruleForm.birthplace = obj[o];
            continue;
          }
          if (o == 'ethnicity') {
            this.ruleForm.ethnicity = obj[o];
            continue;
          }
          if (o == 'height') {
            this.ruleForm.height = obj[o];
            continue;
          }
          if (o == 'email') {
            this.ruleForm.email = obj[o];
            continue;
          }
        }
      }
      this.$http({
        url: `${this.$storage.get('sessionTable')}/session`,
        method: "get"
      }).then(({ data }) => {
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
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.xueliOptions = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });

      this.$http({
        url: `option/job_category/jobcategory`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.jobcategoryOptions = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });

    },
    info(id) {
      this.$http({
        url: `jobseekerinfo/info/${id}`,
        method: "get"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.ruleForm = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    download(file) {
      window.open(`${file}`)
    },
    exportResumePdf() {
      const elementId = "pdfResume";
      const htmlTitle = this.ruleForm.fullname+"的简历.pdf";
      pdfExport.exportPdf(elementId, htmlTitle);
    },
    // 返回
    back() {
      this.parent.showFlag = true;
      this.parent.ExportresumeFlag = false;
    },
  }
};
</script>

<style lang="scss" scoped>
.resume-container {
  font-family: Arial, sans-serif; /* 设置字体 */
  line-height: 1.5;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-top: 50px;
  background-color: #f5f5f5;
  width:800px;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
}

p {
  margin: 5px 0;
}

.el-row {
  margin-bottom: 10px;
}

img {
  margin-right: 20px;
  border-radius: 4px;
  border: 1px solid #ddd; /* 图片边框 */
}
.user-photo-right {
  position: absolute; /* 相对于父容器进行定位 */
  top: -8px; /* 距离顶部20px */
  right: 100px; /* 距离右侧20px */
  width: 10px;
  height: auto;
  border-radius: 4px;
  border: 1px solid #ddd;
}
.user-photo {
  width: 100px; /* 调整图片的大小 */
  height: 120px;
}
</style>

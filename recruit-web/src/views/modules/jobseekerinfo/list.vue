<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <div class="form-container" >
          <el-form-item label="用户名">
            <el-input v-model="searchForm.username"
                      placeholder="用户名" clearable></el-input>
          </el-form-item>
          <el-form-item label="学历">
            <el-input v-model="searchForm.education"
                      placeholder="学历" clearable></el-input>
          </el-form-item>
          <el-form-item label="岗位类别">
            <el-input v-model="searchForm.jobcategory"
                      placeholder="岗位类别" clearable></el-input>
          </el-form-item>
        </div>

        <el-form-item>
          <el-button round @click="search()">查询</el-button>
          <el-button
              v-if="isAuth('jobseekerinfo','新增')"
              type="primary"
              @click="addOrUpdateHandler()"
              round
          >新增
          </el-button>
          <el-button
              v-if="isAuth('jobseekerinfo','删除')"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
              round
          >删除
          </el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <el-table
            v-if="isAuth('jobseekerinfo','查看')"
            :data="dataList"
            border
            v-loading="dataListLoading"
            @selection-change="selectionChangeHandler"
            style="width: 100%;">
          <el-table-column
              type="selection"
              header-align="center"
              align="center"
              width="50">
          </el-table-column>
          <el-table-column
              prop="username"
              header-align="center"
              align="center"
              sortable
              label="用户名">
            <template slot-scope="scope">
              {{ scope.row.username }}
            </template>
          </el-table-column>
          <el-table-column
              prop="fullname"
              header-align="center"
              align="center"
              sortable
              label="姓名">
            <template slot-scope="scope">
              {{ scope.row.fullname }}
            </template>
          </el-table-column>
          <el-table-column
              prop="gender"
              header-align="center"
              align="center"
              sortable
              label="性别">
            <template slot-scope="scope">
              {{ scope.row.gender }}
            </template>
          </el-table-column>
          <el-table-column
              prop="phone"
              header-align="center"
              align="center"
              sortable
              label="电话">
            <template slot-scope="scope">
              {{ scope.row.phone }}
            </template>
          </el-table-column>
          <el-table-column prop="photo"
                           header-align="center"
                           align="center"
                           width="200"
                           label="照片">
            <template slot-scope="scope">
              <div v-if="scope.row.photo">
                <img :src="scope.row.photo.split(',')[0]" min-width="100" height="100">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column
              prop="education"
              header-align="center"
              align="center"
              sortable
              label="学历">
            <template slot-scope="scope">
              {{ scope.row.education }}
            </template>
          </el-table-column>
          <el-table-column
              prop="jobcategory"
              header-align="center"
              align="center"
              sortable
              label="岗位类别">
            <template slot-scope="scope">
              {{ scope.row.jobcategory }}
            </template>
          </el-table-column>
          <el-table-column prop="resume"
                           header-align="center"
                           align="center"
                           label="简历">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="download(scope.row.resume)">下载</el-button>
            </template>
          </el-table-column>
          <el-table-column
              prop="workexperience"
              header-align="center"
              align="center"
              sortable
              label="工作经历">
            <template slot-scope="scope">
              {{ scope.row.workexperience| truncate(30) }}
            </template>
          </el-table-column>
          <el-table-column
              header-align="center"
              align="center"
              label="操作">
            <template slot-scope="scope">
              <el-button v-if="isAuth('jobseekerinfo','查看')" type="text" icon="el-icon-edit" size="small"
                         @click="addOrUpdateHandler(scope.row.id,'info')">详情
              </el-button>
              <el-button v-if="isAuth('jobseekerinfo','修改')" type="text" icon="el-icon-edit" size="small"
                         @click="addOrUpdateHandler(scope.row.id)">修改
              </el-button>
              <el-button v-if="isAuth('jobseekerinfo','删除')" type="text" icon="el-icon-delete" size="small"
                         @click="deleteHandler(scope.row.id)">删除
              </el-button>
              <el-button  type="text" icon="el-icon-download" size="small"
                         @click="exportresumeHandler(scope.row.id)">导出
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-sizes="[5, 10, 20, 50, 100]"
            :page-size="pageSize"
            :total="totalPage"
            layout="total, sizes, prev, pager, next, jumper"
            class="pagination-content"
        ></el-pagination>
      </div>
    </div>
    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>
    <Exportresume v-if="ExportresumeFlag" :parent="this" ref="Exportresume"></Exportresume>


  </div>
</template>
<script>
import AddOrUpdate from "./add-or-update";
import Exportresume from "./Exportresume.vue";


export default {
  data() {
    return {
      xueliOptions: [],
      jobcategoryOptions: [],
      searchForm: {
        key: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      addOrUpdateFlag: false,
      ExportresumeFlag: false,
    };
  },
  mounted() {
    this.init();
    this.getDataList();
  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    },
    truncate: function (text, length) {
        if (text.length <= length) {
          return text;
        } else {
          return text.substring(0, length) + '...';
        }
      }
  },
  components: {
    AddOrUpdate,
    Exportresume,
  },
  methods: {
    init() {
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
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id',
      }
      if (this.searchForm.username != '' && this.searchForm.username != undefined) {
        params['username'] = '%' + this.searchForm.username + '%'
      }
      if (this.searchForm.education != '' && this.searchForm.education != undefined) {
        params['education'] = '%' + this.searchForm.education + '%'
      }
      if (this.searchForm.jobcategory != '' && this.searchForm.jobcategory != undefined) {
        params['jobcategory'] = '%' + this.searchForm.jobcategory + '%'
      }
      this.$http({
        url: "jobseekerinfo/page",
        method: "get",
        params: params
      }).then(({data}) => {
        if (data && data.code === 0) {
          //console.log('Data from backend:', data.data.list)
          this.dataList = data.data.list;
          this.totalPage = data.data.total;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
    // 添加/修改
    addOrUpdateHandler(id, type) {
      this.showFlag = false;
      this.addOrUpdateFlag = true;
      this.crossAddOrUpdateFlag = false;
      if (type != 'info') {
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id, type);
      });
    },
    exportresumeHandler(id, type) {
      this.showFlag = false;
      this.ExportresumeFlag = true;
      if (type != 'info') {
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.Exportresume.init(id, type);
      });
    },
    // 下载
    download(file) {
      window.open(`${file}`)
    },
    // 删除
    deleteHandler(id) {
      var ids = id
          ? [Number(id)]
          : this.dataListSelections.map(item => {
            return Number(item.id);
          });
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "jobseekerinfo/delete",
          method: "post",
          data: ids
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.main-content {
  background-image: url('../../../assets/img/jobcategorybackground.png'); /* 图片路径 */
  background-size: cover;
  background-position: center;
  padding: 2px;
  min-height: 10vh;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-content {
  background: rgba(255, 255, 255, 0.9); /* 减少透明度，增加对比度 */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15); /* 增加阴影效果 */
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between; /* 调整布局 */
  align-items: center;
  gap: 20px;
  width: 100%;
  max-width: 900px; /* 限制最大宽度 */
}

.form-container {
  max-width: 300px;
  text-align: right;
}

.table-content {
  width: 100%;
  max-width: 900px; /* 限制最大宽度 */
  background: rgba(255, 255, 255, 0.95); /* 减少透明度，增加对比度 */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.4); /* 增加阴影效果 */
}

.el-table {
  table-layout: fixed; /* 固定表格布局 */
  width: 100%; /* 确保表格宽度为100% */
}

.el-table th, .el-table td {
  text-align: center; /* 表格内容居中 */
  padding: 12px 8px; /* 调整表格内边距 */
}

.el-table-column {
  background-color: rgba(255, 255, 255, 0.85); /* 调整背景透明度 */
}

.el-table__header-wrapper th {
  background-color: rgba(245, 245, 245, 0.95); /* 表头背景色 */
  color: #333; /* 表头字体颜色 */
  font-weight: bold; /* 表头字体加粗 */
}

.el-table__row:hover {
  background-color: rgba(240, 240, 240, 0.8); /* 行悬停效果 */
}

.el-pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.el-button {
  border-radius: 20px; /* 圆角按钮 */
}

.el-input {
  border-radius: 10px; /* 增加输入框圆角 */
}

.el-form-item {
  margin-bottom: 0; /* 去除表单项底部外边距 */
}

.el-form-item__content {
  display: flex;
  align-items: center;
  gap: 10px; /* 增加表单项内元素的间距 */
}
</style>

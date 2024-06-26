<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="关联表">
          <el-input v-model="searchForm.name" placeholder="关联表id" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button round class="primary-button" @click="search()">查询</el-button>
          <el-button
            v-if="isAuth('job_post_comments','新增')"
            type="primary"
            class="primary-button"
            @click="addOrUpdateHandler()"
            round
          >新增</el-button>
          <el-button
            v-if="isAuth('job_post_comments','删除')"
            :disabled="dataListSelections.length <= 0"
            type="danger"
            class="danger-button"
            @click="deleteHandler()"
            round
          >删除</el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <el-table
          v-if="isAuth('job_post_comments','查看')"
          :data="dataList"
          border
          v-loading="dataListLoading"
          @selection-change="selectionChangeHandler"
          style="width: 100%;"
        >
          <el-table-column
            type="selection"
            header-align="center"
            align="center"
            width="50"
          ></el-table-column>
          <el-table-column
            prop="refid"
            header-align="center"
            align="center"
            sortable
            label="关联表id"
          >
            <template slot-scope="scope">
              {{ scope.row.refid }}
            </template>
          </el-table-column>
          <el-table-column
            prop="content"
            header-align="center"
            align="center"
            sortable
            label="评论内容"
          >
            <template slot-scope="scope">
              {{ scope.row.content }}
            </template>
          </el-table-column>
          <el-table-column
            header-align="center"
            align="center"
            label="操作"
          >
            <template slot-scope="scope">
              <el-button
                v-if="isAuth('job_post_comments','查看')"
                type="text"
                icon="el-icon-edit"
                size="small"
                class="action-button"
                @click="addOrUpdateHandler(scope.row.id,'info')"
              >详情</el-button>
              <el-button
                v-if="isAuth('job_post_comments','修改')"
                type="text"
                icon="el-icon-edit"
                size="small"
                class="action-button"
                @click="addOrUpdateHandler(scope.row.id)"
              >修改</el-button>
              <el-button
                v-if="isAuth('job_post_comments','删除')"
                type="text"
                icon="el-icon-delete"
                size="small"
                class="action-button"
                @click="deleteHandler(scope.row.id)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper"
          class="pagination-content"
        ></el-pagination>
      </div>
    </div>
    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./add-or-update";

export default {
  data() {
    return {
      searchForm: {
        key: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      sfshVisiable: false,
      shForm: {},
      chartVisiable: false,
      addOrUpdateFlag: false,
    };
  },
  mounted() {
    this.init();
    this.getDataList();
  },
  filters: {
    htmlfilter(val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    }
  },
  components: {
    AddOrUpdate,
  },
  methods: {
    init() {
      // 初始化代码
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
      this.$http({
        url: "job_post_comments/page",
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
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
          url: "job_post_comments/delete",
          method: "post",
          data: ids
        }).then(({ data }) => {
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
@keyframes custom-animation {
  0% { opacity: 0; transform: scale(0); }
  50% { opacity: 1; transform: scale(1.2); }
  100% { opacity: 1; transform: scale(1); }
}
.main-content {
  background-image: url('../../../assets/img/commentbackground.png'); /* 确保路径正确 */
  background-size: cover;
  background-position: center;
  padding: 20px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  animation: custom-animation 2s ease-in-out;
}
.form-content {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}
.table-content {
  background-color: rgba(255, 255, 255, 0.8);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  overflow: auto;
}
.el-table {
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 10px;
}
.el-table th, .el-table td {
  text-align: center;
  color: #333;
}
.pagination-content {
  margin-top: 20px;
  display: flex;
  justify-content: center;
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

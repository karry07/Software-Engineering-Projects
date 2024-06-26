<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="面试官">
          <el-input v-model="searchForm.interviewer" placeholder="面试官" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="custom-button" round @click="search()">查询</el-button>
          <el-button
            v-if="isAuth('interview', '新增')"
            type="primary"
            class="custom-button primary"
            @click="addOrUpdateHandler()"
            round
          >新增
          </el-button>
          <el-button
            v-if="isAuth('interview', '删除')"
            :disabled="dataListSelections.length <= 0"
            type="danger"
            class="custom-button danger"
            @click="deleteHandler()"
            round
          >删除
          </el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <el-table
          v-if="isAuth('interview', '查看')"
          :data="dataList"
          border
          v-loading="dataListLoading"
          @selection-change="selectionChangeHandler"
          class="custom-table"
        >
          <el-table-column
            type="selection"
            header-align="center"
            align="center"
            width="50"
          >
          </el-table-column>
          <el-table-column
            prop="interviewer"
            header-align="center"
            align="center"
            sortable
            label="面试官"
          >
            <template slot-scope="scope">
              {{ scope.row.interviewer }}
            </template>
          </el-table-column>
          <el-table-column
            prop="applicationid"
            header-align="center"
            align="center"
            sortable
            label="应聘表ID"
          >
            <template slot-scope="scope">
              {{ scope.row.applicationid }}
            </template>
          </el-table-column>
          <el-table-column
            prop="interviewtime"
            header-align="center"
            align="center"
            sortable
            label="面试时间"
          >
            <template slot-scope="scope">
              {{ scope.row.interviewtime | formatDate }}
            </template>
          </el-table-column>
          <el-table-column
            prop="location"
            header-align="center"
            align="center"
            sortable
            label="面试地点"
          >
            <template slot-scope="scope">
              {{ scope.row.location }}
            </template>
          </el-table-column>
          <el-table-column
            prop="status"
            header-align="center"
            align="center"
            sortable
            label="面试状态"
          >
            <template slot-scope="scope">
              {{ scope.row.status }}
            </template>
          </el-table-column>
          <el-table-column
            prop="feedback"
            header-align="center"
            align="center"
            sortable
            label="面试反馈"
          >
            <template slot-scope="scope">
              {{ scope.row.feedback }}
            </template>
          </el-table-column>
          <el-table-column
            header-align="center"
            align="center"
            label="操作"
          >
            <template slot-scope="scope">
              <el-button
                v-if="isAuth('interview', '查看')"
                type="text"
                icon="el-icon-view"
                size="small"
                @click="addOrUpdateHandler(scope.row.id, 'info')"
                class="action-button"
              >详情
              </el-button>
              <el-button
                v-if="isAuth('interview', '修改')"
                type="text"
                icon="el-icon-edit"
                size="small"
                @click="addOrUpdateHandler(scope.row.id)"
                class="action-button"
              >修改
              </el-button>
              <el-button
                v-if="isAuth('interview', '删除')"
                type="text"
                icon="el-icon-delete"
                size="small"
                @click="deleteHandler(scope.row.id)"
                class="action-button danger"
              >删除
              </el-button>
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
    <!-- 添加/修改页面 -->
    <add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./add-or-update";

export default {
  data() {
    return {
      searchForm: {
        interviewer: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      showFlag: true,
      addOrUpdateFlag: false,
    };
  },
  mounted() {
    this.init();
    this.getDataList();
  },
  components: {
    AddOrUpdate,
  },
  methods: {
    init() {
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
      if (this.searchForm.interviewer) {
        params['title'] = '%' + this.searchForm.interviewer + '%';
      }
      this.$http({
        url: "interview/page",
        method: "get",
        params: params
      }).then(({data}) => {
        if (data && data.code === 0) {
          //console.log('Data from backend:', data.data.list);
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
      if (type !== 'info') {
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id, type);
      });
    },
    // 删除
    deleteHandler(id) {
      const ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => Number(item.id));
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: "interview/delete",
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
  },
  filters: {
    formatDate(value) {
      if (value) {
        return new Date(value).toLocaleString();
      }
      return '';
    }
  }
};
</script>

<style lang="scss" scoped>
@keyframes rotate-in {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.main-content {
  background-image: url('../../../assets/img/interviewbackground.png'); /* 确保路径正确 */
  background-size: cover;
  background-position: center;
  padding: 20px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  animation: rotate-in 1s linear;
}

.custom-button {
  background-color: #409EFF;
  color: white;
  border: none;
  border-radius: 20px;
  padding: 10px 20px;
  transition: background-color 0.3s, transform 0.3s;
}

.custom-button:hover {
  background-color: #66b1ff;
  transform: scale(1.05);
}

.custom-button.primary {
  background-color: #67C23A;
}

.custom-button.primary:hover {
  background-color: #85ce61;
}

.custom-button.danger {
  background-color: #F56C6C;
}

.custom-button.danger:hover {
  background-color: #f78989;
}

.custom-table {
  --el-table-border-color: #ebeef5;
  --el-table-row-hover-bg-color: #f5f7fa;
  --el-table-header-background-color: #f5f7fa;
}

.action-button {
  margin: 0 5px;
  padding: 5px 10px;
  background-color: #409EFF;
  color: white;
  border-radius: 15px;
  transition: background-color 0.3s, transform 0.3s;
}

.action-button:hover {
  background-color: #66b1ff;
  transform: scale(1.05);
}

.action-button.danger {
  background-color: #F56C6C;
}

.action-button.danger:hover {
  background-color: #f78989;
}

.pagination-content {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>

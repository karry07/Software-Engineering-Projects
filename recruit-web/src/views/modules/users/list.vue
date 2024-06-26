<!--管理我们的管理员的list界面，虽然抽象但是易懂-->
<template>
  <div class="main-content">
    <div v-if="!showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item>
          <el-button
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
              round
          >删除
          </el-button>
        </el-form-item>
      </el-form>
      <!--表格-->
      <div class="table-content">
        <el-table
            @selection-change="selectionChangeHandler"
            :data="dataList"
            border
            v-loading="dataListLoading"
            style="width: 100%;"
        >
          <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
          <el-table-column prop="username" header-align="center" align="center" sortable
                           label="用户名"></el-table-column>
          <el-table-column prop="password" header-align="center" align="center" sortable label="密码"></el-table-column>
          <el-table-column
              fixed="right"
              header-align="center"
              align="center"
              width="150"
              label="操作"
          >
            <template slot-scope="scope">
              <el-button
                  type="text"
                  icon="el-icon-edit"
                  size="small"
                  @click="addOrUpdateHandler(scope.row)"
              >修改
              </el-button>
              <el-button
                  type="text"
                  icon="el-icon-delete"
                  size="small"
                  @click="deleteHandler(scope.row.id)"
              >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
            @size-change="sizeChangeHandle"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-sizes="[5,10, 20, 50, 100]"
            :page-size="pageSize"
            :total="totalPage"
            layout="total, sizes, prev, pager, next, jumper"
            class="pagination-content"
        ></el-pagination>
      </div>
    </div>
    <add-or-update v-else :parent="this" ref="addOrUpdate"></add-or-update>
  </div>
</template>
<script>
import AddOrUpdate from "./add-or-update";

export default {
  data() {
    return {
      searchForm: {},
      dataList: [],
      pageIndex: 1,
      pageSize: 5,
      totalPage: 0,
      dataListLoading: false,
      showFlag: false,
      dataListSelections: []
    };
  },
  //钩子函数
  mounted() {
    this.getDataList();
  },
  components: {
    AddOrUpdate
  },
  methods: {
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: "users/page",
        method: "get",
        params: {
          page: this.pageIndex,
          limit: this.pageSize,
          sort: 'id'
        }
      }).then(({data}) => {
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
    // 添加/修改
    addOrUpdateHandler(row) {
      this.showFlag = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(row.id);
      });
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
          url: "users/delete",
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
    },
    // 多选
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    }
  }
};
</script>
<style lang="scss" scoped>
.main-content {
  background-image: url('../../../assets/img/userbackground.png'); /*背景图片*/
  background-size: cover;
  background-position: center;
  padding: 20px;
  min-height: 100vh;  /*最小高度*/
  display: flex;  /*弹性布局*/
  flex-direction: column; /*垂直布局*/
  align-items: center;  /*垂直居中*/
}

.form-content {
  background: rgba(255, 255, 255, 0.9);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between; /*两端对齐*/
  align-items: center;  /*垂直居中*/
  gap: 20px;  /*间隔*/
  width: 100%;  /*宽度100%*/
  max-width: 900px;
}

.table-content {
  width: 100%;
  max-width: 900px;
  background: rgba(255, 255, 255, 0.95);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.4);
}

.el-table {
  table-layout: fixed;
  width: 100%;
}

.el-table th, .el-table td {
  text-align: center;
  padding: 12px 8px;
}

.el-table-column {
  background-color: rgba(255, 255, 255, 0.85);
}

.el-table__header-wrapper th {
  background-color: rgba(245, 245, 245, 0.95);
  color: #333;
  font-weight: bold;
}

.el-table__row:hover {
  background-color: rgba(240, 240, 240, 0.8); /*鼠标悬停颜色*/
}

.el-pagination {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.el-button {
  border-radius: 20px;
}

.el-input {
  border-radius: 10px;
}

.el-form-item {
  margin-bottom: 0;
}

.el-form-item__content {
  display: flex;
  align-items: center;
  gap: 10px;
}
</style>

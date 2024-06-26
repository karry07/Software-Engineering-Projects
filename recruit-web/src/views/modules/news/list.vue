<!--新闻的列表页面-->
<template>
  <div class="main-content">
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content vertical">
        <!--最上面的查询等功能-->
        <el-form-item label="查询新闻">
          <el-input v-model="searchForm.key" placeholder="请输入查询的新闻信息" clearable></el-input>
        </el-form-item>
        <el-form-item class="button-group">
          <el-button type="primary" round @click="search()">查询</el-button>
          <el-button
              v-if="isAuth('news','新增')"
              type="success"
              round
              @click="addOrUpdateHandler()"
          >新增</el-button>
          <el-button
              v-if="isAuth('news','删除')"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              round
              @click="deleteHandler()"
          >删除</el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <el-table
            v-if="isAuth('news','查看')"
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
              prop="title"
              header-align="center"
              align="center"
              sortable
              label="标题">
          </el-table-column>
          <el-table-column prop="picture" header-align="center" align="center" width="200" label="图片">
            <template slot-scope="scope">
              <div v-if="scope.row.picture">
                <img :src="scope.row.picture.split(',')[0]" class="news-image">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column header-align="center" align="center" label="操作">
            <template slot-scope="scope">
              <el-button v-if="isAuth('news','查看')" type="warning" icon="el-icon-view" size="small" @click="addOrUpdateHandler(scope.row.id,'info')">详情</el-button>
              <el-button v-if="isAuth('news','修改')" type="success" icon="el-icon-edit" size="small" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>
              <el-button v-if="isAuth('news','删除')" type="danger" icon="el-icon-delete" size="small" @click="deleteHandler(scope.row.id)">删除</el-button>
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
    init() {},
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
        url: "news/page",
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
      if (type != 'info') {
        type = 'else';
      }
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id, type);
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
          url: "news/delete",
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
  background-image: url('../../../assets/img/newsbackground.png');  /* 背景图片 */
  padding: 20px;
  min-height: 100vh;
  display: flex;  /* 弹性布局 */
  flex-direction: column; /* 垂直布局 */
  align-items: center;    /* 水平居中 */
}

.form-content {
  background: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  width: 100%;
  max-width: 800px;
}

.button-group {
  display: flex;
  justify-content: space-between; /* 两端对齐 */
}

.table-content {
  width: 100%;
  max-width: 1200px;
  background: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);  /* 阴影 */
}

.el-table {
  table-layout: fixed;
  width: 100%;
}

.el-table th, .el-table td {
  text-align: center;
  padding: 12px 8px;
}

.news-image {
  width: 100px;
  height: 100px;
  object-fit: cover;  /* 图片填充 */
}

.el-pagination {
  margin-top: 20px;
}

.el-button--primary {
  background-color: #409eff;
  border-color: #409eff;
  color: #fff;
}

.el-button--success {
  background-color: #67c23a;
  border-color: #67c23a;
  color: #fff;
}

.el-button--danger {
  background-color: #f56c6c;
  border-color: #f56c6c;
  color: #fff;
}

.el-button--warning {
  background-color: #e6a23c;
  border-color: #e6a23c;
  color: #fff;
}
</style>

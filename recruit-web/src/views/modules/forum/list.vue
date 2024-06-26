<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="帖子标题">
          <el-input v-model="searchForm.title"
                    placeholder="帖子标题" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="info" round @click="search()">查询</el-button>
          <el-button
              v-if="isAuth('forum','新增')"
              type="primary"
              @click="addOrUpdateHandler()"
              round
          >新增
          </el-button>
          <el-button
              v-if="isAuth('forum','删除')"
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
            v-if="isAuth('forum','查看')"
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
              prop="userid"
              header-align="center"
              align="center"
              sortable
              label="用户id">
            <template slot-scope="scope">
              {{ scope.row.userid }}
            </template>
          </el-table-column>
          <el-table-column
              prop="title"
              header-align="center"
              align="center"
              sortable
              label="帖子标题">
            <template slot-scope="scope">
              {{ scope.row.title }}
            </template>
          </el-table-column>
          <el-table-column
              prop="content"
              header-align="center"
              align="center"
              sortable
              label="帖子正文">
            <template slot-scope="scope">
              {{ scope.row.content | truncate(30) }}
            </template>
          </el-table-column>
          <el-table-column
              prop="thumbsupnum"
              header-align="center"
              align="center"
              sortable
              label="点赞数">
            <template slot-scope="scope">
              {{ scope.row.thumbsupnum }}
            </template>
          </el-table-column>
          <el-table-column
              header-align="center"
              align="center"
              label="操作">
            <template slot-scope="scope">
              <el-button  v-if="isAuth('forum','查看')" type="primary" icon="el-icon-edit" size="small"
                         @click="addOrUpdateHandler(scope.row.id,'info')">详情
              </el-button>
              <el-button v-if="isAuth('forum','修改')" type="success" icon="el-icon-edit" size="small"
                         @click="addOrUpdateHandler(scope.row.id)">修改
              </el-button>
              <el-button v-if="isAuth('forum','删除')" type="danger" icon="el-icon-delete" size="small"
                         @click="deleteHandler(scope.row.id)">删除
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
      if (this.searchForm.title != '' && this.searchForm.title != undefined) {
        params['title'] = '%' + this.searchForm.title + '%'
      }
      this.$http({
        url: "forum/page",
        method: "get",
        params: params
      }).then(({data}) => {
        if (data && data.code === 0) {
          //console.log(data.data.list)
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
          url: "forum/delete",
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
  background-image: url('../../../assets/img/forumbackground.png'); /* 确保路径正确 */
  background-size: cover;
  background-position: center;
  padding: 20px;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}
.el-button--primary {
  background-color: #409eff;
  border-color: #409eff;
  color: #fff;
  width: 70px;  /* 设置按钮的宽度 */
  height: 30px;  /* 设置按钮的高度 */
  padding: 0 0;
}

.el-button--success {
  background-color: #67c23a;
  border-color: #67c23a;
  color: #fff;
  width: 70px;  /* 设置按钮的宽度 */
  height: 30px;  /* 设置按钮的高度 */
  padding: 0 0;
}

.el-button--danger {
  background-color: #f56c6c;
  border-color: #f56c6c;
  color: #fff;
  width: 70px;  /* 设置按钮的宽度 */
  height: 30px;  /* 设置按钮的高度 */
  padding: 0 0;
}

.el-button--warning {
  background-color: #e6a23c;
  border-color: #e6a23c;
  color: #fff;
  width: 70px;  /* 设置按钮的宽度 */
  height: 30px;  /* 设置按钮的高度 */
  padding: 0 0;
}

.el-button--info {
  background-color: #909399;
  border-color: #909399;
  color: #fff;
  width: 70px;  /* 设置按钮的宽度 */
  height: 30px;  /* 设置按钮的高度 */
  padding: 0 0;
}

.table-content {
  background-color: #f2f2f2; /* 设置背景颜色为浅灰色 */
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.4); /* 增加阴影效果 */
}
</style>

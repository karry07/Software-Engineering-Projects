<!--收藏的列表页面-->
<template>
  <div class="main-content">
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="收藏名称">
          <el-input v-model="searchForm.name" placeholder="收藏名称" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="custom-button" @click="search()">查询</el-button>
          <el-button
            v-if="isAuth('storeup','新增')"
            class="custom-button primary"
            @click="addOrUpdateHandler()"
          >新增</el-button>
          <el-button
            v-if="isAuth('storeup','删除')"
            :disabled="dataListSelections.length <= 0"
            class="custom-button danger"
            @click="deleteHandler()"
          >删除</el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <el-table
            v-if="isAuth('storeup','查看')"
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
                prop="refid"
                header-align="center"
                align="center"
                sortable
                label="收藏id">
                <template slot-scope="scope">
                  {{scope.row.refid}}
                </template>
            </el-table-column>
            <el-table-column
                prop="tablename"
                header-align="center"
                align="center"
                sortable
                label="表名">
                <template slot-scope="scope">
                  {{scope.row.tablename}}
                </template>
            </el-table-column>
            <el-table-column
                prop="name"
                header-align="center"
                align="center"
                sortable
                label="收藏名称">
                <template slot-scope="scope">
                  {{scope.row.name}}
                </template>
            </el-table-column>
            <el-table-column prop="picture"
                header-align="center"
                align="center"
                width="200"
                label="收藏图片">
                <template slot-scope="scope">
                  <div v-if="scope.row.picture">
                    <img :src="scope.row.picture.split(',')[0]" min-width="100" height="100">
                  </div>
                  <div v-else>无图片</div>
                </template>
            </el-table-column>
            <el-table-column
                header-align="center"
                align="center"
                label="操作">
                <!--三个按钮，查看、修改、删除-->
                <template slot-scope="scope">
                  <el-button v-if="isAuth('storeup','查看')" class="custom-button text" icon="el-icon-edit" size="small" @click="addOrUpdateHandler(scope.row.id,'info')">详情</el-button>
                  <el-button v-if="isAuth('storeup','修改')" class="custom-button text" icon="el-icon-edit" size="small" @click="addOrUpdateHandler(scope.row.id)">修改</el-button>
                  <el-button v-if="isAuth('storeup','删除')" class="custom-button text danger" icon="el-icon-delete" size="small" @click="deleteHandler(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--分页-->
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
      addOrUpdateFlag: false
    };
  },
  // 钩子函数，初始化
  mounted() {
    this.init();
    this.getDataList();
  },
  // 防止html注入攻击，后面嫌麻烦没有使用
  filters: {
    htmlfilter(val) {
      return val.replace(/<[^>]*>/g, '').replace(/undefined/g, '');
    }
  },
  components: {
    AddOrUpdate,
  },
  methods: {
    init() {
    },
    // 查询
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    // 获取数据
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        sort: 'id'
      };
      if (this.searchForm.name != '' && this.searchForm.name != undefined) {
        params['name'] = '%' + this.searchForm.name + '%';
      }
      this.$http({
        url: "storeup/page",
        method: "get",
        params: params
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
    selectionChangeHandler(val) {
      this.dataListSelections = val;
    },
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
    // 浏览器打开文件即下载
    download(file) {
      window.open(`${file}`);
    },
    // 删除操作
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
          url: "storeup/delete",
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
  background-image: url('../../../assets/img/shoucangbackground.png'); /*背景图片*/
  background-size: cover;
  background-position: center;
  padding: 20px;
  min-height: 100vh;
  display: flex;
  flex-direction: column; /*垂直布局*/
  animation: fadeIn 1.5s ease-in-out; /*动画*/
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

.el-form-item label {
  color: #409eff;
  font-weight: bold;
}

.custom-button {
  border-radius: 20px;
  padding: 8px 16px;
  transition: background-color 0.3s, border-color 0.3s; /*过渡动画*/
}

.custom-button.primary {
  background-color: #409eff;
  border-color: #409eff;
  color: #9af5eb;
}

.custom-button.danger {
  background-color: #f56c6c;
  border-color: #f56c6c;
  color: #fff;
}

.custom-button:hover {
  background-color: #66b1ff;
  border-color: #66b1ff;
}

.custom-button.danger:hover {
  background-color: #ec8d45;
  border-color: #ff7878;
}

.custom-button.text {
  color: #409eff;
}

.custom-button.text.danger {
  color: #dcd498;
}

.el-table {
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}

.el-table th, .el-table td {
  padding: 10px;
  text-align: center;
}

.el-table th {
  background-color: #f5f7fa;
  color: #409eff;
  font-weight: bold;
}

.el-table tr:nth-child(odd) {
  background-color: #fafafa;
}

.el-table tr:nth-child(even) {
  background-color: #f0f0f0;
}

.el-table tr:hover {
  background-color: #e0f7ff;
}

.pagination-content {
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.pagination-content .el-pagination {
  background-color: #fff;
  border-radius: 20px;
  padding: 10px 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}
</style>

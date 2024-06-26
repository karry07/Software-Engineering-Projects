<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="!showFlag">
      <div class="header">
      </div>
      <div class="table-content1">
        <el-table :data="dataList" border stripe v-loading="dataListLoading" style="width: 100%;">
          <el-table-column prop="id" header-align="center" align="center" sortable label="ID"></el-table-column>
          <el-table-column prop="name" header-align="center" align="center" sortable label="名称"></el-table-column>
          <el-table-column prop="value" header-align="center" align="center"  label="图片">
            <template slot-scope="scope">
              <div v-if="scope.row.value">
                <img :src="scope.row.value.split(',')[0]" class="news-image">
              </div>
              <div v-else>无图片</div>
            </template>
          </el-table-column>
          <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
            <template slot-scope="scope">
              <el-button
                type="primary"
                size="mini"
                @click="addOrUpdateHandler(scope.row)"
                icon="el-icon-edit"
              >修改</el-button>
              <el-button
                type="danger"
                size="mini"
                @click="deleteHandler(scope.row)"
                icon="el-icon-delete"
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
    <add-or-update v-else :parent="this" ref="addOrUpdate" :isEditing="isEditing"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./add-or-update";

export default {
  data() {
    return {
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      showFlag: false,
      isEditing: false,
    };
  },
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
        url: this.$api.configpage,
        method: "get",
        params: {
          page: this.pageIndex,
          limit: this.pageSize,
          sort: 'id'
        }
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
    // 添加/修改
    addOrUpdateHandler(row) {
      this.isEditing = !!row.id;
      this.showFlag = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(row);
      });
    },
    // 删除配置
    deleteHandler(row) {
      this.$confirm('此操作将永久删除该配置, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$api.configpage + '/' + row.id,
          method: 'delete'
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getDataList();
          } else {
            this.$message.error(data.msg);
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  }
};
</script>

<style lang="scss" scoped>
//主要内容样式
.main-content {
  padding: 20px;
  background: linear-gradient(135deg, #f5f7fa, #c3cfe2);
  border-radius: 8px;//圆角
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.header {
  display: flex;
  justify-content: flex-end;  //右对齐
  margin-bottom: 20px;
}

.table-content1 {
  background: #ffffff;  //背景色
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin-top: 80px;
}

.el-table th {
  background-color: #f9fafc;  //表头背景色
  color: #333;         //字体颜色     
  font-weight: bold;  //字体加粗
  text-align: center;
}

.el-table td {
  background-color: #ffffff;
  color: #666;
  text-align: center;
}

.el-table .el-table__row:nth-child(odd) {
  background-color: #f4f8fb;
}

//按钮样式
.el-button {
  margin: 5px 0;
  background: linear-gradient(135deg, #6dd5fa, #2980b9);
  border: none; //去掉边框
  color: #fff;
  &:hover {
    background: linear-gradient(135deg, #2980b9, #6dd5fa);
  }
}

//分页样式
.el-pagination {
  margin-top: 20px;
  text-align: right;
}

.el-pagination .el-pager li:not(.active) {
  background-color: #f5f5f5;
  color: #666;
  border-radius: 4px;
}

.el-pagination .el-pager li.active {
  background-color: #409eff;
  color: #fff;
  border-radius: 4px;
}

.el-pagination .el-icon-arrow-left,
.el-pagination .el-icon-arrow-right {
  color: #409eff;
}

.news-image {
  width: 300px;
  height: 200px;
  object-fit: contain;  //图片不变形
}
</style>

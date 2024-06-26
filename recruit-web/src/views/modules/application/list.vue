<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="企业名称">
          <el-input v-model="searchForm.companyname"
                    placeholder="企业名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="用户名" v-if="isAuth('application','查询用户')">
          <el-input v-model="searchForm.username"
                    placeholder="用户名" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button round @click="search()">查询</el-button>
          <el-button
              v-if="isAuth('application','新增')"
              type="primary"
              @click="addOrUpdateHandler()"
              round
          >新增
          </el-button>
          <el-button
              v-if="isAuth('application','删除')"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
              round
          >删除
          </el-button>
        </el-form-item>
      </el-form>
      <!-- 招聘者信息列表 -->
      <div class="table-content">
        <el-table
            :data="dataList"
            style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="企业编号:">
                  <span>{{ props.row.companynumber }}</span>
                </el-form-item>
                <el-form-item label="企业名称:">
                  <span>{{ props.row.companyname }}</span>
                </el-form-item>
                <el-form-item label="负责人:">
                  <span>{{ props.row.personincharge }}</span>
                </el-form-item>
                <el-form-item label="联系电话:">
                  <span>{{ props.row.contactphone }}</span>
                </el-form-item>
                <el-form-item label="招聘岗位:">
                  <span>{{ props.row.position }}</span>
                </el-form-item>
                <el-form-item label="用户名:">
                  <span>{{ props.row.username }}</span>
                </el-form-item>
                <el-form-item label="求职者姓名:">
                  <span>{{ props.row.fullname }}</span>
                </el-form-item>
                <el-form-item label="求职者电话:">
                  <span>{{ props.row.phone }}</span>
                </el-form-item>
                <el-form-item label="">
                  <el-button type="text" size="small" @click="download(props.row.resume)">查看简历</el-button>
                </el-form-item>
                <el-form-item label="审核:">
                  <span v-if="props.row.ifaudit === '否'">未通过</span>
                  <span v-else-if="props.row.ifaudit === '是'">通过</span>
                </el-form-item>
                <el-form-item label="审核回复:">
                  <span>{{ props.row.auditresponse }}</span>
                </el-form-item>
                <el-form-item label="当前状态:">
                  <span v-if="props.row.status === 'submitted'">已提交</span>
                  <span v-else-if="props.row.status === 'audited'">已审核</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column
              prop="companyname"
              header-align="center"
              align="center"
              sortable
              label="企业名称">
            <template slot-scope="scope">
              {{ scope.row.companyname }}
            </template>
          </el-table-column>
          <el-table-column
              prop="position"
              header-align="center"
              align="center"
              sortable
              label="招聘岗位">
            <template slot-scope="scope">
              {{ scope.row.position }}
            </template>
          </el-table-column>
          <el-table-column
              prop="fullname"
              header-align="center"
              align="center"
              sortable
              label="求职者姓名">
            <template slot-scope="scope">
              {{ scope.row.fullname }}
            </template>
          </el-table-column>
          <el-table-column

              prop="ifaudit"
              header-align="center"
              align="center"
              sortable
              label="审核结果">
            <template slot-scope="scope">
    <span :style="{ backgroundColor: scope.row.ifaudit == '是' ? 'green' : 'red', padding: '5px', color: 'white' }">
      {{ scope.row.ifaudit == '是' ? '通过' : '未通过' }}
    </span>
            </template>
          </el-table-column>
          <el-table-column
              prop="status"
              header-align="center"
              align="center"
              sortable
              label="投递状态">
            <template slot-scope="scope">
              <span v-if="scope.row.status === 'submitted'"
                    :style="{ backgroundColor: 'lightblue', padding: '5px', color: 'white' }">
                    已提交
              </span>
              <span v-else-if="scope.row.status === 'audited'"
                    :style="{ backgroundColor: 'lightgreen', padding: '5px', color: 'white' }">
                    已审核
              </span>
            </template>
          </el-table-column>

          <el-table-column
              header-align="center"
              align="center"
              label="操作">
            <template slot-scope="scope">
              <el-button v-if="isAuth('application','审核')" type="text" icon="el-icon-edit" size="small"
                         @click="shDialog(scope.row)">审核
              </el-button>
              <el-button v-if="isAuth('application','删除')" type="text" icon="el-icon-delete" size="small"
                         @click="deleteHandler(scope.row.id)">删除
              </el-button>
              <el-button v-if="!isAuth('application','审核')" type="text" icon="el-icon-edit" size="small"
                         @click="addOrUpdateHandler(scope.row.id,'info')">详情
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
    <el-dialog
        title="审核"
        :visible.sync="sfshVisiable"
        width="50%">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="审核状态">
          <el-select v-model="shForm.ifaudit" placeholder="审核状态">
            <el-option label="通过" value="是"></el-option>
            <el-option label="不通过" value="否"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" :rows="8" v-model="shForm.auditresponse"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="shDialog">取 消</el-button>
        <el-button type="primary" @click="shHandler">确 定</el-button>
      </span>
    </el-dialog>


  </div>
</template>
<script>
import AddOrUpdate from "./add-or-update";
import {isAuth} from "@/utils/utils";

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
      if (this.searchForm.companyname != '' && this.searchForm.companyname != undefined) {
        params['companyname'] = '%' + this.searchForm.companyname + '%'
      }
      if (this.searchForm.username != '' && this.searchForm.username != undefined) {
        params['username'] = '%' + this.searchForm.username + '%'
      }
      this.$http({
        url: "application/page",
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
    // 审核窗口
    shDialog(row) {
      this.sfshVisiable = !this.sfshVisiable;
      if (row) {
        this.shForm = {
          companynumber: row.companynumber,
          companyname: row.companyname,
          personincharge: row.personincharge,
          contactphone: row.contactphone,
          position: row.position,
          username: row.username,
          fullname: row.fullname,
          phone: row.phone,
          resume: row.resume,
          ifaudit: row.ifaudit,
          auditresponse: row.auditresponse,
          id: row.id
        }
      }
    },
    // 审核
    shHandler() {
      this.$confirm(`确定操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.shForm.status = 'audited';
        this.$http({
          url: "application/update",
          method: "post",
          data: this.shForm
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.getDataList();
                this.shDialog()
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
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
          url: "application/delete",
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
  background-image: url('../../../assets/img/backgroungimg (3).png'); /* 确保路径正确 */
  background-size: cover;
  background-position: center;
  padding: 20px;
  min-height: 100vh;
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

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>

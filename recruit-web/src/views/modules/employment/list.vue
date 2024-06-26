<template>
  <div class="main-content">
    <!-- 列表页 -->
    <div v-if="showFlag">
      <el-form :inline="true" :model="searchForm" class="form-content">
        <el-form-item label="">
          <el-input v-model="searchForm.companyname"
                    placeholder="企业名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="searchForm.position"
                    placeholder="招聘岗位" clearable></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="searchForm.jobcategory"
                    placeholder="岗位类别" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button round @click="search()">查询</el-button>
          <el-button
              v-if="isAuth('employment','新增')"
              type="primary"
              @click="addOrUpdateHandler()"
              round
          >新增
          </el-button>
          <el-button
              v-if="isAuth('employment','删除')"
              :disabled="dataListSelections.length <= 0"
              type="danger"
              @click="deleteHandler()"
              round
          >删除
          </el-button>
        </el-form-item>
      </el-form>
      <div class="table-content">
        <!-- 企业信息表格 -->
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
                <el-form-item label="招聘人数:">
                  <span>{{ props.row.recruitnum }}</span>
                </el-form-item>
                <el-form-item label="岗位类别:">
                  <span>{{ props.row.jobcategory }}</span>
                </el-form-item>
                <el-form-item label="学历要求:">
                  <span>{{ props.row.education }}</span>
                </el-form-item>
                <el-form-item label="岗位要求:">
                  <span>{{ props.row.demand }}</span>
                </el-form-item>
                <el-form-item label="">

                </el-form-item>
                <el-form-item label="工作职责:" prop="duty">
                  <span v-html="props.row.duty"></span>
                </el-form-item>
                <el-form-item label="">
                </el-form-item>
                <el-form-item label="公司照片:">
                  <el-popover
                      placement="right"
                      width="100"
                      trigger="hover"
                      content="点击图片查看大图">
                    <div slot="reference" v-if="props.row.picture">
                      <img :src="props.row.picture.split(',')[0]" width="100" height="100"
                           @click="showLargeImage(props.row.picture.split(',')[0])"
                      >
                    </div>
                    <div v-else>无图片</div>
                  </el-popover>
                  <el-button type="success" plain icon="el-icon-map-location" class="mini"
                             @click="showMap(props.row.companyname)">查看地图
                  </el-button>
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
              prop="jobcategory"
              header-align="center"
              align="center"
              label="岗位类别"
              column-key="jobcategory"
              :filters="[{text: '专技岗位', value: '专技岗位'}, {text: '工勤岗位', value: '工勤岗位'}, {text: '管理岗位', value: '管理岗位'}]"
              :filter-method="filterHandler">
            <template slot-scope="scope">
              {{ scope.row.jobcategory }}
            </template>
          </el-table-column>
          <el-table-column
              prop="education"
              header-align="center"
              align="center"
              label="学历"
              column-key="education"
              :filters="[{text: '博士', value: '博士'}, {text: '硕士', value: '硕士'}, {text: '本科', value: '本科'}, {text: '专科', value: '专科'}]"
              :filter-method="filterHandler">
            <template slot-scope="scope">
              {{ scope.row.education }}
            </template>
          </el-table-column>
          <el-table-column
              header-align="center"
              align="center"
              label="操作">
            <template slot-scope="scope">
              <el-button v-if="isAuth('employment','查看')" type="text" icon="el-icon-edit" size="small"
                         @click="addOrUpdateHandler(scope.row.id,'info')">详情
              </el-button>
              <el-button v-if="isAuth('employment','应聘')" type="text" icon="el-icon-edit" size="small"
                         @click="yingpinxinxiCrossAddOrUpdateHandler(scope.row,'cross')">可编辑投递
              </el-button>
              <el-button v-if="isAuth('employment','修改')" type="text" icon="el-icon-edit" size="small"
                         @click="addOrUpdateHandler(scope.row.id)">修改
              </el-button>
              <el-button v-if="isAuth('employment','删除')" type="text" icon="el-icon-delete" size="small"
                         @click="deleteHandler(scope.row.id)">删除
              </el-button>
              <el-button v-if="isAuth('employment','应聘') " type="text" icon="el-icon-edit" size="small"
                         @click="showApplyDialog(scope.row)">一键投递
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

    <yingpinxinxi-cross-add-or-update v-if="yingpinxinxiCrossAddOrUpdateFlag" :parent="this"
                                      ref="yingpinxinxiCrossaddOrUpdate"></yingpinxinxi-cross-add-or-update>


    <!-- 投递简历对话框 -->
    <el-dialog title="投递简历" :visible.sync="applyDialogVisible" width="50%">
      <el-form :model="applyForm" :rules="rules" ref="applyForm">
        <el-form-item label="公司账号">
          <el-input v-model="applyForm.companynumber" disabled></el-input>
        </el-form-item>
        <el-form-item label="公司名称">
          <el-input v-model="applyForm.companyname" disabled></el-input>
        </el-form-item>
        <el-form-item label="负责人">
          <el-input v-model="applyForm.personincharge" disabled></el-input>
        </el-form-item>
        <el-form-item label="公司联系电话">
          <el-input v-model="applyForm.contactphone" disabled></el-input>
        </el-form-item>
        <el-form-item label="职位">
          <el-input v-model="applyForm.position" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="applyForm.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="fullname">
          <el-input v-model="applyForm.fullname" disabled></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model="applyForm.phone"></el-input>
        </el-form-item>
        <!--        <el-form-item label="简历" prop="resume">-->
        <!--          <el-input type="textarea" v-model="applyForm.resume"></el-input>-->
        <!--        </el-form-item>-->
        <el-form-item label="简历" prop="resume">
          <file-upload
              tip="点击上传简历"
              action="file/upload"
              :limit="1"
              :multiple="true"
              :fileUrls="applyForm.resume?applyForm.resume:''"
              @change="jianliUploadChange"
          ></file-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="applyDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitApplication">提交</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="largeImageVisible" width="50%">
      <img :src="largeImageUrl" style="width: 100%; height: auto;">
    </el-dialog>

    <el-dialog
        :visible.sync="outMap"
        append-to-body
        class="mapUI">
      <!-- 地图 -->
      <div>
        <div id="container" style="height:660px;"></div>
      </div>
      <div class="mapUI-describe">
        <!-- <div>
          <span>当前坐标:</span>
          <span>{{mapData.point}}</span>
        </div> -->
        <div>
          <span>企业名称:</span>
          <span>{{ mapcompanyname }}</span>
        </div>
        <div>
					<span>省份:</span>
					<span>{{mapData.city}}</span>
				</div>
				<div>
					<span>目的地:</span>
					<span>{{mapData.district}}</span>
				</div>
        <div>
          <span>具体位置:</span>
          <span>{{ mapData.address }}</span>
        </div>
        <div>
          <span>退出地图预览</span>
          <span>
						<el-button type="primary" round @click="outMap=false">退出</el-button>
						</span>
        </div>
         <div>你可能想找:</div>
        <div v-if="mapData.searchList" class="searchList">
          <div v-for="(item,index) in mapData.searchList" :key="index" @click="mapcompanynamemohu = item.title"
          :title="item.address" >
            <span @click="showMap(mapcompanynamemohu)">({{item.address.substring(0,2)}}){{item.title}}</span>
          </div>
        </div>
      </div>
    </el-dialog>

  </div>
</template>
<script>
import AddOrUpdate from "./add-or-update";
import yingpinxinxiCrossAddOrUpdate from "../application/add-or-update";
import {getMapAPI} from '@/utils/validate';
import {VueJsonp} from 'vue-jsonp';
import Vue from 'vue';

Vue.use(VueJsonp)
export default {
  data() {
    return {
      outMap: false,
      mapData: {
        point: "", city: null, district: null, searchList: null, address: null
      },
      mapcompanyname: '',
      mapcompanynamemohu: '',
      jobcategoryOptions: [],
      xueliOptions: [],
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
      yingpinxinxiCrossAddOrUpdateFlag: false,
      applyDialogVisible: false,
      applyForm: {
        companynumber: '', // 新增公司编号字段
        companyname: '',   // 新增公司名称字段
        personincharge: '', // 新增负责人字段
        contactphone: '',   // 新增联系电话字段
        position: '',       // 新增职位字段
        username: '',       // 新增用户名字段
        fullname: '',       // 新增全名字段
        phone: '',
        resume: '',
        //jobId: null        // 存储当前投递的招聘信息ID
      },
      rules: {
        phone: [
          {required: true, message: '请输入联系方式', trigger: 'blur'}
        ],
        resume: [
          {required: true, message: '请提交简历', trigger: 'blur'}
        ]
      },
      largeImageVisible: false,
      largeImageUrl: '',
      visible: false
    };
  },
  mounted() {
    this.init();
    this.getDataList();
    this.loadScript();
  },
  filters: {
    htmlfilter: function (val) {
      return val.replace(/<[^>]*>/g).replace(/undefined/g, '');
    }
  },
  components: {
    AddOrUpdate,
    yingpinxinxiCrossAddOrUpdate,
  },
  methods: {
    loadScript() {
      var script = document.createElement('script');
      script.type = 'text/javascript';
      script.src = 'https://map.qq.com/api/js?v=2.exp&key=K35BZ-MUJYJ-5NKFK-DT5K2-CWTQS-AHF6B'; // 请替换为你的API密钥
      document.body.appendChild(script);
    },
    yingpinxinxiCrossAddOrUpdateHandler(row, type) {
      this.showFlag = false;
      this.addOrUpdateFlag = false;
      this.yingpinxinxiCrossAddOrUpdateFlag = true;
      this.$storage.set('crossObj', row);
      this.$storage.set('crossTable', 'employment');
      this.$nextTick(() => {
        this.$refs.yingpinxinxiCrossaddOrUpdate.init(row.id, type);
      });
    },
    init() {
      let sessionTable = this.$storage.get('sessionTable');
      if (sessionTable === 'generaluser') {
        // 获取用户信息
        this.$http({
          url: `${this.$storage.get('sessionTable')}/session`,
          method: "get"
        }).then(({data}) => {
          if (data && data.code === 0) {
            var json = data.data;
            this.applyForm.username = json.username
            this.applyForm.fullname = json.fullname
            this.applyForm.phone = json.phone
            console.log(this.applyForm)
          } else {
            this.$message.error(data.msg);
          }
        });
      }
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
      this.$http({
        url: `option/educationInfo/education`,
        method: "get"
      }).then(({data}) => {
        if (data && data.code === 0) {
          this.xueliOptions = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    search() {
      this.pageIndex = 1;
      this.getDataList();
    },
    showLargeImage(url) {
      this.largeImageUrl = url;
      this.largeImageVisible = true;
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
      if (this.searchForm.position != '' && this.searchForm.position != undefined) {
        params['position'] = '%' + this.searchForm.position + '%'
      }
      if (this.searchForm.jobcategory != '' && this.searchForm.jobcategory != undefined) {
        params['jobcategory'] = '%' + this.searchForm.jobcategory + '%'
      }
      if (this.searchForm.education != '' && this.searchForm.education != undefined) {
        params['education'] = '%' + this.searchForm.education + '%'
      }
      this.$http({
        url: "employment/page",
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
    // 下载
    download(file) {
      window.open(`${file}`)
    },
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
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
          url: "employment/delete",
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
    submitApplication() {
      this.$refs.applyForm.validate((valid) => {
        if (valid) {
          this.$http({
            url: 'application/save',
            method: 'post',
            data: this.applyForm
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.$message({
                message: '投递成功',
                type: 'success'
              });
              this.applyDialogVisible = false;
            } else {
              this.$message.error(data.msg);
            }
          }).catch((error) => {
            this.$message.error('提交失败');
            console.error('Error:', error);
          });
        }
      });
    },
    showApplyDialog(row) {
      //this.applyForm.jobId = row.id;
      this.applyForm.companynumber = row.companynumber;
      this.applyForm.companyname = row.companyname; // 假设招聘信息中包含公司名称
      this.applyForm.personincharge = row.personincharge;
      this.applyForm.position = row.position;
      this.applyForm.contactphone = row.contactphone;
      this.applyDialogVisible = true;
    },
    jianliUploadChange(fileUrls) {
      this.applyForm.resume = fileUrls;
    },
    // 查看地图
    showMap(companyName) {
      // 显示地图组件,优先执行,保证地图组件能够正常创建
      this.outMap = true;
      this.mapcompanyname = companyName;
      this.searchMap()
    },
    searchMap() {
      this.$message.success("正在获取地图数据")
      let url = "https://apis.map.qq.com/ws/place/v1/suggestion";
      let __this = this;
      this.$jsonp(url, {
        key: getMapAPI(),
        keyword: this.mapcompanyname,
        // region: "深圳",
        output: "jsonp",
      })
          .then((res) => {
            res.data.forEach((item) => {
              item.value = item.title;//因为element的远程搜索只能识别value所以需要对返回的数据进行一下处理
            });
             console.log("res.data",res.data);
            __this.mapData.searchList = res.data
            if (res.data[0]) {
              __this.mapData.address = res.data[0].address
              __this.mapData.city = res.data[0].province
					__this.mapData.district = res.data[0].city + "/"+res.data[0].district
              __this.createMap(res.data[0].location.lat, res.data[0].location.lng)
            }
          })
          .catch((err) => {
            console.log(err);
          });
    },
    // 生成地图数据
    createMap(w, h) {
      //步骤：定义map变量 调用 qq.maps.Map() 构造函数   获取地图显示容器
      //自己设置地图中心点
      let qq = window.qq;
      console.log('qq:', qq.maps)
      var myLatlng = new qq.maps.LatLng(w, h);
      this.mapData.point = w + "," + h;
      //定义工厂模式函数
      var myOptions = {
        zoom: 15,               //设置地图缩放级别
        center: myLatlng,      //设置中心点样式
        mapTypeId: qq.maps.MapTypeId.ROADMAP  //设置地图样式详情参见MapType
      }
      //获取父元素
      let content = document.getElementById("container");
      //循环删除子元素，一直删除第一个子元素直到没有子元素即为清空
      while (content.hasChildNodes()) {
        content.removeChild(content.firstChild);
      }
      //获取dom元素添加地图信息
      var map = new qq.maps.Map(document.getElementById("container"), myOptions);
      //给定位的位置添加图片标注
      var marker = new qq.maps.Marker({
        position: myLatlng,
        map: map
      });
      //给定位的位置添加文本标注
      var marker1 = new qq.maps.Label({
        position: myLatlng,
        map: map,
        content: this.form.companyname
      });
      this.$message.success("获取地图数据成功!")
    },



}
}
;
</script>
<style lang="scss" scoped>
.main-content {
  background-image: url('../../../assets/img/backgroungimg (5).png'); /* 确保路径正确 */
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
  max-width: 950px; /* 限制最大宽度 */
}

.table-content {
  width: 100%;
  max-width: 950px; /* 限制最大宽度 */
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

.mapUI {
	overflow: hidden;
	z-index: 9999;
	.mapUI-describe {
		background: #0000005e;
		color: white !important;
		padding: 5px;
		position: absolute;
		top: 0;
		right: 0;
		border-radius: 5px;
		border-top-left-radius: 0;
		border-bottom-right-radius: 0;
		transition: rgba,width .5s;
		&:hover {
			background: rgba(0, 0, 0, 0.7);
		}

		>div {
			margin-bottom: 20px;
			>span {
				display: inline-block;
			}
			>span:nth-child(1){
				width: 30%;
				text-align: left;
			}
			>span:nth-child(2){
				width: 70%;
				text-align: right;
				// color: black;
			}
		}
		.searchList {
			border: 1px solid black;
			height: 200px;
			overflow: auto;

			div {
				margin: 10px 0;
				padding: 10px 0;
				text-align: center;
				color: black;
				font-size: 16px;
			}
			div:hover {
				background-color: #0000002b;
			}
			div::-webkit-scrollbar {
				width: 2px !important;
			}
		}
	}
	.el-dialog {
		margin-top: 10vh !important;
		width: 80% !important;
		font-size: 25px;
	}
	::v-deep .el-dialog__header {
		display: none;
	}
}
</style>
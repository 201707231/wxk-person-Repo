<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate()" v-permission="'device:add'">添加 </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading="listLoading" border fit highlight-current-row @row-dblclick="showParts">
      <el-table-column align="center" label="设备编号" prop="devID" style="width:20%"></el-table-column>
      <el-table-column align="center" label="设备类型" prop="devType"></el-table-column>
      <el-table-column align="center" label="设备状态" prop="devStatus"></el-table-column>
      <el-table-column align="center" label="设备创建时间" prop="createTime" :formatter="timeFormatter2"></el-table-column>

      <el-table-column align="center" label="管理" width="140" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="showUpdate(scope)" v-permission="'device:update'" size="mini"></el-button>
          <el-button type="danger" icon="el-icon-delete" v-permission="'device:delete'" size="mini"> </el-button>
        </template>
      </el-table-column>

    </el-table>
    <!-- 页码 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.pageNum" :page-size="listQuery.pageRow" :total="totalCount" :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>

    <el-dialog title="设备" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempDev" label-position="left" label-width="80px" style='width: 300px; margin-left:50px;'>
        <el-form-item label="设备编号" required placeholder="请输入">
          <el-input type="text" v-model="tempDev.devID">
          </el-input>
        </el-form-item>
        <el-form-item label="设备类型" required>
          <el-select v-model="tempDev.devType" placeholder="请选择" value-key="defID" >
            <el-option v-for="item in $store.getters.devType" :key="item.defID" :label="item.defName" :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备状态" required>
           <el-select v-model="tempDev.devStatus" placeholder="请选择" value-key="defID" >
            <el-option v-for="item in $store.getters.devStatus" :key="item.defID" :label="item.defName" :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button @click="createDev" v-if="dialogStatus==='create'" type="success">创 建</el-button>
        <el-button type="primary" v-else @click="updateDev">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {Time2GMT8} from '@/utils/index.js'
import BaseSelect from '../../components/MSelect/BaseSelect.vue'
import DevTypeSelect from '../../components/MSelect/DevTypeSelect.vue'
import DevStatusSelect from '../../components/MSelect/DevStatusSelect.vue'

export default {
  components:{ BaseSelect ,DevTypeSelect,DevStatusSelect},
  data() {
    return {
      totalCount: 0, //分页组件--数据总条数
      list: [],//表格的数据
      listLoading: false,//数据加载等待动画
      listQuery: {
        pageNum: 1,//页码
        pageRow: 50,//每页条数
      },
      dialogStatus: 'create',
      dialogFormVisible: false,
      tempDev: {
        devID: '',
        devType: { defID: 0, defName: "" },
        devStatus: { defID: 0, defName: "" }
      },
    }
  },
  created() {
    this.getDevices();
  },

  methods: {
    getDevices() {
      this.listLoading = true
      this.api({
        url: '/dev/list',
        method: 'get',
        params: this.listQuery
      }).then(data => {
        console.log(data)
        this.list = data.list
        this.listLoading = false
        this.totalCount = data.totalCount
      })
    },
    handleSizeChange(val) {
      //改变每页数量
      this.listQuery.pageRow = val
      this.handleFilter();
    },
    handleCurrentChange(val) {
      //改变页码
      this.listQuery.pageNum = val
      this.getDevices();
    },
    handleFilter() {
      //查询事件
      this.listQuery.pageNum = 1
      this.getDevices()
    },

    showParts(row) {
      console.log("showParts")
      console.log(row)
        this.$router.push({
　　　　　　path: `/device/devParts`,
　　　　　　query: { devID: row.devID }
　　　　});
    },
    showUpdate(scope) {
      this.tempDev.devID = scope.row.devID
      this.tempDev.devType.defID = scope.row.devTypeID
      this.tempDev.devType.defName = scope.row.devType
      this.tempDev.devStatus.defID = scope.row.devStatusID
      this.tempDev.devStatus.defName = scope.row.devStatus
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    showCreate(){
      this.tempDev.devID=""
      this.tempDev.devType.defID=-1
      this.tempDev.devType.defName=""
      this.tempDev.devStatus.defID=-1
      this.tempDev.devStatus.defName=""
      this.dialogStatus="create"
      this.dialogFormVisible=true
    },
    createDev(){
      this.api({
        url: '/dev/add',
        method: 'get',
        params: {
          devID: this.tempDev.devID,
          devTypeID: this.tempDev.devType.defID,
          devStatusID: this.tempDev.devStatus.defID
        }
      }).then(data => {
          this.$message.success('创建成功')
          this.dialogFormVisible = false
          this.getDevices()
      })
    },
    updateDev(scope) {
      this.api({
        url: '/dev/update',
        method: 'get',
        params: {
          devID: this.tempDev.devID,
          devTypeID: this.tempDev.devType.defID,
          devStatusID: this.tempDev.devStatus.defID
        }
      }).then(data => {
        this.devStatusLoading = false
        this.dialogFormVisible = false
        var idx = this.list.findIndex(x=> x.devID == this.tempDev.devID);
        if (idx>=0) {
          this.list[idx].devType = this.tempDev.devType.defName
          this.list[idx].devTypeID = this.tempDev.devType.defID
          this.list[idx].devStatus = this.tempDev.devStatus.defName
          this.list[idx].devStatusID = this.tempDev.devStatus.defID
        }
      })
    },
    timeFormatter2(a,b,value,c){
      return Time2GMT8(value)
    }

  }
}
</script>

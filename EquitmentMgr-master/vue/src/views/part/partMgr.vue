<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate()" v-permission="'part:add'">添加 </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading="listLoading" border fit highlight-current-row >
      <el-table-column align="center" label="配件编号" prop="partID" style="width:20%"></el-table-column>
      <el-table-column align="center" label="配件类型" prop="partType"></el-table-column>
      <el-table-column align="center" label="配件状态" prop="partStatus"></el-table-column>
      <el-table-column align="center" label="批次" prop="partBatchNum"></el-table-column>
      <el-table-column align="center" label="备注" prop="partNote" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime" :formatter="timeFormatter2"></el-table-column>

      <el-table-column align="center" label="管理" width="140" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="showUpdate(scope)" v-permission="'part:update'" size="mini"> </el-button>
          <el-button type="danger" icon="el-icon-delete" v-permission="'part:delete'" size="mini"> </el-button>
        </template>
      </el-table-column>

    </el-table>
    <!-- 页码 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.pageNum" :page-size="listQuery.pageRow" :total="totalCount" :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>

    <el-dialog title="配件" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempPart" label-position="left" label-width="80px" style='width: 300px; margin-left:50px;'>
        <el-form-item label="配件编号" required placeholder="请输入">
          <el-input type="text" v-model="tempPart.partID">
          </el-input>
        </el-form-item>
        <el-form-item label="配件类型" required>
          <el-select v-model="tempPart.partType" placeholder="请选择" value-key="defID" >
            <el-option v-for="item in $store.getters.partType" :key="item.defID" :label="item.defName" :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="配件状态" required>
          <el-select v-model="tempPart.partStatus" placeholder="请选择" value-key="defID" >
            <el-option v-for="item in $store.getters.partStatus" :key="item.defID" :label="item.defName" :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="批次" > 
          <el-input type="text" v-model="tempPart.partBatchNum"/>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="text" v-model="tempPart.partNote"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button @click="createPart" v-if="dialogStatus==='create'" type="success">创 建</el-button>
        <el-button type="primary" v-else @click="updatePart">修 改</el-button>
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
      tempPart: {
        partID: '',
        partType: { defID: 0, defName: "" },
        partStatus: { defID: 0, defName: "" },
        partBatchNum:"",
        partNote:"",
      },
    }
  },
  created() {
    this.getParts();
  },

  methods: {
    getParts() {
      this.listLoading = true
      this.api({
        url: '/part/list',
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
      this.getParts();
    },
    handleFilter() {
      //查询事件
      this.listQuery.pageNum = 1
      this.getParts()
    },

    showUpdate(scope) {
      this.tempPart.partID = scope.row.partID
      this.tempPart.partType.defID = scope.row.partTypeID
      this.tempPart.partType.defName = scope.row.partType
      this.tempPart.partStatus.defID = scope.row.partStatusID
      this.tempPart.partStatus.defName = scope.row.partStatus
      this.tempPart.partBatchNum=scope.row.partBatchNum
      this.tempPart.partNote=scope.row.partNote
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    showCreate(){
      this.tempPart.partID=""
      this.tempPart.partType.defID=-1
      this.tempPart.partType.defName=""
      this.tempPart.partStatus.defID=-1
      this.tempPart.partStatus.defName=""
      this.tempPart.partBatchNum=""
      this.tempPart.partNote=""
      this.dialogStatus="create"
      this.dialogFormVisible=true
    },
    createPart(){
      this.api({
        url: '/part/add',
        method: 'get',
        params: {
          partID: this.tempPart.partID,
          partTypeID: this.tempPart.partType.defID,
          partStatusID: this.tempPart.partStatus.defID,
          partBatchNum:this.tempPart.partBatchNum,
          partNote:this.tempPart.partNote
        }
      }).then(data => {
          this.$message.success('创建成功')
          this.dialogFormVisible = false
          this.getParts()
      })
    },
    updatePart(scope) {
      this.api({
        url: '/part/update',
        method: 'get',
        params: {
          partID: this.tempPart.partID,
          partTypeID: this.tempPart.partType.defID,
          partStatusID: this.tempPart.partStatus.defID,
          partBatchNum:this.tempPart.partBatchNum,
          partNote:this.tempPart.partNote
        }
      }).then(data => {
        this.dialogFormVisible = false
        var idx = this.list.findIndex(x=> x.partID == this.tempPart.partID);
        if (idx>=0) {
          this.list[idx].partType = this.tempPart.partType.defName
          this.list[idx].partTypeID = this.tempPart.partType.defID
          this.list[idx].partStatus = this.tempPart.partStatus.defName
          this.list[idx].partStatusID = this.tempPart.partStatus.defID
          this.list[idx].partBatchNum=this.tempPart.partBatchNum
          this.list[idx].partNote=this.tempPart.partNote
        }
      })
    },
    timeFormatter2(a,b,value,c){
      return Time2GMT8(value)
    }

  }
}
</script>

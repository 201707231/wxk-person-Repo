<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate()" v-permission="'device:add'">添加 </el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-table :data="list" v-loading="listLoading" border fit highlight-current-row>
      <el-table-column align="center" label="定义编号" prop="defID" style="width:20%"></el-table-column>
      <el-table-column align="center" label="定义类型" prop="defType" :filters="filters" :filter-method="filterMethod"></el-table-column>
      <el-table-column align="center" label="定义名称" prop="defName"></el-table-column>

      <el-table-column align="center" label="管理" width="140" fixed="right" v-if="false">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" @click="showUpdate(scope)" v-permission="'device:update'" size="mini"></el-button>
          <el-button type="danger" icon="el-icon-delete" v-permission="'device:delete'" size="mini"> </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="定义" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempDef" label-position="left" label-width="80px" style='width: 300px; margin-left:50px;'>

        <el-form-item label="定义类型" required>
          <el-select v-model="tempDef.defType" placeholder="请选择" value-key="defID" @change="getAppropriateDefID(tempDef.defType)">
            <el-option v-for="item in $store.getters.defType" :key="item.defID" :label="item.defName" :value="item.defID">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="定义编号" required placeholder="请输入">
          <el-input type="text" v-model="tempDef.defID" disabled>
          </el-input>
        </el-form-item>

        <el-form-item label="定义名称" required>
          <el-input type="text" v-model="tempDef.defName" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button @click="createDef" v-if="dialogStatus==='create'" type="success">创 建</el-button>
        <el-button type="primary" v-else @click="updateDef">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>

export default {
  data() {
    return {
      list: [],//表格的数据
      listLoading: false,//数据加载等待动画
      dialogStatus: 'create',
      dialogFormVisible: false,
      tempDef: {
        defID: "",
        defType: "",
        defName: ""
      },
      filters: this.$store.getters.defType.map((item) => {
        item.text = item.defName
        item.value = item.defID
        return item
      })
    }
  },
  created() {
    this.getDefs();
  },
  methods: {
    getDefs() {
      this.listLoading = true
      this.api({
        url: '/defs/list',
        method: 'get',
      }).then(data => {
        console.log(data)
        this.list = data.list
        this.listLoading = false
      })
    },
    showUpdate(scope) {
      this.tempDef.defID = scope.row.defID
      this.tempDef.defType = scope.row.defType
      this.tempDef.defName = scope.row.defName
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
    },
    showCreate() {
      this.tempDef.defID = ""
      this.tempDef.defType = -1
      this.tempDef.defName = ""
      this.dialogStatus = "create"
      this.dialogFormVisible = true
    },
    createDef() {
      this.api({
        url: '/defs/add',
        method: 'get',
        params: this.tempDef
      }).then(data => {
        this.$message.success('创建成功')
        this.dialogFormVisible = false
        this.getDefs()
        // this.$store.commit ("updateDef")
        this.$store.dispatch("GetDefs")
      })
    },
    updateDef(scope) {
      this.api({
        url: '/defs/update',
        method: 'get',
        params: this.tempDef
      }).then(data => {
        this.devStatusLoading = false
        this.dialogFormVisible = false
        var idx = this.list.findIndex(x => x.defID == this.tempDef.devID);
        if (idx >= 0) {
          this.list[idx].defType = this.tempDef.devType.defName
          this.list[idx].defName = this.tempDef.devType.defID
        }
      })
    },
    getAppropriateDefID(defTypeID) {
      this.api({
        url: '/defs/appropriateID',
        method: 'get',
        params: { defTypeID: defTypeID }
      }).then((data) => {
        console.log(data);
        if (data != 0)
          this.tempDef.defID = data
        else{
          this.$message.warning('出现问题,请联系管理员')
        }
      })
    },
    filterMethod(value, row, column) {
      const property = column['property']
      console.log("property", property)
      return row[property] === value
    },
    timeFormatter2(a, b, value, c) {
      return Time2GMT8(value)
    }
  }
}
</script>

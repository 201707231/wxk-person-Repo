<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" v-permission="'part:add'">为设备添加配件 </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading="listLoading" border fit highlight-current-row>
      <el-table-column align="center" label="配件编号" prop="partID"></el-table-column>
      <el-table-column align="center" label="配件类型" prop="partType"></el-table-column>
      <el-table-column align="center" label="配件状态" prop="partStatus"></el-table-column>
      <el-table-column align="center" label="批次" prop="partBatchNum"></el-table-column>
      <el-table-column align="center" label="备注" prop="partNote"></el-table-column>
      <el-table-column align="center" label="创建时间" prop="createTime" :formatter="TimeFormatter"></el-table-column>

      <el-table-column align="center" label="管理">
        <template slot-scope="scope">
          <el-button type="danger" icon="delete" v-permission="'device:delete'"> 删除 </el-button>
        </template>
      </el-table-column>

    </el-table>

  </div>
</template>

<script>
import {Time2GMT8} from '@/utils/index.js'

export default {
  data() {
    return {
      devID: "",
      list: [],
      listLoading: false,
    }
  },
  mounted() {
    console.log("devParts Mounted");
  },
  created() {
    console.log("devParts created")
    this.devID = this.$route.query.devID
    console.log("devID:", this.devID)
    if (this.devID)
      this.getDevParts();
  },
  methods: {
    getDevParts() {
      console.log("getDevParts")
      this.listLoading = true
      this.api({
        url: '/dev/listDevParts',
        method: 'get',
        params: { devID: this.devID }
      }).then(data => {
        console.log(data)
        this.list = data.list
        this.listLoading = false
      })
    },
    TimeFormatter(a,b,value,c){
      return Time2GMT8(value);
    }


  }

}

</script>

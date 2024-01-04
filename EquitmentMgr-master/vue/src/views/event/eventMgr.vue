<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" v-permission="'event:add'" @click="addEvent">添加 </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-form :inline="true">
      <el-form-item label="设备">
        <el-select v-model="filter.dev" value-key="devID" filterable clearable>
          <el-option v-for="item in devList" :key="item.devID" :label="item.devID" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="配件">
        <el-select v-model="filter.part" value-key="partID" filterable clearable>
          <el-option v-for="item in partList" :key="item.partID" :label="item.partID" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="事件阶段">
        <el-select v-model="filter.eventStage" value-key="defID" clearable>
          <el-option v-for="item in $store.getters.eventStage" :key="item.defID" :label="item.defName" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="事件类型">
        <el-select v-model="filter.eventType" value-key="defID" filterable clearable>
          <el-option v-for="item in $store.getters.eventType" :key="item.defID" :label="item.defName" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="事件时间">
        <el-date-picker v-model="filter.eventTimeInterval" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" format="yyyy-MM-dd" value-format="yyyy-MM-dd" />
      </el-form-item>
      <el-form-item label="关键字">
        <el-input v-model="filter.keyWord" />
      </el-form-item>
      <el-button type="primary" @click="getEvents">查询</el-button>
    </el-form>

    <el-table :data="list" v-loading="listLoading" border fit highlight-current-row>
      <el-table-column align="center" label="发生阶段" prop="eventStage"></el-table-column>
      <el-table-column align="center" label="事件类型" prop="eventType"></el-table-column>
      <el-table-column align="center" label="发生时间" prop="eventTime" :formatter="timeFormatter2"></el-table-column>
      <el-table-column align="center" label="详情" prop="eventDetail" show-overflow-tooltip></el-table-column>
      <el-table-column align="center" label="备注" prop="eventNote" show-overflow-tooltip></el-table-column>

      <el-table-column align="center" label="管理" width="140" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" v-permission="'event:update'" size="mini"></el-button>
          <el-button type="danger" icon="el-icon-delete" v-permission="'event:delete'" size="mini"> </el-button>
        </template>
      </el-table-column>

    </el-table>
    <!-- 页码 -->
    <el-pagination :current-page="filter.pageNum" :page-size="filter.pageRow" :page-sizes="[10, 20, 50, 100]" layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>

  </div>
</template>

<script>

import { Time2GMT8 } from '@/utils/index.js'

export default {
  data() {
    return {
      filter: {
        dev:{},
        part:{},
        eventStage:{},
        eventType:{},
        eventTimeInterval: [],
        keyWord: "",
        pageRow: 10,
        pageNum: 1
      },
      list: [],
      listLoading: false,
      devList: [],
      partList: []
    }
  },
  created() {
    this.getEvents();
    this.queryDevList();
    this.queryPartList();
  },
  methods: {
    getEvents() {
      this.filter.eventStageID=this.filter.eventStage.defID
      this.filter.eventTypeID=this.filter.eventType.defID
      if(this.filter.dev){
        this.filter.devID=this.filter.dev.devID
      } else{
          this.filter.devID=undefined
        }
      if(this.filter.part){
        this.filter.partID=this.filter.part.partID
      }else{
        this.filter.partID=undefined
      }
      this.listLoading = true
      this.api({
        header: { 'Content-Type': 'application/json; charset=utf-8' },
        url: '/event/list',
        method: 'post',
        data: this.filter
      }).then(data => {
        this.$message.success('查询完成')
        this.list = data.list
        this.listLoading = false
      })
    },
    timeFormatter2(a, b, value, c) {
      return Time2GMT8(value)
    },
    addEvent() {
      this.$router.push({ "path": "/event/addEvent" })
    },
    queryDevList() {
      this.api({
        url: '/dev/query',
        method: 'get',
        param: this.param ? this.param : {}
      }).then(
        (data) => {
          console.log("queryDevList", data)
          this.devList = data.list
        }
      )
    },
    queryPartList() {
      this.api({
        url: '/part/query',
        method: 'get',
        param: this.param ? this.param : {}
      }).then(
        (data) => {
          console.log("queryPartList", data)
          this.partList = data.list
        }
      )
    },
  }
}
</script>

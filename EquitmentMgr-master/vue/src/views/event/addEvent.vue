<template>
  <div class="app-container">
    <el-form>
      <el-form-item label="事件阶段">
        <el-select v-model="formData.eventStage" value-key="defID">
          <el-option v-for="item in $store.getters.eventStage" :key="item.defID" :label="item.defName" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="事件类型">
        <el-select v-model="formData.eventType" value-key="defID" filterable>
          <el-option v-for="item in $store.getters.eventType" :key="item.defID" :label="item.defName" :value="item" />
        </el-select>
      </el-form-item>
      <el-form-item label="事件时间">
        <el-date-picker type="datetime" v-model="formData.eventTime" format="yyyy-MM-dd HH:mm:ss" value-format="yyyy-MM-dd HH:mm:ss">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="事件详情">
        <el-input type="textarea" v-model="formData.eventDetail" :autosize="{minRows:2,maxRows:20}" />
      </el-form-item>
      <el-form-item label="事件备注">
        <el-switch label="是否添加备注" v-model="isAddNote" />
        <el-input type="textarea" v-model="formData.eventNote" v-if="isAddNote" class="eventNote" :autosize="{minRows:2,maxRows:20}" />
      </el-form-item>
      <el-form-item>
        <el-tooltip content="添加相关设备配件">
          <el-button icon="el-icon-circle-plus" @click="btn_addRelative_click" />
        </el-tooltip>
      </el-form-item>
      <el-form-item v-for="(item,index) in formData.relative" :key="index">
        <span>{{'设备-'+index}}</span>
        <el-select v-model="formData.relative[index].dev" value-key="devID" filterable>
          <el-option v-for="item in devList" :key="item.devID" :label="item.devID" :value="item" />
        </el-select>
        <span>{{'配件-'+index}}</span>
        <el-select v-model="formData.relative[index].part" value-key="partID" filterable>
          <el-option v-for="item in partList" :key="item.partID" :label="item.partType" :value="item" >
            <span style="float: left">{{ item.partType }}</span>
            <span style="float: right; color: #8492a6; font-size: 13px">{{ item.partID }}</span>
          </el-option>
        </el-select>
        <el-button class="el-icon-delete" @click="removeRelativeByIndex(index)" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import DevTypeSelect from '@/components/MSelect/DevTypeSelect.vue'
import DevStatusSelect from '@/components/MSelect/DevStatusSelect.vue'
import BaseSelect2 from '@/components/MSelect/index.js'
import { query } from '@/utils/query.js'

export default {
  components: { DevTypeSelect, DevStatusSelect, BaseSelect2 },
  data() {
    return {
      isAddNote: false,
      formData: {
        eventStage: {},
        eventType: {},
        eventTime: "",
        relative: [],
      },
      devList: [],
      partList: []
    }
  },
  created() {
    this.queryDevList()
    this.queryPartList()
  },
  methods: {
    //TODO 设备配件列表改为本地缓存
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
    btn_addRelative_click() {
      this.formData.relative.push({})
    },
    removeRelativeByIndex(idx) {
      this.formData.relative.splice(idx, 1);
    },
    onSubmit() {
      this.formData.eventTypeID = this.formData.eventType.defID
      this.formData.eventStageID = this.formData.eventStage.defID
      this.formData.relative.map((item)=>{
        if(item.dev){
          item.devID=item.dev.devID
        }
        if(item.part){
          item.partID=item.part.partID
        }
      })
      console.log("onsubmit",this.formData);
      this.api({
        header: { "Content-Type": "application/json;charset=utf-8" },
        url: "/event/add",
        method: 'post',
        data: this.formData
      }).then(
        (data) => {
          this.$message.success("添加成功")
        }
      )
    },

  }

}
</script>

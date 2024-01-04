<template>
  <el-select v-model="ownValue" value-key="key" :loading="isLoading" @change="handleCurrentChange">
    <el-option v-for="item in list" :key="item.key" :label="item.label" :value="item">
    </el-option>
  </el-select>
</template>

<script>
export default {
  data() {
    return {
      list: [],
      isLoading: false,
      ownValue: this.mKey && this.mLabel ?
         function(x,self){x["key"]=x[self.mKey];x["label"]=x[self.mLabel];return x;}(this.mValue,this)
        : this.mValue,
      url: this.initUrl
    }
  },
  model: {
    prop: 'm-value',
    event: 'change'
  },
  props: ['m-value', 'init-url', 'm-key', 'm-label'],
  created() {
    this.getData()
  },
  watch: {
    mValue() {
      this.ownValue = this.mValue
    },
    initUrl() {
      this.url = this.initUrl
    }
  },
  methods: {
    handleCurrentChange() {
      this.$emit('change', this.ownValue);
    },

    getData() {
      this.isLoading = true
      this.api({
        url: this.url,
        method: 'get',
      }).then(data => {
        console.log("baseSelect Ret Data:", data)
        console.log(this.mKey, this.mLabel)
        if (this.mKey && this.mLabel) {
          this.list = data.list.map(x => { x.key = x[this.mKey]; x.label = x[this.mLabel]; return x; })
        }
        else {
          this.list = data.list
        }
        this.isLoading = false
      })
    }
  }

}
</script>
<template>
  <base-select init-url="/devStatus/list" v-model="ownValue" m-key="defID" m-label="defName"/>
</template>

<script>
import  BaseSelect  from './BaseSelect.vue'

export default {
  components: { BaseSelect },
  props: ['m-value'],
  model: {
    prop: 'm-value',
    event: 'change'
  },

  data() {
    return {
      ownValue: this.mValue,
    }
  },
  watch:{
    ownValue(){
      this.$emit('change',this.ownValue)
    }
  }
}
</script>

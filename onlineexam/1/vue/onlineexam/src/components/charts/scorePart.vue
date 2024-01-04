<template>
  <div class="part" >
    <div class="box" ref="box"></div>
    <div v-if="isNull">
      <span>该门考试还没人参考哦,请提醒学生参加考试。</span>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isNull: false, //是否有成绩
      name: null,
      category: { //保存分数段
        '90分及以上': 0,
        '80-89分': 0,
        '70-79分': 0,
        '60-69分': 0,
        '60分以下': 0,
      }
    }
  },
  created() {
    this.getScoreInfo()

  },
  methods: {
    getScoreInfo() {
      let examCode = this.$route.query.examCode
      this.name = this.$route.query.source
      this.$axios(`/api/scores/${examCode}`).then(res => {
        let data = res.data.data
        if(data.length > 0) {
          let box = this.$refs['box']
          let charts = this.$echarts.init(box)
          data.forEach(element => {
            switch(element.etScore / 10) {
              case 10:
              case 9:
                this.category["90分及以上"]++
                break
              case 8:
                this.category['80-89分']++
                break
              case 7:
                this.category["70-79分"]++
                break
              case 6:
                this.category['60-69分']++
                break
              default:
                this.category['60分以下']++
            }
          });
          let option = {

              title: {
                  text: `${this.name}分数段图`, // 主标题
                  subtext: '', // 副标题
                  x: 'left' // x轴方向对齐方式
              },
              tooltip: {
                  trigger: 'axis'
              },
              xAxis: {
                  type: 'category', // 还有其他的type，可以去官网喵两眼哦
                  data: ['90分及以上','80-89分','70-79分','60-69分','60分以下'], // x轴数据
                  name: '分数段' // x轴名称
              },
              yAxis: {
                  type: 'value',
                  name: '人数' // y轴名称
              },
              legend: {
                  orient: 'vertical',
                  x: 'center',
                  y: 'top',
                  data: ['人数']
              },
              series: [
                  {
                      name: '人数',
                      data: [
                          {value:this.category['90分及以上'], name:'90分及以上'},
                          {value:this.category['80-89分'], name:'80-89分'},
                          {value:this.category['70-79分'], name:'70-79分'},
                          {value:this.category['60-69分'], name:'60-69分'},
                          {value:this.category['60分以下'], name:'60分以下'}
                      ],
                      type: 'line'
                  }
              ],

          };
          charts.setOption(option)
        }else {
          this.isNull = true
        }
      })
    }
  },

}
</script>

<style lang="scss" scoped>
.part {
  position: relative;
  .box{
    width: 600px;
    height: 400px;
  }
}
</style>



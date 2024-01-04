//显示学生成绩
<template>
  <div class="table">
    <p class="title">我的分数</p>
    <section class="content-el">
      <el-table ref="filterTable" :data="score" v-loading="loading">
        <el-table-column
          prop="answerDate"
          label="考试日期"
          sortable
          border
          width="300"
          column-key="answerDate"
          :filters="filter"
          :filter-method="filterHandler"
        >
        </el-table-column>

        <el-table-column prop="subject" label="课程名称" width="300">
        </el-table-column>

        <el-table-column prop="etScore" label="考试分数" width="200" sortable></el-table-column>
        <el-table-column label="是否及格" width="100">
          <template slot-scope="scope">
            <p>{{scope.row.etScore >= 60 ? "及格" : "不及格"}}</p>{{score.etScore}}
          </template>
        </el-table-column>

      </el-table>
      <el-row type="flex" justify="center" align="middle" class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagination.current"
          :page-sizes="[4,6,8,10]"
          :page-size="pagination.size"
          layout="total, sizes, prev, pager, next, jumper"
          :total="pagination.total">
        </el-pagination>
      </el-row>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: {
        current: 1,
        total: null, //记录条数
        size: 10
      },
      loading: false,
      score: [], //学生成绩
      filter: null, //过滤
    }
  },
  created() {
    this.getScore()
    this.loading = true //数据加载则遮罩表格
  },
  methods: {
    getScore() {
      let studentId = this.$cookies.get("cid")
      this.$axios(`/api/score/${this.pagination.current}/${this.pagination.size}/${studentId}`).then(res => {
        if(res.data.code == 200) {
          this.loading = false //去掉遮罩
          this.score = res.data.data.records
          this.pagination = {...res.data.data}
          let mapVal = this.score.map((element,index) => { //通过map得到 filter:[{text,value}]形式的数组对象
            let newVal = {}
            newVal.text = element.answerDate
            newVal.value = element.answerDate
            return newVal
          })
          let hash = []
          const newArr = mapVal.reduce((item, next) => { //对新对象进行去重操作
            hash[next.text] ? '' : hash[next.text] = true && item.push(next);
            return item
          }, []);
          this.filter = newArr
        }
      })
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val
      this.getScore()
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val
      this.getScore()
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    }
  },
    };
</script>

<style lang="scss" scoped>
.pagination {
  padding-top: 20px;
}
.table {
  width: 980px;
  margin: 0 auto;
  .title {
    margin: 20px;
  }
  .content-el {
    background-color: #fff;
    padding: 20px;
  }
}
  .textRed{
    color: #dd5862;
  }
  .textBlue{
    color: #0195ff;
  }
</style>

// 所有学生
<template>
  <div class="all">
    <ul class="top">
      <li class="search-li"><div class="icon"><input type="text" placeholder="考生编号" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
      <li><el-button type="primary" @click="search()">搜索考生成绩</el-button></li>
      <li><el-button type="primary" @click="getGrade()">查询全部</el-button></li>
    </ul>
    <el-table :data="score" border>
      <el-table-column prop="subject" label="科目" width="180"></el-table-column>
      <el-table-column prop="studentId" label="考生编号" width="200"></el-table-column>
      <el-table-column prop="etScore" label="成绩" width="200"></el-table-column>
      <el-table-column prop="answerDate" label="考生时间" width="200"></el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page"
    ></el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      pagination: {
        current: 1,
        total: null,
        size: 6 //每页条数
      },
      score:[],
        key:null//关键字
    };
  },
  created() {
    this.getAnswerInfo();
    this.getGrade()
  },
  methods: {
    getAnswerInfo() {
      this.$axios(`/api/students/${this.pagination.current}/${this.pagination.size}`).then(res => {
        this.pagination = res.data.data;
      }).catch(error => {});
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getAnswerInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getAnswerInfo();
    },
    getGrade(){
        this.$axios(`/api/scores`).then(res => {
            if(res.data.code == 200) {
                this.score=res.data.data
            }
        })
    },
      search() {
          this.$axios(`/api/score/${this.pagination.current}/${this.pagination.size}/${this.key}`).then(res => {
              if(res.data.code == 200) {
                  this.score=res.data.data.records
                  }
          })
      },
  }
};
</script>
<style lang="scss" scoped>
.all {
  padding: 0px 285px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit {
    margin-left: 20px;
  }
  .el-table tr {
    background-color: #dd5862 !important;
  }
}
.el-table .warning-row {
  background: #000 !important;
}

.el-table .success-row {
  background: #dd5862;
}
.top {
  display: flex;
}
.top li {
  margin: 20px;
}
.top .search-li {
  margin-left: auto;
}
.top li {
  display: flex;
  align-items: center;
}
.top .search {
  margin-left: auto;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}
</style>

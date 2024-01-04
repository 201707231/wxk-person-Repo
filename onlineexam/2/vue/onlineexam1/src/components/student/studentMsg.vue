<template>
  <div class="msg">
    <h4 class="title">学生信息</h4>
    <el-form ref="form" :model="studentData" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="studentData.studentName"></el-input>
      </el-form-item>
      <el-form-item label="学院">
        <el-input v-model="studentData.institute"></el-input>
      </el-form-item>
      <el-form-item label="专业">
        <el-input v-model="studentData.major"></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="studentData.grade"></el-input>
      </el-form-item>
      <el-form-item label="班级">
        <el-input v-model="studentData.clazz"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="studentData.sex"></el-input>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="studentData.tel"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
    export default {
        name: "studentMsg",
        data(){
            return{
                studentData:{},
            }
        },
        mounted(){
            this.init();   //页面初始化加载
        },
        methods:{
            init(){
                var id = this.$cookies.get("cid") //获得用户登录信息的id和role
                var role = this.$cookies.get("role");
                this.$axios({
                    url:'/api/student/'+id,
                    method:'get',
                }).then(res => {
                    if(res.data.code == 200 ) {
                            this.studentData=res.data.data
                    }
                })

            }
        }
    }
</script>

<style scoped>
.msg{
  width: 500px;
  height: 500px;
  margin: 0 auto;
}
</style>

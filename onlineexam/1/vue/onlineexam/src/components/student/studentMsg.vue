<template>
  <div class="msg">
    <h4 class="title" style="text-align: center">学生信息</h4>
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
        <el-radio-group v-model="studentData.sex">
          <el-radio :label="'男'">男</el-radio>
          <el-radio :label="'女'">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="studentData.tel"></el-input>
      </el-form-item>
    </el-form>
    <el-button  type="primary" @click="update()">修 改</el-button>
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

            },
        update(){
            this.$axios({
                url: '/api/student',
                method: 'put',
                data: {
                    ...this.studentData
                }
            }).then(res => {
                if(res.data.code ==200) {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    })
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
  text-align: center;
}
</style>

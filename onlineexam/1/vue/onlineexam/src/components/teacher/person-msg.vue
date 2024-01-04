<template>
  <el-row>
    <el-col :span="24">
      <div v-if="Adminform" class="msg">
        <h4 class="title">管理员信息</h4>
        <el-form ref="form" :model="adminData" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="adminData.adminName"></el-input>
          </el-form-item>
          <el-form-item label="性别">
          <el-radio-group v-model="adminData.sex">
            <el-radio :label="'男'">男</el-radio>
            <el-radio :label="'女'">女</el-radio>
          </el-radio-group>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="adminData.tel"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="adminData.email"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="adminData.cardId"></el-input>
          </el-form-item>
        </el-form>
        <el-button  type="primary" @click="updateAdmin()">修 改</el-button>
      </div>
      <div v-if="Teacherform" class="msg">
        <h4 class="title">教师信息</h4>
        <el-form ref="form" :model="teacherData" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="teacherData.teacherName"></el-input>
          </el-form-item>
          <el-form-item label="学院">
            <el-input v-model="teacherData.institute"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="teacherData.sex">
              <el-radio :label="'男'">男</el-radio>
              <el-radio :label="'女'">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="teacherData.tel"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="teacherData.email"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="teacherData.cardId"></el-input>
          </el-form-item>
        </el-form>
        <el-button  type="primary" @click="updateTeacher()">修 改</el-button>
      </div>
    </el-col>
  </el-row>
</template>

<script>
    export default {
        name: "person-msg",
        data(){
            return{
                teacherData:{},
                adminData:{},
                Teacherform:false,
                Adminform:false,
            }
        },
        mounted(){
            this.init();   //页面初始化加载
        },
        methods:{
            init(){
                var id = this.$cookies.get("cid") //获得用户登录信息的id和role
                var role = this.$cookies.get("role");
                //传id和role给后台
                if(role==0){
                    var url="/api/admin/"+id
                }else if(role==1){
                    var url="/api/teacher/"+id
                }else if(role==2){
                    var url="/api/student/"+id
                }

                this.$axios({
                    url:url,
                    method:'get',
                }).then(res => {
                    if(res.data.code == 200 ) {
                        if(role==0){
                            this.adminData=res.data.data
                            this.Adminform=true
                        }else if(role==1){
                            this.teacherData=res.data.data
                            this.Teacherform=true
                        }
                    }
                })

            },
            updateAdmin(){
                let id = this.$cookies.get("cid")
                this.$axios({
                    url: '/api/admin',
                    method: 'put',
                    data: {
                        ...this.adminData
                    }
                }).then(res => {
                    if(res.data.code ==200) {
                        this.$message({
                            message: '修改成功',
                            type: 'success'
                        })
                    }
                })
            },
            updateTeacher(){
                this.$axios({
                    url: '/api/teacher',
                    method: 'put',
                    data: {
                        ...this.teacherData
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

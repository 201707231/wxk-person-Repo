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
            <el-input v-model="adminData.sex"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="adminData.tel"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="adminData.cardId"></el-input>
          </el-form-item>
          <el-form-item label="职称">
            <el-input v-model="adminData.type"></el-input>
          </el-form-item>
        </el-form>
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
            <el-input v-model="teacherData.sex"></el-input>
          </el-form-item>
          <el-form-item label="电话号码">
            <el-input v-model="teacherData.tel"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="teacherData.pwd"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="teacherData.cardId"></el-input>
          </el-form-item>
        </el-form>
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
                Adminform:false
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
                        }else if(role==2){
                            this.studentData=res.data.data
                            this.Studentform=true
                        }
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

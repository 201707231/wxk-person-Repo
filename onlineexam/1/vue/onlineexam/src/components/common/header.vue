<!-- 教师、管理员顶部导航栏 -->
<template>
  <header id="topbar">
    <el-row>
        <el-menu
          :default-active="this.$route.path"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
          style="height: 80px"
          router
        >
          <el-menu-item index="9">在线考试信息管理中心</el-menu-item>
          <el-submenu v-for="(item,index) in menu" :index='item.index' :key="index">
            <template slot="title">
              <div >
                <i class="iconfont" :class="item.icon"></i>
                <span slot="title" class="title">{{item.title}}</span>
              </div>
            </template>
            <el-menu-item-group v-for="(list,index1) in item.content" :key="index1">
              <el-menu-item @click="handleTitle(item.index)" :index="list.path" v-if="list.item1 != null">{{list.item1}}</el-menu-item>
              <el-menu-item @click="handleTitle(item.index)" :index="list.path" v-if="list.item2 != null">{{list.item2}}</el-menu-item>
              <el-menu-item @click="handleTitle(item.index)" :index="list.path" v-if="list.item3 != null">{{list.item3}}</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="10">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title" class="title">设置</span>
            </template>
            <el-menu-item-group>
            <el-menu-item index="10-1" @click="showMes()">用户信息</el-menu-item>
            <el-menu-item index="10-2" @click="updatePwd()">修改密码</el-menu-item>
            <el-menu-item index="10-3" @click="exit()">退出登录</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
    </el-row>
  </header>
</template>

<script>
import store from '@/vuex/store'
import {mapState,mapMutations} from 'vuex'
export default {
  data() {
    return {
      login_flag: false,
      user: { //用户信息
        userName: null,
        userId: null
      }
    }
  },
  created() {
    this.getUserInfo()
      this.addData()
  },
  computed: mapState(["flag","menu"]),
  methods: {
    //显示、隐藏退出按钮
    showSetting() {
      this.login_flag = !this.login_flag
    },
    //左侧栏放大缩小
    ...mapMutations(["toggle"]),
    getUserInfo() { //获取用户信息
      let userName = this.$cookies.get("cname")
      let userId = this.$cookies.get("cid")
      this.user.userName = userName
      this.user.userId = userId
    },
    exit() {
      let role = this.$cookies.get("role")
      this.$router.push({path:"/"}) //跳转到登录页面
      this.$cookies.remove("cname") //清除cookie
      this.$cookies.remove("cid")
      this.$cookies.remove("role")
      if(role == 0) {
        this.menu.pop()
      }
    },
    updatePwd(){
        this.$router.push({path: '/updatePwd'})
    },
    showMes(){
        this.$router.push({path: '/person-msg'})
    },
      handleTitle(index) {
          this.bus.$emit('sendIndex',index)
      },
      addData() {
          let role = this.$cookies.get("role")
          if(role == 0) {
              this.menu.push({
                  index: '5',
                  title: '教师管理',
                  icon: 'icon-Userselect',
                  content:[{item1:'教师管理',path:'/teacherManage'},{item2: '添加教师',path: '/addTeacher'}],
              })
          }
      },
      handleSelect(key, keyPath) {
          console.log(key, keyPath);
      }
  },
  store
}
</script>

<style scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
#topbar {
  position: relative;
  z-index: 10;
  background-color:darkblue;
  height: 80px;
  line-height: 80px;
  color: #fff;
  box-shadow: 5px 0px 10px rgba(0, 0, 0, 0.5);
}
#topbar .topbar-left {
  height: 80px;
  display: flex;
  justify-content: center;
  background: rgba(0, 0, 0, 0.05);
  overflow: hidden;
}
.topbar-left .icon-kaoshi {
  font-size: 60px;
}
.topbar-left .title {
  font-size: 20px;
  cursor: pointer;
}
.topbar-right {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.topbar-right .user-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
.topbar-right .el-icon-menu {
  font-size: 30px;
  margin-left: 20px;
}
.topbar-right .user {
  position: relative;
  margin-right: 40px;
  display: flex;
}
.topbar-right .user .user-img {
  margin-top: 15px;
  margin-left: 10px;
  cursor: pointer;
}
.user .out {
  font-size: 14px;
  position: absolute;
  top: 80px;
  right: 0px;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  padding: 12px;
}
.user .out ul {
  list-style: none;
}
.user .out ul > li {
  height: 26px;
  line-height: 26px;
}
.out a {
  text-decoration: none;
  color: #000;
}
.out .exit {
  margin-top: 4px;
  padding-top: 4px;
  border-top: 1px solid #ccc;
}
</style>

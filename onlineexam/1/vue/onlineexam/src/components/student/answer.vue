<!--考生答题界面-->
<template>
  <div id="answer">
    <!--顶部信息栏-->
    <div class="top">
      <ul class="item">
        <li><i class="iconfont icon-menufold icon20" ref="toggle" @click="slider_flag = !slider_flag"></i></li>
        <li>{{examData.type}}-{{examData.source}}</li>
        <li @click="flag = !flag">
          <i class="iconfont icon-user icon20"></i>
          <div class="msg"  v-if="flag" @click="flag = !flag">
            <p>姓名：{{userInfo.name}}</p>
            <p>准考证号:  {{userInfo.id}}</p>
          </div>
        </li>
        <li><i class="iconfont icon-arrLeft icon20"></i></li>
      </ul>
    </div>

    <div class="flexarea">
      <!--左边题目区-->
      <transition name="slider-fade">
        <div class="flexarea">
        <div class="left" v-if="slider_flag">
          <el-col :span="24">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo">
              <el-menu-item index="1">
                  <span slot="title" ><a href="javascript:;" @click="multi()">选择题</a></span>
              </el-menu-item>
              <el-menu-item index="2">
                <span slot="title" ><a href="javascript:;" @click="judge()">判断题</a></span>
              </el-menu-item>
              <el-menu-item index="3">
                <span slot="title"><a href="javascript:;" @click="fill()">填空题</a></span>
              </el-menu-item>
            </el-menu>
          </el-col>
          <div class="l-bottom">
          <div class="final" @click="commit()" v-if="isPractice==false">结束考试</div>
            <div class="final" @click="exit()" v-if="isPractice">返回</div>
          </div>
        </div>
        </div>
      </transition>

      <!--右边选择答题区-->
      <transition name="slider-fade">
        <div class="right">
          <div class="title">
            <p>{{title}}</p>
            <i class="iconfont icon-right auto-right"></i>
            <span>全卷共{{topicCount[0] + topicCount[1] + topicCount[2]}}题  <i class="iconfont icon-time"></i>倒计时：<b>{{time}}</b>分钟</span>
          </div>

          <div class="content" v-for="(question,index) in questions">

            <p class="topic"><span class="number">{{index+1}}</span>{{question.question}}</p>
            <div v-if="currentType == 1" >
              <el-radio-group v-model="radio[index]"  :key="multi">
                <el-radio :label="1">{{question.answerA}}</el-radio>
                <el-radio :label="2">{{question.answerB}}</el-radio>
                <el-radio :label="3">{{question.answerC}}</el-radio>
                <el-radio :label="4">{{question.answerD}}</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">正确答案：</el-tag><span class="right">{{question.rightAnswer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{question.analysis == null ? '暂无解析': question.analysis}}</li>
                </ul>
              </div>

            </div>
            <div class="fill" v-if="currentType == 2">

                <div v-for="num of part">
                  <el-input placeholder="请填在此处"
                            v-model="fillAnswer[index][num]"
                            clearable>
                  </el-input>
                </div>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">正确答案：</el-tag><span class="right">{{question.answer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{question.analysis == null ? '暂无解析': question.analysis}}</li>
                </ul>
              </div>
            </div>

            <div class="judge" v-if="currentType == 3">
              <el-radio-group v-model="judgeAnswer[index]" v-if="currentType == 3">
                <el-radio :label="1">正确</el-radio>
                <el-radio :label="2">错误</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">正确姿势：</el-tag><span class="right">{{question.answer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{question.analysis == null ? '暂无解析': question.analysis}}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
    import store from '@/vuex/store'
    import {mapState} from 'vuex'
    export default {
        store,
        data() {
            return {
                startTime: null, //考试开始时间
                endTime: null, //考试结束时间
                time: null, //考试持续时间
                reduceAnswer:[],  //vue官方不支持3层以上数据嵌套,如嵌套则会数据渲染出现问题,此变量直接接收3层嵌套时的数据。
                answerScore: 0, //答题总分数
                slider_flag: true, //左侧显示隐藏标识符
                flag: false, //个人信息显示隐藏标识符
                currentType: 1, //当前题型类型  1--选择题  2--填空题  3--判断题
                radio: [], //保存考生所有选择题的选项
                title: "请选择正确的选项",
                userInfo: { //用户信息
                    name: null,
                    id: null
                },
                topicCount: [],//每种类型题目的总数
                score: [],  //每种类型分数的总数
                examData: { //考试信息
                },
                topic: {  //试卷信息

                },
                showQuestion: [], //当前显示题目信息
                showAnswer: {}, //当前题目对应的答案选项
                fillAnswer: [[]], //保存所有填空题答案
                judgeAnswer: [], //保存所有判断题答案
                rightAnswer: '',
                judgeQuestion:[],//判断题
                fillQuestion:[],
                multiQuestion:[],//选择题
                questions:[],//题目
                part: 1,//填空题空格
                examCode1:0,
                toatalScore:0
            }
        },
        created() {
            this.getCookies()
            this.getExamData()
            this.showTime()
        },
        methods: {
            getTime(date) { //日期格式化
                let year = date.getFullYear()
                let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
                let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
                let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
                // 拼接
                return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
            },
            getCookies() {  //获取cookie
                this.userInfo.name = this.$cookies.get("cname")
                this.userInfo.id = this.$cookies.get("cid")
            },
            calcuScore() { //计算答题分数

            },
            getExamData() { //获取当前试卷所有信息
                let date = new Date()
                this.startTime = this.getTime(date)
                let examCode = this.$route.query.examCode //获取路由传递过来的试卷编号
                this.examCode1=examCode
                this.$axios(`/api/exam/${examCode}`).then(res => {  //通过examCode请求试卷详细信息
                    this.examData = { ...res.data.data} //获取考试详情
                    this.time = this.examData.totalScore //获取分钟数
                    let paperId = this.examData.paperId
                    this.$axios(`/api/paper/${paperId}`).then(res => {  //通过paperId获取试题题目信息
                        this.topic = {...res.data}
                        this.judgeQuestion=this.topic[3]
                        this.fillQuestion=this.topic[2]
                        this.multiQuestion=this.topic[1]
                        this.questions=this.multiQuestion
                        let keys = Object.keys(this.topic) //对象转数组
                        keys.forEach(e => {
                            let data = this.topic[e]
                            this.topicCount.push(data.length)//保存每种题目数量
                            let currentScore = 0
                            for(let i = 0; i< data.length; i++) { //计算总分
                                currentScore += data[i].score
                            }
                            this.score.push(currentScore) //把每种题型总分存入score
                        })
                        this.toatalScore=this.score[0]+this.score[1]+this.score[2]
                        let len = this.topicCount[1]
                        let father = []
                        for(let i = 0; i < len; i++) { //根据填空题数量创建二维空数组存放每道题答案
                            let children = [null,null,null,null]
                            father.push(children)
                        }
                        this.fillAnswer = father
                    })
                })
            },
            multi(){
                this.questions=this.multiQuestion
                this.currentType = 1
            },
            judge(){
                this.questions=this.judgeQuestion
                this.currentType=3
            },
            fill(){
                this.questions=this.fillQuestion
                this.currentType = 2
            },
            commit() { //答案提交计算分数
                /* 计算选择题总分 */
                let topic1Answer = this.radio
                let finalScore = 0
                topic1Answer.forEach((element,index) => { //循环每道选择题根据选项计算分数
                    let right = null
                    if(element != null) {
                        switch(element) { //选项1,2,3,4 转换为 "A","B","C","D"
                            case 1:
                                right = "A"
                                break
                            case 2:
                                right = "B"
                                break
                            case 3:
                                right = "C"
                                break
                            case 4:
                                right = "D"
                        }
                        if(right == this.topic[1][index].rightAnswer) { // 当前选项与正确答案对比
                            finalScore += this.topic[1][index].score // 计算总分数
                        }
                        console.log(right,this.topic[1][index].rightAnswer)
                    }
                    // console.log(topic1Answer)
                })
                /**计填空题总分 */
                let fillAnswer = this.fillAnswer
                fillAnswer.forEach((element,index) => { //此处index和 this.index数据不一致，注意
                    element.forEach((inner) => {
                        if(this.topic[2][index].answer.includes(inner)) { //判断填空答案是否与数据库一致
                            console.log("正确")
                            finalScore += this.topic[2][index].score
                        }
                    })
                });
                /** 计算判断题总分 */
                let topic3Answer = this.judgeAnswer
                topic3Answer.forEach((element,index) => {
                    let right = null
                    switch(element) {
                        case 1:
                            right = "T"
                            break
                        case 2:
                            right = "F"
                    }
                    if(right == this.topic[3][index].answer) { // 当前选项与正确答案对比
                        finalScore += this.topic[3][index].score // 计算总分数
                    }
                })
                console.log(`目前总分${finalScore}`)
                if(this.time != 0) {
                    this.$confirm("考试结束时间未到,是否提前交卷","友情提示",{
                        confirmButtonText: '立即交卷',
                        cancelButtonText: '再检查一下',
                        type: 'warning'
                    }).then(() => {
                        console.log("交卷")
                        let date = new Date()
                        this.endTime = this.getTime(date)
                        let answerDate = this.endTime.substr(0,10)
                        //保存成绩
                        this.$axios({
                            url: '/api/score',
                            method: 'post',
                            data: {
                                examCode: this.examData.examCode,
                                studentId: this.userInfo.id, //学号
                                subject: this.examData.source,
                                etScore: finalScore,
                                answerDate: answerDate,
                            }
                        }).then(res => {
                            if(res.data.code == 200) {
                                this.$router.push({path:'/studentScore',query: {
                                        score: finalScore,
                                        startTime: this.startTime,
                                        endTime: this.endTime,
                                        name:this.$cookies.get("cname"),
                                        examCode:this.examCode1,
                                        toatalScore:this.toatalScore
                                    }})
                            }
                        })
                    }).catch(() => {
                        console.log("继续答题")
                    })
                }
            },
            showTime() { //倒计时
                setInterval(() => {
                    this.time -= 1
                    if(this.time == 10) {
                        this.$message({
                            showClose: true,
                            type: 'error',
                            message: '考生注意,考试时间还剩10分钟！！！'
                        })
                        if(this.time == 0) {
                            console.log("考试时间已到,强制交卷。")
                        }
                    }
                },1000 * 60)
            },
            exit(){
                this.$router.push({path:'/startExam'})
            }
        },
        computed:mapState(["isPractice"])
    }
</script>

<style lang="scss">
  .iconfont.icon-time {
    color: #2776df;
    margin: 0px 6px 0px 20px;
  }
  .analysis {
    margin-top: 20px;
    .right {
      color: #2776df;
      font-size: 18px;
      border: 1px solid #2776df;
      padding: 0px 6px;
      border-radius: 4px;
      margin-left: 20px;
    }
    ul li:nth-child(2) {
      margin: 20px 0px;
    }
    ul li:nth-child(3) {
      padding: 10px;
      background-color: #d3c6c9;
      border-radius: 4px;
    }
  }
  .analysis span:nth-child(1) {
    font-size: 18px;
  }
  .mark {
    position: absolute;
    width: 4px;
    height: 4px;
    content: "";
    background-color: red;
    border-radius: 50%;
    top: 0px;
    left: 22px;
  }
  .border {
    position: relative;
    border: 1px solid #FF90AA !important;
  }
  .bg {
    background-color: #5188b8 !important;
  }
  .fill .el-input {
    display: inline-flex;
    width: 150px;
    margin-left: 20px;
    .el-input__inner {
      border: 1px solid transparent;
      border-bottom: 1px solid #eee;
      padding-left: 20px;
    }
  }
  /* slider过渡效果 */
  .slider-fade-enter-active {
    transition: all .3s ease;
  }
  .slider-fade-leave-active {
    transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slider-fade-enter, .slider-fade-leave-to {
    transform: translateX(-100px);
    opacity: 0;
  }

  .operation .end li:nth-child(2) {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: rgb(39, 118, 223);
    border-radius: 50%;
    width: 50px;
    height: 50px;
    color: #fff;
  }
  .operation .end li {
    cursor: pointer;
    margin: 0 100px;
  }
  .operation {
    background-color: #fff;
    border-radius: 4px;
    padding: 10px 0px;
    margin-right: 10px;
  }
  .operation .end {
    display: flex;
    justify-content: center;
    align-items: center;
    color: rgb(39, 118, 223);
  }
  .content .number {
    display: inline-flex;
    justify-content: center;
    align-items: center;
    width: 20px;
    height: 20px;
    background-color: rgb(39, 118, 223);
    border-radius: 4px;
    margin-right: 4px;
  }
  .content {
    padding: 0px 20px;
  }
  .content .topic {
    padding: 20px 0px;
    padding-top: 30px;
  }
  .right .content {
    background-color: #fff;
    margin: 10px;
    margin-left: 0px;

  }
  .content .el-radio-group label {
    color: #000;
    margin: 5px 0px;
  }
  .content .el-radio-group {
    display: flex;
    flex-direction:column;
  }
  .right .title p {
    margin-left: 20px;
  }
  .flexarea {
    display: flex;
  }
  .flexarea .right {
    flex: 1;
  }
  .auto-right {
    margin-left: auto;
    color: #2776df;
    margin-right: 10px;
  }
  .right .title {
    margin-right: 10px;
    padding-right: 30px;
    display: flex;
    margin-top: 10px;
    background-color: #fff;
    height: 50px;
    line-height: 50px;
  }
  .clearfix {
    clear: both;
  }
  .l-bottom .final {
    cursor: pointer;
    display: inline-block;
    text-align: center;
    background-color: rgb(39, 118, 223);
    width: 240px;
    margin: 20px 0px 20px 10px;
    border-radius: 4px;
    height: 30px;
    line-height: 30px;
    color: #fff;
    margin-top: 22px;
  }
  #answer .left .item {
    padding: 0px;
    font-size: 16px;
  }
  .l-bottom {
    border-radius: 4px;
    background-color: #fff;
  }
  .l-bottom .item p {
    margin-bottom: 15px;
    margin-top: 10px;
    color: #000;
    margin-left: 10px;
    letter-spacing: 2px;
  }
  .l-bottom .item li {
    width: 15%;
    margin-left: 5px;
    margin-bottom: 10px;
  }
  .l-bottom .item {
    display: flex;
    flex-direction: column;
  }
  .l-bottom .item ul {
    width: 100%;
    margin-bottom: -8px;
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
  }
  .l-bottom .item ul li a {
    position: relative;
    justify-content: center;
    display: inline-flex;
    align-items: center;
    width: 30px;
    height: 30px;
    border-radius: 50%;
    background-color: #fff;
    border: 1px solid #eee;
    text-align: center;
    color: #000;
    font-size: 16px;
  }
  .left .l-top {
    display: flex;
    justify-content: space-around;
    padding: 16px 0px;
    border: 1px solid #eee;
    border-radius: 4px;
    margin-bottom: 10px;
    background-color: #fff;
  }
  .left {
    width: 260px;
    height: 100%;
    margin: 10px 10px 0px 10px;
  }
  .left .l-top li:nth-child(2) a {
    border: 1px solid #eee;
  }
  .left .l-top li:nth-child(3) a {
    background-color: #5188b8;
    border: none;
  }
  .left .l-top li:nth-child(4) a {
    position: relative;
    border: 1px solid #eee;
  }
  .left .l-top li:nth-child(4) a::before {
    width: 4px;
    height: 4px;
    content: " ";
    position: absolute;
    background-color: red;
    border-radius: 50%;
    top: 0px;
    left: 16px;
  }
  .left .l-top li {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }
  .left .l-top li a {
    display: inline-block;
    padding: 10px;
    border-radius: 50%;
    background-color: #fff;
    border: 1px solid #FF90AA;
  }
  #answer .top {
    background-color: rgb(39, 118, 223);
  }
  #answer .item {
    color: #fff;
    display: flex;
    padding: 20px;
    font-size: 20px;
  }
  #answer .top .item li:nth-child(1) {
    margin-right: 10px;
  }
  #answer .top .item li:nth-child(3) {
    position: relative;
    margin-left: auto;
  }
  #answer {
    padding-bottom: 30px;
  }
  .icon20 {
    font-size: 20px;
    font-weight: bold;
  }
  .item .msg {
    padding: 10px 15px;
    border-radius: 4px;
    top: 47px;
    right: -30px;
    color: #6c757d;
    position: absolute;
    border: 1px solid rgba(0,0,0,.15);
    background-color: #fff;
  }
  .item .msg p {
    font-size: 16px;
    width: 200px;
    text-align: left;
  }
</style>

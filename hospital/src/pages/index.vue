<template>
    <div class="index">
      <mheader></mheader>
      <div class="row nav">
        <div class="col-md-1"></div>
        <div class="col-xs-12 col-md-7">
          <div id="carousel-example-generic" class="carousel slide c-width" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
              <div class="item active">
                <img src="../assets/images/54.jpg" alt="...">
                <div class="carousel-caption">
                  ...
                </div>
              </div>
              <div class="item">
                <img src="../assets/images/95.jpg" alt="...">
                <div class="carousel-caption">
                </div>
              </div>
              <div class="item">
                <img src="../assets/images/99.jpg" alt="...">
                <div class="carousel-caption">
                </div>
              </div>

            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
        <div class="col-md-3">
          <div id="quicklogin" v-if="quicklogin" >
              <div class="quicklogin-title"><h4>用户登录</h4></div>
              <form class="form-horizontal quickform" method="post" action="">
                <div class="form-group">
                  <label for="username" class="col-sm-3 control-label">账号：</label>
                  <div class="col-sm-7">
                    <input type="text" id="username" class="form-control" placeholder="请输入证件号码" v-model="formMess.inputusername">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputPassword3" class="col-sm-3 col-md-3 control-label">密&nbsp &nbsp码：</label>
                  <div class="col-sm-6 col-md-7">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" v-model="formMess.inputpassword">
                  </div>
                </div>
                <div class="form-group">
                  <label for="inputcode" class="col-sm-3 col-md-3 control-label">验证码：</label>
                  <div class="col-sm-6 col-md-4">
                    <input type="text" class="form-control" id="inputcode" v-model="formMess.inputcode">
                  </div>
                  <div class="col-md-3">
                    <input type="button" id="code"  @click="createCode()"/>
                  </div>
                </div>
                <div class="form-group">
                    <div class="checkbox ">
                      <label class="col-sm-5 role"><input name="role" type="radio" checked="checked" value="normaluser" v-model="formMess.inputrole"/>普通用户</label>
                      <label class="col-sm-5 role"><input name="role" type="radio" value="doctor" v-model="formMess.inputrole"/>医生</label>
                    </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-1 col-sm-10">
                  <button type="button" class="btn btn-default sub-btn" @click="validate()">登录</button>
                </div>
              </div>
            </form>
              <div class="quick-extr">
                <p>没有账号？ <a href="#"><router-link to="/sign">立即注册</router-link></a></p>

              </div>
          </div>
          <div id="quickinfo" v-if="quickinfo">
            <div class="quicklogin-title"><h4>用户信息</h4></div>
            <div id="getusername" class="userinfo">用户名：{{username}}</div>
            <div id="usernumber" class="userinfo">证件号：{{card_id}}</div>
            <div id="userpay" class="userinfo">预约数 <a href="#">{{bookSum}}</a> 条</div>
            <div id="usergone" class="userinfo">预约成功未就诊记录 <a href="#">{{someBook}}</a> 条</div>
            <button type="submit" class="btn btn-default info-btn" @click="logout">退出</button>
          </div>
          <div id="dquickinfo" v-if="dquickinfo">
            <div class="quicklogin-title"><h4>医生信息</h4></div>
            <div id="getdname" class="userinfo">用户名：{{username}}</div>
            <div id="dnumber" class="userinfo">证件号：{{card_id}}</div>
            <div id="dbookSum" class="userinfo">预约数 <a href="#">{{bookSum}}</a> 条</div>
            <button type="submit" class="btn btn-default info-btn" @click="logout">退出</button>
          </div>
        </div>
        <div class=" col-md-1"></div>
      </div>
      <div class="row mid">
        <div class="col-md-1"></div>
        <div class="col-md-10 quickorder">
          <div class="guide">
            <div class="ex-img">
              <img src="../assets/images/guide.png">
            </div>
            <div class="explain">
              <p>
                <span class="ex-title">就诊指南</span><br/>
                点击此处获取预约就诊详细信息
              </p>
            </div>
          </div>
          <div class="order">
            <div class="ex-img">
              <img src="../assets/images/order.png">
            </div>
            <div class="explain">
              <p>
                <span class="ex-title">预约挂号</span><br/>
                点击此处获取预约就诊详细信息
              </p>
            </div>
          </div>
          <div class="pay">
            <div class="ex-img">
              <img src="../assets/images/pay.png">
            </div>
            <div class="explain">
              <p>
                <span class="ex-title">个人信息</span><br/>
                点击此处获取预约就诊详细信息
              </p>
            </div>
          </div>
        </div>
        <div class="col-md-1"></div>
      </div>
    </div>

</template>

<script>
    import mheader from '../components/mheader.vue'
    import axios from 'axios'
    import qs from 'qs'
    import store from '../util/store.js'
    export default {
        name: "index",
        components: {mheader},
        data(){
          return{
            formMess:{
              "inputusername": "",
              "inputpassword": "",
              "inputcode": "",
              "inputrole": ""
            },
            username: "",
            card_id: "",
            quicklogin: true, //登录面板
            quickinfo: false, //登录信息
            dquickinfo: false, //登录信息
            bookSum: '',
            someBook: '',
          }


        },
        mounted(){
          this.createCode(),
          this.startTest(),
            this.userBook(sessionStorage.getItem("userId")),
            this.doctorBook(sessionStorage.getItem("doctorId"))
        },
        methods:{
          //随机产生验证码
           createCode(){
              var code=" ";
              var codeLength = 4;//验证码的长度
              var checkCode = document.getElementById("code");
              let random = new Array(0,1,2,3,4,5,6,7,8,9, 'A','B','C','D','E','F','G','H','I','J','K','L', 'M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//随机数
              for(var i = 0; i < codeLength; i++) {//循环操作
                var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）
                code += random[index];//根据索引取得随机数加到code上
              }
              console.log(code)
             if(checkCode){
               checkCode.value = code;//把code值赋给验证码
             }
            },
          //验证验证码规则
            validate(){
              var inputCode = document.getElementById("inputcode").value.toUpperCase();
              var checkCode = document.getElementById("code").value.substr(1);
              //取得输入的验证码并转化为大写
              if(inputCode.length <= 0) { //若输入的验证码长度为0
                alert("请输入验证码！"); //则弹出请输入验证码
              }
              //若输入的验证码与产生的验证码不一致时
              else if(inputCode!=(checkCode)) {
                alert("验证码输入错误！@_@"); //则弹出验证码输入错误
                this.createCode();//刷新验证码
                document.getElementById("inputcode").value = "";//清空文本框
              }
              //输入正确时  进行登录
              else {
                this.login();
              }
          },
          //登录
          login(){
            var data={
              inputusername : this.formMess.inputusername,
              inputpassword : this.formMess.inputpassword
            }
            if(data.inputusername!=""&&data.inputpassword!=""){
              if(this.formMess.inputrole=="normaluser"){
                axios.post("/login.action ",this.$qs.stringify(data)).then(res => {
                  if(res.data!=null){
                    this.quicklogin=false;
                    this.quickinfo=true;
                    this.username=res.data.username;
                    this.card_id=res.data.cardId;

                    //将用户信息放入sessionStroage
                    sessionStorage.setItem("userId",res.data.userid);
                    sessionStorage.setItem("cardId",res.data.cardId);
                    sessionStorage.setItem("userName",res.data.username);
                    sessionStorage.setItem("role","user");
                    sessionStorage.setItem("userToken",true);
                    this.userBook(sessionStorage.getItem("userId"));
                  }
                }).catch(error => {
                  console.log(error);
                });
              }
              else if (this.formMess.inputrole=="doctor") {
                axios.post("/api/doctor/dlogin.action",this.$qs.stringify(data)).then(res => {
                  if(res.data!=null){
                    this.quicklogin=false;
                    this.dquickinfo=true;
                    this.username=res.data.dname;
                    this.card_id=res.data.cardId;
                    //将用户信息放入sessionStroage
                    sessionStorage.setItem("doctorId",res.data.doctorid);
                    sessionStorage.setItem("cardId",res.data.cardId);
                    sessionStorage.setItem("doctorName",res.data.dname);
                    sessionStorage.setItem("role","doctor");
                    sessionStorage.setItem("userToken",true);
                    this.doctorBook(sessionStorage.getItem("doctorId"));
                  }
                }).catch(error => {
                  console.log(error);
                });
              }
              else{
                alert("输入错误")
              }
            }
            else {
              alert("请输入正确的证件号码和密码！")
            }

          },
          //登出
          logout(){
             this.quicklogin=true;
            this.quickinfo=false;
            this.dquickinfo=false;
             sessionStorage.setItem("userId","");
             sessionStorage.setItem("cardId","");
             sessionStorage.setItem("userName","");
            sessionStorage.setItem("doctorId","");
            sessionStorage.setItem("cardId","");
            sessionStorage.setItem("doctorName","");
             sessionStorage.setItem("userToken",false);
             sessionStorage.setItem("role","")
          },
          //初始化检测  刷新页面后无需重新登录
          startTest(){
             let userId=sessionStorage.getItem("userId");
             let doctorId=sessionStorage.getItem("doctorId");
             let userToken=sessionStorage.getItem("userToken");
             if(userToken&&userId!=''){
               this.quicklogin=false;
               this.quickinfo=true;
               this.username=sessionStorage.getItem("userName");
               this.card_id=sessionStorage.getItem("cardId");
             }
             if(userToken&&doctorId!=''){
               this.quicklogin=false;
               this.quickinfo=true;
               this.username=sessionStorage.getItem("userName");
               this.card_id=sessionStorage.getItem("cardId");
             }
          },
          //查询用户预约记录
          userBook(userId){
             this.bookSum=0;
             this.someBook=0;
            var data={
              userId:userId
            }
            axios.post("/api/Book/selectAllByUserId.action",this.$qs.stringify(data)).then(res => {
              if(res.data.data!=null){
                this.username=sessionStorage.getItem("userName");
                let data=res.data.data;
                console.log(data)
                this.bookSum=data.length;
                console.log(this.bookSum)
                for(let i=0;i<data.length;i++){
                  if(data[i].bookState=="未就诊"){
                    this.someBook=this.someBook+1;
                  }
                }
              }
            }).catch(error => {
              console.log(error);
            });
          },
          //查询医生预约
          doctorBook(doctorId){
             this.bookSum=0;
             this.someBook=0;
            var data={
              doctorId:doctorId
            }
            axios.post("/api/Book/selectAllByDId.action",this.$qs.stringify(data)).then(res => {
              if(res.data.data!=null){
                this.quicklogin=false;
                this.dquickinfo=true;
                this.quickinfo=false;
                this.username=sessionStorage.getItem("doctorName");
                let data=res.data.data;
                this.bookSum=data.length;
              }
            }).catch(error => {
              console.log(error);
            });
          },
       }
    }
</script>

<style scoped>
  .c-width{
    width: 100%;
  }
  .item img{
    width: 100%;
    height: 380px;
  }
  .form-group{
    margin-bottom: 10px;
  }
  .quickorder{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-content: center;
    margin-top: 20px;
  }
  .quickorder img{
    width: 98px;
    height: 98px;
    vertical-align: middle;
  }
  .guide,.order,.pay{
    display: flex;
    width: 33%;
    height: 120px;
    background-color: #F1F1F1;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
  }
  .ex-img{
    display: flex;
    margin-left: 5%;
  }
  .explain{
    display: flex;
    margin-left: 3%;
  }
  .ex-title{
    font-size: 20px;
  }

  #quicklogin{
    background-color: #F1F1F1;
    height: 380px;
    width: 100%;
    display: block;
  }
  .quicklogin-title{
    width: 90%;
    text-align: center;
    padding: 5%;
    letter-spacing: 2px;
    color: #333;
    font-size: 1.2em;
    text-shadow: 0 0 2px #999;
  }
  .quickform{
    padding: 0 20px 10px 20px;
    margin: 0 auto;
  }
  .control-label{
    padding-left: 0;
    padding-right: 0;
  }
  .checkbox{
    padding-left: 18px;
    font-size: 13px;
  }
  .autolog{
    margin-left: 22px;
  }
  .role{
    font-size: 14px;
    font-weight: 700;
  }
  .sub-btn{
    width: 88%;
    height: 38px;
    background-color: #2B7A78;
  }
  .quick-extr p{
    margin-left: 30%;
  }
  #quickinfo{
    background-color: #F1F1F1;
    height: 380px;
    width: 100%;
    /*display: none;*/
}
  .userinfo{
    width: 80%;
    margin: 0 auto;
    margin-top: 15px;
    font-size: 1.1em;
  }
  .info-btn{
    width: 80%;
    height: 38px;
    background-color: #5F9EA0;
    margin-left: 10%;
    margin-top: 15px;
  }


</style>

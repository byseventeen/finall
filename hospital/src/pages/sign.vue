<template>
  <div class="sign">    <!--头部-->
    <div class="header">
      <a class="logo" href="##"></a>
      <div class="desc">欢迎注册</div>
    </div>
    <!--版心-->
    <div class="container">
      <!--京东注册模块-->
      <div class="register">
        <!--用户名-->
        <div class="register-box">
          <!--表单项-->
          <div class="box default">
            <label for="userName">用&nbsp;户&nbsp;名</label>
            <input type="text" id="userName" placeholder="您的姓名"/>
            <i></i>
          </div>
          <!--提示信息-->
          <div class="tip">
            <i></i>
            <span></span>
          </div>
        </div>
        <!--性别-->
        <div class="register-box">
          <!--表单项-->
          <div class="box default">
            <label for="userName" style="margin-right: 30px;">性&nbsp;&nbsp;别</label>
            <input type="text" id="gender" placeholder="您的性别" />
            <!--<el-radio-group v-model="form.gender">-->
              <!--<el-radio label="男" style="margin-right: 10px;"></el-radio>-->
              <!--<el-radio label="女" style="margin-right: 10px;"></el-radio>-->
            <!--</el-radio-group>-->
            <i></i>
          </div>
          <!--提示信息-->
          <div class="tip">
            <i></i>
            <span></span>
          </div>
        </div>
        <!--设置密码-->
        <div class="register-box">
          <!--表单项-->
          <div class="box default">
            <label for="pwd">设 置 密 码</label>
            <input type="password" id="pwd" placeholder="建议至少两种字符组合" />
            <i></i>
          </div>
          <!--提示信息-->
          <div class="tip">
            <i></i>
            <span></span>
          </div>
        </div>
        <!--确认密码-->
        <div class="register-box">
          <!--表单项-->
          <div class="box default">
            <label for="pwdd">确 认 密 码</label>
            <input type="password" id="pwdd" placeholder="请再次输入密码" />
            <i></i>
          </div>
          <!--提示信息-->
          <div class="tip">
            <i></i>
            <span></span>
          </div>
        </div>
        <!--设置密码-->
        <div class="register-box">
          <!--表单项-->
          <div class="box default">
            <label for="cardId">证 件 号 码</label>
            <input type="text" id="cardId" placeholder="请输入身份证号码"/>
            <i></i>
          </div>
          <!--提示信息-->
          <div class="tip">
            <i></i>
            <span></span>
          </div>
        </div>
        <!--手机验证-->
        <div class="register-box">
          <!--表单项-->
          <div class="box default">
            <label for="phone">手 机 号 码</label>
            <input type="text" id="phone" placeholder="请输入手机号"  />
            <i></i>
          </div>
          <!--提示信息-->
          <div class="tip">
            <i></i>
            <span></span>
          </div>
        </div>
        <!--注册协议-->
        <div class="register-box xieyi">
          <!--表单项-->
          <div class="box default">
            <input type="checkbox" id="ck" />
            <span>我已阅读并同意<a href="##">《xx医院用户注册协议》</a></span>
          </div>
          <!--提示信息-->
          <div class="tip">
            <i></i>
            <span></span>
          </div>
        </div>
        <!--注册-->
        <button id="btn" @click="sign">注册</button>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import qs from 'qs'
    export default {
        name: "sign",
        data(){
          return{
            form: {  //用户表单信息
              name: '',
              gender: '',
              pwd:'',
              pwdd:'',
              cardId: '',
              phone:'',
            }
          }
        },
        methods:{
            sign(){
              let cardId=document.getElementById("cardId").innerText;
              let gender=document.getElementById("gender").innerText;
              let name=document.getElementById("userName").innerText;
              let pwd=document.getElementById("pwd").innerText;
              let pwdd=document.getElementById("pwdd").innerText;
              let phone=document.getElementById("phone").innerText;

              var data={
                cardId : cardId,
                phone : gender,
                name: name,
                pwd: pwd,
                gender: phone
              };
              console.log(pwd)
              console.log(pwdd)
              if(pwd==pwdd){
                axios.post("/api/Sign.action",this.$qs.stringify(data)).then(res => {
                  if(res.data!="success"){
                    alert("注册成功！请前往登录！")
                    this.$router.push({ path: '/index' })
                  }
                }).catch(error => {
                  console.log(error);
                });
              }else{
                alert("密码不一致！")
              }

            }
        }
    }
</script>

<style scoped>
  /*重置默认的外边距和内边距*/
  *{
    margin:0;
    padding:0;
  }
  /*常用标签的样式重置*/
  i{
    font-style: normal;
  }
  a{
    text-decoration: none;
  }
  input{
    outline: none;
    border:none;
  }
  /*设定html和body的宽度为100%*/
  html,body{
    width:100%;
    font-size: 14px;
  }
  /*版心*/
  .container{
    width:100%;
    margin:0 auto;

  }
  /*头部*/
  .header{
    width:100%;
    height: 110px;
    box-shadow: 10px 10px 10px rgba(111,111,111,.5);
    position: relative;

  }
  /*Logo*/
  .header .logo{
    display: inline-block;
    width: 20%;
    height:50px;
    background: url("../assets/images/logo.jpg") no-repeat;
    position: absolute;
    top:20px;
    left:20px;
  }
  .header .desc{
    font-size: 18px;
    line-height: 34px;
    border-left: 1px solid #ccc;
    position: absolute;
    left: 15%;
    top:28px;
    padding-left: 20px;
    font-weight:bold;
  }
  /*-----------------------------------京东注册模块---------------------------*/
  .register{
    width: 400px;
    margin: 0 auto;
    padding-top:40px;
    position: relative;
  }
  /*包裹表单项和对应的提示信息的容器的样式设置*/
  .register-box{
    width:100%;
    height: 80px;
    padding-bottom: 30px;
  }
  /*表单项样式设置*/
  .register-box .box{
    width:100%;
    height:100%;
    line-height: 40px;
    padding-left: 10px;
    border: 1px solid #ccc;
    position: relative;
  }
  .register-box .box.error{
    border:1px solid red;
  }
  .register-box .box input{
    width:200px;
    font-size:16px;
    padding-left: 20px;
  }
  /*验证通过*/
  .register-box .box.right i{
    width:20px;
    height: 20px;
    background: url(../assets/images/right.png) no-repeat center center;
    position: absolute;
    top:50%;
    margin-top: -10px;
    right: 10px;

  }
  /*提示信息样式设置*/
  .register-box .tip {
    width:100%;
    line-height: 30px;
    font-size: 12px;
  }
  .register-box .tip i{
    width: 20px;
    height: 20px;
    display: inline-block;
    vertical-align: middle;
  }
  .register-box .tip span{
    color: #CCCCCC;
  }
  /*提示信息隐藏样式*/
  .register-box .tip.hide{
    display: none;
  }
  /*提示信息默认样式*/
  .register-box .tip.default i {
    background: url(../assets/images/default.png) no-repeat center center;
  }
  .register-box .tip.default span{
    color: #ccc;
  }
  /*提示信息错误样式*/
  .register-box .tip.error i {
    background: url(../assets/images/error.png) no-repeat center center;
  }
  .register-box .tip.error span{
    color: red;
  }
  /*提示信息密码弱*/
  .register-box .tip.ruo i {
    background: url(../assets/images/ruo.png) no-repeat center center;
  }
  /*提示信息密码中*/
  .register-box .tip.zhong i {
    background: url(../assets/images/zhong.png) no-repeat center center;
  }
  /*提示信息密码中*/
  .register-box .tip.qiang i {
    background: url(../assets/images/qiang.png) no-repeat center center;
  }
  /*京东用户注册协议*/
  .register-box.xieyi{
    width:100%;
    height: 20px;
    padding-bottom: 30px;
    /*	background-color: #ccc;*/
  }
  .register-box.xieyi .box{
    line-height: normal;
    padding: 0;
    border: none;
  }
  .register-box.xieyi .box.error{
    line-height: normal;
    padding: 0;
    border: 1px solid red;
  }
  .register-box.xieyi .box input{
    width: auto;
  }
  /*注册*/
  .register button{
    width: 100%;
    height: 40px;
    font:bold 18px/40px "宋体";
    color: white;
    background-color: #1cc09f;
    outline-color: white;
  }
  .register button:hover{
    background-color: #1cc09f;
  }
</style>

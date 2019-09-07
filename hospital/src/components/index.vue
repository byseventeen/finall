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
          <div class="quicklogin" >
              <div class="quicklogin-title"><h4>用户登录</h4></div>
              <form class="form-horizontal quickform" method="post" action="">
                <div class="form-group">
                  <label for="username" class="col-sm-3 control-label">用户名：</label>
                  <div class="col-sm-7">
                    <input type="text" id="username" class="form-control" placeholder="请输入用户名" v-model="formMess.inputusername">
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
                  <div class="col-sm-7">
                    <div class="checkbox">
                      <label>
                        <input type="checkbox"> 七天内自动登录
                      </label>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <div class="col-sm-offset-1 col-sm-10">
                  <button type="button" class="btn btn-default sub-btn" @click="tijiao()">登录</button>
                </div>
              </div>
            </form>
              <div class="quick-extr">
                <p>没有账号？ <a href="#">立即注册</a></p>

              </div>
          </div>
          <div class="quickinfo">
            <div class="quicklogin-title"><h4>用户信息</h4></div>
            <div id="getusername" class="userinfo">用户名：小花花</div>
            <div id="usernumber" class="userinfo">证件号：16115073003</div>
            <div id="userpay" class="userinfo">预约未支付记录 <a href="#">0</a> 条</div>
            <div id="usergone" class="userinfo">预约成功未就诊记录 <a href="#">0</a> 条</div>
            <button type="submit" class="btn btn-default info-btn">退出</button>
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
                <span class="ex-title">支付缴费</span><br/>
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
    import mheader from './mheader.vue'
    import axios from 'axios'
    export default {
        name: "index",
        components: {mheader},
        data(){
          return{
            formMess:{
              "inputusername": "",
              "inputpassword": "",
              "inputcode": ""
            }
          }
        },
        mounted(){
          this.createCode()
        },
        methods:{
           createCode(){
              var code=" ";
              var codeLength = 4;//验证码的长度
              var checkCode = document.getElementById("code");
              let random = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');//随机数
              for(var i = 0; i < codeLength; i++) {//循环操作
                var index = Math.floor(Math.random()*36);//取得随机数的索引（0~35）
                code += random[index];//根据索引取得随机数加到code上
              }
              console.log(code)
             if(checkCode){
               checkCode.value = code;//把code值赋给验证码
             }
            },
            validate(){
              var inputCode = document.getElementById("inputcode").value.toUpperCase(); //取得输入的验证码并转化为大写
              if(inputCode.length <= 0) { //若输入的验证码长度为0
                alert("请输入验证码！"); //则弹出请输入验证码
              }
              else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
                alert("验证码输入错误！@_@"); //则弹出验证码输入错误
                createCode();//刷新验证码
                document.getElementById("inputcode").value = "";//清空文本框
              }
              else { //输入正确时
                alert("Right");
              }
            },
            tijiao() {
              let config = {
                //formData  提交请求头有两种 multipart/form-data  和 application/x-www-form-urlencoded
                // multipart/form-data   用于type=file 的input提交
                headers: {
                  "Content-Type": "application/x-www-form-urlencoded"
                }
              };
              let formData = new FormData();
              //参数
              formData.append("inputusername",this.inputusername);
              formData.append("inputpassword",this.inputpassword);
              let data = {
                username : this.inputusername,
                pwd : this.inputpassword
              }

              axios.post("http://localhost:8080/login.action ", data).then(res => {
                console.log(res);
              }).catch(error => {
                console.log(error);
              });


          }

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

  .quicklogin{
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
    padding: 0 20px 20px 20px;
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
  .sub-btn{
    width: 88%;
    height: 38px;
    background-color: #2B7A78;
  }
  .quick-extr p{
    margin-left: 30%;
  }
  .quickinfo{
    background-color: #F1F1F1;
    height: 380px;
    width: 100%;
    display: none;
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

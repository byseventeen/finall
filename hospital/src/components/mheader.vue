<template>
  <div class="mheader">
    <div class="row baseMark">
      <div class="col-sm-6 col-md-8">
        <a href="#">
          <img src="../assets/images/logo.jpg" class="load">
        </a>
      </div>
      <div class="col-xs-12 col-md-4">
        <form class="navbar-form navbar-left">
          <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
          </div>
          <button type="submit" class="btn btn-default btn-sub">Submit</button>
        </form>
      </div>
    </div>

    <div class="row nav">
      <div class="col-md-1">1</div>
      <div class="col-xs-12 col-md-10">
        <nav class="navbar navbar-default">
          <div class="container-fluid">

            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
            </div>


            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav">
                <!--<li><a href="./index">首页</a></li>-->
                <li>
                  <router-link to="/index">首页</router-link>
                </li>
                <li><router-link to="/book">预约挂号</router-link></li>
                <li><router-link to="/guide">就诊指南</router-link></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">个人中心 <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><router-link to="/person">我的预约</router-link></li>
                    <li role="separator" class="divider"></li>
                    <li><router-link to="/person">我的缴费</router-link></li>
                    <li role="separator" class="divider"></li>
                    <li><router-link to="/person">我的资料</router-link></li>
                  </ul>
                </li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">公告及反馈 <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><router-link to="/guide">停诊通知</router-link></li>
                    <li role="separator" class="divider"></li>
                    <li><router-link to="/guide">意见反馈</router-link></li>
                    <li role="separator" class="divider"></li>
                    <li><router-link to="/guide">通告</router-link></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
      <div class="col-md-1">1</div>
    </div>


  </div>
</template>

<script type="es6">

  export default {
        name: "mheader",

    }
</script>

<style scoped>
  .baseMark{
    padding: 20px;
  }
  .load{
    width: 227px;
    height: 47px;
    margin-left: 10%;
  }
  .container-fluid{
    background-color: #2B7A78;
    color: #FFFFFF;
  }
  .navbar-nav>li>a{
    color: #ffffff;
  }
  .btn-sub{
    background-color: #5F9EA0;
    color: #FFFFFF;
  }

</style>

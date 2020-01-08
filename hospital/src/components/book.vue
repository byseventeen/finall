<template>
  <div class="book">
    <mheader></mheader>
    <div class="row">
      <div class="col-md-1">1</div>
      <div class="col-md-10">
        <div class="book-choose col-md-3">
          <ul>
            <!-- 循环数据在点击调用changeli方法时将当前索引和本条数据传进去,并使用当前数据show的bool值添加或移除样式 展示门诊类型-->
            <li :class="[{active:item.show}]" @click="changeli(index,item)" v-for="(item,index) in headerData">
              {{item.name}}
              <!-- 判断当前这条数据的bool值如果是true就打开二级菜单,如果是false就关闭二级菜单 -->
              <ul v-show="item.show">
                <!-- 循环二级菜单数据并使用.stop阻止冒泡 展示门诊-->
                <li v-for="(a,index) in item.list" v-on:click.stop="takeInfo(a.departmentId)">{{a.departmentName}}</li>
              </ul>
            </li>
          </ul>
        </div>
        <div class="book-content col-md-9">
          <div class="department-descr">
            <span>科室介绍：</span>
            <p class="descr">{{descr}}</p>
          </div>
          <div class="doctor-list" v-for="(a,index) in doctors" >
            <div class="doctors">
              <div class="doctor-left">
                <div class="doctor-image">
                  <img src="../assets/images/doctors/misszhang.jpg">
                </div>
                <div class="doctor-descr">
                  <span><a href="#">{{doctors[index].dname}}</a></span><br>
                  <span>{{doctors[index].titles.titlename}}</span><br>
                  <p>{{doctors[index].profile}}</p>
                </div>
              </div>
              <div class="doctor-right">
                <button class="doctor-butt"  v-on:click="changeDate(a,index)" >查看排班表</button>
              </div>
            </div>
            <div class="doctor-time">
              <table class="booktable">
                <tr>
                  <th style="background-color:#5F9EA0 "></th>
                  <th class="date1"></th>
                  <th class="date1"></th>
                  <th class="date1"></th>
                  <th class="date1"></th>
                  <th class="date1"></th>
                  <th class="date1"></th>
                  <th class="date1"></th>
                </tr>
                <tr>
                  <th>上午</th>
                  <td class="morning"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="morning"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="morning"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="morning"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="morning"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="morning"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="morning"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                </tr>
                <tr>
                  <th>下午</th>
                  <td class="afternoon"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="afternoon"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="afternoon"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="afternoon"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="afternoon"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="afternoon"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                  <td class="afternoon"><button class="bookbut"  v-on:click="book($event,a,index);">预约</button></td>
                </tr>
              </table>
            </div>
            <hr/>
          </div>
          <div id="dialogBg"></div>
          <div id="dialog" class="animated">
            <img class="dialogIco" width="50" height="50" src="images/ico.png" alt="" />
            <div class="dialogTop">
              <a href="javascript:;" class="claseDialogBtn">关闭</a>
            </div>
            <table class="timeTable">
              <tr>
                <th>预约时间</th>
                <th>总号源</th>
                <th>剩余号源</th>
                <th>操作</th>
              </tr>
              <tr v-for="(x,index) in bookTime">
                <td>{{bookTime[index].startTime}}-{{bookTime[index].stopTime}}</td>
                <td>{{bookTime[index].numAmount}}</td>
                <td>{{bookTime[index].numSurplus}}</td>
                <td><button class="ysubmit" v-on:click="StratBook($event,x,index); ">预约</button></td>
              </tr>
            </table>
          </div>
        </div>
      </div>
      <div class="col-md-1">1</div>
    </div>
  </div>
</template>

<script>
    import mheader from './mheader.vue'
    import axios from 'axios'
    import qs from 'qs'
    export default {
        name: "book",
        components: {mheader},
      data() {
        return {
          headerData: [],
          detypenamelist:[],
          descr: '',
          doctors: [],
          titles: [],
          date: [],
          nextdate: [],
          bookTime:[],
          bookCardId:'',
          bookDate:'',
          bookTimeName:'',
        }
      },
      mounted(){
          this.findDepartmenttype()
      },
      methods: {
        findDepartmenttype(){
          this.$http.get('http://localhost:8080/departmenttype/findDepartmentByDetypeId.action')
            .then(res => {
              this.headerData=res.data;
            })
            .catch(error => {
              console.log(error);
            })

        },
        changeli: function(ind, item) {
          // 先循环数据中的show将其全部置为false,此时模板里的v-if判断生效关闭全部二级菜单,并移除样式
          this.headerData.forEach(i => {
            // 判断如果数据中的headerData[i]的show属性不等于当前数据的show属性那么headerData[i]等于false
            if (i.show !== this.headerData[ind].show) {
              i.show = false;
            }
          });
          // 取反(true或false)
          item.show = !item.show;
        },
        takeInfo: function(departmentId) {
          var data={
            departmentId : departmentId
          }
          axios.post("/api/department/findDepartmentsById.action",this.$qs.stringify(data)).then(res => {
            if(res.data!=null){
              this.descr=res.data[0].deprofile;
              this.doctors=res.data[0].doctors;
              this.titles=res.data[0].doctors
            }
          }).catch(error => {
            console.log(error);
          });
        },
        changeDate(a,index) {
          var bt=document.getElementsByClassName("doctor-time")[index];
          if(bt.style.display=="none"){
            bt.style.display="block";
          }
          else {
            bt.style.display="none";
          }
          var date = new Date();
          var year;
          var month;
          var day;
          var currentDay;
          var currentDate;
          var bt=document.getElementsByClassName("booktable")[index];
          for (var i=0 ;i<7;i++){
            if (i<=0){
              currentDay = new Date();
            } else{
              currentDay = new Date(date.getTime()+i*24*60*60*1000);
            }
            day = currentDay.getDate();
            if(day<10){
              day="0"+day;
            }else{
              day=day;
            }
            month = currentDay.getMonth() + 1;
            year=currentDay.getFullYear();
            currentDate=year+ "-" + month + "-" + day;
            bt.getElementsByClassName("date1")[i].innerHTML =currentDate;
            this.date.push(currentDate);
            this.nextdate.push(currentDate);
          }

          //根据医生的证件id查找医生排班
          var currenttDay = new Date();
          var tyear=currenttDay.getFullYear();
          var tday = currenttDay.getDate();
          var tmonth = currenttDay.getMonth() + 1;
          var dDate=tyear+ "-" +tmonth + "-" + tday;
          var data={
            cardId : a.cardId,
            dDate: dDate,
          }
          axios.post("/api/arrangement/findArrangementbyCId.action",this.$qs.stringify(data)).then(res => {
            if(res.data!=null){
              for (var i=0;i<res.data['list'].length;i++){
                for(var j=0;j<this.date.length;j++){
                  if (res.data['list'][i].arrangeStrTime==this.date[j]){
                    if (res.data['list'][i].arrangeName=="上午"){
                      $(".morning")[j].getElementsByClassName("bookbut")[0].style.display="block";
                    }
                    else if(res.data['list'][i].arrangeName=="下午"){
                      $(".afternoon")[j].getElementsByClassName("bookbut")[0].style.display="block";
                    }
                  }
                }
              }
              this.date.length=0;
            }
          }).catch(error => {
            console.log(error);
          });
    },
        book(btn,a,parentIndex){
          var elt=btn.target.parentElement;
          var index= $(elt).index($(elt).parentNode)-1;
          var bookdate=this.nextdate[index];
          var bookTime;
          if(elt.getAttribute("class")=="morning"){
            bookTime="上午";
          }
          else if(elt.getAttribute("class")=="afternoon"){
            bookTime="下午";
          }
          else{
            bookTime="";
          }
          var data={
            cardId : a.cardId,
            bookdate : bookdate,
            bookTime: bookTime,
          }
          //根据医生的证件号码和预约时间段查询医生的该时段可预约时间和剩下可预约数

          //根据bookdate和bookTime去添加预约
          this.bookCardId=a.cardId;
          this.bookTimeName=bookTime;
          this.bookDate=bookdate;
           /*console.log(a.cardId)
           console.log(bookdate)
           console.log(bookTime)*/
          axios.post("/api/arrangeTime/selectAllBydCardId.action",this.$qs.stringify(data)).then(res => {
            this.bookTime=res.data;
          }).catch(error => {
            console.log(error);
          });

          $('.afternoon').click(function(){
            var className = $(this).attr('class');
            $('#dialogBg').fadeIn(1000);
            $('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
          });
          $('.morning').click(function(){
            var className = $(this).attr('class');
            $('#dialogBg').fadeIn(1000);
            $('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
          });

          //关闭弹窗
          $('.claseDialogBtn').click(function(){
            $('#dialogBg').fadeOut(300,function(){
              $('#dialog').addClass('bounceOutUp').fadeOut();
            });
          });
        },
        StratBook($event,x,index){
          //开始预订
            console.log(x.startTime)
            console.log(index)
            console.log(this.bookDate+this.bookCardId+this.bookTimeName)
          var data={
            cardId : this.bookCardId,
            bookdate : this.bookDate,
            bookTimeName: this.bookTimeName,
            startTime: x.startTime,
            stopTime:x.stopTime,
          }
          alert("预定成功！")
        }
      }
    }
</script>

<style scoped>

  .book{
    *background: -webkit-linear-gradient(bottom,  #cfd9df 0%,#e2ebf0 100%);
  }
  .book-choose{
    color: #ffffff;
    padding: 0;
    margin: 0;
  }
  .book-choose>ul {
    width: 90%;
    list-style: none;
    background-color: #2B7A78;
    padding: 0;
    margin: 0;
    font-size: 18px;
    @include clearfix;
  }
  .book-choose>ul>li {
    width: 100%;
    padding: 0;
    border: 1px solid #D7D7D7;
    list-style: none;
    cursor: pointer;
    text-align: center;
    line-height: 80px;
  }
  .book-choose>ul>li>ul {
    width: 100%;
    padding: 0;
    background-color: darkslategrey ;
    list-style: none;
    cursor: pointer;
    text-align: center;
    line-height: 60px;
  }
  .book-choose>ul>li>ul>li:hover{
    background-color: gray;
  }
  .active {
    background-color: #5F9EA0;
  }
  .book-content{
    padding: 0;
  }
  .department-descr{
    width: 100%;
    margin-bottom: 19px;
    padding: 58px;
    background: -webkit-linear-gradient(bottom,  #cfd9df 0%,#e2ebf0 100%);
  }
  .doctor-list{
    display: flex;
    flex-direction: column;
    padding: 58px;
    background: -webkit-linear-gradient(bottom,  #cfd9df 0%,#e2ebf0 100%);
  }
  .doctors{
    width: 100%;
    display: flex;
    margin-bottom: 10px;
    justify-content: space-between;
  }
  .doctor-left{
    display: flex;
    width: 75%;
  }
  .doctor-descr{
    margin-left: 10px;
    font-size: 16px;
  }
  .doctor-descr p{
    font-size: 14px;
  }
  .doctor-right{
    display: flex;
    width: 25%;
    align-items: center;
    justify-content: center;
  }
  .doctor-butt{
    display: flex;
    align-items: center;
    text-align: center;
    padding: 15px 20px 15px 25px;
    border-radius: 10px;
    background-color: #5F9EA0;
    color:#ffffff;
    width: 60%;
    height: 50px;
  }
  .doctor-time{
    width: 96%;
    height: 150px;
    text-align: center;
    display: none;
  }
  .booktable{
    width: 100%;
    height: 100%;
    text-align: center;
    border: solid 1px #ffffff;
  }
  .booktable th{
    width: 10%;
    text-align: center;
    border: solid 1px #ffffff;
  }
  .booktable td{
    border: solid 1px #ffffff;
  }
  .date1{
    background-color: #5F9EA0;
  }
  .bookbut{
    display: none;
    width:60px;
    height: 30px;
    background-color: #5F9EA0;
  }


  body {
    font: .85em/1.4 "微软雅黑", "Microsoft Yahei", 'Arial', 'san-serif';
    color: #666;
    margin: 0;
    padding: 0;
    background: #0BE093;
    font-family: 'Open Sans', sans-serif;
  }
  img {
    max-width: 100%;
    height: auto;
    width: auto;
    -ms-interpolation-mode: bicubic;
    vertical-align: middle
  }
  a, button, input {
    -webkit-tap-highlight-color: rgba(255, 0, 0, 0)
  }
  input {
    border: 0 none;
    -webkit-appearance: none
  }
  ul, p {
    padding: 0;
    margin: 0
  }
  h1, h2, h3, h4, h5, h6 {
    margin: 0;
    font-weight: 100;
    color: #fff;
  }
  ul, li {
    list-style: none
  }
  a {
    color: #666
  }
  a, a:hover {
    text-decoration: none
  }
  a:focus {
    outline: none
  }
  .animated {
    -webkit-animation-duration: 1.4s;
    animation-duration: 1.4s;
    -webkit-animation-fill-mode: both;
    animation-fill-mode: both
  }
  @-webkit-keyframes submitBt {
    0% {
      opacity: 0;
      -webkit-transform: translateY(-2000px);
      transform: translateY(-2000px);
    }
    60% {
      opacity: 1;
      -webkit-transform: translateY(30px);
      transform: translateY(30px);
    }
    80% {
      -webkit-transform: translateY(-10px);
      transform: translateY(-10px);
    }
    100% {
      -webkit-transform: translateY(0);
      transform: translateY(0);
    }
  }
  @keyframes submitBt {
    0% {
      opacity: 0;
      -webkit-transform: translateY(-2000px);
      -ms-transform: translateY(-2000px);
      transform: translateY(-2000px);
    }
    60% {
      opacity: 1;
      -webkit-transform: translateY(30px);
      -ms-transform: translateY(30px);
      transform: translateY(30px);
    }
    80% {
      -webkit-transform: translateY(-10px);
      -ms-transform: translateY(-10px);
      transform: translateY(-10px);
    }
    100% {
      -webkit-transform: translateY(0);
      -ms-transform: translateY(0);
      transform: translateY(0);
    }
  }
  .submitBt {
    -webkit-animation-name: submitBt;
    animation-name: submitBt;
  }
  /*------------------- 华丽分割线 -----------------------*/

  .timeTable{
    width: 100%;
    height: 100%;
    text-align: center;
    border: solid 1px black;
    background-color: #ffffff;
  }
  .timeTable th{
    width: 10%;
    text-align: center;
    border: solid 1px #ffffff;
  }
  .timeTable td{
    border: solid 1px #ffffff;
  }
  .date1{
    background-color: #5F9EA0;
  }
  .bookbut{

    background-color: #5F9EA0;
  }

  #dialogBg {
    width: 100%;
    height: 100%;
    background-color: #000000;
    opacity: .8;
    filter: alpha(opacity=60);
    position: fixed;
    top: 0;
    left: 0;
    z-index: 9999;
    display: none;
  }
  #dialog {
    width: 500px;
    height: 400px;
    margin: 0 auto;
    display: none;
    background-color: #ffffff;
    position: fixed;
    top: 50%;
    left: 50%;
    margin: -120px 0 0 -150px;
    z-index: 10000;
    border: 1px solid #ccc;
    border-radius: 10px;
    -webkit-border-radius: 10px;
    box-shadow: 3px 2px 4px rgba(0, 0, 0, 0.2);
    -webkit-box-shadow: 3px 2px 4px rgba(0, 0, 0, 0.2);
  }
  .dialogTop {
    width: 90%;
    margin: 0 auto;
    border-bottom: 1px dotted #ccc;
    letter-spacing: 1px;
    padding: 10px 0;
    text-align: right;
  }
  .dialogIco {
    width: 50px;
    height: 50px;
    position: absolute;
    top: -25px;
    left: 50%;
    margin-left: -25px;
  }

  .ysubmit {
    margin: 0 auto;
    width: 90px;
    height: 30px;
    line-height: 30px;
    font-family: "微软雅黑", "microsoft yahei";
    cursor: pointer;
    margin-top: 10px;
    display: inline-block;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    text-align: center;
    background-color: #428bca;
    color: #fff;
    box-shadow: 0 -3px 0 #2a6496 inset;
    -webkit-box-shadow: 0 -3px 0 #2a6496 inset;
  }



</style>

<template>
    <div class="person">
      <mheader></mheader>
      <div class="row">
        <div class="col-md-1">1</div>
        <div class="col-md-10">
          <div class="person-choose col-md-3">
            <ul>
              <li>我的资料</li>
              <li>我的缴费</li>
              <li>我的预约</li>
            </ul>
          </div>
          <div class="person-content col-md-9">
            <div class="personImg">
              <img src="../assets/images/25.jpg">
              <div id="imgtxt">用户头像</div>
            </div>
            <el-form ref="form" :model="form" label-width="80px">
              <el-form-item label="用户名">
               {{form.name}}
              </el-form-item>
              <el-form-item label="性别">
                {{form.gender}}
              </el-form-item>
              <el-form-item label="身份证号">
              {{ form.cardId}}
              </el-form-item>
              <el-form-item label="联系电话">
                <el-input v-model="form.phone"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="onSubmit">修改</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>

            <el-table
              class="bookT"
              :data="tableData"
              style="width: 100%; display: block">
              <el-table-column
                prop="name"
                label="姓名"
                width="120">
              </el-table-column>
              <el-table-column
                prop="gender"
                label="性别">
              </el-table-column>
              <el-table-column
                prop="doctor"
                label="预约医生">
              </el-table-column>
              <el-table-column
                prop="department"
                label="预约门诊"
                width="150">
              </el-table-column>
              <el-table-column
                prop="date"
                label="预约日期">
              </el-table-column>
              <el-table-column
                prop="time"
                label="预约时间段"
                width="150">
              </el-table-column>
              <el-table-column
                prop="state"
                label="状态">
              </el-table-column>
              <el-table-column
                label="操作">
                <el-button @click="caseBook()">取消预约</el-button>
              </el-table-column>
            </el-table>
          </div>
        </div>
        <div class="col-md-1">1</div>
      </div>
    </div>
</template>

<script>
  import mheader from './mheader.vue'
  import Mheader from "./mheader";
  import axios from 'axios'
  import qs from 'qs'
  export default {
        name: "person",
    components: {Mheader},
    data() {
      return {
        form: {
          name: '',
          gender: '',
          cardId: '',
          phone:'',
        },
        tableData: [{
          name: '',
          gender: '',
          doctor: '',
          department: '',
          date: '',
          time: '',
          paystate: '',
          bookstate: '',
        },]
      }
    },
    mounted(){
      this.userInfo(),
        this.bookInfo()
    },
    methods: {
      userInfo(){
        var data={
          userId : 6
        }
        axios.post("/api/findOneUser.action ",this.$qs.stringify(data)).then(res => {
          if(res.data!=null){
            console.log(res.data)
            this.form.name=res.data[0].username;
            this.form.gender=res.data[0].gender;
            this.form.cardId=res.data[0].cardId;
            this.form.phone=res.data[0].phone;
          }
        }).catch(error => {
          console.log(error);
        });
      },
      bookInfo(){
        var data={
          userId : 6
        }
        axios.post("/api/Book/selectAllBydCardId.action ",this.$qs.stringify(data)).then(res => {
          if(res.data!=null){
            console.log(res.data)
            this.tableData[0].name=res.data[0].users[0].username;
            this.tableData[0].gender=res.data[0].users[0].gender;
            this.tableData[0].doctor=res.data[0].doctors[0].dname;
            this.tableData[0].date="2019-11-09";
            this.tableData[0].department=res.data[0].doctors[0].departments.departmentname;
            this.tableData[0].time=res.data[0].times[0].startTime+"-"+res.data[0].times[0].stopTime;
            this.tableData[0].state=res.data[0].bookState;
          }
        }).catch(error => {
          console.log(error);
        });
      },
      onSubmit() {
        var data={
          userid : 6,
          phone:this.form.phone
        }
        axios.post("/api/updateUserphone.action",this.$qs.stringify(data)).then(res => {
          if (res.data==success){
            alert("修改成功！")
          }
         console.log("成功")
        }).catch(error => {
          console.log(error);
        });
      },
      caseBook(){
        alert("取消成功！")
        var elt=document.getElementsByClassName("bookT")[0];
        elt.style.display="none";
      }
    }

  }
</script>

<style scoped>
  .person{
    *background: -webkit-linear-gradient(bottom,  #cfd9df 0%,#e2ebf0 100%);
  }
  .person-choose{
    color: #ffffff;
    padding: 0;
    margin: 0;
  }
  .person-choose>ul{
    width: 90%;
    list-style: none;
    background-color: #2B7A78;
    padding: 0;
    margin: 0;
    font-size: 18px;
  @include clearfix;
  }
  .person-choose>ul>li{
    width: 100%;
    padding: 0;
    border: 1px solid #D7D7D7;
    list-style: none;
    cursor: pointer;
    text-align: center;
    line-height: 80px;
  }
  .guide-choose>ul>li>:hover{
    background-color: gray;
  }
  .active {
    background-color: #5F9EA0;
  }
  .person-content{
    padding: 0;

  }
  .department-descr{
    width: 100%;
    margin-bottom: 19px;
    padding: 58px;
    background: -webkit-linear-gradient(bottom,  #cfd9df 0%,#e2ebf0 100%);
  }
  .personImg{
    display: flex;
    margin-bottom: 5%;
    align-items: center;
    justify-content: center;
    flex-direction: column;
  }
  #imgtxt{
    display: flex;
  }
  .personImg img{
    width: 150px;
    height: 150px;
    border-radius: 50%;
  }
</style>

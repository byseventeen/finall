<template>
    <div class="person">
      <mheader></mheader>
      <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
          <div class="person-choose col-md-3">
            <ul>
              <li>我的资料</li>
              <li>我的预约</li>
            </ul>
          </div>
          <div class="person-content col-md-9">
            <el-form ref="form"
                     :model="form"
                     v-if="userVisable"
                     label-width="80px">
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
            <el-form ref="doctorForm"
                     :model="doctorForm"
                     v-if="doctorVisable"
                     label-width="80px">
              <el-col :span="12">
                <el-form-item label="姓名">
                  {{doctorForm.name}}
                </el-form-item>
                <el-form-item label="性别">
                  {{doctorForm.gender}}
                </el-form-item>
                <el-form-item label="工作证号">
                  {{ doctorForm.cardId}}
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="职称">
                  {{ doctorForm.title}}
                </el-form-item>
                <el-form-item label="所属门诊">
                  {{ doctorForm.department}}
                </el-form-item>
                <el-form-item label="所属科室">
                  {{ doctorForm.departmentType}}
                </el-form-item>
              </el-col>

              <el-form-item label="联系电话">
                <el-input v-model="doctorForm.phone" ></el-input>
              </el-form-item>
              <el-form-item label="个人简介">
                <el-input v-model="doctorForm.descr"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="editDoctorInfo">修改</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>

            <el-table
              class="bookT"
              :data="tableData"
              @row-click="handleEdit"
              style="width: 100%; display: block">
              <el-table-column
                prop="bookId"
                label="预约编号"
                width="80">
              </el-table-column>
              <el-table-column
                prop="userName"
                label="预约人"
                width="90">
              </el-table-column>
              <el-table-column
                prop="dName"
                label="预约医生"
                width="90">
              </el-table-column>
              <el-table-column
                prop="dDepartment"
                label="预约门诊"
                width="100">
              </el-table-column>
              <el-table-column
                prop="arrangementDate"
                label="预约日期"
                width="100">
              </el-table-column>
              <el-table-column
                prop="startTime"
                label="开始时间段"
                width="100">
              </el-table-column>
              <el-table-column
                prop="stopTime"
                label="结束时间段"
                width="100">
              </el-table-column>
              <el-table-column
                prop="bookState"
                label="状态"
                width="90">
              </el-table-column>
              <el-table-column
                label="操作">
                <el-button @click="caseBook()" size="small" v-if="userVisable">取消预约</el-button>
                <el-select v-model="bookState" placeholder="修改预约状态" v-if="doctorVisable" @change="editBookState">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
              </el-table-column>
            </el-table>
          </div>
        </div>
        <div class="col-md-1"></div>
      </div>
    </div>
</template>

<script>
  import mheader from '../components/mheader.vue'
  import Mheader from "../components/mheader";
  import axios from 'axios'
  import qs from 'qs'
  export default {
        name: "person",
    components: {Mheader},
    data() {
      return {
        form: {  //用户表单信息
          name: '',
          gender: '',
          cardId: '',
          phone:'',
        },
        doctorForm: {  //医生表单信息
          name: '',
          gender: '',
          cardId: '',
          phone:'',
          descr:'',
          title:'',
          department:'',
          departmentType:'',
        },
        tableData: [],  //该用户所有预约信息
        bookState: '',   //预约状态
        bookId: '',  //预约编号
        userId: sessionStorage.getItem("userId"),  //用户id
        userVisable: true,  //用户信息表单权限
        doctorVisable:false, //医生信息表单权限
        options: [{
          value: 'end',
          label: '已就诊'
        }, {
          value: 'start',
          label: '未就诊'
        }, {
          value: 'breakCut',
          label: '已爽约'
        }, {
          value: 'cancel',
          label: '已取消'
        }],
      }
    },
    mounted(){
      this.Init()
    },
    methods: {
       //页面初始化
       Init(){
         let role=sessionStorage.getItem("role");
         if(role=="user"){
           this.userVisable=true;
           this.doctorVisable=false;
           this.userInfo();
           this.userBookInfo();
         }
         if (role=="doctor") {
           this.doctorVisable=true;
           this.userVisable=false;
           this.doctorInfo();
           this.doctorBookInfo();
         }
       } ,
      //查看个人信息
      userInfo(){
        var data={
          userId : this.userId
        }
        axios.post("/api/findOneUser.action ",this.$qs.stringify(data)).then(res => {
          if(res.data!=null){
            this.form.name=res.data[0].username;
            this.form.gender=res.data[0].gender;
            this.form.cardId=res.data[0].cardId;
            this.form.phone=res.data[0].phone;
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //查看个人的所有预约
      userBookInfo(){
        var self=this;
        var data={
          userId : this.userId
        }
        axios.post("/api/Book/selectAllByUserId.action",this.$qs.stringify(data)).then(res => {
          if(res.data!=null){
            for(let i in res.data){
              self.tableData=res.data[i];
            }
            // this.tableData=res.data;
            console.log(this.tableData)
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //查看医生个人信息
      doctorInfo(){
        var data={
          doctorid : sessionStorage.getItem("doctorId")
        }
        axios.post("/api/doctor/findOneDoctorById.action ",this.$qs.stringify(data)).then(res => {
          if(res.data!=null){
            this.doctorForm.name=res.data[0].dname;
            this.doctorForm.gender=res.data[0].gender;
            this.doctorForm.cardId=res.data[0].cardId;
            this.doctorForm.phone=res.data[0].phone;
            this.doctorForm.descr=res.data[0].profile;
            this.doctorForm.title=res.data[0].titlename;
            this.doctorForm.department=res.data[0].departmentname;
            this.doctorForm.departmentType=res.data[0].departypementname;
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //查看医生的所有预约
      doctorBookInfo(){
        var self=this;
        var data={
          doctorId : sessionStorage.getItem("doctorId")
        }
        axios.post("/api/Book/selectAllByDId.action",this.$qs.stringify(data)).then(res => {
          if(res.data!=null){
             for(let i in res.data){
               self.tableData=res.data[i];
             }
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //修改信息
      onSubmit() {
        var data={
          userid : this.userid,
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
      //取消预约
      caseBook(){
        var data={
          bookId: this.bookId,
          bookState:"cancel"
        }
        axios.post("/api/Book/updateAppointment.action",this.$qs.stringify(data)).then(res => {
          if (res.data){
            alert("取消成功！")
            this.userBookInfo();
            this.doctorBookInfo();

          }
        }).catch(error => {
          console.log(error);
        });
      },
      //修改医生信息
      editDoctorInfo(){
        var data={
          did: sessionStorage.getItem("doctorId"),
          dname: this.doctorForm.name,
          gender: this.doctorForm.gender,
          cardId: this.doctorForm.cardId,
          phone: this.doctorForm.phone,
          profile:this.doctorForm.descr,
          title:this.doctorForm.title,
          departmentname: this.doctorForm.department,
          departypementname: this.doctorForm.departmentType
        }
        axios.post("/api/doctor/updateDoctor.action",this.$qs.stringify(data)).then(res => {
          if (res.data=="fine!"){
            alert("修改成功！")
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //修改预约状态
      editBookState(){
        var data={
          bookId: this.bookId,
          bookState: this.bookState
        }
        axios.post("/api/Book/updateAppointment.action",this.$qs.stringify(data)).then(res => {
          if (res.data=="fine!"){
            alert("修改成功！")
            this.doctorBookInfo();
          }
        }).catch(error => {
          console.log(error);
        });
      },
      //锁定预约id
      handleEdit(row) {
        this.bookId=row.bookId;
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

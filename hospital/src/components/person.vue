<template>
    <div class="person">
      <mheader></mheader>
      <div class="row">
        <div class="col-md-1">1</div>
        <div class="col-md-10">
          <div class="person-choose col-md-3">
            <ul>
              <li>我的预约</li>
              <li>我的缴费</li>
              <li>我的资料</li>
            </ul>
          </div>
          <div class="person-content col-md-9">
            <div class="department-descr">
              <span>预约流程：</span>
              <p>呼吸内科是广州医学院硕士研究生培养点，设有2个病区，64张床位，对慢性阻塞性肺病、肺癌、支气管哮喘和急性呼吸衰竭等疾病的治疗卓有成效。梯队建设：呼吸内科现有主任医师2人，副主任医师3人，主治医师3人，专科住院医师3人，硕士生导师1人，博士1人，在读博士1人，硕士3人。专家介绍：学科带头人：赵子文，男，主任医师，现任广州市第一人民医院大内科副主任兼呼吸内科主任，吉林大学及广州医学院兼职教授、硕士研究生导师，广东省医学会呼吸病分会副主任委员和结核病分会常委，广州市抗癌协会理事，全国和广东省五一奖章获得者。</p>
            </div>
          </div>
        </div>
        <div class="col-md-1">1</div>
      </div>
    </div>
</template>

<script>
  import mheader from './mheader.vue'
  import Mheader from "./mheader";
  export default {
        name: "person",
    components: {Mheader},
    created:function(){

        this.$http.get('http://localhost:8080/findCategory.action')
          .then(res => {
            this.hotlist = res.data; //把取item的数据赋给 item: []中
            console.log(res.data)
          })
          .catch(error => {
            console.log(error);
          })

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
</style>

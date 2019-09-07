<template>
    <div class="guide">
      <mheader></mheader>
      <div class="row">
        <div class="col-md-1">1</div>
        <div class="col-md-10">
          <div class="guide-choose col-md-3">
            <ul>
              <!-- 循环数据在点击调用changeli方法时将当前索引和本条数据传进去,并使用当前数据show的bool值添加或移除样式 -->
              <li :class="[{active:item.show}]" @click="changeli(index,item)" v-for="(item,index) in headerData">
                <!-- 在这里打印出boll值方便查看 -->
                {{item.name}}{{item.show}}
                <!-- 判断当前这条数据的bool值如果是true就打开二级菜单,如果是false就关闭二级菜单 -->
                <ul v-show="item.show">
                  <!-- 循环二级菜单数据并使用.stop阻止冒泡 -->
                  <li v-for="(a,index) in item.list" v-on:click.stop="doThis(index)">{{a}}</li>
                </ul>

              </li>
            </ul>
          </div>
          <div class="guide-content col-md-9">
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
  export default {
    name: "guide",
    components: {mheader},
    data() {
      return {
        headerData: [{
          name: '就诊指南',
          list: ['预约挂号流程', '常见疾病对应科室', '缴费流程', '整形门诊', '外科门诊'],
          show: false
        }, {
          name: '医院公告',
          list: ['停诊通知', '节假日放假通知', '子集', '子集', '子集'],
          show: false
        }, {
          name: '意见反馈',
          list: ['子集', '子集', '子集', '子集', '子集'],
          show: false
        }, {
          name: '导航4',
          list: ['子集', '子集', '子集', '子集', '子集'],
          show: false
        }, {
          name: '导航5',
          list: ['子集', '子集', '子集', '子集', '子集'],
          show: false
        }]
      }
    },
    methods: {
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
        console.log(item.name)
      },
      doThis: function(index) {
        alert(index)
      }
    }
  }
</script>

<style scoped>
  .guide{
    *background: -webkit-linear-gradient(bottom,  #cfd9df 0%,#e2ebf0 100%);
  }
  .guide-choose{
    color: #ffffff;
    padding: 0;
    margin: 0;
  }
  .guide-choose>ul {
    width: 90%;
    list-style: none;
    background-color: #2B7A78;
    padding: 0;
    margin: 0;
    font-size: 18px;
  @include clearfix;
  }
  .guide-choose>ul>li {
    width: 100%;
    padding: 0;
    border: 1px solid #D7D7D7;
    list-style: none;
    cursor: pointer;
    text-align: center;
    line-height: 80px;
  }
  .guide-choose>ul>li>ul {
    width: 100%;
    padding: 0;
    background-color: darkslategrey ;
    list-style: none;
    cursor: pointer;
    text-align: center;
    line-height: 60px;
  }
  .guide-choose>ul>li>ul>li:hover{
    background-color: gray;
  }
  .active {
    background-color: #5F9EA0;
  }
  .guide-content{
    padding: 0;
  }
  .department-descr{
    width: 100%;
    margin-bottom: 19px;
    padding: 58px;
    background: -webkit-linear-gradient(bottom,  #cfd9df 0%,#e2ebf0 100%);
  }
</style>

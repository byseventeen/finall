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
                {{item.name}}
                <!-- 判断当前这条数据的bool值如果是true就打开二级菜单,如果是false就关闭二级菜单 -->
                <ul v-show="item.show">
                  <!-- 循环二级菜单数据并使用.stop阻止冒泡 -->
                  <li v-for="(a,index) in item.list" v-on:click.stop="doThis(index)">{{a}}</li>
                </ul>

              </li>
            </ul>
          </div>
          <div class="guide-content col-md-9">
            <el-form ref="form" :model="form" label-width="80px" id="guidef" style="display: none">
              <el-form-item label="投诉人">
                <el-input v-model="form.name"></el-input>
              </el-form-item>
              <el-form-item label="投诉对象">
                <el-select v-model="form.region" placeholder="请选择投诉对象">
                  <el-option label="环境" value="shanghai"></el-option>
                  <el-option label="医生" value="beijing"></el-option>
                  <el-option label="医疗服务" value="beijing"></el-option>
                  <el-option label="其他" value="beijing"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="开启匿名">
                <el-switch v-model="form.delivery"></el-switch>
              </el-form-item>
              <el-form-item label="原因">
                <el-input type="textarea" v-model="form.desc"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">提交</el-button>
                <el-button>取消</el-button>
              </el-form-item>
            </el-form>
            <div class="department-descr">
              <span>预约须知：</span>
              <p>
                第一条：六种预约渠道<br/>

                （1）网站<br/>
                （2）广州健康通微信公众号<br/>
                （3）广州健康通支付宝生活号<br/>
                （4）广州健康通APP<br/>
                （5）自助终端<br/>
                （6）12320<br/>
                第二条：预约挂号操作流程<br/>



                第三条：实名制预约，预约时需要提供真实姓名、身份证号、手机号。<br/>

                第四条：港澳台用户注册，需要提供《港澳居民来往内地通行证》、《台湾居民来往大陆通行证》。<br/>

                第五条：预约分：预约挂号、当天挂号。<br/>

                第六条：当天挂号不予以退号及退费<br/>

                第七条：支持当天挂号的预约渠道包括广州健康通微信公众号、广州健康通支付宝生活号、广州健康通APP、自助终端。<br/>

                第八条：支持预约7天内号源，个别医院可预约7天以上号源。<br/>

                第九条：通过微信/支付宝预约的订单，需要留意微信/支付宝消息，系统不发手机短信。<br/>

                第十条：一个身份证只能注册一个账号<br/>

                第十一条：一个手机号只能注册3个账号<br/>

                第十二条：每日预约不能超2次<br/>

                第十三条：每月预约不能超6次<br/>

                第十四条：同一天同一个科室每人只能预约一次<br/>

                第十五条：同一天同一个医生每人只能预约一次<br/>

                第十六条：同一天同一时间段每人只能预约一次<br/>

                第十七条：预约类型：为本人预约、为子女预约、为老人预约。<br/>

                第十八条：为老人预约，仅限55周岁（包含55周岁）以上老人。<br/>

                第十九条：为老人预约，一个账号只能绑定两名老人信息。<br/>

                第二十条：为子女预约，仅限16周岁（包含16周岁）以下儿童。<br/>

                第二十一条：为子女预约，一个账号只能绑定三名子女信息。<br/>

                第二十二条：为子女/老人预约，已添加的子女/老人信息不能删除。<br/>

                第二十三条：为子女/老人预约，已添加的子女/老人信息，如姓名、身份证、手机号都可以修改，每半年可修改一次。<br/>

                第二十四条：为子女预约，子女身份证号码/出生证编号为必填。<br/>

                第二十五条：未满16岁（含16岁）的儿童账号不支持绑定其他小孩信息进行预约挂号。<br/>

                第二十六条：为贯彻落实《广州市人民政府办公厅关于印发广州地区公立医院综合改革实施方案的通知》，对六周岁（含6岁）以下儿童的临床诊断中有创活检和探查、临床手术治疗等体现儿科医务人员技术劳务特点和价值的医疗服务项目，实行加收不超过30％的基本医疗服务价格政策。<br/>

                第二十七条：六周岁（含6岁）以下儿童医疗服务价格加收政策实行期限为5年。<br/>

                第二十八条：支付方式：微信支付、支付宝支付、网银<br/>

                第二十九条：健康通订单支持跨支付渠道支付功能，如在支付宝生活号锁定了号源，可通过微信公众号支付订单。<br/>

                第三十条：取号时间：用户需要在就诊当天，在预约时间前提前30分钟取号，个别医院有个性化要求。<br/>

                第三十一条：取号凭证：取号时需要提供预约成功消息、身份证等信息。<br/>

                第三十二条：正常停诊，停诊信息系统将以手机短信的形式发给用户。<br/>

                第三十三条：临时停诊（当天停诊），预约系统不发手机短信，该突发情况需要医院门诊信息科或驻院客服自行通知用户。<br/>

                第三十四条：取消订单必须在预约就诊前，提前一天（24点前）取消订单。个别医院对订单的取消规则有个性化要求。<br/>

                第三十五条：退费分：自动退费、人工退费、停诊退费。<br/>

                第三十六条：自动退费：预付费的订单，用户成功申请退费后，费用将在7个工作日内退还至用户支付账号内。<br/>

                第三十七条：人工退费：用户成功申请退费后，如超过7个工作日退款仍未到账，用户可拔12320申诉人工退费，人工退费处理时间为7个工作日。<br/>

                第三十八条：停诊退费：当医院HIS系统向预约系统发送停诊通知后，预约系统将在7个工作日内将费用退还至用户支付账号内。如用户未收到相关退费可致电12320申诉人工退费。<br/>

                第三十九条：用户预约成功，既未按时取号就诊，也未提前取消订单，视为爽约。<br/>

                第四十条：爽约次数的计算从账号注册之日起累积统计。<br/>

                第四十一条：累积爽约超过3次（含3次）将列入黑名单。<br/>

                第四十二条：爽约减免机制：“取三减一”，即连续取号三次，则减免一次总爽约次数。<br/>

                第四十三条：爽约次数可以减免，但订单记录（订单标识）系统不会删除。<br/>

                第四十四条：黑名单的用户将不再享受预约挂号服务。<br/>

                第四十五条：列入黑名单三个月后，自动解除黑名单限制。<br/>
              </p>
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
          list: ['预约挂号流程', '常见疾病对应科室', '缴费流程'],
          show: false
        }, {
          name: '医院公告',
          list: ['停诊通知', '节假日放假通知'],
          show: false
        }, {
          name: '意见反馈',
          list: ['投诉反馈', '黑名单申诉'],
          show: false
        }, {
          name: '常见问题',
          list: ['注册', '登录', '密码问题', '手机号码相关问题', '爽约问题'],
          show: false
        },],
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
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
       // alert(index)
        var elt=document.getElementById("guidef");
        elt.style.display="block";
      },
      onSubmit() {
        alert("提交成功！感谢您的提交，我们会尽快处理和完善！");
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

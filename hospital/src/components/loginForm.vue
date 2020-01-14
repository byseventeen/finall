<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="用户名" prop="username">
      <el-input v-model="ruleForm.username" style="width:80%;"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="ruleForm.password" style="width:80%;"></el-input>
    </el-form-item>
    <el-form-item label="登录身份" prop="role">
      <el-radio-group v-model="ruleForm.role">
        <el-radio label="用户登录"></el-radio>
        <el-radio label="医生登录"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="验证码" prop="role">
      <el-input v-model="ruleForm.code" style="width:50%;"></el-input>
      <el-button  id="code" @click="createCode" style="width:30%;">{{beforeCode}}</el-button>
    </el-form-item>
    <el-form-item  prop="type">
      <el-checkbox-group v-model="ruleForm.type">
        <el-checkbox label="七天免登录" name="type"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')" width="100%">立即登录</el-button>
      <el-button type="primary"  style="width: 100px;">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  export default {
    name: "loginForm",
    data() {
      return {
        ruleForm: {
          username: '',
          password: '',
          type: '',
          role: '',
          code: '',
        },
        beforeCode:'',
        rules: {
          resource: [
            { required: true, message: '请选择登录身份', trigger: 'change' }
          ],
        }
      };
    },
    mounted(){
      this.createCode()
    },
    methods: {
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
          this.beforeCode=code;
        }
      },
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }
</script>
<style scoped>
  .demo-ruleForm{
    margin-top:15%;
  }
</style>

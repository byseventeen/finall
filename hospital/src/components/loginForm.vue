<template>
  <el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="账号">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.password"></el-input>
    </el-form-item>
    <el-form-item label="验证码"  prop="verifycode">
      <el-input v-model="form.verifycode" style="width: 50%"></el-input>
    </el-form-item>
    <el-form-item>
      <div class="identifybox">
        <div @click="refreshCode">
          <code :identifyCode="identifyCode"></code>
        </div>
        <el-button @click="refreshCode" type='text' class="textbtn">看不清，换一张</el-button>
      </div>
    </el-form-item>
    <el-form-item label="登录身份">
      <el-radio-group v-model="form.role">
        <el-radio label="医生登录"></el-radio>
        <el-radio label="用户登录"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item>
      <el-checkbox-group v-model="form.type">
        <el-checkbox label="七天免登录" name="type"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">立即登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import Code from "./Code";
    export default {
      name: "loginForm",
      components: {Code},
      data() {
        return {
          form: {
            name: '',
            password: '',
            verifycode: '',
            role: '',
            type: ''
          },
          identifyCodes: '1234567890',
          identifyCode: '',
        }
      },
      mounted(){
        this.identifyCode='',
        this.makeCode(this.identifyCodes,4)
      },
      methods: {
        onSubmit() {
          console.log('submit!');
        },
        // 通过改变input的type使密码可见
        showPassword() {
          this.fontstyle === '' ? (this.fontstyle = 'color: red') : (this.fontstyle = '') // 改变密码可见按钮颜色
          this.passwordType === ''
            ? (this.passwordType = 'password')
            : (this.passwordType = '')
        },
        // 点击登入按钮
        handleLogin() {
          this.$refs.loginForm.validate(valid => {
            if (valid) {
              this.$store.dispatch('Login', this.loginForm).then(res => {
                this.$router.push({ path: '/dashboard/dashboard' })
              })
            }
          })
        },
        // 生成随机数
        randomNum(min, max) {
          return Math.floor(Math.random() * (max - min) + min)
        },
        // 切换验证码
        refreshCode() {
          this.identifyCode = ''
          this.makeCode(this.identifyCodes, 4)
        },
        // 生成四位随机验证码
        makeCode(o, l) {
          for (let i = 0; i < l; i++) {
            this.identifyCode += this.identifyCodes[
              this.randomNum(0, this.identifyCodes.length)
              ]
          }
          console.log(this.identifyCode)
        }
      }
    }
</script>

<style scoped>

  .identifybox{
    display: flex;
    width: 100px;
    justify-content: space-between;
    margin-top:7px;
  }
  .iconstyle{
    color:#409EFF;
  }
</style>

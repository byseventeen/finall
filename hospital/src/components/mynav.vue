<template>
  <div>
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1">预约挂号</el-menu-item>
      <el-menu-item index="2">挂号指南</el-menu-item>
      <el-submenu index="3">
        <template slot="title">个人中心</template>
        <el-menu-item index="2-1">我的预约</el-menu-item>
        <el-menu-item index="2-2">我的缴费</el-menu-item>
        <el-menu-item index="2-3">我的资料</el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">公告和反馈</template>
        <el-menu-item index="2-1">医院公告</el-menu-item>
        <el-menu-item index="2-2">意见反馈</el-menu-item>
      </el-submenu>
      <el-menu-item index="5"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>
    </el-menu>
  </div>
</template>


<script>
  export default {
    name: "mynav",
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

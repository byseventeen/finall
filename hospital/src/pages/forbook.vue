<template>
  <div>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3" class="el-col"><div class="grid-content bg-purple"></div></el-col>
      <el-col :xs="8" :sm="12" :md="16" :lg="18" :xl="18" class="el-col">
        <div class="grid-content bg-purple">
          <el-container>
            <el-header>
              <mynav></mynav>
            </el-header>
            <el-container>
              <el-aside ><Menu></Menu></el-aside>
              <el-main>Main</el-main>
            </el-container>
          </el-container>
        </div>
      </el-col>
      <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="3" class="el-col"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>

  </div>

</template>

<script>
  import Mynav from "../components/mynav";
  import Menu from "../components/menu";
    export default {
        name: "forbook",
      components: {Mynav,Menu}
    }
</script>

<style scoped>
  .grid-content{
    border-radius: 0 !important;
  }
  .el-col {
    border-radius: 0 !important;
    padding: 0 !important;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }

  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
    padding: 0 !important;;
  }

  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }
</style>

<!--suppress JSAnnotator -->
<template>
  <div id="shophead">
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item class="navfont" index="0">预留logo</el-menu-item>
      <!--<el-menu-item index="1" style="width:400px" disabled>&nbsp;</el-menu-item>-->
      <el-menu-item class="navfont1" index="2">首页</el-menu-item>
      <el-menu-item class="navfont1" index="3">购物车</el-menu-item>
      <el-menu-item class="navfont1" index="4">收藏夹</el-menu-item>
      <el-submenu index="5">
        <template slot="title" class="navfont1"><span class="navfont1">我的订单</span></template>
        <el-menu-item index="5-1" class="navfont1">未完成订单</el-menu-item>
        <el-menu-item index="5-2" class="navfont1">已完成订单</el-menu-item>
      </el-submenu>
      <!--<el-menu-item index="6" style="width:200px"></el-menu-item>-->
      <el-menu-item index="7" class="navfont1">
        <a class="ha" href="#">登录</a>
        &nbsp;/&nbsp;
        <a class="ha" href="#">注册</a></el-menu-item>
      <el-menu-item index="8" class="navfont1">客服</el-menu-item>
      <!--<el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>-->
    </el-menu>
    <el-container>
      <el-aside width="120px">
        <el-row class="tac">
          <el-col :span="25">
            <!--<h5>自定义颜色</h5>-->
            <el-menu
              default-active="1"
              class="el-menu-vertical-demo"
              @open="handleOpen"
              @close="handleClose"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
              <el-menu-item index="1" @click="chmenu(1)">
                <i class="el-icon-sort"></i>
                <span slot="title">流量</span>
              </el-menu-item>
              <el-menu-item index="2" @click="chmenu(2)">
                <i class="el-icon-phone"></i>
                <span slot="title">话费</span>
              </el-menu-item>
              <el-menu-item index="3" @click="chmenu(3)">
                <i class="el-icon-printer"></i>
                <span slot="title">配件</span>
              </el-menu-item>
              <el-menu-item index="4" @click="chmenu(4)">
                <i class="el-icon-location-outline"></i>
                <span slot="title">代驾</span>
              </el-menu-item>
              <el-menu-item index="5" @click="chmenu(5)">
                <i class="el-icon-setting"></i>
                <span slot="title">维修</span>
              </el-menu-item>
              <!--<el-menu-item index="6">
                <i class="el-icon-refresh"></i>
                <span slot="title">换新</span>
              </el-menu-item>-->
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
    <hr/>
    <el-footer>
      鄂ICP备 XXXXXXXXXXXXX &nbsp;&nbsp;&nbsp;&nbsp;
      版权所有 &copy;<a class="ha" href="https://blog.tzxcode.cn">&nbsp;tzx-blog</a>
    </el-footer>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        activeIndex: '2',
        activeIndex2: '2',
        activeName21: 'first1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClick1(tab, event) {
        console.log(tab, event);
      },
      chmenu(num){
        if(num == 1){
          this.$router.push("/flow")
        }else if(num == 2){
          this.$router.push("/phonebill")
        }
      }
    }
  }
</script>
<style>
  .el-footer {
    color: #000;
    padding: 20px;
    font-size: 20px;
  }

  .navfont {
    font-size: 28px;
  }

  .navfont1 {
    font-size: 18px;
  }

  .ha:link {
    text-decoration: none;
    color: blue
  }

  .ha:active {
    text-decoration: blink
  }

  .ha:hover {
    text-decoration: underline;
    color: red
  }

  .ha:visited {
    text-decoration: none;
    color: green
  }

  .el-aside {
    text-align: center;
    line-height: 200px;
    width: 150px;
  }
</style>

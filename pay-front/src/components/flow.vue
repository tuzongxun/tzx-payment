<!--suppress JSAnnotator -->
<template>
  <div id="myflow">
    <el-row>
      <el-col :span="5" v-for="(o, index) in 4" :key="o" :offset="index > 0 ? 1 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <img src="../images/liu1.jpg" class="image">
          <div style="padding: 14px;">
            <span>流量购买</span>
            <div class="bottom clearfix">
              <!--<time class="time">{{ currentDate }}</time>-->
              <!--<el-button type="text" class="button" style="margin-right: 5px">直接购买</el-button>-->
              <el-button type="text" class="button" @click="charge">去支付</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row style="margin-top: 25px">
      <el-col :span="5" v-for="(o, index) in 4" :key="o" :offset="index > 0 ? 1 : 0">
        <el-card :body-style="{ padding: '0px' }">
          <img src="../images/liu1.jpg" class="image">
          <div style="padding: 14px;">
            <span>流量购买</span>
            <div class="bottom clearfix">
              <!--<time class="time">{{ currentDate }}</time>-->
              <!--<el-button type="text" class="button" style="margin-right: 5px">直接购买</el-button>-->
              <el-button type="text" class="button">去支付</el-button>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        currentDate: new Date(),
      };
    },
    methods: {
    charge(){
      // this.$axios.get("http://localhost:8088/tzxpay/test");
      this.$router.push("/cashier")
    }
    }
  }
</script>
<style>
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
</style>

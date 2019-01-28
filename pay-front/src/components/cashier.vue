<template>
  <div id="cashier">
    <table>
      <tbody>
      <tr>
        <th class="cth">商品名称:&nbsp;&nbsp;</th>
        <td class="ctd">{{goods.name}}</td>
      </tr>
      <tr>
        <th class="cth">商品价格:&nbsp;&nbsp;</th>
        <td class="ctd">{{goods.price}}&nbsp;元</td>
      </tr>
      <tr>
        <th class="cth">商品详情:&nbsp;&nbsp;</th>
        <td class="ctd">{{goods.desc}}</td>
      </tr>
      <tr>
        <th class="cth">商品有效期:&nbsp;&nbsp;</th>
        <td class="ctd">{{goods.endDate}}&nbsp;天</td>
      </tr>
      </tbody>
    </table>
    <el-form :label-position="labelPosition" label-width="80px" :model="goods" action=""
             method="post" id="chpay">
      <el-row :gutter="10" style="margin-top: 5px">
        <el-col :span="2" style="text-align: right">
          <strong>支付方式:</strong>
        </el-col>
        <el-col :span="8">
          <el-radio-group v-model="cradio2" size="mini" fill="#d5c9a5">
            <el-radio-button label="11">
              <img src="../images/ali.jpg" class="image"
                   style="width:80px;height:50px;margin-top: 5px;margin-bottom: 5px">
              <div style="padding: 14px;">
                <span>支付宝支付</span>
              </div>
            </el-radio-button>
            <el-radio-button label="12">
              <img src="../images/wei.jpg" class="image" style="width:80px;height:60px">
              <div style="padding: 14px;">
                <span>微信支付</span>
              </div>
            </el-radio-button>
            <el-radio-button label="13">
              <img src="../images/union.jpg" class="image" style="width:80px;height:60px">
              <div style="padding: 14px;">
                <span>银联支付</span>
              </div>
            </el-radio-button>
          </el-radio-group>
        </el-col>
      </el-row>
      <el-form-item style="margin-top: 15px;margin-left: 45px">
        <el-button type="primary" style="float: left" @click="topay">去支付</el-button>
      </el-form-item>
      <el-form-item>
        <el-input v-show="false" name="subject" v-model="goods.name"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-show="false" name="total_amount" v-model="goods.price"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-show="false" name="timestamp" v-model="goods.timestamp"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-show="false" name="payType" v-model="goods.payType"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-show="false" name="channel" v-model="goods.channel"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-show="false" name="out_trade_no" v-model="goods.out_trade_no"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-show="false" name="thirdMerId" v-model="goods.thirdMerId"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        cradio: '上海',
        cradio2: '11',
        labelPosition: 'right',
        goods: {
          name: '流量包1',
          price: 20,
          desc: '30天流量包1',
          endDate: 30,
          payType: 'ALIPAY',
          channel: 'WAP',
          out_trade_no: '2123145465469878913152',
          thirdMerId: '201901280000001',
        }
      };
    },
    create() {

    },
    methods: {
      topay() {
        document.getElementById("chpay").action = "http://172.23.130.152:8088/tzxpay/wapPay";
        document.getElementById("chpay").submit();
      }
    }
  }
</script>
<style>
  .cdiv {
    display: inline;
  }

  .el-card {
    background-color: rgba(255, 253, 251, 0.64);
    border: 0px;
  }

  .cfi {
    font-size: 30px;
  }

  .cth {
    /*margin-top: 5px;*/
    padding: 5px;
    text-align: right;
  }

  .ctd {
    text-align: left;
  }
</style>

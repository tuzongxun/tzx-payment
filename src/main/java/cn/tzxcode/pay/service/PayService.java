package cn.tzxcode.pay.service;

import cn.tzxcode.pay.dto.AliPayReq;
import cn.tzxcode.pay.dto.AliPayReqContent;
import cn.tzxcode.pay.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 支付工具类
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 */
@Component
public class PayService {
    @Autowired
    RestTemplate restTemplate;

    public String payToAli() {
    try{
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = "123456878787qqqqq";
        // 订单名称，必填
        String subject = "tzxTest";
        System.out.println(subject);
        // 付款金额，必填
        String total_amount = "0.01";
        // 商品描述，可空
        String body = "tzxshoptest";
        // 超时时间 可空
        String timeout_express = "2m";
        // 销售产品码 必填
        String product_code = "QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
//            AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
//            AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AliPayReqContent content = new AliPayReqContent();
        content.setOut_trade_no(out_trade_no);
        content.setSubject(subject);
        content.setTotal_amount(total_amount);
        content.setBody(body);
        content.setTimeout_express(timeout_express);
        content.setProduct_code(product_code);

        AliPayReq aliPayReq = new AliPayReq();
        aliPayReq.setApp_id("2016092200571010");
        aliPayReq.setBiz_content(JSON.toJSONString(content));
        aliPayReq.setCharset("UTF-8");
        aliPayReq.setMethod("alipay.trade.wap.pay");
        aliPayReq.setSign("111");
        aliPayReq.setSign_type("RSA2");
        aliPayReq.setTimestamp(DateUtil.dateFormat("yyyy-MM-dd HH:mm:ss"));
        aliPayReq.setVersion("1.0");

        HttpClient httpClient=new DefaultHttpClient();
        SSLSocketFactory.getSocketFactory().setHostnameVerifier(new AllowAllHostnameVerifier());
        HttpPost post = new HttpPost("https://openapi.alipaydev.com/gateway.do");
        HttpEntity entity=new StringEntity(JSON.toJSONString(aliPayReq));
        post.setEntity(entity);
        HttpResponse res=httpClient.execute(post);
        String resStr= EntityUtils.toString(res.getEntity());
        System.out.println(resStr);
        return resStr;
//        ResponseEntity<String> res = restTemplate.postForEntity("https://openapi.alipaydev.com/gateway.do", JSON.toJSONString(aliPayReq), String.class);
//        System.out.println(res.getStatusCodeValue());
//        System.out.println(res.getStatusCodeValue());
//        System.out.println(res.getBody());

        //        aliPayReq.setBizModel(model);
//            // 设置异步通知地址
//        aliPayReq.setNotifyUrl(AlipayConfig.notify_url);
//            // 设置同步地址
//        aliPayReq.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
//        String form = "";
//            try {
        // 调用SDK生成表单
//                form = client.pageExecute(alipay_request).getBody();
//                response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
//                response.getWriter().write(form);//直接将完整的表单html输出到页面
//                response.getWriter().flush();
//                response.getWriter().close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
//        return res.getBody();
    return null;
    }

}

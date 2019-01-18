package cn.tzxcode.pay.service;

import cn.tzxcode.pay.dao.PayConfigDao;
import cn.tzxcode.pay.dto.AliPayReqDTO;
import cn.tzxcode.pay.dto.AliPayReqContentDTO;
import cn.tzxcode.pay.dto.MerchantReqDTO;
import cn.tzxcode.pay.entity.PayConfigEntity;
import cn.tzxcode.pay.util.AliSignUtil;
import cn.tzxcode.pay.util.DateUtil;
import cn.tzxcode.pay.util.MapUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 支付宝支付业务处理层
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Service
@Slf4j
public class AliPayService {

    @Autowired
    PayConfigDao payConfigDao;

    public String payToAli(HttpServletResponse response, MerchantReqDTO merchantReqDTO) {
        try {
            //获取支付配置信息
            PayConfigEntity payConfigEntity=payConfigDao.findPayConfig(merchantReqDTO.getMerchantId(),
                    merchantReqDTO.getPayType(),merchantReqDTO.getChannel());

            // 封装请求支付信息content
            AliPayReqContentDTO bizContent = new AliPayReqContentDTO();
            bizContent.setOut_trade_no(merchantReqDTO.getOut_trade_no());
            bizContent.setSeller_id("2088221724747503");
            bizContent.setTotal_amount(merchantReqDTO.getTotal_amount());
            bizContent.setSubject(merchantReqDTO.getSubject());
            bizContent.setSellerEmail("1160569243@qq.com");
            bizContent.setTimeout_express(payConfigEntity.getTimeout_express());
            bizContent.setBody(merchantReqDTO.getSubject());
            bizContent.setProduct_code(payConfigEntity.getProduct_code());
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(payConfigEntity.getGateWay());

            String bizStr = JSONObject.toJSONString(bizContent);
            //封装待签名请求信息
            AliPayReqDTO aliPayReqDTO = new AliPayReqDTO();
            aliPayReqDTO.setApp_id(payConfigEntity.getAppId());
            aliPayReqDTO.setMethod(payConfigEntity.getMethod());
            aliPayReqDTO.setCharset(payConfigEntity.getCharset());
            aliPayReqDTO.setTimestamp(merchantReqDTO.getTimestamp());
            aliPayReqDTO.setVersion(payConfigEntity.getVersion());
            aliPayReqDTO.setNotify_url(payConfigEntity.getNotifyUrl());
            aliPayReqDTO.setBiz_content(bizStr);
            aliPayReqDTO.setSign_type(payConfigEntity.getSign_type());
            //待签名参数排序
            String reqStr = AliSignUtil.orderParamsMapAndReturnParamsString(MapUtil.objectToMap(aliPayReqDTO));
            //rsa2签名加密
            String sign = AliSignUtil.signRsa2(reqStr, payConfigEntity.getPrivateKey(), payConfigEntity.getCharset());

            //组装表单方式的请求参数
            List<NameValuePair> content1 = new ArrayList<NameValuePair>();
            content1.add(new BasicNameValuePair("app_id", payConfigEntity.getAppId()));
            content1.add(new BasicNameValuePair("method", payConfigEntity.getMethod()));
            content1.add(new BasicNameValuePair("charset", payConfigEntity.getCharset()));
            content1.add(new BasicNameValuePair("timestamp", merchantReqDTO.getTimestamp()));
            content1.add(new BasicNameValuePair("version", payConfigEntity.getVersion()));
            content1.add(new BasicNameValuePair("biz_content", bizStr));
            content1.add(new BasicNameValuePair("sign_type", payConfigEntity.getSign_type()));
            content1.add(new BasicNameValuePair("notify_url", payConfigEntity.getNotifyUrl()));
            content1.add(new BasicNameValuePair("sign", sign));
            System.out.println(content1.toString());
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content1, "UTF-8");
            post.setEntity(entity);

            //发起支付请求
            HttpResponse res = httpClient.execute(post);
            if ("alipay.trade.wap.pay".equals(payConfigEntity.getMethod())) {
                //    支付宝wap支付直接跳转到支付宝支付页面
                Header locationHeader = res.getFirstHeader("Location");
                String location = locationHeader.getValue();
                System.out.println(location);
                response.sendRedirect(location);
                return "pay success";
            }
            return "pay fail";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return res.getBody();
        return "pay fail";
    }

}

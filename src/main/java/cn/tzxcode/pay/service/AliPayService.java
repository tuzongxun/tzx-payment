package cn.tzxcode.pay.service;

import cn.tzxcode.pay.dao.PayConfigDao;
import cn.tzxcode.pay.dto.AliPayReqDTO;
import cn.tzxcode.pay.dto.AliPayReqContentDTO;
import cn.tzxcode.pay.dto.MerchantReqDTO;
import cn.tzxcode.pay.entity.PayConfig;
import cn.tzxcode.pay.util.AliSignUtil;
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
            String appId = merchantReqDTO.getAppId();
            String sellerId = merchantReqDTO.getMerchantId();
            String payType = merchantReqDTO.getPayType();
            String channel = merchantReqDTO.getChannel();
            //获取支付配置信息
            // PayConfigEntity payConfigEntity = payConfigDao.findPayConfig(merchantReqDTO.getMerchantId(),
            //         merchantReqDTO.getPayType(), merchantReqDTO.getChannel());
            List<PayConfig> payconfigList = payConfigDao.findAll();
            log.info("AliPayService|payToAli|支付配置信息：" + payconfigList);
            String privateKey = null;
            String charset = null;
            String gateWay = null;
            String method = null;
            String version = null;
            String signType = null;
            String notifyUrl = null;
            String timeoutExpress = null;
            String productCode = null;
            String sellerEmail = null;
            readConfig(payconfigList, method, signType, version, notifyUrl, timeoutExpress, productCode,
                    gateWay, sellerEmail,
                    privateKey, charset);
            // 封装请求支付信息content
            AliPayReqContentDTO bizContent = new AliPayReqContentDTO();
            assembleBizContent(merchantReqDTO,bizContent,sellerId,sellerEmail,timeoutExpress,productCode);
            String bizStr = JSONObject.toJSONString(bizContent);
            //封装待签名请求信息
            AliPayReqDTO aliPayReqDTO = new AliPayReqDTO();
            assembleSignParam(merchantReqDTO,bizStr,aliPayReqDTO,appId,method,charset,version,notifyUrl,signType);
            //待签名参数排序
            String reqStr =
                    AliSignUtil.orderParamsMapAndReturnParamsString(MapUtil.objectToMap(aliPayReqDTO));
            log.info("AliPayService|payToAli|待签名信息：" + reqStr);
            //rsa2签名加密
            String sign = AliSignUtil.signRsa2(reqStr, privateKey, charset);
            log.info("AliPayService|payToAli|签名信息：" + sign);

            //发起支付请求
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(gateWay);
            //组装表单方式的请求参数
            List<NameValuePair> content1 = new ArrayList<NameValuePair>();
            assembleFormParam(merchantReqDTO,bizStr,sign,content1,method,charset,version,appId,signType,notifyUrl);
            log.info("AliPayService|payToAli|发送支付宝请求数据：" + content1.toString());
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content1, "UTF-8");
            post.setEntity(entity);
            HttpResponse res = httpClient.execute(post);
            if ("alipay.trade.wap.pay".equals(method)) {
                // 支付宝wap支付直接跳转到支付宝支付页面
                Header locationHeader = res.getFirstHeader("Location");
                String location = locationHeader.getValue();
                log.info("AliPayService|payToAli|支付响应跳转地址：" + location);
                response.sendRedirect(location);
                return "pay success";
            }
            return "pay fail";
        } catch (Exception e) {
            log.error("AliPayService|payToAli|支付异常：" + e);
        }
        return "pay fail";
    }

    /**
     * 组装支付请求表单参数
     * @param merchantReqDTO
     * @param bizStr
     * @param sign
     * @param content1
     * @param method
     * @param charset
     * @param version
     * @param appId
     * @param signType
     * @param notifyUrl
     */
    private void assembleFormParam(MerchantReqDTO merchantReqDTO, String bizStr, String sign,
                                   List<NameValuePair> content1, String method, String charset,
                                   String version, String appId, String signType, String notifyUrl) {
        content1.add(new BasicNameValuePair("app_id", appId));
        content1.add(new BasicNameValuePair("method", method));
        content1.add(new BasicNameValuePair("charset", charset));
        content1.add(new BasicNameValuePair("timestamp", merchantReqDTO.getTimestamp()));
        content1.add(new BasicNameValuePair("version", version));
        content1.add(new BasicNameValuePair("biz_content", bizStr));
        content1.add(new BasicNameValuePair("sign_type", signType));
        content1.add(new BasicNameValuePair("notify_url", notifyUrl));
        content1.add(new BasicNameValuePair("sign", sign));
    }

    /**
     * 组装签名数据
     *
     * @param merchantReqDTO
     * @param bizStr
     * @param aliPayReqDTO
     * @param appId
     * @param method
     * @param charset
     * @param version
     * @param notifyUrl
     * @param signType
     */
    private void assembleSignParam(MerchantReqDTO merchantReqDTO, String bizStr,
                                   AliPayReqDTO aliPayReqDTO, String appId, String method, String charset,
                                   String version, String notifyUrl, String signType) {
        aliPayReqDTO.setApp_id(appId);
        aliPayReqDTO.setMethod(method);
        aliPayReqDTO.setCharset(charset);
        aliPayReqDTO.setTimestamp(merchantReqDTO.getTimestamp());
        aliPayReqDTO.setVersion(version);
        aliPayReqDTO.setNotify_url(notifyUrl);
        aliPayReqDTO.setBiz_content(bizStr);
        aliPayReqDTO.setSign_type(signType);
    }

    /**
     * 组装支付请求报文体
     *
     * @param merchantReqDTO
     * @param bizContent
     * @param sellerId
     * @param sellerEmail
     * @param timeoutExpress
     * @param productCcode
     */
    private void assembleBizContent(MerchantReqDTO merchantReqDTO, AliPayReqContentDTO bizContent,
                                    String sellerId, String sellerEmail, String timeoutExpress,
                                    String productCcode) {
        bizContent.setOut_trade_no(merchantReqDTO.getOut_trade_no());
        bizContent.setSeller_id(sellerId);
        bizContent.setTotal_amount(merchantReqDTO.getTotal_amount());
        bizContent.setSubject(merchantReqDTO.getSubject());
        bizContent.setSellerEmail(sellerEmail);
        bizContent.setTimeout_express(timeoutExpress);
        bizContent.setBody(merchantReqDTO.getSubject());
        bizContent.setProduct_code(productCcode);
    }

    /**
     * 读取配置文件
     *
     * @param payconfigList
     * @param method
     * @param signType
     * @param version
     * @param notifyUrl
     * @param timeoutExpress
     * @param productCode
     * @param gateWay
     * @param sellerEmail
     * @param privateKey
     * @param charset
     */
    private void readConfig(List<PayConfig> payconfigList, String method, String signType,
                            String version, String notifyUrl, String timeoutExpress, String productCode,
                            String gateWay, String sellerEmail, String privateKey, String charset) {
        for (PayConfig payConfig : payconfigList) {
            String paramName = payConfig.getParamName();
            switch (paramName) {
                case "method":
                    method = payConfig.getParamValue();
                    break;
                case "sign_type":
                    signType = payConfig.getParamValue();
                    break;
                case "version":
                    version = payConfig.getParamValue();
                    break;
                case "notifyUrl":
                    notifyUrl = payConfig.getParamValue();
                    break;
                case "timeout_express":
                    timeoutExpress = payConfig.getParamValue();
                    break;
                case "product_code":
                    productCode = payConfig.getParamValue();
                    break;
                case "gateWay":
                    gateWay = payConfig.getParamValue();
                    break;
                case "sellerEmail":
                    sellerEmail = payConfig.getParamValue();
                    break;
                case "privateKey":
                    privateKey = payConfig.getParamValue();
                    break;
                case "charset":
                    charset = payConfig.getParamValue();
                    break;
                default:
                    break;
            }
        }
    }

}

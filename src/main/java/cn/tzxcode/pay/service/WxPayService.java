package cn.tzxcode.pay.service;

import cn.tzxcode.pay.dao.PayConfigDao;
import cn.tzxcode.pay.dto.MerchantReqDTO;
import cn.tzxcode.pay.entity.PayConfig;
import cn.tzxcode.pay.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.*;

/**
 * 微信支付业务逻辑处理
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Service
@Slf4j
public class WxPayService implements PayService {

    @Autowired
    PayConfigDao payConfigDao;

    @Override
    public String toPay(HttpServletResponse response, MerchantReqDTO merchantReqDTO) {
        merchantReqDTO.setTimestamp(DateUtil.dateFormat("yyyy-MM-dd HH:mm:ss"));
        List<PayConfig> payconfigList = payConfigDao.findByThirdMerId(merchantReqDTO.getThirdMerId());
        if (payconfigList == null || payconfigList.size() == 0) {
            log.error("WxPayService|toPay|无支付权限");
            return "无支付权限";
        }
        String appid = payconfigList.get(0).getAppId();
        String mch_id = payconfigList.get(0).getSellerId();
        String key = readConfig(payconfigList, "privateKey");
        String notifyUrl = readConfig(payconfigList, "notifyUrl");
        String gateWay = readConfig(payconfigList, "gateWay");
        try {
            Map<String, String> paramMap = new HashMap<>(16);
            StringBuffer batchCode = new StringBuffer();
            String dateStr = DateUtil.dateFormat("yyyyMMdd");
            batchCode.append(dateStr).append(SerialNumUtil.generateRandomCode(10));
            String nonce_str = batchCode.toString();
            //WAP网站应用
            String scene_info =
                    "{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"https://pay.qq.com\",\"wap_name\": " +
                            "\"流量充值\"}}";
            paramMap.put("appid", appid);
            paramMap.put("mch_id", mch_id);
            paramMap.put("nonce_str", nonce_str);
            paramMap.put("notify_url", notifyUrl);
            paramMap.put("scene_info", scene_info);
            paramMap=assembleReqParam(paramMap,merchantReqDTO);
            String stringSignTemp = AliSignUtil.orderParamsMapAndReturnParamsString(paramMap);
            String signStr = stringSignTemp + "&key=" + key;
            String sign = MD5.encode16(signStr.getBytes()).toUpperCase();
            paramMap.put("sign", sign);
            String paramXml = MapUtil.mapToXml(paramMap);
            //发起支付请求
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(gateWay);
            StringEntity entity = new StringEntity(paramXml, "UTF-8");
            post.setEntity(entity);
            HttpResponse res = httpClient.execute(post);
            System.out.println(res.getStatusLine().getStatusCode());
            System.out.println(EntityUtils.toString(res.getEntity(), "UTF-8"));
            return "pay success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pay fail";
    }

    private Map<String, String> assembleReqParam(Map<String, String> paramMap,MerchantReqDTO merchantReqDTO){
        paramMap.put("body", merchantReqDTO.getSubject());
        paramMap.put("out_trade_no", merchantReqDTO.getOut_trade_no());
        paramMap.put("spbill_create_ip", merchantReqDTO.getClientIp());
        paramMap.put("trade_type", merchantReqDTO.getChannel());
        paramMap.put("total_fee", merchantReqDTO.getTotal_amount() );
        return paramMap;
    }

}

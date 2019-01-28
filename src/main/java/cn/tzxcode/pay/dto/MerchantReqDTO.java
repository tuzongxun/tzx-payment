package cn.tzxcode.pay.dto;

import cn.tzxcode.pay.util.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * 商户请求数据
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Data
public class MerchantReqDTO {
    private String timestamp = DateUtil.dateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 商户订单号，商户网站订单系统中唯一订单号，必填
     */
    private String out_trade_no = new Date().getTime() + "tzxqq";
    /**
     * 订单名称，必填
     */
    private String subject = "tzxTest";
    /**
     * 付款金额，必填
     */
    private String total_amount = "0.01";

    /**
     * 商户Id
     */
    private String merchantId;
    /**
     * 支付机构类型，如ALIPAY
     */
    private String payType;
    /**
     * 支付渠道，如WAP
     */
    private String channel;
    /**
     * 商户应用id
     */
    private String appId;
    /**
     * 本系统为商户生成的id
     */
    private String thirdMerId;

}

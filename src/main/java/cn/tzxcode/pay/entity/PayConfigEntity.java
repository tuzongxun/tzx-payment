package cn.tzxcode.pay.entity;

import cn.tzxcode.pay.util.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 *支付配置信息实体类
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Data
public class PayConfigEntity {
    /**
     * 开放平台中开通的商户应用Id
     */
    private String appId = "appId";
    /**
     * 支付宝要求参数，可理解为支付渠道方式
     */
    private String method = "alipay.trade.wap.pay";
    /**
     * 字符集编码
     */
    private String charset = "UTF-8";
    /**
     * 签名类型
     */
    private String sign_type = "RSA2";
    /**
     * 支付接口版本号
     */
    private String version = "1.0";
    /**
     * 异步结果通知url
     */
    private String notifyUrl = "https://blog.tzxcode.cn";
    /**
     * 超时时间 可空
     */
    private String timeout_express = "2m";
    /**
     * 销售产品码 必填
     */
    private String product_code = "QUICK_WAP_WAY";
    /**
     * 支付宝支付网关url
     */
    private String gateWay = "https://openapi.alipay.com/gateway.do";
    /**
     * 商户号
     */
    private String seller_id;
    /**
     * 商户邮箱
     */
    private String sellerEmail;
    /**
     * 应用私钥
     */
    private String privateKey = "秘钥工具生成的秘钥";
}

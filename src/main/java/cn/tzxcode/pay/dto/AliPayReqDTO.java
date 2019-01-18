package cn.tzxcode.pay.dto;

import lombok.Data;

/**
 *支付宝支付请求参数
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Data
public class AliPayReqDTO {
    /**
     * 支付宝分配给开发者的应用ID
     */
    private String app_id;
    /**
     * 接口名称，如alipay.trade.wap.pay
     */
    private String method;
    /**
     * 请求使用的编码格式，如utf-8,gbk,gb2312等
     */
    private String charset;
    /**
     * 发送请求的时间，固定格式"yyyy-MM-dd HH:mm:ss"
     */
    private String timestamp;
    /**
     * 调用的接口版本，固定为：1.0
     */
    private String version;
    /**
     * 异步结果通知地址
     */
    private String notify_url;
    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String sign_type;
    /**
     * 商户请求参数的签名串
     */
    private String sign;

    /**
     * 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递
     */
    private Object biz_content;

}

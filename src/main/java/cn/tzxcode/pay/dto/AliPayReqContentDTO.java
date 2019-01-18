package cn.tzxcode.pay.dto;

import lombok.Data;

/**
 *支付宝支付参数体
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Data
public class AliPayReqContentDTO {

    /**
     * 商户网站唯一订单号.必填
     */
    private String out_trade_no;
    /**
     * 商品的标题/交易标题/订单标题/订单关键字等。必填
     */
    private String subject;
    /**
     * 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。必填
     */
    private String total_amount;
    /**
     * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。非必填
     */
    private String body;
    /**
     * 该笔订单允许的最晚付款时间，逾期将关闭交易。
     * 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     * 该参数数值不接受小数点， 如 1.5h，可转换为 90m。注：若为空，则默认为15d。
     * 非必填
     */
    private String timeout_express;
    /**
     * 销售产品码，商家和支付宝签约的产品码。如：QUICK_WAP_WAY。必填
     */
    private String product_code;
    /**
     * 支付宝api说明必选，实际wap测试没有使用
     */
    private String quit_url;
    /**
     * 商户号
     */
    private String seller_id;
    /**
     * 商户邮箱
     */
    private String sellerEmail;

}

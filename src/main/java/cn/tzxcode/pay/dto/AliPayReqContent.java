package cn.tzxcode.pay.dto;

/**
 * 支付宝支付参数体
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 */
public class AliPayReqContent {

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
     * 销售产品码，商家和支付宝签约的产品码。该产品请填写固定值：QUICK_WAP_WAY。必填
     */
    private String product_code;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeout_express() {
        return timeout_express;
    }

    public void setTimeout_express(String timeout_express) {
        this.timeout_express = timeout_express;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    @Override
    public String toString() {
        return "AliPayReqContent{" +
                "out_trade_no='" + out_trade_no + '\'' +
                ", subject='" + subject + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", body='" + body + '\'' +
                ", timeout_express='" + timeout_express + '\'' +
                ", product_code='" + product_code + '\'' +
                '}';
    }
}

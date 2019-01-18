package cn.tzxcode.pay.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 支付配置信息实体类
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Data
@Entity
@NamedQuery(name = "PayConfig.findAll", query = "SELECT p FROM PayConfig p")
public class PayConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * 开放平台中开通的商户应用Id
     */
    @Column(name = "app_id")
    private String appId ;
    /**
     * 商户号
     */
    @Column(name = "seller_id")
    private String sellerId;
    /**
     * 支付机构标示
     */
    @Column(name = "pay_type")
    private String payType;
    /**
     * 支付渠道标示
     */
    @Column(name = "channel")
    private String channel;
    /**
     * 支付配置参数名
     */
    @Column(name = "param_name")
    private String paramName;
    /**
     * 支付配置参数值
     */
    @Lob
    @Column(name = "param_value")
    private String paramValue;
    /**
     * 支付配置参数说明
     */
    @Column(name = "param_desc")
    private String paramDesc;
    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

}

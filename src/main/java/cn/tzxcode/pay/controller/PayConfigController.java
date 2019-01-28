package cn.tzxcode.pay.controller;

import cn.tzxcode.pay.dao.PayConfigDao;
import cn.tzxcode.pay.entity.PayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 支付配置对外接口
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Controller
@RequestMapping("/tzxpay")
public class PayConfigController {
    @Autowired
    PayConfigDao payConfigDao;

    @GetMapping("/addConfig")
    @ResponseBody
    public String addConfig(){
       PayConfig payConfig=assembleConfig();
        payConfigDao.save(payConfig);
        return "success";
    }

    /**
     * 模拟增加配置文件
     */
    private PayConfig assembleConfig(){
        PayConfig entity=new PayConfig();
        entity.setThirdMerId("2019010100000001");
        entity.setAppId("11111");
        entity.setChannel("WAP");
        entity.setPayType("ALIPAY");
        entity.setSellerId("22222");
        entity.setParamName("method");
        entity.setParamValue("alipay.trade.wap.pay");
        entity.setParamDesc("支付method");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        return entity;
    }
}

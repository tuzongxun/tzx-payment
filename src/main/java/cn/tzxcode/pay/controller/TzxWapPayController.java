package cn.tzxcode.pay.controller;

import cn.tzxcode.pay.dto.MerchantReqDTO;
import cn.tzxcode.pay.service.AliPayService;
import cn.tzxcode.pay.service.PayService;
import cn.tzxcode.pay.service.WxPayService;
import cn.tzxcode.pay.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * wap支付统一对外接口
 *
 * @author tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Controller
@RequestMapping("/tzxpay")
@Slf4j
public class TzxWapPayController {

    /**
     * wap支付接口
     *
     * @param response
     */
    @PostMapping("/wapPay")
    public void wapPay(MerchantReqDTO merchantReqDTO,
                       HttpServletResponse response) {
        // MerchantReqDTO merchantReqDTO1 = new MerchantReqDTO();
        log.info("TzxWapPayController|wapPay|开始处理wap支付请求:" + merchantReqDTO);
        String payOrg = merchantReqDTO.getPayType();
        PayService payService = (PayService) SpringUtil.getBean("ALIPAY".equals(payOrg) ? "aliPayService" :
                "wxPayService");
        // aliPayService.toPay(response, merchantReqDTO);
        payService.toPay(response, merchantReqDTO);
    }

}

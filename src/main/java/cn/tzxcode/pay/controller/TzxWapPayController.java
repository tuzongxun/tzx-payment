package cn.tzxcode.pay.controller;

import cn.tzxcode.pay.dto.MerchantReqDTO;
import cn.tzxcode.pay.service.AliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * wap支付统一对外接口
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Controller
@RequestMapping("/tzxpay")
@Slf4j
public class TzxWapPayController {

    @Autowired
    AliPayService aliPayService;

    /**
     * wap支付接口
     *
     * @param response
     */
    @GetMapping("/wapPay")
    public void wapPay(HttpServletResponse response) {
        MerchantReqDTO merchantReqDTO = new MerchantReqDTO();
        log.info("TzxWapPayController|wapPay|开始处理wap支付请求:" + merchantReqDTO);
        aliPayService.payToAli(response, merchantReqDTO);
    }

}

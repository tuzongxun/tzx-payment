package cn.tzxcode.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 *测试
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Controller
@RequestMapping("/tzxpay")
public class TestController {


    @GetMapping("/test")
    @ResponseBody
    public String test(HttpServletResponse response) {
        return "hello world";
    }
}

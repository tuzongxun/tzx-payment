package cn.tzxcode.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tzxpay")
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "hello world";
    }
}

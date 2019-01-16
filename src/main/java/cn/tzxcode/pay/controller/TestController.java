package cn.tzxcode.pay.controller;

import cn.tzxcode.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
@RequestMapping("/tzxpay")
public class TestController {
    @Autowired
    PayService payService;

    @GetMapping("/test")
//    @ResponseBody
    public void test(HttpServletResponse response){
        String res=payService.payToAli();
        try {
            response.setContentType("text/html;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pr=response.getWriter();
            pr.print(res);
            pr.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
//        return "hello world";
    }
}

package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*该控制类是为了查找jsp或者带参数访问jsp或者跳转 */
@Controller
@RequestMapping("/pages")
public class PagesController {

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/regForm")
    public String regForm(){
        return "regForm";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

   //测试
    @RequestMapping("/ajaxCommit")
    public String ajaxCommit(){
        return "ajaxCommit";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}

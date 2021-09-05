package com.xiexin.controller;


import com.xiexin.bean.AdminInfo;
import com.xiexin.bean.Dog;
import com.xiexin.bean.Lover;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    //注册成功后，如果是单体项目---就要跳转到登录页面，这个跳转是后台的转发，重定向，总之是
    //后台负责跳转，携带数据的跳转页面
    //如果是 新型的项目，即前后端分离，那么后台只负责返回给前端json数据
    //跳转是前端来处理的，前端根据后台返回code代码，进行跳转
    //如果前端负责跳转，他会起一个好听的名字，叫做路由

    //什么是前后端分离？ 项目上的分离 和 数据上的分离
    //项目的分离： 前端一个项目，后台一个项目，两个项目，他们的认证是token / jwt + redis
    //数据上的分离： 还是一个项目，只不过前后端用json来交互数据
    //很少用jstl /el 表达式来写项目，他们的认证是 session

    //layui在ssm/boot框架的使用，其实是数据上的分离，也可以项目上的分离
    //那么他就是json交互的，那么后台只需要给他返回json数据就可以了
    //以前在servlet中，resp.getWriter().print(new JSONObject.toString(map)),输出
    //现在高级了，用MVC框架
    @RequestMapping("/reg")
    @ResponseBody
    public Map reg(String adminName, String adminPwd, String adminPwdR,String adminTime) {

        System.out.println("adminPwdR = " + adminPwdR);
        System.out.println("adminTime = " + adminTime);
        Map codeMap = new HashMap();
        if (!adminPwd.equals(adminPwdR)) {
            codeMap.put("code", "4401");
            codeMap.put("msg", "你输入的密码和重复密码不一致，注册失败");
            return codeMap;
        }
        if (adminName.length() <= 0) {
            codeMap.put("code", "4402");
            codeMap.put("msg", "adminName表单填写完整");
            return codeMap;
        }
        if (adminPwd.length() <= 0) {
            codeMap.put("code", "4402");
            codeMap.put("msg", "adminName表单填写完整");
            return codeMap;
        }
        codeMap.put("code", "0");
        codeMap.put("msg", "注册成功");
        return codeMap;
    }

    //传统版本 不返回json，跳转使用转发或者重定向
    @RequestMapping("/regForm")
    @ResponseBody
    public String regForm(String adminName, String adminPwd) {
        System.out.println("adminName = " + adminName);
        System.out.println("adminPwd = " + adminPwd);
       //注册成功跳转到登录页
        return "loginForm";
    }

//springmvc第二种收参数方式，叫做实体类收参数
    @RequestMapping("/regByBean")
    public Map regByBean(AdminInfo adminInfo){
        System.out.println("adminInfo = " + adminInfo);
        Map codeMap = new HashMap();
        codeMap.put("code", "0");
        codeMap.put("msg", "注册成功");
        return codeMap;
    }


    //ajax接收 数据/集合

   @RequestMapping("/ajax03")
   @ResponseBody
   public  Map ajax03(@RequestParam("ids[]") List<Integer> ids){ //前端 ids[]  后台ids[]
       for (Integer id:ids) {
           System.out.println("id = " + id);
       }
       Map codeMap = new HashMap();
       codeMap.put("code", "0");
       codeMap.put("msg", "请求访问成功");
       codeMap.put("data",ids);
       return codeMap;
   }


   //ajax接收JSON对象
    @RequestMapping("/ajax04")
    @ResponseBody
    public  Map ajax04(@RequestBody AdminInfo adminInfo){   //@RequestBody-  注解就是 指前端用json请求
        System.out.println("adminInfo = " + adminInfo);
        Map codeMap = new HashMap();
        codeMap.put("code", "0");
        codeMap.put("msg", "请求访问成功");
        codeMap.put("data",adminInfo);
        return codeMap;
    }


    //ajax接收 前端传来的多个对象
    @RequestMapping("/ajax05")
    @ResponseBody
    public Map ajax05(@ModelAttribute Lover lover,@ModelAttribute Dog dog ){

        Map codeMap = new HashMap();
        codeMap.put("code", "0");
        codeMap.put("msg", "请求访问成功");
        codeMap.put("data1","lover");
        codeMap.put("data2","dog");
        return codeMap;
    }

    //前端传来的多个对象，需要根据请求的前缀 进行绑定
    @InitBinder("lover")
    public void bindding01(WebDataBinder webDataBinder){ //WebDataBinder网络数据绑定
        webDataBinder.setFieldDefaultPrefix("lover.");//前缀 lover.name:
    }

    @InitBinder("dog")
    public void bindding02(WebDataBinder webDataBinder){ //WebDataBinder网络数据绑定
        webDataBinder.setFieldDefaultPrefix("dog.");
    }
    //ajax06
   // @GetMapping("/ajax06")
    @RequestMapping("/ajax06")
    @ResponseBody
    public Map ajax06(@RequestBody List<Lover> loverList){ //@RequestBody他是方法体中拿取的数据的，所以不能用Get请求
        for (Lover lover:loverList) {
            System.out.println("lover = " + lover);
        }
        Map codeMap = new HashMap();
        codeMap.put("code", "0");
        codeMap.put("msg", "请求访问成功");
        codeMap.put("data","loverList");
        return codeMap;

    }

    //ajax07
    @RequestMapping("/ajax07")
    @ResponseBody   //常用，还记得我们用servlet 多表的动态参数，就是用的map  07 搞定一切
    public Map ajax07(@RequestBody Map map){
        System.out.println("map = " + map.get("adminName"));
        Map codeMap = new HashMap();
        codeMap.put("code", "0");
        codeMap.put("msg", "请求访问成功");
        codeMap.put("data","map");
        return codeMap;
    }

    //ajax08
    @RequestMapping("/ajax08")
    @ResponseBody
    public Map ajax08(Lover lover, @RequestParam(value = "limit",required = false,defaultValue = "5") Integer pageSize, Integer page){
        System.out.println("lover = " + lover);
        System.out.println("page = " + page);
        System.out.println("pageSize = " + pageSize);
        return null;
    }

//以上是 前后端分离，最新项目用到的知识点，那么也有传统项目，   后台负责跳转到另一个界面

    //第一种  springMVC 的传值方式;原始方式: request + session + request 的转发
    //传统的mvc的方法（不返回json数据，不使用 @ResponseBody）,他要跳转
    @RequestMapping("/yuansheng")
   // public  String yuansheng(AdminInfo adminInfo, HttpSession session){  //
    public  String yuansheng(HttpSession session, HttpServletRequest request){
        System.out.println("原生方式  页面赋值");
      //  System.out.println("adminInfo = " + adminInfo);
        //登录如果验证成功， 那么需要把登录信息 放到session域当中
     //   session.setAttribute("adminInfo",adminInfo);
        String adminName = request.getParameter("adminName");
        String adminPwd = request.getParameter("adminPwd");
        request.setAttribute("adminName",adminName);
        request.setAttribute("adminPwd",adminPwd);
      //  request.getRequestDispatcher("home.jsp").forward(request,response);
        //servlet转发
        //springMVC中的转发
       // return "home"; //这个和 PageController 里的查找jsp的方法没联系
     //   return "forward:/WEB-INF/pages/home.jsp"; //springMVC中的转发
       // return "forward:/pages/home"; //springmvc中的转发
        //重定向，servlet   response.sendreDirect("/ww.baidu.com") 重定向携带不了数据
        return "redirect:https://www.baidu.com"; //不带/ 和带/ 区别： http://localhost:8080
    }

    //第二种  springMVC 的传值方式;
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(AdminInfo adminInfo){
        //model 和视图 通俗， 数据显示，  modelAndView 可以代替 转发功能，更强大了
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上model 的绑定，即数据的绑定");
        mv.setViewName("home");
        return mv;
    }



    //第三种  springMVC 的传值方式;
    @RequestMapping("/model")
    public String model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());
        return "home";

    }



    //第四种  springMVC 的传值方式;
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }


    // //第五种  springMVC 的传值方式; map 灵活
    @RequestMapping("/map")
    public String map(AdminInfo adminInfo,Map<String,Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }





  /* 第二遍 //第一种  springMVC 的传值方式;原始方式: request + session + request 的转发
    //传统的mvc的方法（不返回json数据，不使用 @ResponseBody）,他要跳转
    @RequestMapping("/yuansheng")
    // public  String yuansheng(AdminInfo adminInfo, HttpSession session){  //
    public  String yuansheng(HttpSession session, HttpServletRequest request){
        System.out.println("原生方式  页面赋值");
        //  System.out.println("adminInfo = " + adminInfo);
        //登录如果验证成功， 那么需要把登录信息 放到session域当中
        //   session.setAttribute("adminInfo",adminInfo);
        String adminName = request.getParameter("adminName");
        String adminPwd = request.getParameter("adminPwd");
        request.setAttribute("adminName",adminName);
        request.setAttribute("adminPwd",adminPwd);
        //  request.getRequestDispatcher("home.jsp").forward(request,response);
        //servlet转发
        //springMVC中的转发
        // return "home"; //这个和 PageController 里的查找jsp的方法没联系
        //   return "forward:/WEB-INF/pages/home.jsp"; //springMVC中的转发
        // return "forward:/pages/home"; //springmvc中的转发
        //重定向，servlet   response.sendreDirect("/ww.baidu.com") 重定向携带不了数据
        return "redirect:https://www.baidu.com"; //不带/ 和带/ 区别： http://localhost:8080
    }

    //第二种  springMVC 的传值方式;
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(AdminInfo adminInfo){
        //model 和视图 通俗， 数据显示，  modelAndView 可以代替 转发功能，更强大了
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上model 的绑定，即数据的绑定");
        mv.setViewName("home");
        return mv;
    }



    //第三种  springMVC 的传值方式;
    @RequestMapping("/model")
    public String model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());
        return "home";

    }



    //第四种  springMVC 的传值方式;
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }


    // //第五种  springMVC 的传值方式; map 灵活
    @RequestMapping("/map")
    public String map(AdminInfo adminInfo,Map<String,Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }*/

  /* 第三遍 //第一种  springMVC 的传值方式;原始方式: request + session + request 的转发
    //传统的mvc的方法（不返回json数据，不使用 @ResponseBody）,他要跳转
    @RequestMapping("/yuansheng")
    // public  String yuansheng(AdminInfo adminInfo, HttpSession session){  //
    public  String yuansheng(HttpSession session, HttpServletRequest request){
        System.out.println("原生方式  页面赋值");
        //  System.out.println("adminInfo = " + adminInfo);
        //登录如果验证成功， 那么需要把登录信息 放到session域当中
        //   session.setAttribute("adminInfo",adminInfo);
        String adminName = request.getParameter("adminName");
        String adminPwd = request.getParameter("adminPwd");
        request.setAttribute("adminName",adminName);
        request.setAttribute("adminPwd",adminPwd);
        //  request.getRequestDispatcher("home.jsp").forward(request,response);
        //servlet转发
        //springMVC中的转发
        // return "home"; //这个和 PageController 里的查找jsp的方法没联系
        //   return "forward:/WEB-INF/pages/home.jsp"; //springMVC中的转发
        // return "forward:/pages/home"; //springmvc中的转发
        //重定向，servlet   response.sendreDirect("/ww.baidu.com") 重定向携带不了数据
        return "redirect:https://www.baidu.com"; //不带/ 和带/ 区别： http://localhost:8080
    }

    //第二种  springMVC 的传值方式;
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(AdminInfo adminInfo){
        //model 和视图 通俗， 数据显示，  modelAndView 可以代替 转发功能，更强大了
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上model 的绑定，即数据的绑定");
        mv.setViewName("home");
        return mv;
    }



    //第三种  springMVC 的传值方式;
    @RequestMapping("/model")
    public String model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());
        return "home";

    }



    //第四种  springMVC 的传值方式;
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }


    // //第五种  springMVC 的传值方式; map 灵活
    @RequestMapping("/map")
    public String map(AdminInfo adminInfo,Map<String,Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }*/

/* 第四遍 //第一种  springMVC 的传值方式;原始方式: request + session + request 的转发
    //传统的mvc的方法（不返回json数据，不使用 @ResponseBody）,他要跳转
    @RequestMapping("/yuansheng")
    // public  String yuansheng(AdminInfo adminInfo, HttpSession session){  //
    public  String yuansheng(HttpSession session, HttpServletRequest request){
        System.out.println("原生方式  页面赋值");
        //  System.out.println("adminInfo = " + adminInfo);
        //登录如果验证成功， 那么需要把登录信息 放到session域当中
        //   session.setAttribute("adminInfo",adminInfo);
        String adminName = request.getParameter("adminName");
        String adminPwd = request.getParameter("adminPwd");
        request.setAttribute("adminName",adminName);
        request.setAttribute("adminPwd",adminPwd);
        //  request.getRequestDispatcher("home.jsp").forward(request,response);
        //servlet转发
        //springMVC中的转发
        // return "home"; //这个和 PageController 里的查找jsp的方法没联系
        //   return "forward:/WEB-INF/pages/home.jsp"; //springMVC中的转发
        // return "forward:/pages/home"; //springmvc中的转发
        //重定向，servlet   response.sendreDirect("/ww.baidu.com") 重定向携带不了数据
        return "redirect:https://www.baidu.com"; //不带/ 和带/ 区别： http://localhost:8080
    }

    //第二种  springMVC 的传值方式;
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(AdminInfo adminInfo){
        //model 和视图 通俗， 数据显示，  modelAndView 可以代替 转发功能，更强大了
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上model 的绑定，即数据的绑定");
        mv.setViewName("home");
        return mv;
    }



    //第三种  springMVC 的传值方式;
    @RequestMapping("/model")
    public String model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());
        return "home";

    }



    //第四种  springMVC 的传值方式;
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }


    // //第五种  springMVC 的传值方式; map 灵活
    @RequestMapping("/map")
    public String map(AdminInfo adminInfo,Map<String,Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }*/

/* 第五遍 //第一种  springMVC 的传值方式;原始方式: request + session + request 的转发
    //传统的mvc的方法（不返回json数据，不使用 @ResponseBody）,他要跳转
    @RequestMapping("/yuansheng")
    // public  String yuansheng(AdminInfo adminInfo, HttpSession session){  //
    public  String yuansheng(HttpSession session, HttpServletRequest request){
        System.out.println("原生方式  页面赋值");
        //  System.out.println("adminInfo = " + adminInfo);
        //登录如果验证成功， 那么需要把登录信息 放到session域当中
        //   session.setAttribute("adminInfo",adminInfo);
        String adminName = request.getParameter("adminName");
        String adminPwd = request.getParameter("adminPwd");
        request.setAttribute("adminName",adminName);
        request.setAttribute("adminPwd",adminPwd);
        //  request.getRequestDispatcher("home.jsp").forward(request,response);
        //servlet转发
        //springMVC中的转发
        // return "home"; //这个和 PageController 里的查找jsp的方法没联系
        //   return "forward:/WEB-INF/pages/home.jsp"; //springMVC中的转发
        // return "forward:/pages/home"; //springmvc中的转发
        //重定向，servlet   response.sendreDirect("/ww.baidu.com") 重定向携带不了数据
        return "redirect:https://www.baidu.com"; //不带/ 和带/ 区别： http://localhost:8080
    }

    //第二种  springMVC 的传值方式;
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(AdminInfo adminInfo){
        //model 和视图 通俗， 数据显示，  modelAndView 可以代替 转发功能，更强大了
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上model 的绑定，即数据的绑定");
        mv.setViewName("home");
        return mv;
    }



    //第三种  springMVC 的传值方式;
    @RequestMapping("/model")
    public String model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());
        return "home";

    }



    //第四种  springMVC 的传值方式;
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }


    // //第五种  springMVC 的传值方式; map 灵活
    @RequestMapping("/map")
    public String map(AdminInfo adminInfo,Map<String,Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }*/

}


package com.xiexin.controller;

import com.xiexin.bean.Buyrecord;
import com.xiexin.bean.Dog;
import com.xiexin.bean.Farmer;
import com.xiexin.bean.Lover;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/admin")
public class MyfarmController {
    @RequestMapping("/ajax002")
    @ResponseBody
    public Map reg1623(@RequestBody Map map){
        System.out.println("map = " + map);
        Map hashMap = new HashMap();
        Object key;
        int sl= Integer.parseInt((String) map.get("sl"));
        int jg= Integer.parseInt((String) map.get("jg"));
        map.put("总价",sl*jg);
        hashMap.put("code","0");
        hashMap.put("msg","成功");
        hashMap.put("data",map);
        System.out.println("hashMap = " + hashMap);

        return hashMap;
    }


    //ajax接收 前端传来的多个对象
    @RequestMapping("/ajax001")
    @ResponseBody
    public Map ajax05(@ModelAttribute Farmer farmer, @ModelAttribute Buyrecord buyrecord){

        System.out.println("farmer = " + farmer);
        System.out.println("buyrecord = " + buyrecord);
        Map codeMap = new HashMap();
        codeMap.put("code", "0");
        codeMap.put("msg", "请求访问成功");
        codeMap.put("data1","farmer");
        codeMap.put("data2","buyrecord");
        return codeMap;
    }

    //前端传来的多个对象，需要根据请求的前缀 进行绑定
    @InitBinder("farmer")
    public void bindding01(WebDataBinder webDataBinder){ //WebDataBinder网络数据绑定
        webDataBinder.setFieldDefaultPrefix("lover.");
    }

    @InitBinder("buyrecord")
    public void bindding02(WebDataBinder webDataBinder){ //WebDataBinder网络数据绑定
        webDataBinder.setFieldDefaultPrefix("dog.");
    }


}

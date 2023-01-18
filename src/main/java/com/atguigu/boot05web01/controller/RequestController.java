package com.atguigu.boot05web01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {

    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","成功了...");
        request.setAttribute("code",200);


        // 使用GetMapping先映射到goto
        // 然后使用原生HttpServletRequest向请求域中放入msg,code
        // 再转发到/success
        return "forward:/success";
    }

    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                          @RequestAttribute("code") Integer code,
                          HttpServletRequest request
                          ){
        // GetMapping映射到/success，然后使用了@ResponseBody注解，将返回的map集合变为响应体
        // 使用@RequestAttribute注解将请求域中放入的msg和code提取出来，赋值给msg和code
        // 再传入一个原生的HttpServletRequest，用它来获取请求域中的对象

        // 获取到msg1，将其存入map集合中去。同时在浏览器输出进行比较
        // 发现相同
        Object msg1 = request.getAttribute("msg");

        Map<String,Object> map = new HashMap<>();
        map.put("ann",msg);
        map.put("servlet",msg1);
        return map;
    }

}

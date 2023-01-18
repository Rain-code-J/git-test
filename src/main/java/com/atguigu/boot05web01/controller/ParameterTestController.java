package com.atguigu.boot05web01.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {


    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable(value = "id") String id,
                                     @PathVariable(value = "username") String name,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader("User-Agent") String userAgent,
                                     @RequestHeader Map<String,String> header,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters")List<String> inters,
                                     @RequestParam Map<String,String> params,
                                     @CookieValue("Idea-dd64a919") String Idea,
                                     @CookieValue("Idea-dd64a919")Cookie cookie
                                     ){
        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name",name);
//        map.put("pv",pv);
//        map.put("userAgent",userAgent);
//        map.put("headers",header);

        map.put("age",age);
        map.put("inters",inters);
        map.put("params",params);

        map.put("idea",Idea);
        System.out.println(cookie.getName() + "=" + cookie.getValue());
        return map;
    }

    @PostMapping("/save")
    public Map postMethod(@RequestBody String content){
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        return map;
    }

    // /cars/sell;low=34;brand=byd,audi,yd
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){
        // @MatrixVariable注解里面的value值是分号后面所要查询的key
        // 如果查询到，它就会将其赋值给Integer low
        // 而且其真正的path是sell而不是sell;low=34;brand=byd,audi,yd
        Map<String,Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);
        return map;
    }

    // /boss/1;age=20/2;age=10
    @GetMapping("/boss/{bossID}/{empID}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossID") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empID") Integer empAge){
        // 这块和上面不同的是，有两个age
        // 为了区分他们使用了pathVar：矩阵变量所在的URI路径变量的名称，即：bossID
        // 它可以消除歧义，从而明确的知道所要获取的是哪个age
        Map<String,Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }
}

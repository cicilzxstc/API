package com.example.yuapiinterface.controller;

import com.example.yuapiclientsdk.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


/**
 * 名称API
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getNameByGet(@RequestParam String name){
        return "GET 你的名字是"+name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name){
        return "POST 你的名字是"+name;
    }

    // 接收请求后 服务端对各个参数按相同的步骤生成签名，如果签名一致，则确定发送方
    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request){
        return "POST 你的名字是"+user.getUsername();
    }
}

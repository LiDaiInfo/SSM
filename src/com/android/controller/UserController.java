package com.android.controller;

import com.android.bean.User;
import com.android.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;

@Controller
@RequestMapping("/user.do")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/selectUser.do")
    @ResponseBody
    public void abc1(){
        User user =  userService.selectUser(1);
        System.out.println("用户信息:"+user);
        System.out.println("controller方法执行啦.........");
    }

}

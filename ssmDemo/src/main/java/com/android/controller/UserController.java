package com.android.controller;

import com.android.bean.User;
import com.android.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
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


    //登录
    @RequestMapping("/login.do")
    public  String login(User user, HttpSession session){
        System.out.println("user信息:"+user);
        User u = userService.login(user);
        session.setAttribute("user",u);
        if (u!=null){
            return "main";
        }else{
            return "redirect:../login.jsp";
        }


    }


    //分页查询
    @RequestMapping("/queryUsers.do")
    public String fenye_query(int currentPage, Model model,User user){

        PageInfo<User> pageInfo = userService.selectAllUser(currentPage, 8,user);
//                model.addAttribute("pages",pageInfo.getPages());
//                model.addAttribute("list",pageInfo.getList());
                    model.addAttribute("pageInfo",pageInfo);
        return "emp_main";
    }



    //删除用户
    @RequestMapping("/delete.do")
    public String deletUser(Integer[] ids){
        System.out.println("-------传递进来的id址------:"+ids);

        for (int a:ids){
            System.out.println("传递进来的id址:"+a);
        }

        userService.deleteUserById(ids);

       return  "redirect:queryUsers.do?currentPage=1";
    }


    //跳转到添加页面
    @RequestMapping("/toAdd.do")
    public String toAdd(){
        return "emp_add";
    }

    @RequestMapping("/addUser.do")
    public String addUser(User user,HttpSession session){
        user.setDeleteflag(0);//在职
//        User u = (User) session.getAttribute("user");
//        user.setUsername(u.getUsername());
//        user.setPassword(u.getPassword());
        userService.saveUser(user);
        return  "redirect:queryUsers.do?currentPage=1";
    }



    @RequestMapping("/toUpdate.do")
    public String toUpdate(Integer id,Model model){

        User user = userService.selectUser(id);
                model.addAttribute("user",user);

        return "emp_update";
    }


    @RequestMapping("/updateUser.do")
    public String updateUser(User user){



        userService.UpdateUser(user);

        return  "redirect:queryUsers.do?currentPage=1";
    }


}

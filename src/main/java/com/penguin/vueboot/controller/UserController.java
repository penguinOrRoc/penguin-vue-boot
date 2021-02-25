package com.penguin.vueboot.controller;

import com.penguin.vueboot.common.CommonResult;
import com.penguin.vueboot.pojo.UserPojo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class UserController {
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    @GetMapping("/editUserInfo")
    public String editUserInfo() {
        return "/user/useredit";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        //使用Shiro编写用户认证操作
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3.执行登录方法
        try {
            //执行该方法会跳转到UserRealm 的doGetAuthenticationInfo方法
            subject.login(token);
            model.addAttribute("msg", "你好呀~");
            return "/user/userInfo";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户不存在~");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误~");
            return "login";
        }
    }


}



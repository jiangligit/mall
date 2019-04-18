package com.rimi.mall.controller;

import com.rimi.mall.pojo.User;
import com.rimi.mall.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录相关操作
 *
 * @author Administrator
 * @date 2019-04-15 14:13
 */
@Controller
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @RequestMapping("/login")
    public String login(User user, Model model){
        //登录操作，如果用户名或密码不存在，则跳转登录页面
        //如果登录成功，则跳转到成功页面

        boolean result = loginService.login(user);
        System.out.println("登录结果 "+result);
        if(result){
            model.addAttribute("msg","登录成功");
            return "success";
        }
        else {
            model.addAttribute("msg","登录失败");
            return "fail";
        }

    }
    @ResponseBody
    @RequestMapping("/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("msg","test界面");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "/online",method = RequestMethod.GET)
    public String online(){
        return String.valueOf(loginService.online());
    }

    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        return String.valueOf(loginService.logout());
    }



}

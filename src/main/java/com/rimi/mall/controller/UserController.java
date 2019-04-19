package com.rimi.mall.controller;

import com.rimi.mall.pojo.User;
import com.rimi.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 * @date 2019-04-18 14:11
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/user")
    public List<User> list(Model model){
        System.out.println("user/");
        List<User> userList = userService.selectAllWithAnnotation();
        return userList;
    }
}

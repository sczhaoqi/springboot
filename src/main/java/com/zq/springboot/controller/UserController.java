package com.zq.springboot.controller;

import com.zq.springboot.model.User;
import com.zq.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * User控制层
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show(@RequestParam(value = "employeeNumber")String employeeNumber){
        User user = userService.findByEmployeeNumber(employeeNumber);
        if(null != user)
            return user.getId()+"/"+user.getEprRole()+"/"+user.getPassword();
        else return "null";
    }
}


package com.zq.springboot.service;

import com.zq.springboot.dao.UserRepositoty;
import com.zq.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * User业务逻辑
 */
@Service
public class UserService {
    @Autowired
    private UserRepositoty userRepositoty;

    public User findByEmployeeNumber(String employeeNumber){
        User user = null;
        try{
            user = userRepositoty.findByEmployeeNumber(employeeNumber);
        }catch (Exception e){}
        return user;
    }
}


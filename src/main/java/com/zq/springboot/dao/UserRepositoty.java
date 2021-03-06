package com.zq.springboot.dao;

import com.zq.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by Song on 2017/2/15.
 * User表操作接口
 */
@Repository
public interface UserRepositoty extends JpaRepository<User,Long> {

    @Query("select t from User t where t.employeeNumber = :employee_number")
    User findByEmployeeNumber(@Param("employee_number") String employee_number);
}


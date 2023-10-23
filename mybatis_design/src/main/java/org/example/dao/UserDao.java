package org.example.dao;


import org.example.domain.User;
import org.example.mybatis.annotations.Select;

import java.util.List;

public interface UserDao {


    @Select("select * from user")
    List<User> findAll();
}

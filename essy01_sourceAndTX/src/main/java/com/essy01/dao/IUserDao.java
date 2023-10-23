package com.essy01.dao;


import com.essy01.domain.QueryVo;
import com.essy01.domain.User;

import java.util.List;

/**
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();


    List<User> findUserByCondition(User user);


    List<User> findUserInIds(QueryVo vo);

}

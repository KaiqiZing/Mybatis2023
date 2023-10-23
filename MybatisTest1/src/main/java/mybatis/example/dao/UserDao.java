package mybatis.example.dao;

import mybatis.example.domain.User;

import java.util.List;

public interface UserDao {
    // 查询所有用户
    List<User> findAll();

}

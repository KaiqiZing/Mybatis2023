package mybatis.example.dao;

import mybatis.example.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

}

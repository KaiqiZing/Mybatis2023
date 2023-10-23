package com.essy01oneToMany.dao;

import com.essy01oneToMany.domain.Role;

import java.util.List;

public interface IRoleDao {

    List<Role> findAllRole();

}

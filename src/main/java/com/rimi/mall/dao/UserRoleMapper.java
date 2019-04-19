package com.rimi.mall.dao;

import com.rimi.mall.pojo.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}
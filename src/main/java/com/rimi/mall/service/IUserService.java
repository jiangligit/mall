package com.rimi.mall.service;

import com.rimi.mall.pojo.User;

import java.util.List;

/**
 * @author Administrator
 * @date 2019-04-15 15:18
 */
public interface IUserService {
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户
     */
    User findByUserName(String username);

    /**
     * 查询所有
     * @return
     */
    List<User> selectAll();
    List<User> selectAllWithAnnotation();
}

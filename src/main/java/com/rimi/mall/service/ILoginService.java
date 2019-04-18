package com.rimi.mall.service;

import com.rimi.mall.pojo.User;

/**
 * @author Administrator
 * @date 2019-04-15 14:21
 */
public interface ILoginService {
    /**
     * 登录处理，判断用户是否可以登录
     * 成功返回true,失败返回false
     *
     * @param user 登录用户信息
     * @return 返回登录结果
     */
    boolean login(User user);

    /**
     * 在线人数
     * @return
     */
    long online();

    /**
     * 退出
     * @return
     */
    boolean logout();

    /**
     * 活跃人数
     * @return
     */
    long active();
}

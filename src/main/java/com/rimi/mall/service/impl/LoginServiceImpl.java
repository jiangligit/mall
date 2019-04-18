package com.rimi.mall.service.impl;

import com.rimi.mall.commons.Constant;
import com.rimi.mall.pojo.User;
import com.rimi.mall.service.ILoginService;
import com.rimi.mall.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @author Administrator
 * @date 2019-04-15 14:26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private IUserService userService;
    /**
     * 登录处理，判断用户是否可以登录
     * 成功返回true,失败返回false
     *
     * @param user 登录用户信息
     * @return 返回登录结果
     */
    @Override
    public boolean login(User user) {
        //判断用户是否登录
        boolean isLogin = isReLogin();
        if (isLogin) {
            return true;
        } else {
            return shiroLogin(user);
        }
    }
    @Override
    public long online(){
        //获取redis在线人数
        String online = (String) redisTemplate.boundValueOps(Constant.REDIS_ONLINE).get();
        return online !=null ?Long.parseLong(online):0;
    }

    /**
     * 退出
     * @return 是否推出成功
     */
    @Override
    public boolean logout(){
        try {
            SecurityUtils.getSubject().logout();
            //在线人数减1
            redisTemplate.boundValueOps(Constant.REDIS_ONLINE).decrement();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 活跃人数
     * @return
     */
    @Override
    public long active() {
        //获取当前日期
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
        //当前日期转化为key
        String key = simpleDateFormat.format(date);
        //获取用户
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUserName(username);

        //偏移量
        Long offset = user.getId()*8;
        //位图操作
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return  connection.setBit((Constant.REDIS_ACTIVE_PREFIX+":"+key).getBytes(),offset,true);
            }
        });
        return 0;
    }


    Long getActive(String month){
        Calendar instance = Calendar.getInstance();
        int i = instance.get(Calendar.YEAR);
        //每月
        Set<String> keys = redisTemplate.keys(Constant.REDIS_ACTIVE_PREFIX + ":" + i + ":" + month);

        long active = 0;

        for (String key : keys) {
            //获取每个key中的活跃数

            active = redisTemplate.execute(new RedisCallback<Long>() {

                @Override
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.bitCount(key.getBytes());
                }
            });
        }
        return active;

    }


    /**
     * 登录操作
     *
     * @param user
     * @return 登录结果
     */
    private boolean shiroLogin(User user) {
        //组装用户身份令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        token.setRememberMe(true);
        //登录验证
        /**
         * SecurityUtils.getSubject().login(token);传入token到Realm的doGetAuthenticationInfo(AuthenticationToken token)方法中
         */
        try {

            SecurityUtils.getSubject().login(token);
            System.out.println(" shiroLogin has role admin1 :"+SecurityUtils.getSubject().hasRole("admin1"));
            //添加登录人数
            BoundValueOperations ops = redisTemplate.boundValueOps(Constant.REDIS_ONLINE);
            ops.increment();
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 判断是否已经登录
     * @return
     */
    private boolean isReLogin() {
        //获取当前用户
        Subject user = SecurityUtils.getSubject();
        
        System.out.println(" isReLogin has role admin1 :"+user.hasRole("admin1"));
        //判断是否登录
        return user.isAuthenticated();

    }


}

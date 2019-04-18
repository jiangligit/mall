package com.rimi.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.rimi.mall.commons.Constant;
import com.rimi.mall.dao.UserMapper;
import com.rimi.mall.pojo.User;
import com.rimi.mall.service.IUserService;
import com.rimi.mall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2019-04-15 15:19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    /**
     * 根据用户名获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    @Cacheable(value = "user",key = "#username")
    public User findByUserName(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> selectAll() {
        //缓存

        String value = redisTemplate.boundValueOps(Constant.REDIS_CONTENT).get();
        if(StringUtils.isNotBlank(value)){
            System.out.println("从redis中获取数据");
            List<User> userList = JSONArray.parseArray(value,User.class);
            return userList;

        }
        System.out.println("从数据库获取数据");
        List<User> list = userMapper.selectAll();



        String jsonlist = JSONArray.toJSONString(list);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        ops.set(Constant.REDIS_CONTENT,jsonlist,5*60, TimeUnit.SECONDS);
        return  list;
    }
    @Override
    @Cacheable(value = "user",key = "#root.method")
    public List<User> selectAllWithAnnotation() {
        System.out.println("selectAllWithAnnotation");
        List<User> list = userMapper.selectAll();
        return  list;
    }
}

package com.example.novel_energy_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.novel_energy_system.mapper.UserMapper;
import com.example.novel_energy_system.pojo.User;
import com.example.novel_energy_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 判断用户名
     * @param user
     * @return
     */
    public User findByUsername(User user){
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,user.getUsername()));
    }

    public User findUserById(String userId) {

        return userMapper.selectById(userId);
    }

}

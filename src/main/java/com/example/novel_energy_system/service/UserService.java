package com.example.novel_energy_system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.novel_energy_system.pojo.User;

public interface UserService extends IService<User> {


    User findByUsername(User user);

    User findUserById(String userId);
}

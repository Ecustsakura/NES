package com.example.novel_energy_system.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.novel_energy_system.annotation.UserLoginToken;
import com.example.novel_energy_system.common.CodeMsg;
import com.example.novel_energy_system.common.Result;
import com.example.novel_energy_system.pojo.User;
import com.example.novel_energy_system.service.UserService;
import com.example.novel_energy_system.service.impl.TokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询用户信息
     * @return
     */
    @UserLoginToken
    @GetMapping("/list")
    public Result<Object> list(){
        return Result.success(userService.list());
    }


    /**
     * 登录验证
     * @param user
     * @param response
     * @return
     */
    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public Result<Object> login(User user, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        //获取用户账号密码
        User userForBase = new User();
        userForBase.setId(userService.findByUsername(user).getId());
        userForBase.setUsername(userService.findByUsername(user).getUsername());
        userForBase.setPassword(userService.findByUsername(user).getPassword());
        //判断账号或密码是否正确
        if (!userForBase.getPassword().equals(user.getPassword())) {
            return Result.error(CodeMsg.USER_OR_PASS_ERROR);
        } else {
            String token = tokenService.getToken(userForBase);
            jsonObject.put("token", token);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return Result.success(jsonObject);
        }
    }


}

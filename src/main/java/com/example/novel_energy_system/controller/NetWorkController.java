package com.example.novel_energy_system.controller;

import com.example.novel_energy_system.annotation.UserLoginToken;
import com.example.novel_energy_system.common.Result;
import com.example.novel_energy_system.pojo.PowerFlow;
import com.example.novel_energy_system.service.NetWorkService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


@RestController
@RequestMapping("/user/module")
public class NetWorkController {

    @Autowired
    private NetWorkService netWorkService;

    @UserLoginToken
    @PutMapping("/pf")
    public Result pf(){

    // TODO添加输入

        String result = netWorkService.pf_calculate();
        return Result.success(result);

    }


}

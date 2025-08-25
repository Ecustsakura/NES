package com.example.novel_energy_system.controller;

import com.example.novel_energy_system.common.Result;
import com.example.novel_energy_system.service.PowerGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @eo.api-type http
 * @eo.groupName PowerGrid
 * @eo.path
 */


@RestController
@RequestMapping("/module")
public class PowerGridController {

    @Autowired
    private PowerGridService powerGridService;

    /**
     * 计算潮流
     *
     *
     * @return Result.success(result)
     */

    @GetMapping("/pf")
    public Result pf(){
        Map<String,Object> result = powerGridService.getPFJson();
        return Result.success(result);
    }

    @GetMapping("/pfsave")
    public Result<Void> pfsave(){
        Result<Void> result = powerGridService.savePF();
        return result;
    }
}

package com.example.novel_energy_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.novel_energy_system.pojo.PowerFlow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PowerFlowMapper extends BaseMapper<PowerFlow> {
    int insertPowerFlow(PowerFlow powerFlow);
}

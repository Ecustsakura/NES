package com.example.novel_energy_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.novel_energy_system.pojo.ALK;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ALKMapper extends BaseMapper<ALK> {
    int insertBatch(List<ALK> list);
}

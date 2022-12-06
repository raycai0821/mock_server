package com.huifu.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huifu.mock.entity.MockDataEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MockDataDao extends BaseMapper<MockDataEntity> {

}

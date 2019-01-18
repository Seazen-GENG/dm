package com.fuhui.dao.tcs;

import com.fuhui.entity.tcs.Device;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceMapper {
    int deleteByPrimaryKey(Long iFdeviceid);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long iFdeviceid);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

}
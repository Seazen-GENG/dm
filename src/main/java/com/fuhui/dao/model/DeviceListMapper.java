package com.fuhui.dao.model;

import com.fuhui.entity.model.DeviceList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceListMapper {

    List<DeviceList> selectListByParam(@Param("factoryIds") String factoryIds, @Param("schoolId") Long schoolId, @Param("name") String name, @Param("model") String model, @Param("deviceType") String deviceType, @Param("status") Integer status,
                                       @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    String selectListByParamCount(@Param("factoryIds") String factoryIds, @Param("schoolId") Long schoolId, @Param("name") String name, @Param("model") String model, @Param("deviceType") String deviceType, @Param("status") Integer status);
}

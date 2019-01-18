package com.fuhui.dao.tcs;

import com.fuhui.entity.tcs.Factory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FactoryMapper {
    int deleteByPrimaryKey(Long iFactoryid);

    int insert(Factory record);

    int insertSelective(Factory record);

    Factory selectByPrimaryKey(Long iFactoryid);

    int updateByPrimaryKeySelective(Factory record);

    int updateByPrimaryKey(Factory record);

    List<Factory> getFactoryList(@Param("factoryId") Long factoryId, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    String getFactoryListCount(@Param("factoryId") Long factoryId);

    String getDeviceNum(@Param("factoryId") Long factoryId);
}
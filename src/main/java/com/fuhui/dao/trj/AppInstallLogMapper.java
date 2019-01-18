package com.fuhui.dao.trj;

import com.fuhui.entity.trj.AppInstallLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppInstallLogMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(AppInstallLog record);

    int insertSelective(AppInstallLog record);

    AppInstallLog selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(AppInstallLog record);

    int updateByPrimaryKey(AppInstallLog record);

    String getDownCount(@Param("pName") String pName);
}
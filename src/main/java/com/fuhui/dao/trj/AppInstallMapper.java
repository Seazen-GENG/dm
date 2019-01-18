package com.fuhui.dao.trj;

import com.fuhui.entity.trj.AppInstall;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AppInstallMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(AppInstall record);

    int insertSelective(AppInstall record);

    AppInstall selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(AppInstall record);

    int updateByPrimaryKey(AppInstall record);

    AppInstall selectByMac(@Param("mac") String mac, @Param("appId") Long appId, @Param("packName") String packName);

    List<AppInstall> selectByAppId(@Param("pName") String pName, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    String selectByAppIdCount(@Param("pName") String pName);

    AppInstall selectAppUsers(@Param("appId") Long appId, @Param("versionId") Long versionId);
}
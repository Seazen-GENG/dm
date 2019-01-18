package com.fuhui.dao.model;

import com.fuhui.entity.model.PadInstallApp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PadInstallAppMapper {

    ////旧的获取当前应版本版本的方法
    List<PadInstallApp> getPadInstallApp(@Param("padId") Long padId);

    List<PadInstallApp> getPadAppList(@Param("mac") String mac);

    List<PadInstallApp> getPadAppLogList(@Param("mac") String mac, @Param("isPage") String isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    String getPadAppLogListCount(@Param("mac") String mac);

    List<PadInstallApp> getAppInfoBySchool(@Param("schoolId") Long schoolId);
}

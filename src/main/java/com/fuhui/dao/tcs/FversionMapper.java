package com.fuhui.dao.tcs;

import com.fuhui.entity.model.DevSchoolList;
import com.fuhui.entity.tcs.Fversion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FversionMapper {
    int deleteByPrimaryKey(Long iFversionid);

    int insert(Fversion record);

    int insertSelective(Fversion record);

    Fversion selectByPrimaryKey(Long iFversionid);

    int updateByPrimaryKeySelective(Fversion record);

    int updateByPrimaryKey(Fversion record);

    List<Fversion> selectListByDeviceId(@Param("deviceId") Long deviceId, @Param("status") Integer status, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    String selectListByDeviceIdCount(@Param("deviceId") Long deviceId, @Param("status") Integer status);

    List<DevSchoolList> getSchoolListByDeviceId(@Param("deviceId") Long deviceId, @Param("deviceType") Integer deviceType, @Param("schoolStatus") Integer schoolStatus, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum
            , @Param("pLine") Integer pLine);

    String getSchoolListByDeviceIdCount(@Param("deviceId") Long deviceId, @Param("deviceType") Integer deviceType, @Param("schoolStatus") Integer schoolStatus);

    Fversion getNewVersionByMac(@Param("mac") String mac, @Param("configTime") String configTime);

    Fversion selectRepeatVersion(@Param("deviceId") Long deviceId,@Param("versionCode") String versionCode);

    Fversion queryFactoryAndDevice(@Param("versionCode") String versionCode);

    ///根据当前固件版本反差出设备ID及厂商ID
    Fversion queryFactoryAndDeviceByCodeAndSchoolId(@Param("versionCode") String versionCode,@Param("schoolId") Long schoolId);
}
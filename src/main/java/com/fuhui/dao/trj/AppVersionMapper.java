package com.fuhui.dao.trj;

import com.fuhui.entity.trj.AppVersion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppVersionMapper {
    int deleteByPrimaryKey(Long iVersionid);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Long iVersionid);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);

    /*判断是否存在要添加的版本*/
    AppVersion selectByParam(@Param("appId") long appId, @Param("version") String version, @Param("versionCode") String versionCode);

    /****判断当前应用版本*************/
    AppVersion selectByAppId(@Param("appId") long appId,@Param("versionCode")String versionCode);

    AppVersion selectByAppIdAndSchoolId(@Param("appId") long appId, @Param("schoolId") long schoolId,@Param("versionCode")String versionCode);

    AppVersion selectByAppIdMax(@Param("appId") long appId,@Param("versionCode")String versionCode);

    AppVersion selectByAppIdAndSchoolIdMax(@Param("appId") long appId, @Param("schoolId") long schoolId,@Param("versionCode")String versionCode);

    AppVersion queryByAppIdAndVersionId(@Param("appId") long appId, @Param("versionCode") String versionCode);

    AppVersion selectByAppIdAndVersionId(@Param("appId") long appId, @Param("versionCode") String versionCode);

    AppVersion selectByAppIdAndVersionIdAndSchoolId(@Param("appId") long appId, @Param("versionCode") String versionCode, @Param("schoolId") long schoolId);

    List<AppVersion> queryByAppId(@Param("appId") Long appId);

    /**根据appId获取当前版本信息**/
    AppVersion selectThisVersion(@Param("appId") Long appId);

}
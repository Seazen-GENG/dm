package com.fuhui.dao.txu;

import com.fuhui.entity.txu.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface AppMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);

    /**
     * 根据学校ID获取学校可用的应用列表
     */
    List<App> getAppListBySchoolId(@Param("iSchoolid") long iSchoolid, @Param("iPlace") Integer iPlace, @Param("iStatus") Integer iStatus, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine, @Param("isPage") Integer isPage);

    List<App> getNewConfigInfo(@Param("appConfigId") long appConfigId, @Param("iSchoolid") long iSchoolid);

    List<App> selectBySchoolId(@Param("schoolId") long schoolId);

    int deleteBySchoolId(@Param("schoolId") long schoolId);

    //查询是否存在需要修改应用版本的学校
    List<App> selectByAppIdAndVersionId(@Param("appId") long appId, @Param("versionId") long versionId);

    int setAppVersionBySchoolId(App record);

    /******条件查询appList表*****/
    List<App> queryAppList(@Param("appIds") Long appIds, @Param("name") String name, @Param("packageName") String packageName, @Param("type") Integer type, @Param("place") Integer place, @Param("status") Integer status,
                           @Param("productId") Long productId, @Param("schoolId") Long schoolId, @Param("sort") Integer sort, @Param("sCTime") String sCTime, @Param("eCTime") String eCTime, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    int updateByAppIdAndSchoolId(@Param("appId") Long appId, @Param("schoolId") Long schoolId, @Param("versionId") Long versionId,@Param("date") Date date);

}
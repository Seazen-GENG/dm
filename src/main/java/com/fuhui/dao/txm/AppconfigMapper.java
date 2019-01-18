package com.fuhui.dao.txm;

import com.fuhui.entity.txm.Appconfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppconfigMapper {
    int deleteByPrimaryKey(Long iConfigid);

    int add(Appconfig record);

    int insertSelective(Appconfig record);

    Appconfig selectByPrimaryKey(long iConfigid);

    int update(Appconfig record);

    int setStatus(Appconfig record);

    List getList(@Param("iSchoolid") String iSchoolid, @Param("appIds") String appIds, @Param("name") String name, @Param("iStatus") Integer iStatus, @Param("pNum") Integer pNum,
                 @Param("pLine") Integer pLine, @Param("isPage") Integer isPage, @Param("sTime") String sTime, @Param("eTime") String eTime);

    Appconfig getInfoList(@Param("iSchoolid") long iSchoolid, @Param("configId") long configId,@Param("appConfigTime") String appConfigTime);

    Appconfig selectBySchoolId(@Param("schoolId") long schoolId);
}
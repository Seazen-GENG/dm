package com.fuhui.dao.tcs;

import com.fuhui.entity.tcs.FversionInstallLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FversionInstallLogMapper {
    int deleteByPrimaryKey(Long iLogid);

    int insert(FversionInstallLog record);

    int insertSelective(FversionInstallLog record);

    FversionInstallLog selectByPrimaryKey(Long iLogid);

    int updateByPrimaryKeySelective(FversionInstallLog record);

    int updateByPrimaryKey(FversionInstallLog record);

    List<FversionInstallLog> selectByParam(@Param("schoolId") Long schoolId, @Param("padId") Long padId, @Param("mac") String mac, @Param("fdeviceId") Long fdeviceId, @Param("fversionId") Long fversionId, @Param("version") String version, @Param("sTime") String sTime, @Param("eTime") String eTime, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    String selectByParamCount(@Param("schoolId") Long schoolId, @Param("padId") Long padId, @Param("mac") String mac, @Param("fdeviceId") Long fdeviceId, @Param("fversionId") Long fversionId, @Param("version") String version, @Param("sTime") String sTime, @Param("eTime") String eTime);
}
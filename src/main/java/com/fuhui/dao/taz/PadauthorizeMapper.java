package com.fuhui.dao.taz;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


import com.fuhui.entity.taz.Padauthorize;

import java.util.List;

@Mapper
@Repository
public interface PadauthorizeMapper {
    int deleteByPrimaryKey(Long iAuzid);

    int add(Padauthorize record);

    int insertSelective(Padauthorize record);

    Padauthorize selectByPrimaryKey(Long iAuzid);

    int update(Padauthorize record);

    int updateByPrimaryKey(Padauthorize record);

    Padauthorize selectBySerialNo(@Param("serialNo") String serialNo);

    List<Padauthorize> selectBySchoolId(@Param("schoolId") Long schoolId);
}
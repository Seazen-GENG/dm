package com.fuhui.dao.txu;

import com.fuhui.entity.txu.School;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SchoolMapper {
    int deleteByPrimaryKey(Long iSchoolid);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Long iSchoolid);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);

    List<School> seletSchoolList();

    List<School> seletSchoolListByAppId(@Param("appId") Long appId);

    List<School> queryListByAppId(@Param("appId") Long appId);

    List<School> getSchoolListByVersionId(@Param("versionId") Long versionId);
}
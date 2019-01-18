package com.fuhui.dao.txu;

import com.fuhui.entity.txu.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper {
    int deleteByPrimaryKey(Long iXgradeid);

    int insert(Grade record);

    int insertSelective(Grade record);

    Grade selectByPrimaryKey(Long iXgradeid);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);

    List getGradeListBySchoolId(@Param("schoolId") Long schoolId, @Param("status") Integer status);

    List<Grade> findByschoolidAndGradeId(@Param("shoolId") long shoolId, @Param("rangIds") String rangIds);
}
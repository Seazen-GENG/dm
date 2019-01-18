package com.fuhui.dao.txu;

import com.fuhui.entity.txu.Platform;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlatformMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(Platform record);

    int insertSelective(Platform record);

    Platform selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKey(Platform record);

    List<Platform> selectBySchoolIdAndType(@Param("schoolId")Long schoolId,@Param("type") Integer type);
}
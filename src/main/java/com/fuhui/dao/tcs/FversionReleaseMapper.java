package com.fuhui.dao.tcs;

import com.fuhui.entity.tcs.FversionRelease;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FversionReleaseMapper {
    int deleteByPrimaryKey(Long iReleaseid);

    int insert(FversionRelease record);

    int insertSelective(FversionRelease record);

    FversionRelease selectByPrimaryKey(Long iReleaseid);

    int updateByPrimaryKeySelective(FversionRelease record);

    int updateByPrimaryKey(FversionRelease record);

    int insertList(List<FversionRelease> record);
}
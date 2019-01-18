package com.fuhui.dao.tcs;

import com.fuhui.entity.tcs.FversionReleasePadList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FversionReleasePadListMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(FversionReleasePadList record);

    int insertSelective(FversionReleasePadList record);

    FversionReleasePadList selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(FversionReleasePadList record);

    int updateByPrimaryKey(FversionReleasePadList record);
}
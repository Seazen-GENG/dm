package com.fuhui.dao.txm;

import com.fuhui.entity.txm.AppConfigconPadList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AppConfigconPadListMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(AppConfigconPadList record);

    int insertSelective(AppConfigconPadList record);

    AppConfigconPadList selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(AppConfigconPadList record);

    int updateByPrimaryKey(AppConfigconPadList record);

    AppConfigconPadList selectByPadId(@Param("padId") Long padId);
}
package com.fuhui.dao.txm;

import com.fuhui.entity.txm.PadConfigconfigList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PadConfigconfigListMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(PadConfigconfigList record);

    int insertSelective(PadConfigconfigList record);

    PadConfigconfigList selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(PadConfigconfigList record);

    int updateByPrimaryKey(PadConfigconfigList record);

    void updateFinaly(PadConfigconfigList record);

    List<PadConfigconfigList> selectByConfigId(@Param("config") Long config);

    PadConfigconfigList selectByConfig(@Param("config") Long config);
}
package com.fuhui.dao.tpd;

import com.fuhui.entity.tpd.PadConfigLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PadConfigLogMapper {
    int deleteByPrimaryKey(Long iLogid);

    int insert(PadConfigLog record);

    int insertSelective(PadConfigLog record);

    PadConfigLog selectByPrimaryKey(Long iLogid);

    int updateByPrimaryKeySelective(PadConfigLog record);

    int updateByPrimaryKey(PadConfigLog record);
}
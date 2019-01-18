package com.fuhui.dao.tpd;

import com.fuhui.entity.tpd.PadBangDingLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PadBangDingLogMapper {
    int deleteByPrimaryKey(Long iLogid);

    int insert(PadBangDingLog record);

    int insertSelective(PadBangDingLog record);

    PadBangDingLog selectByPrimaryKey(Long iLogid);

    int updateByPrimaryKeySelective(PadBangDingLog record);

    int updateByPrimaryKey(PadBangDingLog record);

    int insertList(List<PadBangDingLog> record);
}
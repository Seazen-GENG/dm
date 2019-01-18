package com.fuhui.dao.tpd;

import com.fuhui.entity.tpd.PadSwitchLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PadSwitchLogMapper {
    int deleteByPrimaryKey(Long iLogid);

    int insert(PadSwitchLog record);

    int insertSelective(PadSwitchLog record);

    PadSwitchLog selectByPrimaryKey(Long iLogid);

    int updateByPrimaryKeySelective(PadSwitchLog record);

    int updateByPrimaryKey(PadSwitchLog record);

    List<PadSwitchLog> selectByPadMac(@Param("padMac") String padMac, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    PadSwitchLog selectByMacFinal(@Param("padMac") String padMac);

    String selectCountAll(@Param("padMac") String padMac);
}
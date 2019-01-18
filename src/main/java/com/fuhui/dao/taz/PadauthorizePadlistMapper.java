package com.fuhui.dao.taz;

import com.fuhui.entity.taz.PadauthorizePadlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PadauthorizePadlistMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(PadauthorizePadlist record);

    int add(PadauthorizePadlist record);

    PadauthorizePadlist selectByPrimaryKey(Long iIdx);

    int update(PadauthorizePadlist record);

    int updateByPrimaryKey(PadauthorizePadlist record);

    PadauthorizePadlist selectBySerialNoAndPadId(@Param("auzId") long auzId,@Param("padId") long padId);
}
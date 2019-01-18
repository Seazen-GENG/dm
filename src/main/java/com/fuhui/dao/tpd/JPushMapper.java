package com.fuhui.dao.tpd;

import com.fuhui.entity.tpd.JPush;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JPushMapper {
    int deleteByPrimaryKey(Long iJpushid);

    int insert(JPush record);

    int savePadJPush(JPush record);

    JPush getPadJPushInfo(Long iJpushid);

    int updateByPrimaryKeySelective(JPush record);

    int updateByPrimaryKeyWithBLOBs(JPush record);

    int updateByPrimaryKey(JPush record);

    JPush getPadJPushInfoByPadId(@Param("padId") Long padId);
}
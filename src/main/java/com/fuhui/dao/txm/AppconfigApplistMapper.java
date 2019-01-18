package com.fuhui.dao.txm;

import com.fuhui.entity.txm.AppconfigApplist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppconfigApplistMapper {
    int deleteByPrimaryKey(Long iIdx);

    int saveAppList(AppconfigApplist record);

    int insertSelective(AppconfigApplist record);

    AppconfigApplist selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(AppconfigApplist record);

    int updateByPrimaryKey(AppconfigApplist record);

//    int saveConfigAppList(@Param("Idx") long Idx,@Param("configId") long configId,@Param("userId") long userId,@Param("cTime") String cTime,@Param("appList") List appList);

    int deleteByConfigId(long configId);

    List<AppconfigApplist> getInfo(@Param("configId") long configId, @Param("schoolId") long schoolId, @Param("isShowAppAll") Integer isShowAppAll);

    int deleteBySchooId(@Param("schoolId") long schoolId);
}
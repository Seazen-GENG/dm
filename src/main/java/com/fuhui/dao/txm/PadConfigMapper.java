package com.fuhui.dao.txm;

import com.fuhui.entity.txm.PadConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PadConfigMapper {
    int deleteByPrimaryKey(Long iConfigid);

    int insert(PadConfig record);

    int insertSelective(PadConfig record);

    PadConfig selectByPrimaryKey(Long iConfigid);

    int updateByPrimaryKeySelective(PadConfig record);

    int updateByPrimaryKey(PadConfig record);

    List<PadConfig> getList(@Param("schoolIds") String schoolIds, @Param("name") String name , @Param("type") Integer type,@Param("status") Integer status,@Param("pNum") Integer pNum, @Param("pLine") Integer pLine, @Param("isPage") Integer isPage);

    List<PadConfig> getNewInfoByPadMac(@Param("SchoolId") long SchoolId, @Param("PadId") long PadId, @Param("Type") Integer Type , @Param("configTime") String configTime);

    List<PadConfig> getPadConfigListBySchool(@Param("schoolId") long schoolId);

    List<PadConfig> getPadConfigListByPad(@Param("schoolId") long schoolId, @Param("padId") long padId);
}
package com.fuhui.dao.tpd;

import com.fuhui.entity.tpd.Pad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface PadMapper {
    int deleteByPrimaryKey(Long iPadid);

    int add(Pad record);

    int insertSelective(Pad record);

    Pad selectByPrimaryKey(Long iPadid);

    int update(Pad record);

    int updateByPrimaryKey(Pad record);

    int updatePadBangdingByPrimaryKey(Pad record);

    Pad getInfoByPadMac(@Param("padMac") String padMac);

    List<Pad> getNewInfoByPadMac(@Param("padMac") String padMac);

    Pad selectByPadId(@Param("padId") Long padId);

    Pad getAuthorizeByPadMac(@Param("padMac") String padMac);

    List<Pad> getPadList(@Param("schoolIds") String schoolIds, @Param("padMac") String padMac, @Param("classId") String classId, @Param("factoryId") long factoryId,
                         @Param("deviceId") long deviceId, @Param("model") String model, @Param("fVersion") String fVersion, @Param("type") Integer type,
                         @Param("status") Integer status, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);

    void updatePadauz(Pad record);

    Pad getPadCount(@Param("schoolIds") String schoolIds, @Param("padMac") String padMac, @Param("classId") String classId, @Param("factoryId") long factoryId,
                    @Param("deviceId") long deviceId, @Param("model") String model, @Param("fVersion") String fVersion, @Param("type") Integer type,
                    @Param("status") Integer status);

    String getPadNumBySchool(@Param("schoolId") Long schoolId);
}
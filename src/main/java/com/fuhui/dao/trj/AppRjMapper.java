package com.fuhui.dao.trj;

import com.fuhui.entity.trj.AppRj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppRjMapper {
    int deleteByPrimaryKey(Long iAppid);

    int insert(AppRj record);

    int insertSelective(AppRj record);

    AppRj selectByPrimaryKey(Long iAppid);

    int updateByPrimaryKeySelective(AppRj record);

    int updateByPrimaryKey(AppRj record);

    List<AppRj> selectAllapp();

     /******条件查询appList表*****/
    List<AppRj> queryAppList(@Param("appIds") Long appIds, @Param("name") String name, @Param("packageName") String packageName, @Param("type") Integer type, @Param("place") Integer place, @Param("status") Integer status,
                             @Param("productId") Long productId, @Param("schoolId") Long schoolId, @Param("sort") Integer sort, @Param("sCTime") String sCTime, @Param("eCTime") String eCTime, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);
}
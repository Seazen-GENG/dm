package com.fuhui.dao.twj;

import com.fuhui.entity.twj.WjFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WjFileMapper {
    int deleteByPrimaryKey(Long iFileid);

    int insert(WjFile record);

    int insertSelective(WjFile record);

    WjFile selectByPrimaryKey(Long iFileid);

    int updateByPrimaryKeySelective(WjFile record);

    int updateByPrimaryKey(WjFile record);

    WjFile selectFileByMd5(@Param("md5") String md5);
}
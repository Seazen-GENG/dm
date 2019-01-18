package com.fuhui.dao.txu;

import com.fuhui.entity.txu.Year;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface YearMapper {
    int deleteByPrimaryKey(Long iYearid);

    int insert(Year record);

    int add(Year record);

    Year selectByPrimaryKey(Long iYearid);

    int update(Year record);

    int updateByPrimaryKey(Year record);

    List<Year> selectByYear(@Param("schoolIds") String schoolIds,@Param("name") String name, @Param("code") String code
            ,@Param("status") String status,@Param("isPage") Integer isPage,@Param("pNum") Integer pNum,@Param("pLine") Integer pLine);
}
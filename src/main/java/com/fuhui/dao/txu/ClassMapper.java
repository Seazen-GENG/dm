package com.fuhui.dao.txu;

import com.fuhui.entity.txu.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassMapper {
    int deleteByPrimaryKey(Long iClassid);

    int insert(Class record);

    int add(Class record);

    Class selectByPrimaryKey(Long iClassid);

    int update(Class record);

    int updateByPrimaryKey(Class record);

    List<Class> findByschoolidAndRangIds(@Param("shoolId") long shoolId, @Param("rangIds") String rangIds);

    List<Class> selectClassList(@Param("schoolIds") String schoolIds, @Param("name") String name, @Param("code") String code, @Param("status") String status, @Param("gradeId") String gradeId,
                                @Param("xGradeId") String xGradeId, @Param("yearId") String yearId, @Param("headId") String headId, @Param("sCTime") String sCTime,
                                @Param("eCTime") String eCTime, @Param("isPage") Integer isPage, @Param("pNum") Integer pNum, @Param("pLine") Integer pLine);
}
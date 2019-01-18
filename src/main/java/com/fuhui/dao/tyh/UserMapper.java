package com.fuhui.dao.tyh;

import com.fuhui.entity.tyh.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long iUserid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long iUserid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(@Param("name") String name);
}
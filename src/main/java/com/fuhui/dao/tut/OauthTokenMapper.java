package com.fuhui.dao.tut;

import com.fuhui.entity.tut.OauthToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OauthTokenMapper {
    int deleteByPrimaryKey(Long iId);

    int insert(OauthToken record);

    int insertSelective(OauthToken record);

    OauthToken selectByPrimaryKey(Long iId);

    OauthToken selectByPadId(Long padId);

    int updateByPrimaryKeySelective(OauthToken record);

    int updateByPrimaryKey(OauthToken record);

    OauthToken selectByToken(@Param("token") String token);

}
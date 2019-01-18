package com.fuhui.dao.trj;

import com.fuhui.entity.trj.Oauth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OauthMapper {
    int deleteByPrimaryKey(Long iAppid);

    int insert(Oauth record);

    int insertSelective(Oauth record);

    Oauth selectByPrimaryKey(Long iAppid);

    int updateByPrimaryKeySelective(Oauth record);

    int updateByPrimaryKey(Oauth record);

    Oauth selectByIdAndKey(@Param("iAppid") Long iAppid,@Param("key") String key);
}
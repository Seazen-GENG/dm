package com.fuhui.dao.tqx;

import com.fuhui.entity.tqx.Token;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TokenMapper {
    int deleteByPrimaryKey(Long iTokenid);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(Long iTokenid);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}
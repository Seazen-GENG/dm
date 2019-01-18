package com.fuhui.dao.tms;

import com.fuhui.entity.tms.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(Long iMsgid);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long iMsgid);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}
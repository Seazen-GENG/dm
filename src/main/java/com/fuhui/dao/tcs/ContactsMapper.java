package com.fuhui.dao.tcs;

import com.fuhui.entity.tcs.Contacts;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ContactsMapper {
    int deleteByPrimaryKey(Long iFcontactsid);

    int insert(Contacts record);

    int insertSelective(Contacts record);

    Contacts selectByPrimaryKey(Long iFcontactsid);

    int updateByPrimaryKeySelective(Contacts record);

    int updateByPrimaryKey(Contacts record);
}
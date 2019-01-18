package com.fuhui.dao.twf;

import com.fuhui.entity.twf.WfLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WfLogMapper {
    int deleteByPrimaryKey(Long iLogid);

    int insert(WfLog record);

    int insertSelective(WfLog record);

    WfLog selectByPrimaryKey(Long iLogid);

    int updateByPrimaryKeySelective(WfLog record);

    int updateByPrimaryKey(WfLog record);
}
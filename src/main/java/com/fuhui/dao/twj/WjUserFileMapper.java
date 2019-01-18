package com.fuhui.dao.twj;

import com.fuhui.entity.twj.WjUserFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface WjUserFileMapper {
    int deleteByPrimaryKey(Long iUfid);

    int insert(WjUserFile record);

    int insertSelective(WjUserFile record);

    WjUserFile selectByPrimaryKey(Long iUfid);

    int updateByPrimaryKeySelective(WjUserFile record);

    int updateByPrimaryKey(WjUserFile record);
}
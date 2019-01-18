package com.fuhui.dao.tjc;

import com.fuhui.entity.tjc.Stage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StageMapper {
    int deleteByPrimaryKey(Long iStageid);

    int insert(Stage record);

    int insertSelective(Stage record);

    Stage selectByPrimaryKey(Long iStageid);

    int updateByPrimaryKeySelective(Stage record);

    int updateByPrimaryKey(Stage record);
}
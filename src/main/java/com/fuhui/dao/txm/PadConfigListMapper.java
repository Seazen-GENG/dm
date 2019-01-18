package com.fuhui.dao.txm;

import com.fuhui.entity.txm.PadConfigList;

public interface PadConfigListMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(PadConfigList record);

    int insertSelective(PadConfigList record);

    PadConfigList selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(PadConfigList record);

    int updateByPrimaryKeyWithBLOBs(PadConfigList record);

    int updateByPrimaryKey(PadConfigList record);

}
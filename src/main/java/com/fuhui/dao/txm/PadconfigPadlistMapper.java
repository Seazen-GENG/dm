package com.fuhui.dao.txm;

import com.fuhui.entity.txm.PadconfigPadlist;

public interface PadconfigPadlistMapper {
    int deleteByPrimaryKey(Long iIdx);

    int insert(PadconfigPadlist record);

    int insertSelective(PadconfigPadlist record);

    PadconfigPadlist selectByPrimaryKey(Long iIdx);

    int updateByPrimaryKeySelective(PadconfigPadlist record);

    int updateByPrimaryKey(PadconfigPadlist record);
}
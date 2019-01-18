package com.fuhui.dao.tut;

import com.fuhui.entity.tut.IpAddress;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IpAddressMapper {
    int deleteByPrimaryKey(Long iId);

    int insert(IpAddress record);

    int insertSelective(IpAddress record);

    IpAddress selectByPrimaryKey(Long iId);

    int updateByPrimaryKeySelective(IpAddress record);

    int updateByPrimaryKey(IpAddress record);

    IpAddress selectByIpAndAddress(@Param("ip") String ip, @Param("address") String address);
}
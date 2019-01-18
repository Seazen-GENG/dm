package com.fuhui.dao.model;

import com.fuhui.entity.model.padConfigModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface padConfigModelMapper {

    List<padConfigModel> getPadConfigModel(@Param("padId") Long padId);

}

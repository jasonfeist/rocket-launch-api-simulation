package com.jasonfeist.rocket.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MissionStatusMapper {

    @Select("SELECT id FROM mission_status WHERE UPPER(status) = UPPER(#{status})")
    Integer getIdByStatus(@Param("status") String status);
}

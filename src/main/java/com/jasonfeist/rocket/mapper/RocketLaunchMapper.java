package com.jasonfeist.rocket.mapper;

import com.jasonfeist.rocket.model.IntegerKeyValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface RocketLaunchMapper {

    @Select("<script>" +
            "SELECT avg(mission_cost) AS value FROM rocket_launches rl " +
            "JOIN rocket_companies rc ON rc.id=rl.rocket_company_id " +
            "WHERE 1=1 " +
            "  <if test='rocketCompany != null'>" +
            "  AND UPPER(rc.name)=UPPER(#{rocketCompany})" +
            "  </if>" +
            "  <if test='startDate != null'>" +
            "  <![CDATA[AND launch_time_date >= #{startDate}]]>" +
            "  </if>" +
            "  <if test='endDate != null'>" +
            "  <![CDATA[AND launch_time_date <= #{endDate}]]>" +
            "  </if>" +
            "</script>")
    Float getAverageLaunchCost(@Param("rocketCompany") String rocketCompany,
                               @Param("startDate") Timestamp startDate,
                               @Param("endDate") Timestamp endDate);

    @Select("<script>" +
            "SELECT value FROM (" +
            "  SELECT mission_status_id, (COUNT(*) / (SUM(COUNT(*)) OVER() )) * 100 AS value FROM rocket_launches rl " +
            "  JOIN rocket_companies rc ON rc.id=rl.rocket_company_id " +
            "  WHERE 1=1 " +
            "    <if test='rocketCompany != null'>" +
            "    AND UPPER(rc.name)=UPPER(#{rocketCompany})" +
            "    </if>" +
            "    <if test='startDate != null'>" +
            "    <![CDATA[AND launch_time_date >= #{startDate}]]>" +
            "    </if>" +
            "    <if test='endDate != null'>" +
            "    <![CDATA[AND launch_time_date <= #{endDate}]]>" +
            "    </if>" +
            "  GROUP BY mission_status_id" +
            ") g WHERE mission_status_id=#{statusId}" +
            "</script>")
    Float getPercentByStatus(@Param("statusId") Integer statusId,
                             @Param("rocketCompany") String rocketCompany,
                             @Param("startDate") Timestamp startDate,
                             @Param("endDate") Timestamp endDate);

    @Select("<script>" +
            "SELECT launch_month FROM (" +
            "  SELECT extract(month from launch_time_date) AS launch_month FROM rocket_launches rl " +
            "  JOIN rocket_companies rc ON rc.id=rl.rocket_company_id " +
            "  WHERE 1=1 " +
            "    <if test='rocketCompany != null'>" +
            "    AND UPPER(rc.name)=UPPER(#{rocketCompany})" +
            "    </if>" +
            "    <if test='startDate != null'>" +
            "    <![CDATA[AND launch_time_date >= #{startDate}]]>" +
            "    </if>" +
            "    <if test='endDate != null'>" +
            "    <![CDATA[AND launch_time_date <= #{endDate}]]>" +
            "    </if>" +
            ") g GROUP BY launch_month ORDER BY count(launch_month) DESC LIMIT 1" +
            "</script>")
    Integer getLaunchCountsByMonth(@Param("rocketCompany") String rocketCompany,
                             @Param("startDate") Timestamp startDate,
                             @Param("endDate") Timestamp endDate);

    @Select("<script>" +
            "SELECT location AS label, count(location) AS value FROM (" +
            "  SELECT location FROM rocket_launches rl " +
            "  JOIN launch_location ll on ll.id=rl.launch_location_id " +
            "  JOIN rocket_companies rc ON rc.id=rl.rocket_company_id " +
            "  WHERE 1=1 " +
            "    <if test='rocketCompany != null'>" +
            "    AND UPPER(rc.name)=UPPER(#{rocketCompany})" +
            "    </if>" +
            "    <if test='startDate != null'>" +
            "    <![CDATA[AND launch_time_date >= #{startDate}]]>" +
            "    </if>" +
            "    <if test='endDate != null'>" +
            "    <![CDATA[AND launch_time_date <= #{endDate}]]>" +
            "    </if>" +
            ") g GROUP BY location ORDER BY count(location) DESC LIMIT 3" +
            "</script>")
    List<IntegerKeyValue> getLaunchLocationCounts(@Param("rocketCompany") String rocketCompany,
                                                  @Param("startDate") Timestamp startDate,
                                                  @Param("endDate") Timestamp endDate);

    @Select("<script>" +
            "SELECT country AS label, count(country) AS value FROM (" +
            "  SELECT trim(reverse(split_part(reverse(location),',', 1))) AS country FROM rocket_launches rl " +
            "  JOIN launch_location ll on ll.id=rl.launch_location_id " +
            "  JOIN rocket_companies rc ON rc.id=rl.rocket_company_id " +
            "  WHERE 1=1 " +
            "    <if test='rocketCompany != null'>" +
            "    AND UPPER(rc.name)=UPPER(#{rocketCompany})" +
            "    </if>" +
            "    <if test='startDate != null'>" +
            "    <![CDATA[AND launch_time_date >= #{startDate}]]>" +
            "    </if>" +
            "    <if test='endDate != null'>" +
            "    <![CDATA[AND launch_time_date <= #{endDate}]]>" +
            "    </if>" +
            ") g GROUP BY country ORDER BY count(country) DESC LIMIT 3" +
            "</script>")
    List<IntegerKeyValue> getLaunchCountryCounts(@Param("rocketCompany") String rocketCompany,
                                                 @Param("startDate") Timestamp startDate,
                                                 @Param("endDate") Timestamp endDate);
}

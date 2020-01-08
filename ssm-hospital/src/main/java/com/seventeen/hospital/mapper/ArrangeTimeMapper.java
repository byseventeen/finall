package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.ArrangeTime;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ArrangeTimeMapper extends Mapper<ArrangeTime> {
    //根据医生证件号码查询所有该医生号源信息和值班信息
    @Select("select * FROM arrangetime WHERE arrangetime.dcard_id=#{dCardId} AND arrangetime_name=#{arrangeTimeName}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "dCardId",column = "dcard_id"),
            @Result(property = "arrangeTimeName",column = "arrangetime_name"),
            @Result(property = "startTime",column = "start_time"),
            @Result(property = "stopTime",column = "stop_time"),
            @Result(property = "numAmount",column = "num_amount"),
            @Result(property = "numSurplus",column = "num_surplus"),
    })
    List<ArrangeTime> selectAllBydCardId(Map map);

    //多对一。医生多，根据医生的id查出医生的门诊信息等
    @Select("select * FROM arrangetime WHERE arrangetime.id=#{arrageTimeId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "dCardId",column = "dcard_id"),
            @Result(property = "arrangeTimeName",column = "arrangetime_name"),
            @Result(property = "startTime",column = "start_time"),
            @Result(property = "stopTime",column = "stop_time"),
            @Result(property = "numAmount",column = "num_amount"),
            @Result(property = "numSurplus",column = "num_surplus"),
    })
    List<ArrangeTime> findArrangeTimebyId(int arrageTimeId);
}

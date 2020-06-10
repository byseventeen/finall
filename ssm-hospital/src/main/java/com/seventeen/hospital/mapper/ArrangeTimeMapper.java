package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.ArrangeTime;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
            @Result(property = "numSurplus",column = "arrange_date"),
            @Result(property = "arrangeDate",column = "num_surplus"),
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

    //增加预约 修改号源 剩余号源-1 总号源不变
    @Update("update arrangetime set num_surplus=num_surplus-1 WHERE id = #{arrangeTimeId};")
    int updateArrangementTime(Integer arrangeTimeId);

    //根据医生证件号码 预约日期  预约时段开始时间查找timelist
    @Select("select * FROM arrangetime WHERE  arrangetime.dcard_id=#{dCardId} and arrangetime.arrange_date=#{dDate} and arrangetime.start_time=#{time}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "dCardId",column = "dcard_id"),
            @Result(property = "arrangeTimeName",column = "arrangetime_name"),
            @Result(property = "startTime",column = "start_time"),
            @Result(property = "stopTime",column = "stop_time"),
            @Result(property = "numAmount",column = "num_amount"),
            @Result(property = "numSurplus",column = "arrange_date"),
            @Result(property = "arrangeDate",column = "num_surplus"),
    })
    List<ArrangeTime> selectAllByCIdDate(Map map);
}

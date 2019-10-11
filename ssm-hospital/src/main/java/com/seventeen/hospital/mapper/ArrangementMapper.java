package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Arrangement;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface ArrangementMapper extends Mapper<Arrangement> {

    @Select("select * from departmenttype where id = #{departmenttypeId}")
    @Results({
            @Result(property="departmenttypeid", column="id"),
            @Result(property="departypementname", column="detype_name"),
            @Result(property="departments", column="id", many=@Many(
                    select="com.seventeen.hospital.mapper.DepartmentMapper.selectDepartmentByDeptypeId"
            ))
    })
    List<Arrangement> findArrangementbydId(Integer departmenttypeId);

    @Select("select * from arrangement where doctor_id = #{did} and #{sDate} < arrange_date and arrange_date < #{dDate}")
    @Results({
            @Result(property="id", column="id"),
            @Result(property="doctorId", column="doctor_id"),
            @Result(property="departmentId", column="department_id"),
            @Result(property="arrangeDate", column="arrange_date"),
            @Result(property="arrangeName", column="arrange_name"),
    })
    //List<Arrangement> findArrangementbycIddDate(@Param("doctor_id") Integer did, @Param("arrange_date") String sDate, @Param("arrange_date") String dDate);
    List<Arrangement> findArrangementbycIddDate(Map map);


    @Select("select * from arrangement where doctor_id = #{did} and #{Date} = arrange_date ")
    @Results({
            @Result(property="id", column="id"),
            @Result(property="doctorId", column="doctor_id"),
            @Result(property="departmentId", column="department_id"),
            @Result(property="arrangeDate", column="arrange_date"),
            @Result(property="arrangeName", column="arrange_name"),
    })
    List<Arrangement> findArrangementbyIdOneDate(Map map);

    @Update("update arrangement set arrange_date=#{arrangeDate},arrange_name=#{arrangeName}  WHERE doctor_id = #{did} and arrange_date=#{arrangeDate};")
    int updateArrangement(Map map);

    @Delete("delete from arrangement WHERE doctor_id = #{did} and arrange_date=#{arrangeDate} and arrange_name=#{arrangeName};")
    int deleteArrangement(Map map);

}

package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Doctor;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DoctorMapper extends Mapper<Doctor> {
    //根据医生id查询所有该id医生信息
    @Select("select * FROM doctor WHERE doctor.id=#{doctorid}")
    @Results({
            @Result(property = "doctorid",column = "id"),
            @Result(property = "dname",column = "dname"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "cardId",column = "card_id"),
            @Result(property = "password",column = "password"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "departmentId",column = "department_id"),
            @Result(property = "titleId",column = "title_id"),
            @Result(property = "profile",column = "profile"),
    })
    List<Doctor> selectDoctorById(int doctorid);
}
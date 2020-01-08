package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Doctor;
import org.apache.ibatis.annotations.Many;
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


    //根据门诊id查询所有该id医生信息
    @Select("select * FROM doctor,title WHERE doctor.title_id=title.id AND doctor.department_id=#{departmentid}")
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
            @Result(property = "titles.titlename",column = "title_name"),
    })
    List<Doctor> selectDoctorBydepId(int departmentid);


    //多对一。医生多，根据医生的id查出医生的门诊信息等
    @Select("select * FROM department,doctor WHERE doctor.department_id=department.id AND doctor.id=#{doctorId}")
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
            @Result(property = "departments.departmentname",column = "department_name"),
    })
    List<Doctor> findDoctorsbyId(int doctorId);
}
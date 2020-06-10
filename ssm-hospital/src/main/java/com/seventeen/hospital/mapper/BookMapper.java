package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Book;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface BookMapper extends Mapper<Book> {
    //查询所有用户所有预约信息
    @Select("select * FROM book ")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "doctorId",column = "doctor_id"),
            @Result(property = "arrangeId",column = "arrange_id"),
            @Result(property = "timeId",column = "time_id"),
            @Result(property = "payState",column = "pay_state"),
            @Result(property = "bookState",column = "book_state"),
            @Result(property="users", column="user_id", many=@Many(
                    select="com.seventeen.hospital.mapper.UserMapper.selectUserById"
            )),
            @Result(property="doctors", column="doctor_id", many=@Many(
                    select="com.seventeen.hospital.mapper.DoctorMapper.findDoctorsbyId"
            )),
            @Result(property="arrangements", column="arrange_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangementMapper.findArrangementbyId"
            )),
            @Result(property="times", column="time_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangeTimeMapper.findArrangeTimebyId"
            )),
    })
    List<Book> selectAllAppointment();

    //根据用户id查询该用户所有预约信息
    @Select("select * FROM book WHERE book.user_id=#{userId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "doctorId",column = "doctor_id"),
            @Result(property = "arrangeId",column = "arrange_id"),
            @Result(property = "timeId",column = "time_id"),
            @Result(property = "payState",column = "pay_state"),
            @Result(property = "bookState",column = "book_state"),
            @Result(property="users", column="user_id", many=@Many(
                    select="com.seventeen.hospital.mapper.UserMapper.selectUserById"
            )),
            @Result(property="doctors", column="doctor_id", many=@Many(
                    select="com.seventeen.hospital.mapper.DoctorMapper.findDoctorsbyId"
            )),
            @Result(property="arrangements", column="arrange_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangementMapper.findArrangementbyId"
            )),
            @Result(property="times", column="time_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangeTimeMapper.findArrangeTimebyId"
            )),
    })
    List<Book> selectAllByUserId(Integer userId);

    //根据医生id查询该用户所有预约信息
    @Select("select * FROM book WHERE book.doctor_id=#{doctorId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "doctorId",column = "doctor_id"),
            @Result(property = "arrangeId",column = "arrange_id"),
            @Result(property = "timeId",column = "time_id"),
            @Result(property = "payState",column = "pay_state"),
            @Result(property = "bookState",column = "book_state"),
            @Result(property="users", column="user_id", many=@Many(
                    select="com.seventeen.hospital.mapper.UserMapper.selectUserById"
            )),
            @Result(property="doctors", column="doctor_id", many=@Many(
                    select="com.seventeen.hospital.mapper.DoctorMapper.findDoctorsbyId"
            )),
            @Result(property="arrangements", column="arrange_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangementMapper.findArrangementbyId"
            )),
            @Result(property="times", column="time_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangeTimeMapper.findArrangeTimebyId"
            )),
    })
    List<Book> selectAllByDId(Integer userId);

    //根据医生id  timeID查找预约记录
    @Select("select * FROM book WHERE book.time_id=#{timeId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "doctorId",column = "doctor_id"),
            @Result(property = "arrangeId",column = "arrange_id"),
            @Result(property = "timeId",column = "time_id"),
            @Result(property = "payState",column = "pay_state"),
            @Result(property = "bookState",column = "book_state"),
            @Result(property="users", column="user_id", many=@Many(
                    select="com.seventeen.hospital.mapper.UserMapper.selectUserById"
            )),
            @Result(property="doctors", column="doctor_id", many=@Many(
                    select="com.seventeen.hospital.mapper.DoctorMapper.findDoctorsbyId"
            )),
            @Result(property="arrangements", column="arrange_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangementMapper.findArrangementbyId"
            )),
            @Result(property="times", column="time_id", many=@Many(
                    select="com.seventeen.hospital.mapper.ArrangeTimeMapper.findArrangeTimebyId"
            )),
    })
    List<Book> selectAllByDIdDate(Map map);



}

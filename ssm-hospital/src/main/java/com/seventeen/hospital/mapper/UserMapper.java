package com.seventeen.hospital.mapper;


import com.seventeen.hospital.beans.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserMapper extends Mapper<User> {
    //根据用户id查询所有该id下所有职称信息
    @Select("select * FROM user WHERE user.id=#{userId}")
    @Results({
            @Result(property = "userid",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "gender",column = "gender"),
            @Result(property = "cardId",column = "card_id"),
            @Result(property = "phone",column = "phone"),
    })
    List<User> selectUserById(int userId);
}

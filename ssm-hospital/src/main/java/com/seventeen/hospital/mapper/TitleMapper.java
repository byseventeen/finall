package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Title;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TitleMapper extends Mapper<Title> {

    //根据职称id查询所有该id下所有职称信息
    @Select("select * FROM title WHERE title.id=#{titleid}")
    @Results({
            @Result(property = "tid",column = "id"),
            @Result(property = "titlename",column = "title_name"),
            @Result(property = "tprice",column = "title_price")
    })
    List<Title> selectTitleById(int titleid);
}

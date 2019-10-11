package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Department;
import com.seventeen.hospital.beans.Departmenttype;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DepartmenttypeMapper extends Mapper<Departmenttype> {
    //一对多，根据科室id查询该科室的所有门诊以及门诊信息
    @Select("select * from departmenttype where id = #{departmenttypeId}")
    @Results({
            @Result(property="departmenttypeid", column="id"),
            @Result(property="departypementname", column="detype_name"),
            @Result(property="departments", column="id", many=@Many(
                    select="com.seventeen.hospital.mapper.DepartmentMapper.selectDepartmentByDeptypeId"
            ))
    })
    List<Departmenttype> selectDepartmenttypeById(int departmenttypeId);


}

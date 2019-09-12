package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Department;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DepartmentMapper extends Mapper<Department> {

   @Select("select * FROM department,departmenttype WHERE department.detype_id=departmenttype.id")
   @Results({
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "department_type.departmenttypeid",column = "id"),
           @Result(property = "department_type.departmentname",column = "detype_name")
   })
   List<Department> findAllDepartment();

   @Select("select * from department where detype_id = #{deptypeid}")
   @Results(value={
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "dtypeid",column = "detype_id"),
   })
   List<Department> selectDepartmentByDeptypeId(int deptypeid);


}

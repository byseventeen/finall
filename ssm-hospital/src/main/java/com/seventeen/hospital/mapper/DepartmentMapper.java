package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Department;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface DepartmentMapper extends Mapper<Department> {

   //多对一。门诊多，门诊类型一，查询所有门诊信息，包括门诊类型
   @Select("select * FROM department,departmenttype WHERE department.detype_id=departmenttype.id")
   @Results({
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "department_type.departmenttypeid",column = "id"),
           @Result(property = "department_type.departypementname",column = "detype_name")
   })
   List<Department> findAllDepartment();

   //多对一。门诊多，门诊类型一，查询所有门诊信息，包括门诊类型
   @Select("select * from department where detype_id = #{deptypeid}")
   @Results(value={
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "dtypeid",column = "detype_id"),
   })
   List<Department> selectDepartmentByDeptypeId(int deptypeid);

   //根据门诊id查询门诊所有信息和科室信息
   @Select("select * FROM department,departmenttype WHERE department.detype_id=departmenttype.id AND department.id=#{department}")
   @Results({
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "department_type.departmenttypeid",column = "id"),
           @Result(property = "department_type.departypementname",column = "detype_name")
   })
   List<Department> selectDepartmentById(int departmentid);

   //根据门诊名字查询该门诊所有信息和科室信息
   @Select("select * FROM department,departmenttype WHERE department.detype_id=departmenttype.id AND department.department_name=#{dname}")
   @Results({
           @Result(property = "departmentid",column = "id"),
           @Result(property = "departmentname",column = "department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property = "department_type.departmenttypeid",column = "id"),
           @Result(property = "department_type.departypementname",column = "detype_name")
   })
   List<Department> selectDepartmentByDname(String dname);


   //一对多，根据门诊id查询该科室的所有医生以及医生信息
   @Select("select * from department where id = #{departmentid}")
   @Results({
           @Result(property="departmentid", column="id"),
           @Result(property="departmentname", column="department_name"),
           @Result(property = "deprofile",column = "department_profile"),
           @Result(property="doctors", column="id", many=@Many(
                   select="com.seventeen.hospital.mapper.DoctorMapper.selectDoctorBydepId"
           ))
   })
   List<Department> selectDepartmentsById(int departmentid);


}

package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Department;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface DepartmentMapper extends Mapper<Department> {
}

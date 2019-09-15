package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.Department;

import java.util.List;

public interface IDepartmentService extends ICommonService<Department> {
    List<Department> findAllDepartment();
}
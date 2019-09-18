package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IDepartmentService;
import com.seventeen.hospital.beans.Department;
import com.seventeen.hospital.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "departmentService")
public class DepartmentServiceImpl extends CommonServiceImpl<Department> implements IDepartmentService {

    @Autowired
    protected DepartmentMapper departmentMapper;

    @Override
    protected Mapper getMapper() {
        return this.departmentMapper;
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentMapper.findAllDepartment();
    }

    @Override
    public List<Department> findDepartmentById(int departmentid) {
        return departmentMapper.selectDepartmentById(departmentid);
    }

    @Override
    public List<Department> findDepartmentByDname(String dname) {
        return departmentMapper.selectDepartmentByDname(dname);
    }
}

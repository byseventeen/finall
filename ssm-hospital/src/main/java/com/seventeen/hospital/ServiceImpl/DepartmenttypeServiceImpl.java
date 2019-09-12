package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IDepartmenttypeService;
import com.seventeen.hospital.beans.Department;
import com.seventeen.hospital.beans.Departmenttype;
import com.seventeen.hospital.mapper.DepartmenttypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Service(value = "departmenttypeService")
public class DepartmenttypeServiceImpl extends CommonServiceImpl<Departmenttype> implements IDepartmenttypeService {

    @Autowired
    protected DepartmenttypeMapper departmenttypeMapper;

    @Override
    protected Mapper getMapper() {
        return this.departmenttypeMapper;
    }

    @Override
    public List<Departmenttype> findDepartmentBytypeId(int deptypeid) {
        return departmenttypeMapper.selectDepartmenttypeById(deptypeid);
    }
}

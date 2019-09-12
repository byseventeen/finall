package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.Departmenttype;

import java.util.List;

public interface IDepartmenttypeService extends ICommonService<Departmenttype> {
    List<Departmenttype> findDepartmentBytypeId(int deptypeid);
}

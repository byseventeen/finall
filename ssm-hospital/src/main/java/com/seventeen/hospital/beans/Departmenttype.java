package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
public class Departmenttype {
    @Id
    @Column(name = "id")
    private Integer departmenttypeid;
    @Column(name = "detype_name")
    private String departypementname;
    @Transient
    private List<Department> departments;
}

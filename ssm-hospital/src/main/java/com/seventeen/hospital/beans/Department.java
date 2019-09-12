package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;


@Data
public class Department {
    @Id
    @Column(name = "id")
    private Integer departmentid;
    @Column(name = "department_name")
    private String departmentname;
    @Column(name = "detype_id")
    private Integer dtypeid;
    @Column(name = "department_profile")
    private String deprofile;
    @Transient
    private Departmenttype department_type;
}

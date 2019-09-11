package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Departmenttype {
    @Id
    @Column(name = "id")
    private Integer departmenttypeid;
    @Column(name = "detype_name")
    private String departmentname;
}

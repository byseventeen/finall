package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class Arrangement {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "arrange_date")
    private Date arrangeDate;
    @Column(name = "arrange_name")
    private String arrangeName;
}

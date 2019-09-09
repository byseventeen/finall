package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Doctor {
    @Id
    @Column(name = "id")
    private Integer doctorid;
    private String dname;
    private String gender;
    @Column(name = "card_id")
    private String cardId;
    private String password;
    private String phone;
    @Column(name = "department_id")
    private Integer departmentId;
    @Column(name = "title_id")
    private Integer titleId;
    private String profile;
}

package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Advice {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "advicetype")
    private String adviceType;
    @Column(name = "advice")
    private String advice;
}




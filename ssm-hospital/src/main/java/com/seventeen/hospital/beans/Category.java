package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Category {
    @Id
    @Column(name="id")
    private Integer cid;
    private String cname;

}

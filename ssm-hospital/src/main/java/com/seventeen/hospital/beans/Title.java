package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Title {
    @Id
    @Column(name = "id")
    private Integer tid;
    @Column(name = "title_name")
    private String titlename;
    @Column(name = "title_price")
    private Integer tprice;
}

package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
public class ArrangeTime {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "dcard_id")
    private Integer dCardId;
    @Column(name = "arrangetime_name")
    private String arrangeTimeName;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "stop_time")
    private String stopTime;
    @Column(name = "num_amount")
    private Integer numAmount;
    @Column(name = "num_surplus")
    private Integer numSurplus;
}

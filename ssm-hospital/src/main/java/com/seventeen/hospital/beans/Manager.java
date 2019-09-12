package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class Manager {
    @Id
    @Column(name = "id")
    private Integer mid;
    @Column(name = "manager_name")
    private String mname;
    private String gender;
    @Column(name = "card_id")
    private String cardId;
    private String password;
    private String phone;

}

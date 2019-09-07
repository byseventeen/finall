package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class User {
    @Id
    @Column(name = "id")
    private Integer userid;
    private String username;
    private String gender;
    @Column(name = "card_id")
    private String cardId;
    private String password;
    private String phone;
}

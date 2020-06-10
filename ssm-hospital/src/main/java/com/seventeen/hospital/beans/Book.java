package com.seventeen.hospital.beans;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Data
public class Book {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "doctor_id")
    private Integer doctorId;
    @Column(name = "arrange_id")
    private Integer arrangeId;
    @Column(name = "time_id")
    private Integer timeId;
    @Column(name = "book_state")
    private String bookState;
    @Transient
    private List<User> users;
    @Transient
    private List<Doctor> doctors;
    @Transient
    private List<Arrangement> arrangements;
    @Transient
    private List<ArrangeTime> times;
}

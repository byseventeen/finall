package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.Doctor;

import java.util.List;

public interface IDoctorService extends ICommonService<Doctor> {
    List<Doctor> findDoctorById(int doctorid);
}

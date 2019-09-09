package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Doctor;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface DoctorMapper extends Mapper<Doctor> {
}

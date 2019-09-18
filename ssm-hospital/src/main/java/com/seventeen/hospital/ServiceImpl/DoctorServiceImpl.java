package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IDoctorService;
import com.seventeen.hospital.beans.Doctor;
import com.seventeen.hospital.mapper.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value = "doctorService")
public class DoctorServiceImpl extends CommonServiceImpl<Doctor> implements IDoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    protected Mapper getMapper() {
        return this.doctorMapper;
    }

    @Override
    public List<Doctor> findDoctorById(int doctorid) {
        return doctorMapper.selectDoctorById(doctorid);
    }
}

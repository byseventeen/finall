package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IAdviceService;
import com.seventeen.hospital.beans.Advice;
import com.seventeen.hospital.mapper.AdviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service(value="adviceService")
public class AdviceServiceImpl extends CommonServiceImpl<Advice> implements IAdviceService {

    @Autowired
    private AdviceMapper adviceMapper;

    @Override
    protected Mapper getMapper() {
        return this.adviceMapper;
    }
}

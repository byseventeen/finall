package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IManagerService;
import com.seventeen.hospital.beans.Manager;
import com.seventeen.hospital.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@Service(value="managerService")
public class ManagerServiceImpl extends CommonServiceImpl<Manager> implements IManagerService {
    @Autowired
    protected ManagerMapper managerMapper;

    @Override
    protected Mapper getMapper() {
        return this.managerMapper;
    }
}

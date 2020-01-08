package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IUserService;
import com.seventeen.hospital.beans.User;
import com.seventeen.hospital.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value="userService")
public class UserServiceImpl extends CommonServiceImpl<User> implements IUserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    protected Mapper getMapper() {
        return this.userMapper;
    }


}

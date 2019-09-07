package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
}

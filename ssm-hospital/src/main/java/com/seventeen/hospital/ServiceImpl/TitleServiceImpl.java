package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.ITitleService;
import com.seventeen.hospital.beans.Title;
import com.seventeen.hospital.mapper.TitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value="titleService")
public class TitleServiceImpl extends CommonServiceImpl<Title> implements ITitleService {

    @Autowired
    protected TitleMapper titleMapper;

    @Override
    protected Mapper getMapper() {
        return this.titleMapper;
    }

    @Override
    public List<Title> findTitleById(int titleid) {
        return titleMapper.selectTitleById(titleid);
    }
}

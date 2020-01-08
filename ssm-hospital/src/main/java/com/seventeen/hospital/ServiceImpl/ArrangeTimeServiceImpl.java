package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IArrangeTimeService;
import com.seventeen.hospital.beans.ArrangeTime;
import com.seventeen.hospital.mapper.ArrangeTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Service(value="arrangeTimeService")
public class ArrangeTimeServiceImpl extends CommonServiceImpl<ArrangeTime> implements IArrangeTimeService {

    @Autowired
    private ArrangeTimeMapper arrangeTimeMapper;

    @Override
    protected Mapper getMapper() {
        return this.arrangeTimeMapper;
    }

    @Override
    public List<ArrangeTime> selectAllBydCardId(int dCardId,String arrangeTimeName) {
       // return arrangeTimeMapper.selectAllBydCardId(dCardId,arrangeTimeName);

        HashMap<String,Object> hm = new HashMap<>();
        hm.put("dCardId",dCardId);
        hm.put("arrangeTimeName",arrangeTimeName);
        return arrangeTimeMapper.selectAllBydCardId(hm);
    }
}

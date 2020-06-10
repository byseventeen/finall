package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IArrangeTimeService;
import com.seventeen.hospital.beans.ArrangeTime;
import com.seventeen.hospital.mapper.ArrangeTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
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
    //增加预约 修改号源 剩余号源-1 总号源不变
    @Override
    public String updateArrangementTime(Integer arrangeTimeId) {
        return String.valueOf(arrangeTimeMapper.updateArrangementTime(arrangeTimeId));
    }

    /**
     * 根据医生证件号码 预约日期  预约时段开始时间查找timelist
     */
    @Override
    public List<ArrangeTime> selectAllByCIdDate(int dCardId, Date dDate, String time) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("dCardId",dCardId);
        hm.put("dDate",dDate);
        hm.put("time",time);
        return arrangeTimeMapper.selectAllByCIdDate(hm);
    }
}

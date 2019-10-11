package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IArrangementService;
import com.seventeen.hospital.beans.Arrangement;
import com.seventeen.hospital.mapper.ArrangementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service(value = "arrangementService")
public class ArrangementServiceImpl extends CommonServiceImpl<Arrangement> implements IArrangementService {

    @Autowired
    protected ArrangementMapper arrangementMapper;

    @Override
    protected Mapper getMapper() {
        return this.arrangementMapper;
    }

    @Override
    public List<Arrangement> findArrangementbycIddDate(Integer did, String sDate, String dDate) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("did",did);
        hm.put("sDate",sDate);
        hm.put("dDate",dDate);
        return arrangementMapper.findArrangementbycIddDate(hm);
    }

    @Override
    public List<Arrangement> findArrangementbyIdOneDate(Integer did, String Date) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("did",did);
        hm.put("Date",Date);
        return arrangementMapper.findArrangementbyIdOneDate(hm);
    }

    @Override
    public String updateArrangement(Integer did, String arrangeDate, String arrangeName) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("did",did);
        hm.put("arrangeDate",arrangeDate);
        hm.put("arrangeName",arrangeName);
        return String.valueOf(arrangementMapper.updateArrangement(hm));
    }

    @Override
    public String deleteArrangement(Integer did, String arrangeDate, String arrangeName) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("did",did);
        hm.put("arrangeDate",arrangeDate);
        hm.put("arrangeName",arrangeName);
        return String.valueOf(arrangementMapper.deleteArrangement(hm));
    }
}

package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.Arrangement;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IArrangementService extends ICommonService<Arrangement> {
    //根据医生编号，开始时间和结束时间找到医生的基础排班表
    List<Arrangement> findArrangementbycIddDate(Integer did, String sDate, String dDate);

    List<Arrangement> findArrangementbyIdOneDate(Integer did, String Date);

    String updateArrangement(Integer did, String arrangeDate, String arrangeName);

    String deleteArrangement(Integer did, String arrangeDate, String arrangeName);
}

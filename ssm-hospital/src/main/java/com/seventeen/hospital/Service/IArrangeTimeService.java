package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.ArrangeTime;

import java.util.Date;
import java.util.List;

public interface IArrangeTimeService extends ICommonService<ArrangeTime> {
    List<ArrangeTime> selectAllBydCardId(int dCardId,String arrangeTimeName);

    //增加预约 修改号源 剩余号源-1 总号源不变
    String updateArrangementTime(Integer arrangeTimeId);

    List<ArrangeTime> selectAllByCIdDate(int dCardId,Date dDate,String time);


}

package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.ArrangeTime;

import java.util.List;

public interface IArrangeTimeService extends ICommonService<ArrangeTime> {
    List<ArrangeTime> selectAllBydCardId(int dCardId,String arrangeTimeName);
}

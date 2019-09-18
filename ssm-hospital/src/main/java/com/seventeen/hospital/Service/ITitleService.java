package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.Title;

import java.util.List;

public interface ITitleService extends ICommonService<Title> {
    List<Title> findTitleById(int titleid);
}

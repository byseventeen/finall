package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.ArrangeTime;
import com.seventeen.hospital.beans.Book;

import java.util.List;

public interface IBookService extends ICommonService<Book>{
    //查询所有用户所有预约信息
    List<Book> selectAllAppointment();

    //根据用户id查询该用户的所有预约记录
    List<Book> selectAllByUserId(int userId);

    //根据医生id查询该用户的所有预约记录
    List<Book> selectAllByDId(int doctorId);

    //根据医生id arrangeId 和timeId查找预约记录
    List<Book> selectAllByDIdDate(int timeId);


}


package com.seventeen.hospital.Service;

import com.seventeen.hospital.beans.ArrangeTime;
import com.seventeen.hospital.beans.Book;

import java.util.List;

public interface IBookService extends ICommonService<Book>{
    List<Book> selectAllByUserId(int userId);
}

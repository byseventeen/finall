package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IBookService;
import com.seventeen.hospital.beans.Book;
import com.seventeen.hospital.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service(value="bookService")
public class BookServiceImpl extends CommonServiceImpl<Book> implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    protected Mapper getMapper() {
        return this.bookMapper;
    }

    @Override
    public List<Book> selectAllByUserId(int userId) {
        return bookMapper.selectAllByUserId(userId);
    }
}

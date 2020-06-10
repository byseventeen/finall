package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.IBookService;
import com.seventeen.hospital.beans.Book;
import com.seventeen.hospital.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

@Service(value="bookService")
public class BookServiceImpl extends CommonServiceImpl<Book> implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    protected Mapper getMapper() {
        return this.bookMapper;
    }

    //根据用户id查询该用户的所有预约记录
    @Override
    public List<Book> selectAllByUserId(int userId) {
        return bookMapper.selectAllByUserId(userId);
    }

    //根据医生id查询该用户的所有预约记录
    @Override
    public List<Book> selectAllByDId(int doctorId) {
        return bookMapper.selectAllByDId(doctorId);
    }

    /**
     * //根据医生id arrangeId 和timeId查找预约记录
     * @param dId
     * @param arrangeId
     * @param timeId
     * @return
     */
    @Override
    public List<Book> selectAllByDIdDate(int timeId) {
        HashMap<String,Object> hm = new HashMap<>();
        hm.put("timeId",timeId);
        return bookMapper.selectAllByDIdDate(hm);
    }

    //查询所有用户所有预约信息
    @Override
    public List<Book> selectAllAppointment() {
        return bookMapper.selectAllAppointment();
    }
}

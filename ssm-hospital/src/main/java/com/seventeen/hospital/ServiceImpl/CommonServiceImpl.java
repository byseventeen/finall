package com.seventeen.hospital.ServiceImpl;

import com.seventeen.hospital.Service.ICommonService;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public abstract class CommonServiceImpl<T> implements ICommonService<T> {

    protected abstract Mapper getMapper();

    @Override
    public List<T> findAll() {
        return getMapper().selectAll();
    }

    public List<T> find(T t){return getMapper().select(t);}


    @Override
    public T findById(int id) {
        return (T) getMapper().selectOne(id);
    }

    @Override
    public void add(Object t) {
        getMapper().insert(t);
    }

    @Override
    public void update(Object t) {
        getMapper().updateByPrimaryKeySelective(t);
    }

    @Override
    public void delete(int id) {
        getMapper().deleteByPrimaryKey(id);
    }
}

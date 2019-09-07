package com.seventeen.hospital.Service;

import java.util.List;

public interface ICommonService<T> {
    /**
     * 查询所有
     * @return
     */
    List<T> findAll();

    /**
     * 条件查询
     * @param t
     * @return
     */
    List<T> find(T t);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T findById(int id);

    /**
     * 添加
     * @param t
     */
    void add(T t);

    /**
     * 修改
     * @param t
     */
    void update(T t);

    /**
     * 删除
     * @param id
     */
    void delete(int id);

}

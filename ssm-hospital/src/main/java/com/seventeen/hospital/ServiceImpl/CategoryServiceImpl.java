package com.seventeen.hospital.ServiceImpl;


import com.seventeen.hospital.Service.ICategoryService;
import com.seventeen.hospital.beans.Category;
import com.seventeen.hospital.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl extends CommonServiceImpl<Category> implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    protected Mapper getMapper() {
        return this.categoryMapper;
    }


   /* @Override
    public void insertCategory(Category category) {

       // categoryMapper.insertCategory(category);
    }

    @Override
    public void deleteCategory(Category category) {
      //  categoryMapper.deleteCategory(category);
    }

    @Override
    public List<Category> findCategory() {
       // return categoryMapper.findCategory();
        List list=categoryMapper.selectAll();
        return list;
    }

    @Override
    public void updateCategory(Category category) {
       // categoryMapper.updateCategory(category);
    }*/
}

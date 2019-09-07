package com.seventeen.hospital.mapper;

import com.seventeen.hospital.beans.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CategoryMapper extends Mapper<Category> {
   /* @Insert("insert into category (cname) values(#{cname})")
    void insertCategory(Category category);

    @Delete("delete from category where cname = #{cname}")
    void deleteCategory(Category category);

    @Select("select * from category")
    List<Category> findCategory();

    @Update("update category set cname = #{cname} where id = #{cid}")
    void updateCategory(Category category);*/
}

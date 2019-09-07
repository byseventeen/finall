package com.seventeen.hospital.Controller;


import com.seventeen.hospital.Service.ICategoryService;
import com.seventeen.hospital.beans.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CategoryController {
    @Resource(name = "categoryService")
    private ICategoryService categoryService;

    @RequestMapping(path="/addCategory.action",produces = {"text/html;charset=utf-8"})
    public String addCategory(){
        Category category=new Category();
        category.setCname("喵星人");
        categoryService.add(category);
        return "aaa";
    }

    @RequestMapping(path="/deleteCategory.action",produces = {"text/html;charset=utf-8"})
    public String deleteCategory(){
        categoryService.delete(9);
        return "aaa";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path="/findCategory.action")
    public List<Category> findCategory(){
        List<Category> findList= categoryService.findAll();
        for (Category category: findList) {
            System.out.println(category);
        }
        //model.addAttribute("list",findList);
        return findList;
        //return "finres";
    }




    @RequestMapping(path="/updateCategory.action",produces = {"text/html;charset=utf-8"})
    public String updateCategory(){
        Category category=new Category();
        category.setCname("肥宅");
        category.setCid(1);
        categoryService.update(category);
        return "aaa";
    }


}

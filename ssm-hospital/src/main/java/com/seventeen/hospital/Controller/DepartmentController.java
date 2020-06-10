package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IDepartmentService;
import com.seventeen.hospital.beans.Department;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/department")
public class DepartmentController {

    @Resource(name = "departmentService")
    private IDepartmentService departmentService;

    //查找门诊表里的所有的门诊
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllDepartment.action")
    public List<Department> findAll(){
        List<Department> findlist=departmentService.findAll();
        for (Department t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

    //根据科室id查找该科室下的所有门诊信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneDepartment.action",method= RequestMethod.POST)
    public List<Department> findOneDepartment(HttpServletRequest request){
        int x= Integer.parseInt(request.getParameter("dptypeid"));
        Department department=new Department();
        department.setDtypeid(x);
        List<Department> findlist=departmentService.find(department);
        for (Department t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

    //添加门诊
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addDepartment.action")
    public String addDepartment(){
        Department department=new Department();
        department.setDepartmentname("内科");
        departmentService.add(department);
        return "fine!";
    }

    //根据id更改某个门诊的信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/updateDepartment.action")
    public String updateDepartment(){
        Department department=new Department();
        department.setDtypeid(2);
        department.setDepartmentname("骨科");
        departmentService.update(department);
        return "fine!";
    }

    //根据id删除某个门诊
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteDepartmen.action")
    public String deleteDepartmenttype(){
        departmentService.delete(2);
        return "fine!";
    }

    //多对一。门诊多，门诊类型一，多对一，查询所有门诊，并查出科室的所有信息
    @ResponseBody
    @RequestMapping("/find.action")
    public List<Department> findAllDepartment(){
        List<Department> departmentList = departmentService.findAllDepartment();
        System.out.println(departmentList);
        return departmentList;
    }

    //多对一。门诊多，科室一，传一个门诊id过来，根据id找到门诊和科室信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findDepartmentById.action")
    public List<Department> findDepartmentById(int departmentid){
        List<Department> findlist=departmentService.findDepartmentById(departmentid);
        return findlist;
    }

    //根据门诊id查找所有该门诊下的信息 包括医生信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path="/findDepartmentsById.action",method= RequestMethod.POST)
    public List findAllDepartments(HttpServletRequest request) {
        int departmentId= Integer.parseInt(request.getParameter("departmentId"));
        List<Department> departmentList=departmentService.findDepartmentsById(departmentId);
        System.out.println(departmentList);
        return departmentList;
    }


}

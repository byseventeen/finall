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
import java.util.List;

@Controller
@RequestMapping(path="/department")
public class DepartmentController {

    @Resource(name = "departmentService")
    private IDepartmentService departmentService;

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

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addDepartment.action")
    public String addDepartment(){
        Department department=new Department();
        department.setDepartmentname("内科");
        departmentService.add(department);
        return "fine!";
    }

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

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteDepartmenttype.action")
    public String deleteDepartmenttype(){
        departmentService.delete(2);
        return "fine!";
    }
}

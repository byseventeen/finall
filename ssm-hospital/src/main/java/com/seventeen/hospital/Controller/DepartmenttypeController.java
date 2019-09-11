package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IDepartmenttypeService;
import com.seventeen.hospital.beans.Departmenttype;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(path="/departmenttype")
public class DepartmenttypeController {

    @Resource(name = "departmenttypeService")
    private IDepartmenttypeService departmenttypeService;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllDepartmenttype.action")
    public List<Departmenttype> findAll(){
        List<Departmenttype> findlist=departmenttypeService.findAll();
        for (Departmenttype t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneDepartmenttype.action")
    public List<Departmenttype> findOneDepartmenttyper(){
        Departmenttype departmenttype=new Departmenttype();
        departmenttype.setDepartmentname("内科");
        List<Departmenttype> findlist=departmenttypeService.find(departmenttype);
        for (Departmenttype t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addDepartmenttype.action")
    public String addDepartmenttype(){
        Departmenttype departmenttype=new Departmenttype();
        departmenttype.setDepartmentname("内科");
        departmenttypeService.add(departmenttype);
        return "fine!";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/updateDepartmenttype.action")
    public String updateDepartmenttype(){
        Departmenttype departmenttype=new Departmenttype();
        departmenttype.setDepartmenttypeid(2);
        departmenttype.setDepartmentname("骨科");
        departmenttypeService.update(departmenttype);
        return "fine!";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteDepartmenttype.action")
    public String deleteDepartmenttype(){
        departmenttypeService.delete(2);
        return "fine!";
    }
}

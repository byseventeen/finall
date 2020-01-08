package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IDepartmenttypeService;
import com.seventeen.hospital.beans.Department;
import com.seventeen.hospital.beans.Departmenttype;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/departmenttype")
public class DepartmenttypeController {

    @Resource(name = "departmenttypeService")
    private IDepartmenttypeService departmenttypeService;

    //查找depaertmenttype下的所有数据
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllDepartmenttype.action")
    public List<Departmenttype> findAll(){
        List<Departmenttype> findlist=departmenttypeService.findAll();
        return findlist;
    }

    //根据设置门诊类型的任意属性查询某门诊类型信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneDepartmenttype.action")
    public List<Departmenttype> findOneDepartmenttyper(){
        Departmenttype departmenttype=new Departmenttype();
        departmenttype.setDepartypementname("内科");
        List<Departmenttype> findlist=departmenttypeService.find(departmenttype);
        for (Departmenttype t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

    //添加门诊类型
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addDepartmenttype.action")
    public String addDepartmenttype(){
        Departmenttype departmenttype=new Departmenttype();
        departmenttype.setDepartypementname("内科");
        departmenttypeService.add(departmenttype);
        return "fine!";
    }

    //更新门诊类型
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/updateDepartmenttype.action")
    public String updateDepartmenttype(){
        Departmenttype departmenttype=new Departmenttype();
        departmenttype.setDepartmenttypeid(2);
        departmenttype.setDepartypementname("骨科");
        departmenttypeService.update(departmenttype);
        return "fine!";
    }

    //删除门诊类型
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteDepartmenttype.action")
    public String deleteDepartmenttype(){
        departmenttypeService.delete(2);
        return "fine!";
    }

    //根据门诊类型查找所有门诊,菜单处查找所有的门诊类型以及门诊的信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping("/findDepartmentByDetypeId.action")
    public List findAllDepartment() {

        List secondFloorList=new ArrayList();
        int a;
        List<Departmenttype> findlist = findAll();  //查找门诊类型的所有信息

        for (int i = 0; i < findlist.size(); i++) {
            a = findlist.get(i).getDepartmenttypeid();
            //根据门诊类型id查找该类型下所有门诊信息
            List<Departmenttype> tempDepartmenttypeList=departmenttypeService.findDepartmentBytypeId(a);

            Map thirdFloorMap=new HashMap();
            thirdFloorMap.put("name","");
            thirdFloorMap.put("list","");
            thirdFloorMap.put("show",false);
            secondFloorList.add(thirdFloorMap);

            List fourthList=new ArrayList();


            for (Departmenttype d:tempDepartmenttypeList) {
                for (int j = 0; j < d.getDepartments().size(); j++) {
                    Map fifthMap=new HashMap();
                    fifthMap.put("departmentName",d.getDepartments().get(j).getDepartmentname());
                    fifthMap.put("departmentId",d.getDepartments().get(j).getDepartmentid());
                    fourthList.add(fifthMap);
                }
            }
            thirdFloorMap.put("list",fourthList);
            thirdFloorMap.put("name",findlist.get(i).getDepartypementname());

        }
        return secondFloorList;
    }
}

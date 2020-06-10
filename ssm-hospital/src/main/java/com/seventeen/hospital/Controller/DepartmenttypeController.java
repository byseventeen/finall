package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IArrangeTimeService;
import com.seventeen.hospital.Service.IBookService;
import com.seventeen.hospital.Service.IDepartmentService;
import com.seventeen.hospital.Service.IDepartmenttypeService;
import com.seventeen.hospital.beans.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path="/departmenttype")
public class DepartmenttypeController {

    @Resource(name = "departmenttypeService")
    private IDepartmenttypeService departmenttypeService;

    @Resource(name = "departmentService")
    private IDepartmentService departmentService;

    @Resource(name = "arrangeTimeService")
    private IArrangeTimeService arrangeTimeService;

    @Resource(name = "bookService")
    private IBookService bookService;

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

    //根据门诊类型id查找所有门诊,查找所有的门诊类型以及门诊下医生
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path="/findAllDepartmentById.action",method= RequestMethod.POST)
    public Map<String,Object> findAllDepartmentById(HttpServletRequest request) throws ParseException {
        System.out.println("根据门诊类型id查找所有门诊,查找所有的门诊类型以及门诊下医生,method= RequestMethod.POST");
        Map<String,Object> toMap=new HashMap<>();
        List dIdList=new ArrayList();
        List dCardIdList=new ArrayList();
        List timeArrayList=new ArrayList();

       // Integer departmenttypeId= Integer.valueOf(request.getParameter("departmenttype"));
       // String startTime= Integer.valueOf(request.getParameter("time"));
       // String dateStr= Integer.valueOf(request.getParameter("dDate"));
        Integer departmenttypeId=1;
        String startTime="08:00";
        String dateStr="2020-04-23";
        SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd");
        Date dDate = simpleDateFormatt.parse(dateStr);

        //查找科室下所有门诊以及门诊下医生id  号源时段表的id
        List<Departmenttype> findlist = departmenttypeService.findDepartmentBytypeId(departmenttypeId);
        List<Department> depList=findlist.get(0).getDepartments();
        for(int i=0;i<depList.size();i++){
            Integer departmentId=depList.get(i).getDepartmentid();
            List<Department> departmentList=departmentService.findDepartmentsById(departmentId);
            List<Doctor> doctorList= departmentList.get(0).getDoctors();
            for (int j=0;j<doctorList.size();j++){
                Integer dId=doctorList.get(j).getDoctorid();
                Integer dCardId= Integer.valueOf(doctorList.get(j).getCardId());
                dIdList.add(dId);
                dCardIdList.add(dCardId);
            }
        }
        //根据cardId  dDate  和时段开始时间找到对应的timeId
        for(int i=0;i<dCardIdList.size();i++){
            Integer dCardId= (Integer) dCardIdList.get(i);
            List<ArrangeTime> timeList=arrangeTimeService.selectAllByCIdDate(dCardId,dDate,startTime);
            for(int j=0;j<timeList.size();j++){
                Integer timeId=timeList.get(j).getId();
                timeArrayList.add(timeId);
            }
        }
        List<Book> bookList=new ArrayList<>();
        for(int i=0;i<dIdList.size();i++){
            Integer dId= (Integer) dIdList.get(i);
            Integer timeId=1;
            for (int j=0;j<timeArrayList.size();j++){
                 timeId= (Integer) timeArrayList.get(j);
                bookList=bookService.selectAllByDIdDate(timeId);
            }
        }
        List secondFloorList=new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 设置日期格式
        if(bookList.size()>1){
            for(int i=0;i<bookList.size();i++){
                Integer departmentId=bookList.get(i).getDoctors().get(0).getDepartmentId();
                List<Department> departmentList=departmentService.findDepartmentById(departmentId);
                Integer departTypeId=departmentList.get(0).getDepartment_type().getDepartmenttypeid();
                List<Departmenttype> departTypeList=departmenttypeService.findDepartmentBytypeId(departTypeId);
                //获取日期与处理日期
                Date date=bookList.get(i).getArrangements().get(0).getArrangeDate();
                String arrangeDate = simpleDateFormat.format(date);

                Map thirdFloorMap=new HashMap();
                thirdFloorMap.put("bookId",bookList.get(i).getId());
                thirdFloorMap.put("userName",bookList.get(i).getUsers().get(0).getUsername());
                thirdFloorMap.put("cardId",bookList.get(i).getUsers().get(0).getCardId());
                thirdFloorMap.put("gender",bookList.get(i).getUsers().get(0).getGender());
                thirdFloorMap.put("userPhone",bookList.get(i).getUsers().get(0).getPhone());
                thirdFloorMap.put("dName",bookList.get(i).getDoctors().get(0).getDname());
                thirdFloorMap.put("dPhone",bookList.get(i).getDoctors().get(0).getPhone());
                thirdFloorMap.put("title",bookList.get(i).getDoctors().get(0).getTitleId());
                thirdFloorMap.put("dDepartment",bookList.get(i).getDoctors().get(0).getDepartments().getDepartmentname());
                thirdFloorMap.put("dDepartTypeName",departTypeList.get(0).getDepartypementname());
                thirdFloorMap.put("arrangementName",bookList.get(i).getArrangements().get(0).getArrangeName());
                thirdFloorMap.put("arrangementDate",arrangeDate);
                thirdFloorMap.put("numAmount",bookList.get(i).getTimes().get(0).getNumAmount());
                thirdFloorMap.put("numSurplus",bookList.get(i).getTimes().get(0).getNumSurplus());
                thirdFloorMap.put("startTime",bookList.get(i).getTimes().get(0).getStartTime());
                thirdFloorMap.put("stopTime",bookList.get(i).getTimes().get(0).getStopTime());
                thirdFloorMap.put("Time",bookList.get(i).getTimes().get(0).getStartTime()+"-"+bookList.get(i).getTimes().get(0).getStopTime());
                thirdFloorMap.put("bookState",bookList.get(0).getBookState());
                secondFloorList.add(thirdFloorMap);
            }
            toMap.put("data",secondFloorList);
            toMap.put("code","0000");
            toMap.put("msg","查询成功！");
        }else{
            toMap.put("data",null);
            toMap.put("code","0001");
            toMap.put("msg","查询失败！");
        }



        return toMap;
    }
}

package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IArrangementService;
import com.seventeen.hospital.Service.IDepartmentService;
import com.seventeen.hospital.Service.IDoctorService;
import com.seventeen.hospital.beans.Arrangement;
import com.seventeen.hospital.beans.Department;
import com.seventeen.hospital.beans.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path="/arrangement")
public class ArrangementController {

    @Resource(name = "arrangementService")
    private IArrangementService arrangementService;

    @Resource(name="doctorService")
    private IDoctorService doctorService;

    @Resource(name = "departmentService")
    private IDepartmentService departmentService;

    //查找排班表里的所有的排班
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllArrangement.action")
    public List<Arrangement> findAll(){
        List<Arrangement> findlist=arrangementService.findAll();
        for (int i=0;i<findlist.size();i++){
            Date date=findlist.get(i).getArrangeDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 设置日期格式
            String strTime = simpleDateFormat.format(date);  // 格式转换
            System.out.println(strTime);
        }
        return findlist;
    }

    //根据医生card_id 查找该医生的所有排班信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findArrangementbyCId.action",method = RequestMethod.POST)
    public Map findArrangementbyCId(HttpServletRequest request) throws ParseException {
        String cardId= request.getParameter("cardId");
        String checkedDate=request.getParameter("dDate");
        //根据cardId锁定某个医生并且拿到信息
        Doctor doctor=new Doctor();
        doctor.setCardId(cardId);
        List<Doctor> dlist=doctorService.find(doctor);
        Integer did=dlist.get(0).getDoctorid();
        String dname=dlist.get(0).getDname();
        int departmentId=dlist.get(0).getDepartmentId();

        //处理date  string转成date格式
        SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormatt.parse(checkedDate);
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DAY_OF_MONTH, 7);
        Date t=c.getTime();
        String stopDate = simpleDateFormatt.format(t);
        //根据did 和dDate找到医生排班信息
        List<Arrangement> findlist=arrangementService.findArrangementbycIddDate(did,checkedDate,stopDate);
        Map TopFloorMap=new HashMap();
        TopFloorMap.put("departmentName","内科");
        TopFloorMap.put("doctorName",dname);
        TopFloorMap.put("doctorCardId",cardId);
        TopFloorMap.put("startDate",checkedDate);
        List secondList=new ArrayList();
        for (int i=0;i<findlist.size();i++){
            Date date=findlist.get(i).getArrangeDate();
            //日期格式转换为普通格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 设置日期格式
            String strTime = simpleDateFormat.format(date);
            Map<String,Object> secondFloorMap=new HashMap();
            secondFloorMap.put("arrangeStrTime",strTime);
            secondFloorMap.put("arrangeName",findlist.get(i).getArrangeName());
            secondList.add(secondFloorMap);
        }
        TopFloorMap.put("list",secondList);
        return TopFloorMap;
    }

    //添加医生排班
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addArrangement.action",method= RequestMethod.POST )
    public Map addDoctorArrange(HttpServletRequest request) throws ParseException {
        String cardId= request.getParameter("cardId");
        String arrangeTime= request.getParameter("arrangeTime");
        String[] array=request.getParameterValues("arrangeDate[]");

        //根据cardId锁定某个医生并且拿到信息
        Doctor doctor=new Doctor();
        doctor.setCardId(cardId);
        List<Doctor> dlist=doctorService.find(doctor);
        Integer did=dlist.get(0).getDoctorid();
        String dname=dlist.get(0).getDname();
        Integer departmentId=dlist.get(0).getDepartmentId();
        //根据departmentId找到门诊名称
        Department department1=new Department();
        department1.setDepartmentid(departmentId);
        List<Department> departmentList=departmentService.find(department1);
        String departmentName=departmentList.get(0).getDepartmentname();

        for (int i=0;i<array.length;i++){
            Arrangement arrangement=new Arrangement();
            arrangement.setDepartmentId(departmentId);
            arrangement.setDoctorId(did);
            arrangement.setArrangeName(arrangeTime);
            String dateString = array[i];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(dateString);
            arrangement.setArrangeDate(date);
            arrangementService.add(arrangement);
        }
        Map TopFloorMap=new HashMap();
        TopFloorMap.put("departmentName",departmentName);
        TopFloorMap.put("doctorName",dname);

        return TopFloorMap;
    }

    //修改医生排班
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/modifyArrangement.action",method= RequestMethod.POST )
    public Map  modifyDoctorArrange(HttpServletRequest request) throws ParseException {
        System.out.println("2222");
        String cardId= request.getParameter("cardId");
        String arrangeTime= request.getParameter("arrangeTime");
        String[] array=request.getParameterValues("arrangeDate[]");

        //根据cardId锁定某个医生并且拿到信息
        Doctor doctor=new Doctor();
        doctor.setCardId(cardId);
        List<Doctor> dlist=doctorService.find(doctor);
        Integer did=dlist.get(0).getDoctorid();
        String dname=dlist.get(0).getDname();
        Integer departmentId=dlist.get(0).getDepartmentId();
        //根据departmentId找到门诊名称
        Department department1=new Department();
        department1.setDepartmentid(departmentId);
        List<Department> departmentList=departmentService.find(department1);
        String departmentName=departmentList.get(0).getDepartmentname();
        String msg ;

        Map<String,Object> TopFloorMap=new HashMap();
        //找出该日期的所有arrangement排班 判断是否有冲突
        for (int j=0;j<array.length;j++){
            String ddString = array[j];
            System.out.println(ddString);
            List<Arrangement> arlist=arrangementService.findArrangementbyIdOneDate(did,ddString);

            for (Arrangement a:arlist) {
                System.out.println(a.getArrangeName()+arrangeTime);
                if (a.getArrangeName().equals(arrangeTime)){
                    TopFloorMap.put("msg",ddString+arrangeTime+"值班已存在，不允许修改！");
                    System.out.println(1111);
                    return TopFloorMap;
                }
                else{
                    arrangementService.updateArrangement(did,ddString,arrangeTime);
                    msg="修改成功";
                    TopFloorMap.put("msg",msg);
                    System.out.println(TopFloorMap);
                }
            }
        }
        return TopFloorMap;
    }

    //删除医生排班
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteDoctorArrange.action",method= RequestMethod.POST )
    public Map  deleteDoctorArrange(HttpServletRequest request) throws ParseException {
        System.out.println("2222");
        String cardId= request.getParameter("cardId");
        String arrangeTime= request.getParameter("arrangeTime");
        String[] array=request.getParameterValues("arrangeDate[]");

        //根据cardId锁定某个医生并且拿到信息
        Doctor doctor=new Doctor();
        doctor.setCardId(cardId);
        List<Doctor> dlist=doctorService.find(doctor);
        Integer did=dlist.get(0).getDoctorid();
        String dname=dlist.get(0).getDname();
        Integer departmentId=dlist.get(0).getDepartmentId();
        System.out.println(did+dname+departmentId);
        //根据departmentId找到门诊名称
        Department department1=new Department();
        department1.setDepartmentid(departmentId);
        List<Department> departmentList=departmentService.find(department1);
        String departmentName=departmentList.get(0).getDepartmentname();
        System.out.println(departmentName);

        Map TopFloorMap=new HashMap();
        //找出该日期的所有arrangement排班 判断是否存在
        for (int j=0;j<array.length;j++){
            String ddString = array[j];
            System.out.println(ddString);
            List<Arrangement> arlist=arrangementService.findArrangementbyIdOneDate(did,ddString);
            System.out.println(arlist);
            if (arlist.size()!=0){
                for (Arrangement a:arlist) {
                    System.out.println(a.getArrangeName()+arrangeTime);
                    if (a.getArrangeName().equals(arrangeTime)){
                        arrangementService.deleteArrangement(did,ddString,arrangeTime);
                        TopFloorMap.put("msg","删除成功");
                    }
                    else{
                        TopFloorMap.put("msg",ddString+arrangeTime+"值班不存在，不允许删除！");
                        return TopFloorMap;
                    }
                }
            }
            else{
                TopFloorMap.put("msg",ddString+"值班不存在，不允许删除！");
                return TopFloorMap;
            }
        }
        return TopFloorMap;
    }
}

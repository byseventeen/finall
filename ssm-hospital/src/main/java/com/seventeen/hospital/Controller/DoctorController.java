package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IDepartmentService;
import com.seventeen.hospital.Service.IDoctorService;
import com.seventeen.hospital.Service.ITitleService;
import com.seventeen.hospital.beans.Department;
import com.seventeen.hospital.beans.Doctor;
import com.seventeen.hospital.beans.Title;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path="/doctor")
public class DoctorController {
    @Resource(name = "doctorService")
    private IDoctorService doctorService;

    @Resource(name = "departmentService")
    private IDepartmentService departmentService;

    @Resource(name = "titleService")
    private ITitleService titleService;

    @Autowired
    DepartmentController departmentController;

    @Autowired
    TitleController titleController;

    //查询医生表里的所有医生信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllDoctor.action")
    public List findAll(){
        List findlist=doctorService.findAll();
        for (int i=0; i<findlist.size();i++){
            System.out.println(findlist.get(i));
        }
        return findlist;
    }

    //查询医生所有信息，包括门诊信息，科室信息和职称信息等
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllDoctors.action")
    public Map<String,Object> findAllDoctors(){
        List secondFloorList=new ArrayList();
        Map<String,Object> toMap=new HashMap<>();
        int a,b;
        String departmentname=null;
        String departypementname = null;
        String titlename= null;
        List<Doctor> findlist=doctorService.findAll();
        for (int i=0; i<findlist.size();i++){
            a=findlist.get(i).getDepartmentId();
            b=findlist.get(i).getTitleId();

            List<Title> tempmentlist=titleController.findDepartmentById(b);

            List<Department> tempDepartmentlist= departmentController.findDepartmentById(a);
            for (int j=0;j<tempDepartmentlist.size();j++){
                 departmentname=tempDepartmentlist.get(j).getDepartmentname();
                 departypementname=tempDepartmentlist.get(j).getDepartment_type().getDepartypementname();
                 titlename=tempmentlist.get(j).getTitlename();

                Map thirdFloorMap=new HashMap();
                thirdFloorMap.put("doctorid",findlist.get(i).getDoctorid());
                thirdFloorMap.put("dname",findlist.get(i).getDname());
                thirdFloorMap.put("gender",findlist.get(i).getGender());
                thirdFloorMap.put("cardId",findlist.get(i).getCardId());
                thirdFloorMap.put("phone",findlist.get(i).getPhone());
                thirdFloorMap.put("profile",findlist.get(i).getProfile());
                thirdFloorMap.put("departmentname",departmentname);
                thirdFloorMap.put("departypementname",departypementname);
                thirdFloorMap.put("titlename",titlename);
                secondFloorList.add(thirdFloorMap);
            }
            toMap.put("data",secondFloorList);
        }
        return toMap;
    }

    //通过id查询一个医生的所有信息，包括门诊信息，科室信息和职称信息等
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneDoctorById.action")
    public List<Doctor> findOneDoctorById(HttpServletRequest request){

        List secondFloorList=new ArrayList();
        Map<String,Object> toMap=new HashMap<>();
        int a,b;
        String departmentname=null;
        String departypementname = null;
        String titlename= null;
        List<Doctor> findlist=doctorService.findDoctorById(Integer.parseInt(request.getParameter("doctorid")));

        for (int i=0; i<findlist.size();i++){
            a=findlist.get(i).getDepartmentId();
            b=findlist.get(i).getTitleId();

            List<Title> tempmentlist=titleController.findDepartmentById(b);

            List<Department> tempDepartmentlist= departmentController.findDepartmentById(a);
            for (int j=0;j<tempDepartmentlist.size();j++){
                departmentname=tempDepartmentlist.get(j).getDepartmentname();
                departypementname=tempDepartmentlist.get(j).getDepartment_type().getDepartypementname();
                titlename=tempmentlist.get(j).getTitlename();

                Map thirdFloorMap=new HashMap();
                thirdFloorMap.put("doctorid",findlist.get(i).getDoctorid());
                thirdFloorMap.put("dname",findlist.get(i).getDname());
                thirdFloorMap.put("gender",findlist.get(i).getGender());
                thirdFloorMap.put("cardId",findlist.get(i).getCardId());
                thirdFloorMap.put("phone",findlist.get(i).getPhone());
                thirdFloorMap.put("profile",findlist.get(i).getProfile());
                thirdFloorMap.put("departmentname",departmentname);
                thirdFloorMap.put("departypementname",departypementname);
                thirdFloorMap.put("titlename",titlename);
                secondFloorList.add(thirdFloorMap);
            }
        }

        return secondFloorList;
    }

    //查询医生表里的某个医生的信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneDoctor.action")
    public List<Doctor> findOneDoctor(){
        Doctor doctor=new Doctor();
        doctor.setDname("仙女教母");
        List<Doctor> findlist=doctorService.find(doctor);
        for (Doctor t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

    //添加医生
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addDoctor.action",method= RequestMethod.POST )
    public String addDoctor(HttpServletRequest request){
        String departmentname=request.getParameter("departmentname");
        String titlename=request.getParameter("titlename");

        List<Department> departmentList=departmentService.findDepartmentByDname(departmentname);

        Title title=new Title();
        title.setTitlename(titlename);
        List<Title> tlist= titleService.find(title);
        int tid=tlist.get(0).getTid();

        Doctor doctor=new Doctor();
        doctor.setDname(request.getParameter("dname"));
        doctor.setGender(request.getParameter("gender"));
        doctor.setCardId(request.getParameter("cardId"));
        doctor.setPassword("123");
        doctor.setPhone(request.getParameter("phone"));
        doctor.setDepartmentId(departmentList.get(0).getDepartmentid());
        doctor.setTitleId(tid);
        doctor.setProfile(request.getParameter("profile"));
        doctorService.add(doctor);
        return "fine!";
    }

    //更新某个医生信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/updateDoctor.action",method= RequestMethod.POST)
    public String updateDoctor(HttpServletRequest request){
        System.out.println("调用controller....");
         String departmentname=request.getParameter("departmentname");
         String titlename=request.getParameter("title");

         Title title=new Title();
         title.setTitlename(titlename);
         List<Title> tlist= titleService.find(title);
         int tid=tlist.get(0).getTid();

        List<Department> departmentList=departmentService.findDepartmentByDname(departmentname);
        System.out.println(request.getParameter("did"));
        System.out.println(departmentList.get(0).getDepartmentid());

        Doctor doctor=new Doctor();
        doctor.setDoctorid(Integer.valueOf(request.getParameter("did")));
        doctor.setDname(request.getParameter("dname"));
        doctor.setGender(request.getParameter("gender"));
        doctor.setCardId(request.getParameter("cardId"));
        doctor.setPhone(request.getParameter("phone"));
        doctor.setDepartmentId(departmentList.get(0).getDepartmentid());
        doctor.setTitleId(tid);
        doctor.setProfile(request.getParameter("profile"));
        doctorService.update(doctor);
        return "fine!";
    }

    //删除某个医生
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteDoctor.action")
    public String deleteDoctor(HttpServletRequest request){
        doctorService.delete(Integer.parseInt(request.getParameter("doctorid")));
        return "fine!";
    }


    //医生登录的验证
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/dlogin.action",method= RequestMethod.POST)
    public Doctor login(HttpServletRequest request){
        Doctor doctor=new Doctor();
        doctor.setCardId(request.getParameter("inputusername"));
        doctor.setPassword(request.getParameter("inputpassword"));
        List<Doctor> loginMesg=doctorService.find(doctor);
        Doctor rtdoctor=new Doctor();
        rtdoctor.setDname(loginMesg.get(0).getDname());
        System.out.println(loginMesg.get(0));
        System.out.println(loginMesg.get(0).getDname());
        rtdoctor.setCardId(loginMesg.get(0).getCardId());
        if (loginMesg!=null){
            return rtdoctor;
        }
        else
            return null;
    }


}

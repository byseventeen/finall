package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IDoctorService;
import com.seventeen.hospital.beans.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path="/doctor")
public class DoctorController {
    @Resource(name = "doctorService")
    private IDoctorService doctorService;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllDoctor.action")
    public List<Doctor> findAll(){
        List<Doctor> findlist=doctorService.findAll();
        for (Doctor t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

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

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addDoctor.action")
    public String addDoctor(){
        Doctor doctor=new Doctor();
        doctor.setDname("kk女神");
        doctor.setGender("女");
        doctor.setCardId("123457");
        doctor.setPassword("123");
        doctor.setPhone("123");
        doctor.setDepartmentId(2);
        doctor.setTitleId(1);
        doctor.setProfile("kk is a beauty!");
        doctorService.add(doctor);
        return "fine!";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/updateDoctor.action")
    public String updateDoctor(){
        Doctor doctor=new Doctor();
        doctor.setDname("kk女神");
        doctor.setGender("女");
        doctor.setCardId("123457");
        doctor.setPassword("123");
        doctor.setPhone("1234");
        doctor.setDepartmentId(2);
        doctor.setTitleId(1);
        doctor.setProfile("kk is a beauty!");
        doctorService.update(doctor);
        return "fine!";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteDoctor.action")
    public String deleteDoctor(){
        doctorService.delete(2);
        return "fine!";
    }

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

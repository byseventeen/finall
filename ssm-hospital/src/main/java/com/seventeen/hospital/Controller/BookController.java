package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.*;
import com.seventeen.hospital.beans.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(path="/Book")
public class BookController {
    @Resource(name = "bookService")
    private IBookService bookService;

    @Resource(name = "userService")
    private IUserService userService ;

    @Resource(name = "departmentService")
    private IDepartmentService departmentService;

    @Resource(name = "departmenttypeService")
    private IDepartmenttypeService departmenttypeService;

    @Resource(name = "doctorService")
    private IDoctorService doctorService;

    @Resource(name = "arrangementService")
    private IArrangementService arrangementService;

    @Resource(name = "arrangeTimeService")
    private IArrangeTimeService arrangeTimeService;

    @Resource(name = "titleService")
    private ITitleService titleService;
    //查询所有用户的所有预约信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path="/selectAllAppointment.action")
    public Map<String,Object> selectAllAppointment(){
        System.out.println("//查询所有用户的所有预约信息");
        Map<String,Object> toMap=new HashMap<>();
        List secondFloorList=new ArrayList();
        List<Book> findlist=bookService.selectAllAppointment();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 设置日期格式
        for (int i=0; i<findlist.size();i++){
            List<User> userList=findlist.get(i).getUsers();
            List<Doctor> doctorList=findlist.get(i).getDoctors();
            List<Arrangement> arrangementList=findlist.get(i).getArrangements();
            List<ArrangeTime> timeList=findlist.get(i).getTimes();
            Integer titleId=doctorList.get(0).getTitleId();
            List<Title> titleList=titleService.findTitleById(titleId);
            Integer departmentId=doctorList.get(0).getDepartmentId();
            List<Department> departmentList=departmentService.findDepartmentById(departmentId);
            Integer departTypeId=departmentList.get(0).getDepartment_type().getDepartmenttypeid();
            List<Departmenttype> departTypeList=departmenttypeService.findDepartmentBytypeId(departTypeId);
            //日期格式转换为普通格式
            String arrangeDate = simpleDateFormat.format(arrangementList.get(0).getArrangeDate());
            Map thirdFloorMap=new HashMap();
            thirdFloorMap.put("bookId",findlist.get(i).getId());
            thirdFloorMap.put("userName",userList.get(0).getUsername());
            thirdFloorMap.put("cardId",userList.get(0).getCardId());
            thirdFloorMap.put("gender",userList.get(0).getGender());
            thirdFloorMap.put("userPhone",userList.get(0).getPhone());
            thirdFloorMap.put("dName",doctorList.get(0).getDname());
            thirdFloorMap.put("dPhone",doctorList.get(0).getPhone());
            thirdFloorMap.put("title",titleList.get(0).getTitlename());
            thirdFloorMap.put("dDepartment",doctorList.get(0).getDepartments().getDepartmentname());
            thirdFloorMap.put("dDepartTypeName",departTypeList.get(0).getDepartypementname());
            thirdFloorMap.put("arrangementName",arrangementList.get(0).getArrangeName());
            thirdFloorMap.put("arrangementDate",arrangeDate);
            thirdFloorMap.put("numAmount",timeList.get(0).getNumAmount());
            thirdFloorMap.put("numSurplus",timeList.get(0).getNumSurplus());
            thirdFloorMap.put("startTime",timeList.get(0).getStartTime());
            thirdFloorMap.put("stopTime",timeList.get(0).getStopTime());
            thirdFloorMap.put("Time",timeList.get(0).getStartTime()+"-"+timeList.get(0).getStopTime());
            thirdFloorMap.put("bookState",findlist.get(i).getBookState());
            secondFloorList.add(thirdFloorMap);
        }
        toMap.put("data",secondFloorList);
        toMap.put("recordsFiltered",secondFloorList.size());
        toMap.put("recordsTotal",secondFloorList.size());
        return toMap;
    }

    //根据用户id查询该用户id下的所有预约信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/selectAllByUserId.action",method= RequestMethod.POST)
    public Map<String,Object> selectAllBydCardId(HttpServletRequest request){
        System.out.println("根据用户id查找该用户所有预约,method= RequestMethod.POST");
        Integer userId= Integer.parseInt(request.getParameter("userId"));
        Map<String,Object> toMap=new HashMap<>();
        List secondFloorList=new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 设置日期格式
        List<Book> findList=bookService.selectAllByUserId(userId);
        for(int i=0;i<findList.size();i++){
            Map thirdFloorMap=new HashMap();
            List<User> userList=findList.get(i).getUsers();
            List<Doctor> doctorList=findList.get(i).getDoctors();
            List<Arrangement> arrangementList=findList.get(i).getArrangements();
            List<ArrangeTime> timeList=findList.get(i).getTimes();
            //日期格式转换为普通格式
            String arrangeDate = simpleDateFormat.format(arrangementList.get(0).getArrangeDate());

            thirdFloorMap.put("bookId",findList.get(i).getId());
            thirdFloorMap.put("userName",userList.get(0).getUsername());
           // thirdFloorMap.put("cardId",userList.get(0).getCardId());
           // thirdFloorMap.put("gender",userList.get(0).getGender());
           // thirdFloorMap.put("userPhone",userList.get(0).getPhone());
            thirdFloorMap.put("dName",doctorList.get(0).getDname());
          //  thirdFloorMap.put("dPhone",doctorList.get(0).getPhone());
            thirdFloorMap.put("dDepartment",doctorList.get(0).getDepartments().getDepartmentname());
          //  thirdFloorMap.put("arrangementName",arrangementList.get(0).getArrangeName());
            thirdFloorMap.put("arrangementDate",arrangeDate);
          //  thirdFloorMap.put("numAmount",timeList.get(0).getNumAmount());
           // thirdFloorMap.put("numSurplus",timeList.get(0).getNumSurplus());
            thirdFloorMap.put("startTime",timeList.get(0).getStartTime());
            thirdFloorMap.put("stopTime",timeList.get(0).getStopTime());
            thirdFloorMap.put("bookState",findList.get(i).getBookState());
            secondFloorList.add(thirdFloorMap);
        }
        toMap.put("data",secondFloorList);
        return toMap;
    }


    //根据医生id查询预约该医生的所有预约信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/selectAllByDId.action",method= RequestMethod.POST)
    public Map<String,Object> selectAllByDId(HttpServletRequest request){
        System.out.println("根据用户id查找该医生所有预约,method= RequestMethod.POST");
         Integer doctorId= Integer.parseInt(request.getParameter("doctorId"));
        //String arrangeTimeName=request.getParameter("bookTime");
        //int dCardId=123458;
        // String arrangeTimeName="上午";
        //System.out.println(arrangeTimeName);
        Map<String,Object> toMap=new HashMap<>();
        List secondFloorList=new ArrayList();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  // 设置日期格式
        List<Book> findList=bookService.selectAllByDId(doctorId);
        for(int i=0;i<findList.size();i++){
            Map thirdFloorMap=new HashMap();
            List<User> userList=findList.get(i).getUsers();
            List<Doctor> doctorList=findList.get(i).getDoctors();
            List<Arrangement> arrangementList=findList.get(i).getArrangements();
            List<ArrangeTime> timeList=findList.get(i).getTimes();
            //日期格式转换为普通格式
            String arrangeDate = simpleDateFormat.format(arrangementList.get(0).getArrangeDate());

            thirdFloorMap.put("bookId",findList.get(i).getId());
            thirdFloorMap.put("userName",userList.get(0).getUsername());
            thirdFloorMap.put("cardId",userList.get(0).getCardId());
            thirdFloorMap.put("gender",userList.get(0).getGender());
            thirdFloorMap.put("userPhone",userList.get(0).getPhone());
            thirdFloorMap.put("dName",doctorList.get(0).getDname());
            thirdFloorMap.put("dPhone",doctorList.get(0).getPhone());
            thirdFloorMap.put("dDepartment",doctorList.get(0).getDepartments().getDepartmentname());
            thirdFloorMap.put("arrangementName",arrangementList.get(0).getArrangeName());
            thirdFloorMap.put("arrangementDate",arrangeDate);
            thirdFloorMap.put("numAmount",timeList.get(0).getNumAmount());
            thirdFloorMap.put("numSurplus",timeList.get(0).getNumSurplus());
            thirdFloorMap.put("startTime",timeList.get(0).getStartTime());
            thirdFloorMap.put("stopTime",timeList.get(0).getStopTime());
            thirdFloorMap.put("bookState",findList.get(i).getBookState());
            secondFloorList.add(thirdFloorMap);
        }
        toMap.put("data",secondFloorList);
        return toMap;
    }


    //根据bookId删除预约
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteAppointment.action")
    public String deleteAppointment(HttpServletRequest request){
        bookService.delete(Integer.parseInt(request.getParameter("bookId")));
        return "fine!";
    }

    //根据bookId修改预约状态
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/updateAppointment.action",method= RequestMethod.POST)
    public String updateAppointment(HttpServletRequest request){
        System.out.println("修改用户预约状态");
        String State=request.getParameter("bookState");
        String bookState="";
        String end="end";
        String start="start";
        String breakCut="break";
        String cancel="cancel";
         Integer bookId=Integer.parseInt(request.getParameter("bookId"));
         if(State.equals(end)){
             bookState="已就诊";
         }
         if(State.equals(start)){
             bookState="未就诊";
         }
         if(State.equals(breakCut)){
             bookState="已爽约";
         }
         if(State.equals(cancel)){
             bookState="已取消";
         }
        Book book=new Book();
        book.setId(bookId);
        book.setBookState(bookState);
        bookService.update(book);
        return "fine!";
    }

    //添加预约信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addAppointment.action",method= RequestMethod.POST )
    public Map<String,Object> addAppointment(HttpServletRequest request) throws ParseException {
        System.out.println("添加预约");
        Map<String,Object> toMap=new HashMap<>();
        String userId=request.getParameter("userCardId");
        String departmentName=request.getParameter("departmentName");
        String doctorName=request.getParameter("doctorName");
        String Arrangedate=request.getParameter("Arrangedate");
        String arrangelott=request.getParameter("Arrangelot");
        String startTime=request.getParameter("stratTime");
        String stopTime=request.getParameter("stopTime");
        String bookState="未就诊";
        SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd");
        Date arrangeDate = simpleDateFormatt.parse(Arrangedate);
        String arrangelot="";
        if(arrangelott.equals("morning")){
            arrangelot="上午";
        }
        if(arrangelott.equals("afternoon")){
            arrangelot="下午";
        }
        //根据用户身份证号码获取用户信息
        User user=new User();
        user.setCardId(userId);
        List<User> userList=userService.find(user);
        //根门诊名字获取门诊id
        List<Department> departmentList=departmentService.findDepartmentByDname(departmentName);
        Integer departmentId =departmentList.get(0).getDepartmentid();
        //根据医生名字和门诊id获取医生id和医生工卡号
        Doctor doctor=new Doctor();
        doctor.setDname(doctorName);
        doctor.setDepartmentId(departmentId);
        List<Doctor> doctorList=doctorService.find(doctor);
        Integer doctorId=doctorList.get(0).getDoctorid();
        String dCardId=doctorList.get(0).getCardId();
        //根据门诊id  医生id 预约日期和上下午获取arrangeId
        Arrangement arrangement=new Arrangement();
        arrangement.setDoctorId(doctorId);
        arrangement.setDepartmentId(departmentId);
        arrangement.setArrangeDate(arrangeDate);
        arrangement.setArrangeName(arrangelot);
        List<Arrangement> arrangementList=arrangementService.find(arrangement);
        Integer arrangementId=arrangementList.get(0).getId();
        //根据医生id 预约日期 和预约时段获取timeId
        ArrangeTime arrangeTime=new ArrangeTime();
        arrangeTime.setDCardId(Integer.parseInt(dCardId));
        arrangeTime.setArrangeDate(arrangeDate);
        arrangeTime.setStartTime(startTime);
        arrangeTime.setStopTime(stopTime);
        List<ArrangeTime> arrangeTimeList=arrangeTimeService.find(arrangeTime);

        Integer arrangeTimeId=arrangeTimeList.get(0).getId();
        Integer surplusNum=arrangeTimeList.get(0).getNumSurplus();
        if(surplusNum>0){
            Book Appointment=new Book();
            Appointment.setUserId(userList.get(0).getUserid());
            Appointment.setDoctorId(doctorId);
            Appointment.setArrangeId(arrangementId);
            Appointment.setTimeId(arrangeTimeId);
            Appointment.setBookState(bookState);
            //判断此人是否一天预约超过两次  同一时段是否还有其他预约
            Integer arrangeSum=0;
            List<Book> personBookList=bookService.selectAllByUserId(userList.get(0).getUserid());
           for(int i=0;i<personBookList.size();i++){
               Date arrangedate=personBookList.get(i).getArrangements().get(0).getArrangeDate();
               String time=personBookList.get(i).getTimes().get(0).getStartTime();
               if(arrangedate.equals(arrangeDate)){   //判断是否为同一天
                   arrangeSum=arrangeSum+1;
                  if(time.equals(startTime)){  //判断是同一时段  如果是跳出循环
                      toMap.put("code","0001");
                      toMap.put("data","null");
                      toMap.put("msg","预约失败，重复预约！");
                      break;
                  }else{
                      if(i==personBookList.size()-1){  //判断是否到了循环末尾
                          if(arrangeSum<2){  //是否已经预约次数小于2  是 则预约成功
                              bookService.add(Appointment);
                              arrangeTimeService.updateArrangementTime(arrangeTimeId);
                              toMap.put("code","0000");
                              toMap.put("data","null");
                              toMap.put("msg","预约成功");
                          }else{
                              toMap.put("code","0001");
                              toMap.put("data","null");
                              toMap.put("msg","预约失败，超过当天可预约次数!");
                          }
                      }
                  }
               }else{   //不是同一天时
                   if(i==personBookList.size()-1){  //判断是否处于循环末尾
                       bookService.add(Appointment);
                       arrangeTimeService.updateArrangementTime(arrangeTimeId);
                       toMap.put("code","0000");
                       toMap.put("data","null");
                       toMap.put("msg","预约成功");
                   }
               }
            }
        }else{  //号源不足
            toMap.put("code","0001");
            toMap.put("data","null");
            toMap.put("msg","预约失败");
        }
        return toMap;

    }


    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/seleAppointment.action")
    public List<ArrangeTime> seleAppointment(HttpServletRequest request) throws ParseException {
        //根据医生id 预约日期 和预约时段获取timeId
        String Arrangedate="2020-04-15";
        String startTime="08:00";
        String stopTime="09:00";
        SimpleDateFormat simpleDateFormatt = new SimpleDateFormat("yyyy-MM-dd");
        Date arrangeDate = simpleDateFormatt.parse(Arrangedate);
        ArrangeTime arrangeTime=new ArrangeTime();
        arrangeTime.setDCardId(123458);
        arrangeTime.setArrangeDate(arrangeDate);
        arrangeTime.setStartTime(startTime);
        arrangeTime.setStopTime(stopTime);
        System.out.println( "2;"+arrangeDate+"3;"+startTime+"4;"+stopTime);
        List<ArrangeTime> arrangeTimeList=arrangeTimeService.find(arrangeTime);
        System.out.println(arrangeTimeList);

        return arrangeTimeList;
    }
}

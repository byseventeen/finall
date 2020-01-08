package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IArrangeTimeService;
import com.seventeen.hospital.beans.ArrangeTime;
import com.seventeen.hospital.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path="/arrangeTime")
public class ArrangeTimeController {
    @Resource(name = "arrangeTimeService")
    private IArrangeTimeService arrangeTimeService;

    //根据医生card查找该医生的所有时间和号源
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/selectAllBydCardId.action",method= RequestMethod.POST)
    public List<ArrangeTime> selectAllBydCardId(HttpServletRequest request){
        System.out.println("根据医生card查找该医生的所有时间和号源,method= RequestMethod.POST");
        int dCardId= Integer.parseInt(request.getParameter("cardId"));
        String arrangeTimeName=request.getParameter("bookTime");
        //int dCardId=123458;
       // String arrangeTimeName="上午";
        System.out.println(arrangeTimeName);
        List<ArrangeTime> findList=arrangeTimeService.selectAllBydCardId(dCardId,arrangeTimeName);
        return findList;
    }
}

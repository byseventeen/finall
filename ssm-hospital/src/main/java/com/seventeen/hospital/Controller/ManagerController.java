package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IManagerService;
import com.seventeen.hospital.beans.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ManagerController {

    @Resource(name = "managerService")
    private IManagerService managerService;

    @CrossOrigin
    @RequestMapping(path = "/mlogin.action",method= RequestMethod.POST)
    public String login(HttpServletRequest request) {
        Manager manager=new Manager();
        String managerid=request.getParameter("managerid");
        String managerpwd=request.getParameter("managerpwd");

        if (managerid!=""&&managerpwd!=""){
            manager.setCardId(managerid);
            manager.setPassword(managerpwd);
        }
        List<Manager> loginMesg=managerService.find(manager);
        String msg;
        if (loginMesg.size() != 0){
            msg="success";
        }
        else
            msg="fail";

        return msg;
    }
}

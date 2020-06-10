package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IManagerService;
import com.seventeen.hospital.beans.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ManagerController {

    @Resource(name = "managerService")
    private IManagerService managerService;

    @CrossOrigin
    @RequestMapping(path = "/mlogin.action",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request) {
        System.out.println("管理员登录");
        Map<String,Object> toMap=new HashMap<>();
        Manager manager=new Manager();
        String managerid=request.getParameter("managerid");
        String managerpwd=request.getParameter("managerpwd");

        if (managerid!=""&&managerpwd!=""){
            manager.setCardId(managerid);
            manager.setPassword(managerpwd);
        }
        List<Manager> loginMesg=managerService.find(manager);
        Manager loginManager=loginMesg.get(0);
        String msg;
        if (loginMesg.size() != 0){
            //创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            session.setAttribute("loginName", loginManager.getMname());
            msg="success";
            toMap.put("code","0000");
            toMap.put("data",loginManager);
            toMap.put("msg",msg);
            return toMap;
        }
        else {
            msg = "fail";
            toMap.put("code", "0001");
            toMap.put("data", "null");
            toMap.put("msg", msg);
            return toMap;
        }
    }

    //检测管理员是否登录过
    @CrossOrigin
    @RequestMapping(path = "/mCheckLogin.action",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> mCheckLogin(HttpServletRequest request) {
        Map<String,Object> toMap=new HashMap<>();
        String loginName = String.valueOf(request.getSession().getAttribute("loginName"));
        System.out.println(loginName);
        if(loginName!="null"){
            toMap.put("code","0000");
            toMap.put("data","null");
            toMap.put("msg","success");
        }else{
            toMap.put("code","0001");
            toMap.put("data","null");
            toMap.put("msg","fail");
        }
        return toMap;
    }

    //登出
    @CrossOrigin
    @RequestMapping(path = "/mlogout.action",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> logout(HttpServletRequest request) {
        System.out.println("调用退出登陆 ");
        Map<String,Object> toMap=new HashMap<>();
        HttpSession session=request.getSession();
        session.removeAttribute("loginName");
        Object lout=session.getAttribute("loginName");
        if (lout==null){
            toMap.put("code","0000");
            toMap.put("data","null");
            toMap.put("msg","success");
        }else{
            toMap.put("code","0001");
            toMap.put("data","null");
            toMap.put("msg","fail");
        }
        return toMap;
    }

}

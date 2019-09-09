package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IUserService;
import com.seventeen.hospital.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllUser.action")
    public List<User> findAllUser(){
        List<User> findList=userService.findAll();
        for (User user:findList) {
            System.out.println(user);
        }
        return findList;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneUser.action")
    public List<User> findOneUser(){
        User user=new User();
        user.setUsername("变形金刚");
        List<User> findList= userService.find(user);
        for (User list:findList) {
            System.out.println(list);
        }
        return findList;
    }

    @CrossOrigin
    @RequestMapping(path = "/addUser.action")
    public String addUser(){
        User user=new User();
        user.setUsername("钢铁侠");
        user.setGender("男");
        user.setCardId("441523200001027777");
        user.setPassword("123");
        user.setPhone("123");
        userService.add(user);
        return "aaa";
    }

    @CrossOrigin
    @RequestMapping(path = "/updateUser.action")
    public String updateUser(){
        User user=new User();
        user.setUserid(2);
        user.setUsername("变形金刚");
        user.setGender("男");
        user.setCardId("441523200001027777");
        user.setPassword("123");
        user.setPhone("123");
        userService.update(user);
        return "aaa";
    }

    @CrossOrigin
    @RequestMapping(path = "/deleteUser.action")
    public String deleteUser(){
        userService.delete(2);
        return "aaa";
    }

    @CrossOrigin
    @RequestMapping(path = "/login.action",method= RequestMethod.POST)
    @ResponseBody
    public User login(HttpServletRequest request) {
        User user=new User();
        user.setCardId(request.getParameter("inputusername"));
        user.setPassword(request.getParameter("inputpassword"));
        List<User> loginMesg=userService.find(user);
        User rtuser=new User();
        rtuser.setUsername(loginMesg.get(0).getUsername());
        rtuser.setCardId(loginMesg.get(0).getCardId());
        if (loginMesg!=null){
            return rtuser;
        }
        else
            return null;
    }


   /* @CrossOrigin
    @RequestMapping(path = "/dlogin.action",method= RequestMethod.POST)
    @ResponseBody
    public String dlogin(){
        System.out.println("11111111");
        System.out.println();
        return "ok";
    }*/




}

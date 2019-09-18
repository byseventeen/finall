package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IUserService;
import com.seventeen.hospital.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;

    //查询用户表里的所有用户
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllUser.action")
    public Map<String,Object> findAllUser(){
        List<User> findList=userService.findAll();
        Map<String,Object> toMap=new HashMap<>();
        toMap.put("data",findList);
        return toMap;
    }

    //根据用户id查找某个用户
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneUser.action")
    public List<User> findOneUser(HttpServletRequest request){
        System.out.println("111111");
        User user=new User();
        user.setUserid(Integer.valueOf(request.getParameter("userid")));

        List<User> findList= userService.find(user);

        return findList;
    }

    //添加用户
    @CrossOrigin
    @RequestMapping(path = "/addUser.action",method= RequestMethod.POST)
    public String addUser(HttpServletRequest request){
        System.out.println("111111");
        User user=new User();
        user.setUsername(request.getParameter("username"));
        user.setGender(request.getParameter("gender"));
        user.setCardId(request.getParameter("cardId"));
        user.setPassword("123");
        user.setPhone(request.getParameter("phone"));
        user.setCardId(request.getParameter("cardId"));
        userService.add(user);
        String msg="success";
        return msg;
    }

    //更新某个用户
    @CrossOrigin
    @RequestMapping(path = "/updateUser.action",method= RequestMethod.POST)
    public String updateUser(HttpServletRequest request){
        System.out.println("111111");
        User user=new User();
        user.setUserid(Integer.valueOf(request.getParameter("userid")));
        user.setUsername(request.getParameter("username"));
        user.setGender(request.getParameter("gender"));
        user.setCardId(request.getParameter("cardId"));
        user.setPhone(request.getParameter("phone"));
        userService.update(user);
        String msg="success";
        return msg;
    }

    //根据用户id删除某个用户
    @CrossOrigin
    @RequestMapping(path = "/deleteUser.action")
    public String deleteUser(HttpServletRequest request){
        userService.delete(Integer.parseInt(request.getParameter("userid")));
        String msg="success";
        return msg;
    }

    //用户的登录验证
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

}

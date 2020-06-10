package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IUserService;
import com.seventeen.hospital.beans.User;
import com.seventeen.hospital.util.MD;
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
        //toMap.put("recordsFiltered",findList.size());
        toMap.put("recordsTotal",findList.size());
        toMap.put("recordsFiltered",findList.size());
        return toMap;
    }

    //根据用户id查找某个用户
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findOneUser.action",method= RequestMethod.POST)
    public List<User> findOneUser(HttpServletRequest request){
        System.out.println("//根据用户id查找某个用户");
        User user=new User();
        int userId= Integer.parseInt(request.getParameter("userId"));
        user.setUserid(userId);
        List<User> findList= userService.find(user);
        System.out.println(findList);
        return findList;
    }

    //添加用户
    @CrossOrigin
    @RequestMapping(path = "/addUser.action",method= RequestMethod.POST)
    public String addUser(HttpServletRequest request){
        User user=new User();
        String passWord = MD.md5("123456");
        String cardId=request.getParameter("cardId");
        String msg="";
        User quser=new User();
        quser.setCardId(cardId);
        List<User> userList=userService.find(user);
        if (userList!=null){
            user.setUsername(request.getParameter("username"));
            user.setGender(request.getParameter("gender"));
            user.setCardId(request.getParameter("cardId"));
            user.setPassword(passWord);
            user.setPhone(request.getParameter("phone"));
            user.setCardId(cardId);
            userService.add(user);
            msg="success";
        }else{
            msg="fail";
        }
        return msg;
    }
    //注册
    @CrossOrigin
    @RequestMapping(path = "/Sign.action",method= RequestMethod.POST)
    public String Sign(HttpServletRequest request){
        User user=new User();
        String passWord = MD.md5(request.getParameter("pwd"));
        String cardId=request.getParameter("cardId");
        String msg="";
        User quser=new User();
        quser.setCardId(cardId);
        List<User> userList=userService.find(user);
        if (userList!=null){
            user.setUsername(request.getParameter("username"));
            user.setGender(request.getParameter("gender"));
            user.setCardId(request.getParameter("cardId"));
            user.setPassword(passWord);
            user.setPhone(request.getParameter("phone"));
            user.setCardId(cardId);
            userService.add(user);
            msg="success";
        }else{
            msg="fail";
        }
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

    //更新某个用户  修改手机号码
    @CrossOrigin
    @RequestMapping(path = "/updateUserphone.action",method= RequestMethod.POST)
    public String updateUserPhone(HttpServletRequest request){
        System.out.println("111111");
        User user=new User();
        user.setUserid(Integer.valueOf(request.getParameter("userid")));
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
        rtuser.setUserid(loginMesg.get(0).getUserid());
        if (loginMesg!=null){
            return rtuser;
        }
        else
            return null;
    }

    //根据用户cardId查找该用户信息
    @CrossOrigin
    @RequestMapping(path = "/findUserByCardId.action",method= RequestMethod.POST)
    @ResponseBody
    public List<User> findUserByCardId(HttpServletRequest request) {
        User user=new User();
        user.setCardId(request.getParameter("userCardId"));
        List<User> userList=userService.find(user);
        if (userList!=null){
            return userList;
        }
        else
            return null;
    }

}

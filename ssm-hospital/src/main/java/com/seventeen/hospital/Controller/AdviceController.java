package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.IAdviceService;
import com.seventeen.hospital.beans.Advice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path="/advice")
public class AdviceController {

    @Resource(name = "adviceService")
    private IAdviceService adviceService;

    //查询意见表里面所有的意见信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllAdvice.action")
    public Map<String,Object> findAllAdvice(){
        List<Advice> findList=adviceService.findAll();
        Map<String,Object> toMap=new HashMap<>();
        toMap.put("data",findList);
        toMap.put("recordsFiltered",findList.size());
        toMap.put("recordsTotal",findList.size());
        return toMap;
    }

    //添加意见
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addAdvice.action",method= RequestMethod.POST)
    public Map<String,Object> addDepartment(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("添加意见");
        String adviceType= request.getParameter("adviceType");
        String adviceText= request.getParameter("adviceText");
       // String type=decodeURI(adviceType);
        System.out.println(adviceText);
        System.out.println(URLDecoder.decode(adviceText, "UTF-8"));
        String type=URLDecoder.decode(adviceType, "UTF-8");
        String text=URLDecoder.decode(adviceText, "UTF-8");
        System.out.println(type);
        System.out.println(text);
        Advice advice=new Advice();
        advice.setAdviceType(type);
        advice.setAdvice(text);
        adviceService.add(advice);
        Map<String,Object> toMap=new HashMap<>();
        toMap.put("code","0000");
        toMap.put("data",null);
        toMap.put("msg","提交意见成功！");
        return toMap;
    }
}

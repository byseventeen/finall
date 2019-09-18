package com.seventeen.hospital.Controller;

import com.seventeen.hospital.Service.ITitleService;
import com.seventeen.hospital.beans.Title;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(path="/title")
public class TitleController {
    @Resource(name = "titleService")
    private ITitleService titleService;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findAllTitle.action")
    public List<Title> findAll(){
        List<Title> findlist=titleService.findAll();
        for (Title t:findlist) {
            System.out.println(t);
        }
        return findlist;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/addTitle.action")
    public String addTitle(){
        Title title=new Title();
        title.setTitlename("院长");
        titleService.add(title);
        return "fine!";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/updateTitle.action")
    public String updateTitle(){
        Title title=new Title();
        title.setTid(5);
        title.setTitlename("骨科");
        title.setTprice(50);
        titleService.update(title);
        return "fine!";
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/deleteTitle.action")
    public String deleteTitle(){
        titleService.delete(5);
        return "fine!";
    }

    //多对一，传一个id过来，根据id找到门诊和科室信息
    @ResponseBody
    @CrossOrigin
    @RequestMapping(path = "/findTitleById.action")
    public List<Title> findDepartmentById(int titleid){
        List<Title> findlist=titleService.findTitleById(titleid);
        return findlist;
    }


}

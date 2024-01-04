package com.controller.manager;

import com.entity.Type;
import com.service.manager.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class TypeController {

    @Autowired
    TypeService typeService;

    @RequestMapping("/deleteType")
    public String toAddType(Integer tid){
        typeService.deleteTypeById(tid);
        return "forward:/manager/allTypes";
    }

    @RequestMapping("/addType")
    public  String addType(@ModelAttribute("type")Type type){
        typeService.addType(type);
        return "forward:/manager/allTypes";
    }

    @RequestMapping("/allTypes")
    public String findAllGoodsType(Model model){

        List<Type> typeList=typeService.findAllGoodsType();
        model.addAttribute("allTypes",typeList);
        return "/manager/typemain";
    }
}

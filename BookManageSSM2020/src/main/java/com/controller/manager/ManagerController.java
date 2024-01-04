package com.controller.manager;

import com.entity.Manager;
import com.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @RequestMapping("/manager")
    public String toLogin(@ModelAttribute("manager")Manager manager){
        return "/manager/login";
    }

    @RequestMapping("/manager/login")
    public String login(@ModelAttribute("manager") Manager manager, HttpSession session){
        Boolean rst=managerService.isLogin(manager);
        if(rst){
            Manager manager1=managerService.findManager(manager);
            session.setAttribute("manager",manager1);
            return "/manager/index";
        }else {
            return "/manager/login";
        }
    }

    @RequestMapping("/manager/toUpdatePass")
    public String toUpdatePass(){
        return "/manager/pass";
    }

    @RequestMapping("/manager/updatePass")
    public String updatePass(String pass, String repass, Manager manager, HttpSession session, HttpServletRequest request){
        if(pass.equals(repass)){
            Manager m=(Manager)session.getAttribute("manager") ;
            manager.setId(m.getId());
            manager.setPassword(pass);

            managerService.updatePass(manager);
            request.setAttribute("state","sucess");
            return "forward:/manager/toUpdatePass";
        }else {
            request.setAttribute("state","f");
            return "forward:/manager/toUpdatePass";
        }
    }

    @RequestMapping("/manager/outlogin")
    public String outlogin(HttpSession session){
        session.invalidate();
        return "/manager/login";
    }

    @RequestMapping("/manager/toUpdateManager")
    public String toUpdateManager(Model model,HttpSession session){
        Manager manager=(Manager) session.getAttribute("manager");
        Manager newManager=managerService.findManagerById(manager.getId());

        model.addAttribute("manager",newManager);
        session.setAttribute("manager",newManager);
        return "/manager/manageredit";
    }

    @RequestMapping("/manager/updateManager")
    public String updateManager(@ModelAttribute Manager manager,HttpServletRequest request){
        managerService.updateManager(manager);

        request.setAttribute("state","s");
        return "forward:/manager/toUpdateManager";
    }
}

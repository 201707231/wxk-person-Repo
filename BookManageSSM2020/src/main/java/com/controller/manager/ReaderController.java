package com.controller.manager;

import com.entity.Reader;
import com.service.manager.ReaderService;
import com.utils.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ReaderController {

    @Autowired
    ReaderService readerService;

    DataFormat dataFormat=new DataFormat();

    @RequestMapping("/allReaders")
    public String findAllReaders(Model model,Integer pageCur,HttpSession session){
        if (pageCur == null) {
            pageCur = 1;
        }

        int pageRow = 3;
        List<Reader> readerList=readerService.findAllReaders(pageCur, pageRow);
        Integer totalCount = readerService.findReadersTotalCount();
        Integer totalPage = (totalCount+pageRow-1)/pageRow;
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageCur", pageCur);
        model.addAttribute("readers",readerList);

        session.setAttribute("select","all");
        return "/manager/readermain";
    }

    @RequestMapping("/toAddReader")
    public String toAddReader(HttpSession session){
        session.setAttribute("info","insert");
        return "/manager/readeredit";
    }

    @InitBinder("reader")
    public void init(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("reader.");
    }
    @RequestMapping("/addReader")
    public String addReader(@ModelAttribute Reader reader,String cmd){
        if(cmd.equals("insert")){
            reader.setStart(dataFormat.currentTime());
            reader.setEnd(dataFormat.lastTime());
            reader.setPunishcount(0);//未实现惩罚功能
            reader.setLendcount(0);
            readerService.addReader(reader);
        }else if(cmd.equals("update")){
            readerService.updateReader(reader);
        }
        return "forward:/manager/allReaders";
    }

    @RequestMapping("/toUpdateReader")
    public String toUpdateReader(Model model, HttpSession session,String id){
        Reader reader=readerService.findReaderById(id);
        model.addAttribute("reader",reader);
        session.setAttribute("info","update");
        return "/manager/readeredit";
    }

    @RequestMapping("/deleteReader")
    public String deleteReader(String id){
        readerService.deleteReaderById(id);
        return "forward:/manager/allReaders";
    }

    @RequestMapping("/findReadersByTag")
    public String findReadersByTag(String name,Model model,HttpSession session){
        List<Reader> readerList=readerService.findReadersByTag(name);
        model.addAttribute("readers",readerList);

        session.setAttribute("select","tag");
        return "/manager/readermain";
    }
}

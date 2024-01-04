package com.controller.manager;

import com.entity.Book;
import com.entity.Type;
import com.mysql.cj.Session;
import com.service.manager.BookService;
import com.service.manager.TypeService;
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
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/allBooks")
    public String findAllBooks(Model model,Integer pageCur,HttpSession session){
        if (pageCur == null) {
            pageCur = 1;
        }

        int pageRow = 3;
        List<Book> bookList=bookService.findAllBooks(pageCur, pageRow);
        Integer totalCount = bookService.findBooksTotalCount();
        Integer totalPage=(totalCount+pageRow-1)/pageRow; //= totalCount / pageRow + 1;
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageCur", pageCur);
        model.addAttribute("allBooks",bookList);

        session.setAttribute("selectAct","all");
        return "/manager/bookmain";
    }

    @RequestMapping("/toAddBook")
    public  String toAddBook(Model model,HttpSession session){
        List<Type> typeList=typeService.findAllGoodsType();
        model.addAttribute("allTypes",typeList);
        session.setAttribute("info","insert");
        return "/manager/bookedit";
    }

    @InitBinder("book")
    public void init(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("book.");
    }
    @RequestMapping("/addBook")
    public String addBook(@ModelAttribute("book") Book book,Integer tid,String cmd){
        Type type=typeService.findTypeById(tid);
        book.setType(type);
        if(cmd.equals("insert")) {
            bookService.addBook(book);
        }else if(cmd.equals("update")){
            bookService.updateBook(book);
        }
        return "forward:/manager/allBooks";
    }

    @RequestMapping("/toUpdateBook")
    public String updateBook(Model model, Integer id, HttpSession session){
        Book book=bookService.findBookByISBN(id);
        List<Type> typeList=typeService.findAllGoodsType();
        model.addAttribute("allTypes",typeList);
        model.addAttribute("oneBook",book);
        session.setAttribute("info","update");
        return "/manager/bookedit";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(Integer id){
        bookService.deleteBookById(id);
        return "forward:/manager/allBooks";
    }

    @RequestMapping("/findBookByTag")
    public String findBookByTag(String name,Model model,Integer pageCur,HttpSession session){//取消分页功能
        if (pageCur == null) {
            pageCur = 1;
        }

        int pageRow = 3;
        Integer totalCount = bookService.findGoodsByTagCount(name);
        List<Book> bookList=bookService.findGoodsByTag(name,pageCur, pageRow);
        Integer totalPage=(totalCount+pageRow-1)/pageRow; //= totalCount / pageRow + 1;
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageCur", pageCur);
        model.addAttribute("allBooks",bookList);

        session.setAttribute("selectAct","tag");
        return "/manager/bookmain";
    }
}

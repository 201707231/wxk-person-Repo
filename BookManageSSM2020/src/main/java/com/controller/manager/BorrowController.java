package com.controller.manager;

import com.entity.Book;
import com.entity.Borrow;
import com.entity.Manager;
import com.entity.Reader;
import com.service.manager.BookService;
import com.service.manager.BorrowService;
import com.service.manager.ReaderService;
import com.utils.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class BorrowController {

    @Autowired
    ReaderService readerService;

    @Autowired
    BorrowService borrowService;

    @Autowired
    BookService bookService;

    DataFormat dataFormat=new DataFormat();

    @RequestMapping("/getAllBooks")
    public String toLendBook(Model model,Integer pageCur){
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
        return "/manager/borrowmain";
    }

    @RequestMapping("/lendBook")
    public String lendBook(Borrow borrow, String bid, String rid, HttpSession session){
        Manager manager=(Manager)session.getAttribute("manager") ;
        Integer mid=manager.getId();
        borrow.setBid(bid);
        borrow.setRid(rid);
        borrow.setBorrowtime(dataFormat.currentTimeS());
        borrow.setReturntime(dataFormat.returnTime());
        borrow.setState("未归还");
        borrow.setRenewtimes(0);
        borrow.setMid(mid);

        readerService.updateLendCount(rid);//读者借阅次数加一
        bookService.updateBookNum(Integer.valueOf(bid));//图书数量减一
        borrowService.addBorrow(borrow);//借阅表信息增加
        return "forward:/manager/toReturnBook";
    }

    @RequestMapping("/toReturnBook")
    public String toReturnBook(Model model,Integer pageCur,HttpSession session){
        if (pageCur == null) {
            pageCur = 1;
        }

        int pageRow = 3;
        List<Borrow> borrowList=borrowService.findAllBorrow(pageCur, pageRow);
        Integer totalCount = borrowService.findBorrowsTotalCount();
        Integer totalPage=(totalCount+pageRow-1)/pageRow; //= totalCount / pageRow + 1;
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageCur", pageCur);
        model.addAttribute("allBorrow",borrowList);

        session.setAttribute("s","all");
        return "/manager/returnmain";
    }

    @RequestMapping("/returnBook")
    public String returnBook(Borrow borrow,Integer id){
        borrow.setState("已归还");
        borrow.setPracticaltime(dataFormat.currentTimeS());
        borrow.setId(id);

        borrowService.returnBorrow(borrow);
        Integer bid=borrowService.findBookId(id);
        if(bid>0) {
            bookService.addBookNum(bid);
        }
        return "forward:/manager/toReturnBook";
    }

    @RequestMapping("/renewBook")
    public String renewBook(Borrow borrow,Integer id){
        String time=borrowService.findReturnTime(id);
        borrow.setReturntime(dataFormat.addNewMonth(time));
        borrow.setId(id);

        borrowService.renewBorrow(borrow);
        return "forward:/manager/toReturnBook";
    }

    @RequestMapping("/findBorrowByTag")
    public String findBorrowByTag(String rid,Model model,HttpSession session){
        List<Borrow> borrowList=borrowService.findBorrowByTag(rid);
        model.addAttribute("allBorrow",borrowList);

        session.setAttribute("s","tag");
        return "/manager/returnmain";
    }
}

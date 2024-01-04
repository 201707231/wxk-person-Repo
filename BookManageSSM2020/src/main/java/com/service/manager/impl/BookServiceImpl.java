package com.service.manager.impl;

import com.dao.manager.BookDao;
import com.entity.Book;
import com.service.manager.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Resource(name = "bookDao")
    BookDao bookDao;

    @Override
    public List<Book> findAllBooks(int pageCur,int pageRow) {
        Map<String,Object> parm=new HashMap<>();
        parm.put("begin",(pageCur-1)*pageRow);
        parm.put("pageRows",pageRow);

        return bookDao.findAllBooks(parm);
    }

    @Override
    public void addBook(Book book) {
         bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book findBookByISBN(Integer id) {
        return bookDao.findBookByISBN(id);
    }

    @Override
    public void updateBookNum(Integer id) {
        bookDao.updateBookNum(id);
    }

    @Override
    public void addBookNum(Integer id) {
        bookDao.addBookNum(id);
    }

    @Override
    public Integer findBooksTotalCount() {
        return bookDao.findBooksTotalCount();
    }

    @Override
    public List<Book> findGoodsByTag(String name,int pageCur,int pageRow) {
        Map<String,Object> parm=new HashMap<>();
        parm.put("begin",(pageCur-1)*pageRow);
        parm.put("pageRows",pageRow);
        parm.put("name",name);

        return bookDao.findGoodsByTag(parm);
    }

    @Override
    public Integer findGoodsByTagCount(String name) {
        return bookDao.findGoodsByTagCount(name);
    }
}

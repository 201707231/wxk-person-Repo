package com.service.manager;

import com.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> findAllBooks(int pageCur,int pageRow);
    void addBook(Book book);
    void deleteBookById(Integer id);
    void updateBook(Book book);
    Book findBookByISBN(Integer id);
    void updateBookNum(Integer id);
    void addBookNum(Integer id);
    Integer findBooksTotalCount();
    List<Book> findGoodsByTag(String name,int pageCur,int pageRow);
    Integer findGoodsByTagCount(String name);
}

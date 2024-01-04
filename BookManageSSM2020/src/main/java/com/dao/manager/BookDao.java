package com.dao.manager;

import com.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("bookDao")
public interface BookDao {
    List<Book> findAllBooks(Map<String,Object> param);
    void addBook(Book book);
    void deleteBookById(Integer id);
    void updateBook(Book book);
    Book findBookByISBN(Integer id);
    void updateBookNum(Integer id);
    void addBookNum(Integer id);
    Integer findBooksTotalCount();
    List<Book> findGoodsByTag(Map<String,Object> param);
    Integer findGoodsByTagCount(String name);
}

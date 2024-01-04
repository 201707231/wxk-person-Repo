package com.dao.manager;

import com.entity.Reader;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("readerDao")
public interface ReaderDao {
    List<Reader> findAllReaders(Map<String,Object> param);
    void addReader(Reader reader);
    void deleteReaderById(String id);
    void updateReader(Reader reader);
    Reader findReaderById(String id);
    Integer findReadersTotalCount();
    List<Reader> findReadersByTag(String name);
    void updateLendCount(String id);
}

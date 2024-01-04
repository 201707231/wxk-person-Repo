package com.service.manager;

import com.entity.Reader;

import java.util.List;

public interface ReaderService {
    List<Reader> findAllReaders(int pageCur,int pageRow);
    void addReader(Reader reader);
    void deleteReaderById(String id);
    void updateReader(Reader reader);
    Reader findReaderById(String id);
    Integer findReadersTotalCount();
    List<Reader> findReadersByTag(String name);
    void updateLendCount(String id);
}

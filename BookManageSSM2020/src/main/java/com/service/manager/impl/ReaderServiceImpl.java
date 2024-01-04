package com.service.manager.impl;

import com.dao.manager.ReaderDao;
import com.entity.Reader;
import com.service.manager.ReaderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("readerService")
public class ReaderServiceImpl implements ReaderService {

    @Resource(name="readerDao")
    ReaderDao readerDao;

    @Override
    public List<Reader> findAllReaders(int pageCur, int pageRow) {
        Map<String,Object> parm=new HashMap<>();
        parm.put("begin",(pageCur-1)*pageRow);
        parm.put("pageRows",pageRow);

        return readerDao.findAllReaders(parm);
    }

    @Override
    public void addReader(Reader reader) {
        readerDao.addReader(reader);
    }

    @Override
    public void deleteReaderById(String id) {
        readerDao.deleteReaderById(id);
    }

    @Override
    public void updateReader(Reader reader) {
        readerDao.updateReader(reader);
    }

    @Override
    public Reader findReaderById(String id) {
        return readerDao.findReaderById(id);
    }

    @Override
    public Integer findReadersTotalCount() {
        return readerDao.findReadersTotalCount();
    }

    @Override
    public List<Reader> findReadersByTag(String name) {
        return readerDao.findReadersByTag(name);
    }

    @Override
    public void updateLendCount(String id) {
        readerDao.updateLendCount(id);
    }
}

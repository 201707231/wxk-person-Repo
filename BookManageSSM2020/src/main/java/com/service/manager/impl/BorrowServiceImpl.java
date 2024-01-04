package com.service.manager.impl;

import com.dao.manager.BorrowDao;
import com.entity.Borrow;
import com.service.manager.BorrowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("borrowService")
public class BorrowServiceImpl implements BorrowService {

    @Resource(name = "borrowDao")
    BorrowDao borrowDao;

    @Override
    public List<Borrow> findAllBorrow(int pageCur,int pageRow) {
        Map<String,Object> parm=new HashMap<>();
        parm.put("begin",(pageCur-1)*pageRow);
        parm.put("pageRows",pageRow);

        return borrowDao.findAllBorrow(parm);
    }

    @Override
    public void addBorrow(Borrow borrow) {
         borrowDao.addBorrow(borrow);
    }

    @Override
    public void updateBorrow(Borrow borrow) {
        borrowDao.updateBorrow(borrow);
    }

    @Override
    public void returnBorrow(Borrow borrow) {
        borrowDao.returnBorrow(borrow);
    }

    @Override
    public Integer findBookId(Integer id) {
        return borrowDao.findBookId(id);
    }

    @Override
    public void renewBorrow(Borrow borrow) {
        borrowDao.renewBorrow(borrow);
    }

    @Override
    public String findReturnTime(Integer id) {
        return borrowDao.findReturnTime(id);
    }

    @Override
    public Integer findBorrowsTotalCount() {
        return borrowDao.findBorrowsTotalCount();
    }

    @Override
    public List<Borrow> findBorrowByTag(String rid) {
        return borrowDao.findBorrowByTag(rid);
    }
}

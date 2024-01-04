package com.service.manager;

import com.entity.Borrow;

import java.util.List;

public interface BorrowService {
    List<Borrow> findAllBorrow(int pageCur,int pageRow);
    void addBorrow(Borrow borrow);
    void updateBorrow(Borrow borrow);
    void returnBorrow(Borrow borrow);
    Integer findBookId(Integer id);
    void renewBorrow(Borrow borrow);
    String findReturnTime(Integer id);
    Integer findBorrowsTotalCount();
    List<Borrow> findBorrowByTag(String rid);
}

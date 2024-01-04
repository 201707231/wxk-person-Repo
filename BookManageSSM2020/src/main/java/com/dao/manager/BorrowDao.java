package com.dao.manager;

import com.entity.Borrow;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("borrowDao")
public interface BorrowDao {
     List<Borrow> findAllBorrow(Map<String,Object> param);
     void addBorrow(Borrow borrow);
     void updateBorrow(Borrow borrow);
     void returnBorrow(Borrow borrow);
     Integer findBookId(Integer id);
     void renewBorrow(Borrow borrow);
     String findReturnTime(Integer id);
     Integer findBorrowsTotalCount();
     List<Borrow> findBorrowByTag(String rid);
}

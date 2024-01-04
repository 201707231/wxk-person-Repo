package com.dao.manager;

import com.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("typeDao")
public interface TypeDao {
     List<Type> findAllBooksType();
     void addType(Type type);
     void deleteTypeById(Integer tid);
     void updateType(Type type);
     Type findTypeById(Integer tid);
}

package com.service.manager;

import com.entity.Book;
import com.entity.Type;

import java.util.List;

public interface TypeService {
    public List<Type> findAllGoodsType();
    void addType(Type type);
    void deleteTypeById(Integer tid);
    void updateType(Type type);
    Type findTypeById(Integer tid);

}

package com.service.manager.impl;

import com.dao.manager.TypeDao;
import com.entity.Type;
import com.service.manager.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("typeService")
public class TypeServiceImpl implements TypeService {

    @Resource(name = "typeDao")
    TypeDao typeDao;

    @Override
    public List<Type> findAllGoodsType() {
        return typeDao.findAllBooksType();
    }

    @Override
    public void addType(Type type) {
        typeDao.addType(type);
    }

    @Override
    public void deleteTypeById(Integer tid) {
        typeDao.deleteTypeById(tid);
    }

    @Override
    public void updateType(Type type) {
        typeDao.updateType(type);
    }

    @Override
    public Type findTypeById(Integer tid) {
        return typeDao.findTypeById(tid);
    }
}

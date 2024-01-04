package com.service.manager.impl;

import com.dao.manager.ManagerDao;
import com.entity.Manager;
import com.service.manager.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Resource(name="managerDao")
    ManagerDao managerDao;

    @Override
    public Boolean isLogin(Manager manager) {
        Integer rst=managerDao.findManagerCount(manager);
        if(rst==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Manager findManager(Manager manager) {
        return managerDao.findManager(manager);
    }

    @Override
    public void updatePass(Manager manager) {
        managerDao.updatePass(manager);
    }

    @Override
    public void updateManager(Manager manager) {
        managerDao.updateManager(manager);
    }

    @Override
    public Manager findManagerById(Integer id) {
        return managerDao.findManagerById(id);
    }
}

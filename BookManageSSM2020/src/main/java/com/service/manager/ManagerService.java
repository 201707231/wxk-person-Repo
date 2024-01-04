package com.service.manager;

import com.entity.Manager;

public interface ManagerService {
    Boolean isLogin(Manager manager);
    Manager findManager(Manager manager);
    void updatePass(Manager manager);
    void updateManager(Manager manager);
    Manager findManagerById(Integer id);
}

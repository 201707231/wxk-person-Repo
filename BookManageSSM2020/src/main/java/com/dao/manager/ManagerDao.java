package com.dao.manager;

import com.entity.Manager;
import org.springframework.stereotype.Repository;

@Repository("managerDao")
public interface ManagerDao {
    Integer findManagerCount(Manager manager);
    Manager findManager(Manager manager);
    void updatePass(Manager manager);
    void updateManager(Manager manager);
    Manager findManagerById(Integer id);
}

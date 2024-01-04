package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.DevStatusDao;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: heeexy
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
@Service
public class DevStatusService {
    @Autowired
    private DevStatusDao devStatusDao;

    /**
     * 设备列表
     */
    public JSONObject listDevStatus(JSONObject jsonObject) {
        List<JSONObject> list = devStatusDao.listDevStatus();
        return CommonUtil.successPage( list);
    }
   
}

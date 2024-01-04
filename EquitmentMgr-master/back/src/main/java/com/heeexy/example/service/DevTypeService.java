package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.DevTypeDao;
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
public class DevTypeService {
    @Autowired
    private DevTypeDao devTypeDao;

    /**
     * 设备列表
     */
    public JSONObject listDevType(JSONObject jsonObject) {
        List<JSONObject> list = devTypeDao.listDevType();
        return CommonUtil.successPage( list);
    }
   
}

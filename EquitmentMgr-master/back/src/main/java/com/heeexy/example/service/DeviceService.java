package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.DeviceDao;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author: heeexy
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
@Service
public class DeviceService {
    @Autowired
    private DeviceDao devDao;

    /**
     * 设备列表
     */
    public JSONObject listDevice(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = devDao.countDev(jsonObject);
        List<JSONObject> list = devDao.listDevice(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
  
    
    /**
     * 设备查询
     */
    public JSONObject queryDevice(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> list = devDao.queryDevice(jsonObject);
        return CommonUtil.successPage( list);
    }

    /**
     *  更新设备信息
     */
    public JSONObject updateDevice(JSONObject jsonObject){
        devDao.updateDevice(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 添加设备
     */
    public JSONObject addDevice(JSONObject jsonObject){
        devDao.addDevice(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 查询设备的配件
     */
    public JSONObject listDevParts(JSONObject jsonObject){
        List<JSONObject> list=devDao.listDevParts(jsonObject);
        return CommonUtil.successPage(list);
    }


}

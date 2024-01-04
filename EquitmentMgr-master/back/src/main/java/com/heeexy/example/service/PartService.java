package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PartDao;
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
public class PartService {
    @Autowired
    private PartDao partDao;

    /**
     * 配件列表
     */
    public JSONObject listPart(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = partDao.countPart(jsonObject);
        List<JSONObject> list = partDao.listPart(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }
  
    
    /**
     * 配件查询
     */
    public JSONObject queryPart(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        List<JSONObject> list = partDao.queryPart(jsonObject);
        return CommonUtil.successPage( list);
    }

    /**
     *  更新配件信息
     */
    public JSONObject updatePart(JSONObject jsonObject){
        partDao.updatePart(jsonObject);
        return CommonUtil.successJson();
    }

    /**
     * 添加配件
     */
    public JSONObject addPart(JSONObject jsonObject){
        partDao.addPart(jsonObject);
        return CommonUtil.successJson();
    }


}

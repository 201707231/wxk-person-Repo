package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.DefsDao;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: heeexy
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
@Service
public class DefsService {
    @Autowired
    private DefsDao defsDao;

    /**
     * 定义列表
     */
    public JSONObject listDefs(JSONObject jsonObject) {
        List<JSONObject> list = defsDao.listDefs();
        return CommonUtil.successPage(list);
    }

    /**
     * 查询
     */
    public JSONObject queryDefs(JSONObject jsonObject) {
        List<JSONObject> list = defsDao.listDefs();
        return CommonUtil.successPage(list);
    }
    /**
     * 添加定义
     * @param jsonObject
     * @return
     */
    public JSONObject addDefs(JSONObject jsonObject) {
        if (!jsonObject.containsKey("defID"))
            return CommonUtil.errorJson(ErrorEnum.E_500);
        int count=defsDao.count(jsonObject);
        if(count>=1){
            return CommonUtil.errorJson(ErrorEnum.E_90001);
        }
        count =defsDao.addDef(jsonObject);
        if(count!=1){
            return CommonUtil.errorJson(ErrorEnum.E_400);
        }
        return CommonUtil.successJson();
    };

    // 根据现有的defID 推断出合适的defID便于插入
    public int getAppropriateDefID(JSONObject jsonObject) throws Exception{
        if(!jsonObject.containsKey("defTypeID"))
            throw new Exception("缺少必要参数");
        int defTypeID=jsonObject.getIntValue("defTypeID");
        List<JSONObject> defs=defsDao.queryDefs(jsonObject);
        List<Integer> list=new ArrayList<Integer>();
        for (JSONObject item : defs) {
            list.add(item.getInteger("defID"));
        }
        int min=Collections.min(list);
        // NOTE Magic Number 100
        int max=min+100;
        while(true){
            min++;
            if(min>=max)
                return 0;
            if(!list.contains(min)){
                jsonObject.put("defID", min);
                jsonObject.put("defTypeID", null);
                if(defsDao.count(jsonObject)<1)
                    return min;
            }
        }
    }

}

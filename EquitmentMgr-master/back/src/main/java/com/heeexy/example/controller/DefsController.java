package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.DefsService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: heeexy
 * @description: 设备相关controller
 * @date: 2021/08/15 10:19
 */
@RestController
@RequestMapping("/defs")
public class DefsController {
    @Autowired
    private DefsService defsService;

    /**
     * 查询定义列表
     */
    @GetMapping("/list")
    public JSONObject listDefs(HttpServletRequest request) {
        return defsService.listDefs(CommonUtil.request2Json(request));
    }

    /**
     * 查询定义列表
     */
    @PostMapping("/query")
    public JSONObject queryDefs(@RequestBody JSONObject json) {
        return defsService.queryDefs(json);
    }

    @GetMapping("/add")
    public JSONObject addDefs(HttpServletRequest request) {
        return defsService.addDefs(CommonUtil.request2Json(request));
    }

    @GetMapping("/appropriateID")
    public JSONObject getappropriateDefID(HttpServletRequest request) {
        try{
           int max= defsService.getAppropriateDefID(CommonUtil.request2Json(request));
           return CommonUtil.successJson(max);
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorEnum.E_500);
        }
    }
}

package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.annotation.RequiresPermissions;
import com.heeexy.example.service.PartService;
import com.heeexy.example.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: heeexy
 * @description: 设备相关controller
 * @date: 2021/08/15 10:19
 */
@RestController
@RequestMapping("/part")
public class PartController {
    @Autowired
    private PartService partService;

    /**
     * 查询配件列表
     */
    @RequiresPermissions("part:list")
    @GetMapping("/list")
    public JSONObject listDevice(HttpServletRequest request) {
        return partService.listPart(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("part:list")
    @GetMapping("/query")
    public JSONObject queryDevice(HttpServletRequest request) {
        return partService.queryPart(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("part:update")
    @GetMapping("/update")
    public JSONObject updateDevice(HttpServletRequest request){
        return partService.updatePart(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("part:add")
    @GetMapping("/add")
    public JSONObject addDevice(HttpServletRequest request){
        return partService.addPart(CommonUtil.request2Json(request));
    }

}

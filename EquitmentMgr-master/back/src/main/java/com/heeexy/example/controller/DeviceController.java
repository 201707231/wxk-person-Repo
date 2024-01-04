package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.config.annotation.RequiresPermissions;
import com.heeexy.example.service.DeviceService;
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
@RequestMapping("/dev")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    /**
     * 查询设备列表
     */
    @RequiresPermissions("device:list")
    @GetMapping("/list")
    public JSONObject listDevice(HttpServletRequest request) {
        return deviceService.listDevice(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("device:list")
    @GetMapping("/query")
    public JSONObject queryDevice(HttpServletRequest request) {
        return deviceService.queryDevice(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("device:update")
    @GetMapping("/update")
    public JSONObject updateDevice(HttpServletRequest request){
        return deviceService.updateDevice(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("device:add")
    @GetMapping("/add")
    public JSONObject addDevice(HttpServletRequest request){
        return deviceService.addDevice(CommonUtil.request2Json(request));
    }

    @RequiresPermissions("device:list")
    @GetMapping("/listDevParts")
    public JSONObject listDevParts(HttpServletRequest request){
        return deviceService.listDevParts(CommonUtil.request2Json(request));
    }
}

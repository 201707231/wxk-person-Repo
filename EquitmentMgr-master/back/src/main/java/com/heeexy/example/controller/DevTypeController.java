package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.DevTypeService;
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
@RequestMapping("/devType")
public class DevTypeController {
    @Autowired
    private DevTypeService devTypeService;

    /**
     * 查询用户列表
     */
    @GetMapping("/list")
    public JSONObject listDevType(HttpServletRequest request) {
        return devTypeService.listDevType(CommonUtil.request2Json(request));
    }

}

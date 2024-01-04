package com.heeexy.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonBooleanFormatVisitor;
import com.heeexy.example.config.annotation.RequiresPermissions;
import com.heeexy.example.service.EventService;
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
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    /**
     * 查询设备列表
     */
    @RequiresPermissions("event:list")
    @PostMapping("/list")
    public JSONObject listDevice(@RequestBody String body, HttpServletRequest request) {
        // JSONObject json = CommonUtil.request2Json(request);
        JSONObject json = JSONObject.parseObject(body);
        JSONArray jsontmp = json.getJSONArray("eventTimeInterval");
        if (jsontmp != null && !jsontmp.isEmpty()) {
            json.put("eventBeginTime", jsontmp.get(0));
            json.put("eventEndTime", jsontmp.get(1));
        }
        return eventService.listEvent(json);
    }

    /**
     * 增加事件
     */
    @RequiresPermissions("event:add")
    @PostMapping("/add")
    public JSONObject addEvent(@RequestBody JSONObject json) {
        try {
            CommonUtil.fixParams(json, "eventStageID","eventTypeID","eventDetail","eventTime");
            
            eventService.addEvent(json);
        } catch (Exception ex) {
            return CommonUtil.errorJson(ErrorEnum.E_400);
        }
        return CommonUtil.successJson();
    }

}

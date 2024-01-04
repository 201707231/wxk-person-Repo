package com.heeexy.example.service.options;

import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.StringTools;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OptionAspect {

  @Autowired
  private IFOption addDevPartOpt;
  @Autowired
  private IFOption removeDevPartOpt;

  OptionAspect() {
    evtOptMap = new HashMap<Integer, String>();
    // Magic Number
    evtOptMap.put(60101, "addDevPart");
    evtOptMap.put(60102, "removeDevPart");
  }

  private HashMap<Integer, String> evtOptMap;

  @Pointcut("execution(* com.heeexy.example.service.EventService.addEvent(..))")
  public void point() {
  }

  @AfterReturning("point()")
  public void OptionMethod(JoinPoint jp) throws Exception {
    IFOption op = null;
    Object[] args = jp.getArgs();
    JSONObject json = (JSONObject) args[0];
    int eventType = json.getIntValue("eventTypeID");
    if (eventType <= 0 || !evtOptMap.containsKey(eventType))
      return;
    String optStr =(String) evtOptMap.get(eventType);
    if (StringTools.isNullOrEmpty(optStr))
      return;
    String[] optStrs = optStr.split("\\|");
    try {
      for (int i = 0; i < optStrs.length; ++i) {
        switch (optStrs[i]) {
          case "addDevPart":
            op = addDevPartOpt;
            break;
          case "removeDevPart":
            op = removeDevPartOpt;
            break;
        }
        if (op != null){
          JSONArray arr=json.getJSONArray("relative");
          if (arr.isEmpty() || arr.size() <= 0) {
            return;
          }
          for (int j = 0; j < arr.size(); j++) {
            JSONObject item = arr.getJSONObject(j);
            op.option(item);
          }
        }
      }
    } catch (Exception e) {
      throw new Exception("Option Failed");
    }

  }
}

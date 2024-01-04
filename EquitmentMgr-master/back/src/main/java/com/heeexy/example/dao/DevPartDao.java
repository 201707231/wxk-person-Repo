package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;

public interface DevPartDao {

  public int addDevPart(JSONObject json);

  public int removeDevPart(JSONObject json);

}

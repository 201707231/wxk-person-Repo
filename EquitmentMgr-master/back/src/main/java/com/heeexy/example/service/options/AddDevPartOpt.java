package com.heeexy.example.service.options;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.DevPartDao;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddDevPartOpt implements IFOption {
  @Autowired
  private DevPartDao dpd;

  @Override
  public JSONObject option(JSONObject json) {
    int num = dpd.addDevPart(json);
    if (num == 1)
      return CommonUtil.successJson();
    return CommonUtil.errorJson(ErrorEnum.E_10008);
  }

}

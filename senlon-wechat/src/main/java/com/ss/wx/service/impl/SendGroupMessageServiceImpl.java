package com.ss.wx.service.impl;

import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WeixinUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.service.SendGroupMessageService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class SendGroupMessageServiceImpl implements SendGroupMessageService {
    //根据标签进行群发
    private static String group_message_send_url="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
    @Override
    public JSONObject sendGroupMessage(JSONObject jsonObj, JwWebJwid jwid) {
        Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwid.getWeixinAppid(), jwid.getWeixinAppsecret());
        String accessToken = map.get("accessToken").toString();
        if(accessToken != null){
            String requestUrl = group_message_send_url.replace("ACCESS_TOKEN",accessToken);
            JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", jsonObj.toString());
            return jsonObject;
        }
        return null;
    }
}

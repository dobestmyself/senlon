package com.ss.wx.service;


import com.ss.wx.domain.JwWebJwid;
import net.sf.json.JSONObject;

public interface SendGroupMessageService {
    /**
     * 群发消息
     */
    JSONObject sendGroupMessage(JSONObject jsonObj, JwWebJwid jwid);
}

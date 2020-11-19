package com.ss.wx.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信消息处理
 */
public interface WechatService {
    public String coreService(HttpServletRequest request);
}

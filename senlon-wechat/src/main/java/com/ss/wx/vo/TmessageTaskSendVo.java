package com.ss.wx.vo;

public class TmessageTaskSendVo {
    private String touser;
    private String templateId;
    private String url;
    private String miniprogram;
    private String appid;
    private String pagepath;
    private String data;
    private String jwid;

    public String getJwid() {
        return jwid;
    }

    public void setJwid(String jwid) {
        this.jwid = jwid;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(String miniprogram) {
        this.miniprogram = miniprogram;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPagepath() {
        return pagepath;
    }

    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

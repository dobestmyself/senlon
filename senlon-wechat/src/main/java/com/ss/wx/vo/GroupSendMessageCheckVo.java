package com.ss.wx.vo;

import java.util.Date;

public class GroupSendMessageCheckVo {
    private String id;
    private String createBy;
    private String isToAll;
    private String templateName;
    private String msgType;
    private String checkStatus;
    private Date checkTime;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getIsToAll() {
        return isToAll;
    }

    public void setIsToAll(String isToAll) {
        this.isToAll = isToAll;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

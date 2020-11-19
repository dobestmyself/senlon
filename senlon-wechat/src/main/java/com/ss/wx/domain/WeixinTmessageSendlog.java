package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 发送模板消息日志对象 weixin_tmessage_sendlog
 * 
 * @author shishuai
 * @date 2020-07-20
 */
public class WeixinTmessageSendlog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    @Excel(name = "ID")
    private String id;

    /** OPENID */
    @Excel(name = "OPENID")
    private String openid;

    /** 任务ID */
    @Excel(name = "任务ID")
    private String taskId;

    /** 消息ID */
    @Excel(name = "消息ID")
    private String msgId;

    /** 状态 0：成功 1：失败 */
    @Excel(name = "状态 0：成功 1：失败")
    private String status;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createDate;

    /** JSON数据 */
    @Excel(name = "JSON数据")
    private String dataJson;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setTaskId(String taskId) 
    {
        this.taskId = taskId;
    }

    public String getTaskId() 
    {
        return taskId;
    }
    public void setMsgId(String msgId) 
    {
        this.msgId = msgId;
    }

    public String getMsgId() 
    {
        return msgId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setDataJson(String dataJson) 
    {
        this.dataJson = dataJson;
    }

    public String getDataJson() 
    {
        return dataJson;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("taskId", getTaskId())
            .append("msgId", getMsgId())
            .append("status", getStatus())
            .append("createDate", getCreateDate())
            .append("dataJson", getDataJson())
            .toString();
    }
}

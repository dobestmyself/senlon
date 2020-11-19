package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 群发日志明细对象 weixin_group_message_send_detail
 * 
 * @author shishuai
 * @date 2020-11-10
 */
public class WeixinGroupMessageSendDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 群发日志id */
    @Excel(name = "群发日志id")
    private String sendLogId;

    /** 发送公众号账号 */
    @Excel(name = "发送公众号账号")
    private String sendJwid;

    /** 发送公众号名称 */
    @Excel(name = "发送公众号名称")
    private String sendJwidName;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String msgType;

    /** 群发模板id */
    @Excel(name = "群发模板id")
    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 群发消息的媒体id */
    @Excel(name = "群发消息的媒体id")
    private String mediaId;

    /** 群发的状态 */
    @Excel(name = "群发的状态")
    private String sendStatus;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date sendTime;

    /** 返回的错误码 */
    @Excel(name = "返回的错误码")
    private String returnErrcode;

    /** 返回的错误信息 */
    @Excel(name = "返回的错误信息")
    private String returnErrmsg;

    /** 返回消息发送任务的id */
    @Excel(name = "返回消息发送任务的id")
    private String returnMsgId;

    /** 返回消息的数据id */
    @Excel(name = "返回消息的数据id")
    private String returnMsgDataId;

    /** 事件推送状态 */
    @Excel(name = "事件推送状态")
    private String pushStatus;

    /** 事件推送总粉丝数 */
    @Excel(name = "事件推送总粉丝数")
    private Long pushTotalcount;

    /** 事件推送过滤后粉丝数 */
    @Excel(name = "事件推送过滤后粉丝数")
    private Long pushFiltercount;

    /** 事件推送发送成功粉丝数 */
    @Excel(name = "事件推送发送成功粉丝数")
    private Long pushSendcount;

    /** 事件推送发送失败粉丝数 */
    @Excel(name = "事件推送发送失败粉丝数")
    private Long pushErrorcount;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setSendLogId(String sendLogId) 
    {
        this.sendLogId = sendLogId;
    }

    public String getSendLogId() 
    {
        return sendLogId;
    }
    public void setSendJwid(String sendJwid) 
    {
        this.sendJwid = sendJwid;
    }

    public String getSendJwid() 
    {
        return sendJwid;
    }
    public void setSendJwidName(String sendJwidName) 
    {
        this.sendJwidName = sendJwidName;
    }

    public String getSendJwidName() 
    {
        return sendJwidName;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setMediaId(String mediaId) 
    {
        this.mediaId = mediaId;
    }

    public String getMediaId() 
    {
        return mediaId;
    }
    public void setSendStatus(String sendStatus) 
    {
        this.sendStatus = sendStatus;
    }

    public String getSendStatus() 
    {
        return sendStatus;
    }
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }
    public void setReturnErrcode(String returnErrcode) 
    {
        this.returnErrcode = returnErrcode;
    }

    public String getReturnErrcode() 
    {
        return returnErrcode;
    }
    public void setReturnErrmsg(String returnErrmsg) 
    {
        this.returnErrmsg = returnErrmsg;
    }

    public String getReturnErrmsg() 
    {
        return returnErrmsg;
    }
    public void setReturnMsgId(String returnMsgId) 
    {
        this.returnMsgId = returnMsgId;
    }

    public String getReturnMsgId() 
    {
        return returnMsgId;
    }
    public void setReturnMsgDataId(String returnMsgDataId) 
    {
        this.returnMsgDataId = returnMsgDataId;
    }

    public String getReturnMsgDataId() 
    {
        return returnMsgDataId;
    }
    public void setPushStatus(String pushStatus) 
    {
        this.pushStatus = pushStatus;
    }

    public String getPushStatus() 
    {
        return pushStatus;
    }
    public void setPushTotalcount(Long pushTotalcount) 
    {
        this.pushTotalcount = pushTotalcount;
    }

    public Long getPushTotalcount() 
    {
        return pushTotalcount;
    }
    public void setPushFiltercount(Long pushFiltercount) 
    {
        this.pushFiltercount = pushFiltercount;
    }

    public Long getPushFiltercount() 
    {
        return pushFiltercount;
    }
    public void setPushSendcount(Long pushSendcount) 
    {
        this.pushSendcount = pushSendcount;
    }

    public Long getPushSendcount() 
    {
        return pushSendcount;
    }
    public void setPushErrorcount(Long pushErrorcount) 
    {
        this.pushErrorcount = pushErrorcount;
    }

    public Long getPushErrorcount() 
    {
        return pushErrorcount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sendLogId", getSendLogId())
            .append("sendJwid", getSendJwid())
            .append("sendJwidName", getSendJwidName())
            .append("msgType", getMsgType())
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("mediaId", getMediaId())
            .append("sendStatus", getSendStatus())
            .append("sendTime", getSendTime())
            .append("returnErrcode", getReturnErrcode())
            .append("returnErrmsg", getReturnErrmsg())
            .append("returnMsgId", getReturnMsgId())
            .append("returnMsgDataId", getReturnMsgDataId())
            .append("pushStatus", getPushStatus())
            .append("pushTotalcount", getPushTotalcount())
            .append("pushFiltercount", getPushFiltercount())
            .append("pushSendcount", getPushSendcount())
            .append("pushErrorcount", getPushErrorcount())
            .toString();
    }
}

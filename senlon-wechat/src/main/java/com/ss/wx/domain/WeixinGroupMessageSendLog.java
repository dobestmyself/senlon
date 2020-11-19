package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 群发消息日志对象 weixin_group_message_send_log
 * 
 * @author shishuai
 * @date 2020-11-10
 */
public class WeixinGroupMessageSendLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String msgType;

    /** 接受群组 */
    @Excel(name = "接受群组")
    private String groupId;

    /** 全部人员 */
    @Excel(name = "全部人员")
    private String isToAll;

    /** 参与 */
    @Excel(name = "参与")
    private String param;

    /** 公众号原始id */
    @Excel(name = "公众号原始id")
    private String jwid;

    /** 审核人名称 */
    @Excel(name = "审核人名称")
    private String auditName;


    /** 审核日期 */
    @Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date auditDate;

    /** 审核意见 */
    @Excel(name = "审核意见")
    private String auditRemark;

    /** 审核状态： */
    @Excel(name = "审核状态：")
    private String auditStatus;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date sendDate;

    /** 发送状态 */
    @Excel(name = "发送状态")
    private String sendStatus;

    /** 发送返回消息 */
    @Excel(name = "发送返回消息")
    private String sendResult;

    /** 发送的公众号原始id */
    @Excel(name = "发送的公众号原始id")
    private String sendJwid;

    /** 公众号名称 */
    @Excel(name = "公众号名称")
    private String sendJwidName;

    /** 模板id */
    @Excel(name = "模板id")
    private String templateId;

    /** 群发方式： */
    @Excel(name = "群发方式：")
    private String sendType;

    /** 定时群发时间 */
    @Excel(name = "定时群发时间", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date sendTaskTime;

    /** 标签id */
    @Excel(name = "标签id")
    private String tagId;

    /** 判定转载后是否继续群发 0:转载时停止群发 1:转载时继续群发 */
    @Excel(name = "判定转载后是否继续群发 0:转载时停止群发 1:转载时继续群发")
    private String sendIgnoreReprint;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setGroupId(String groupId) 
    {
        this.groupId = groupId;
    }

    public String getGroupId() 
    {
        return groupId;
    }
    public void setIsToAll(String isToAll) 
    {
        this.isToAll = isToAll;
    }

    public String getIsToAll() 
    {
        return isToAll;
    }
    public void setParam(String param) 
    {
        this.param = param;
    }

    public String getParam() 
    {
        return param;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setAuditName(String auditName) 
    {
        this.auditName = auditName;
    }

    public String getAuditName() 
    {
        return auditName;
    }
    public void setAuditDate(Date auditDate) 
    {
        this.auditDate = auditDate;
    }

    public Date getAuditDate() 
    {
        return auditDate;
    }
    public void setAuditRemark(String auditRemark) 
    {
        this.auditRemark = auditRemark;
    }

    public String getAuditRemark() 
    {
        return auditRemark;
    }
    public void setAuditStatus(String auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatus() 
    {
        return auditStatus;
    }
    public void setSendDate(Date sendDate) 
    {
        this.sendDate = sendDate;
    }

    public Date getSendDate() 
    {
        return sendDate;
    }
    public void setSendStatus(String sendStatus) 
    {
        this.sendStatus = sendStatus;
    }

    public String getSendStatus() 
    {
        return sendStatus;
    }
    public void setSendResult(String sendResult) 
    {
        this.sendResult = sendResult;
    }

    public String getSendResult() 
    {
        return sendResult;
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
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setSendType(String sendType) 
    {
        this.sendType = sendType;
    }

    public String getSendType() 
    {
        return sendType;
    }
    public void setSendTaskTime(Date sendTaskTime) 
    {
        this.sendTaskTime = sendTaskTime;
    }

    public Date getSendTaskTime() 
    {
        return sendTaskTime;
    }
    public void setTagId(String tagId) 
    {
        this.tagId = tagId;
    }

    public String getTagId() 
    {
        return tagId;
    }
    public void setSendIgnoreReprint(String sendIgnoreReprint) 
    {
        this.sendIgnoreReprint = sendIgnoreReprint;
    }

    public String getSendIgnoreReprint() 
    {
        return sendIgnoreReprint;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("msgType", getMsgType())
            .append("groupId", getGroupId())
            .append("isToAll", getIsToAll())
            .append("param", getParam())
            .append("jwid", getJwid())
            .append("auditName", getAuditName())
            .append("auditDate", getAuditDate())
            .append("auditRemark", getAuditRemark())
            .append("auditStatus", getAuditStatus())
            .append("sendDate", getSendDate())
            .append("sendStatus", getSendStatus())
            .append("sendResult", getSendResult())
            .append("sendJwid", getSendJwid())
            .append("sendJwidName", getSendJwidName())
            .append("templateId", getTemplateId())
            .append("sendType", getSendType())
            .append("sendTaskTime", getSendTaskTime())
            .append("tagId", getTagId())
            .append("sendIgnoreReprint", getSendIgnoreReprint())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}

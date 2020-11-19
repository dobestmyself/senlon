package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 模板消息发送对象 weixin_tmessagetask
 * 
 * @author shishuai
 * @date 2020-07-17
 */
public class WeixinTmessagetask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @Excel(name = "null")
    private String id;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String templateId;

    private String templateName;

    /** JWID */
    @Excel(name = "JWID")
    private String jwid;

    /** 发送模式  0：标签模式  1：列表模式 */
    @Excel(name = "发送模式  0：标签模式  1：列表模式")
    private String sendType;

    /** 标签ID */
    @Excel(name = "标签ID")
    private String tagId;

    private String tagName;

    /** OPENID */
    @Excel(name = "OPENID")
    private String openid;

    /** 跳转方式 0 ： 跳转链接 1：跳转小程序 */
    @Excel(name = "跳转方式 0 ： 跳转链接 1：跳转小程序")
    private String redirectMode;

    /** 跳转链接 */
    @Excel(name = "跳转链接")
    private String url;

    /** 小程序APPID */
    @Excel(name = "小程序APPID")
    private String miniAppid;

    /** 小程序跳转链接 */
    @Excel(name = "小程序跳转链接")
    private String pagepath;

    /** 颜色 */
    @Excel(name = "颜色")
    private String color;

    /** 发送总人数 */
    @Excel(name = "发送总人数")
    private Long totalCount;

    /** 成功总人数 */
    @Excel(name = "成功总人数")
    private Long successCount;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createDate;

    /** 发送时间 */
    @Excel(name = "发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date taskSendTime;

    /** 发送状态 0：未发送 1：已发送 2：发送失败 9：定时发送 */
    @Excel(name = "发送状态 0：未发送 1：已发送 2：发送失败 9：定时发送")
    private String taskSendStatus;

    /** 实际发送时间 */
    @Excel(name = "实际发送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date sendTime;

    /** JSON数据 */
    @Excel(name = "JSON数据")
    private String dataJson;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setSendType(String sendType) 
    {
        this.sendType = sendType;
    }

    public String getSendType() 
    {
        return sendType;
    }
    public void setTagId(String tagId) 
    {
        this.tagId = tagId;
    }

    public String getTagId() 
    {
        return tagId;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setRedirectMode(String redirectMode) 
    {
        this.redirectMode = redirectMode;
    }

    public String getRedirectMode() 
    {
        return redirectMode;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setMiniAppid(String miniAppid) 
    {
        this.miniAppid = miniAppid;
    }

    public String getMiniAppid() 
    {
        return miniAppid;
    }
    public void setPagepath(String pagepath) 
    {
        this.pagepath = pagepath;
    }

    public String getPagepath() 
    {
        return pagepath;
    }
    public void setColor(String color) 
    {
        this.color = color;
    }

    public String getColor() 
    {
        return color;
    }
    public void setTotalCount(Long totalCount) 
    {
        this.totalCount = totalCount;
    }

    public Long getTotalCount() 
    {
        return totalCount;
    }
    public void setSuccessCount(Long successCount) 
    {
        this.successCount = successCount;
    }

    public Long getSuccessCount() 
    {
        return successCount;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setTaskSendTime(Date taskSendTime) 
    {
        this.taskSendTime = taskSendTime;
    }

    public Date getTaskSendTime() 
    {
        return taskSendTime;
    }
    public void setTaskSendStatus(String taskSendStatus) 
    {
        this.taskSendStatus = taskSendStatus;
    }

    public String getTaskSendStatus() 
    {
        return taskSendStatus;
    }
    public void setSendTime(Date sendTime) 
    {
        this.sendTime = sendTime;
    }

    public Date getSendTime() 
    {
        return sendTime;
    }
    public void setDataJson(String dataJson) 
    {
        this.dataJson = dataJson;
    }

    public String getDataJson() 
    {
        return dataJson;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateId", getTemplateId())
            .append("jwid", getJwid())
            .append("sendType", getSendType())
            .append("tagId", getTagId())
            .append("openid", getOpenid())
            .append("redirectMode", getRedirectMode())
            .append("url", getUrl())
            .append("miniAppid", getMiniAppid())
            .append("pagepath", getPagepath())
            .append("color", getColor())
            .append("totalCount", getTotalCount())
            .append("successCount", getSuccessCount())
            .append("createDate", getCreateDate())
            .append("taskSendTime", getTaskSendTime())
            .append("taskSendStatus", getTaskSendStatus())
            .append("sendTime", getSendTime())
            .append("dataJson", getDataJson())
            .append("title", getTitle())
            .toString();
    }
}

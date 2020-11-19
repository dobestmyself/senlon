package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 微信第三方平台账号对象 weixin_open_account
 * 
 * @author shishuai
 * @date 2020-07-13
 */
public class WeixinOpenAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String id;

    /** AppID */
    @Excel(name = "AppID")
    private String appid;

    /** AppSecret */
    private String appsecret;

    /** 第三方平台推送 : ticket */
    @Excel(name = "第三方平台推送 : ticket")
    private String ticket;

    /** 推送时间 */
    @Excel(name = "推送时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date getTicketTime;

    /** 平台方access_token */
    private String componentAccessToken;

    /** 平台方获取access_token时间 */
    @Excel(name = "平台方获取access_token时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date getAccessTokenTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setAppid(String appid) 
    {
        this.appid = appid;
    }

    public String getAppid() 
    {
        return appid;
    }
    public void setAppsecret(String appsecret) 
    {
        this.appsecret = appsecret;
    }

    public String getAppsecret() 
    {
        return appsecret;
    }
    public void setTicket(String ticket) 
    {
        this.ticket = ticket;
    }

    public String getTicket() 
    {
        return ticket;
    }
    public void setGetTicketTime(Date getTicketTime) 
    {
        this.getTicketTime = getTicketTime;
    }

    public Date getGetTicketTime() 
    {
        return getTicketTime;
    }
    public void setComponentAccessToken(String componentAccessToken) 
    {
        this.componentAccessToken = componentAccessToken;
    }

    public String getComponentAccessToken() 
    {
        return componentAccessToken;
    }
    public void setGetAccessTokenTime(Date getAccessTokenTime) 
    {
        this.getAccessTokenTime = getAccessTokenTime;
    }

    public Date getGetAccessTokenTime() 
    {
        return getAccessTokenTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appid", getAppid())
            .append("appsecret", getAppsecret())
            .append("ticket", getTicket())
            .append("getTicketTime", getGetTicketTime())
            .append("componentAccessToken", getComponentAccessToken())
            .append("getAccessTokenTime", getGetAccessTokenTime())
            .toString();
    }
}

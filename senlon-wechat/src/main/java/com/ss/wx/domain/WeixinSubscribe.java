package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 关注欢迎语对象 weixin_subscribe
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public class WeixinSubscribe extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 微信ID */
    @Excel(name = "微信ID")
    private String jwid;

    /** 消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息) */
    @Excel(name = "消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)")
    private String msgType;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jwid", getJwid())
            .append("msgType", getMsgType())
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 关键字对象 weixin_autoresponse
 * 
 * @author shishuai
 * @date 2020-08-05
 */
public class WeixinAutoresponse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息) */
    @Excel(name = "消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)")
    private String msgType;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String templateId;

    /** 关联模板名称 */
    @Excel(name = "关联模板名称")
    private String templateName;

    /** 微信ID */
    @Excel(name = "微信ID")
    private String jwid;

    /** 关键字类型1:全匹配  2：模糊匹配 */
    @Excel(name = "关键字类型1:全匹配  2：模糊匹配")
    private String keywordType;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String iswork;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }

    public String getKeyword() 
    {
        return keyword;
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
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setKeywordType(String keywordType) 
    {
        this.keywordType = keywordType;
    }

    public String getKeywordType() 
    {
        return keywordType;
    }
    public void setIswork(String iswork) 
    {
        this.iswork = iswork;
    }

    public String getIswork() 
    {
        return iswork;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("keyword", getKeyword())
            .append("msgType", getMsgType())
            .append("templateId", getTemplateId())
            .append("templateName", getTemplateName())
            .append("jwid", getJwid())
            .append("keywordType", getKeywordType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("iswork", getIswork())
            .toString();
    }
}

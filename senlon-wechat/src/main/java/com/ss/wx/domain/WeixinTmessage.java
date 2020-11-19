package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 消息模板对象 weixin_tmessage
 * 
 * @author shishuai
 * @date 2020-07-16
 */
public class WeixinTmessage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 模板ID */
    @Excel(name = "模板ID")
    private String templateId;

    /** JWID */
    @Excel(name = "JWID")
    private String jwid;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 主行业 */
    @Excel(name = "主行业")
    private String industry;

    /** 子行业 */
    @Excel(name = "子行业")
    private String subIndustry;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 示例 */
    @Excel(name = "示例")
    private String example;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date createDate;

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
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setSubIndustry(String subIndustry) 
    {
        this.subIndustry = subIndustry;
    }

    public String getSubIndustry() 
    {
        return subIndustry;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setExample(String example) 
    {
        this.example = example;
    }

    public String getExample() 
    {
        return example;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateId", getTemplateId())
            .append("jwid", getJwid())
            .append("title", getTitle())
            .append("industry", getIndustry())
            .append("subIndustry", getSubIndustry())
            .append("content", getContent())
            .append("example", getExample())
            .append("createDate", getCreateDate())
            .toString();
    }
}

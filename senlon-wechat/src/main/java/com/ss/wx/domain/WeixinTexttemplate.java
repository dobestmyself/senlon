package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 文本模板对象 weixin_texttemplate
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public class WeixinTexttemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板内容 */
    @Excel(name = "模板内容")
    private String templateContent;

    /** 公众号原始id */
    private String jwid;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTemplateName(String templateName) 
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setTemplateContent(String templateContent) 
    {
        this.templateContent = templateContent;
    }

    public String getTemplateContent() 
    {
        return templateContent;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateName", getTemplateName())
            .append("templateContent", getTemplateContent())
            .append("jwid", getJwid())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

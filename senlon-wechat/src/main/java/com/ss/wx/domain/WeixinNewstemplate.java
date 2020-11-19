package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 图文模板对象 weixin_newstemplate
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public class WeixinNewstemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    private String templateId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String templateName;

    /** 模板类型 */
    private String templateType;

    /** 图文素材媒体id */
    private String mediaId;

    /** 公众号原始id */
    private String jwid;

    /** 上传状态 "0"未上传，"1"上传中，"2"上传成功，"3"上传失败 */
    private String uploadType;

    /** 上传时间 */
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date uploadTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public void setTemplateName(String templateName)
    {
        this.templateName = templateName;
    }

    public String getTemplateName() 
    {
        return templateName;
    }
    public void setTemplateType(String templateType) 
    {
        this.templateType = templateType;
    }

    public String getTemplateType() 
    {
        return templateType;
    }
    public void setMediaId(String mediaId) 
    {
        this.mediaId = mediaId;
    }

    public String getMediaId() 
    {
        return mediaId;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setUploadType(String uploadType) 
    {
        this.uploadType = uploadType;
    }

    public String getUploadType() 
    {
        return uploadType;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateName", getTemplateName())
            .append("templateType", getTemplateType())
            .append("mediaId", getMediaId())
            .append("jwid", getJwid())
            .append("uploadType", getUploadType())
            .append("uploadTime", getUploadTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

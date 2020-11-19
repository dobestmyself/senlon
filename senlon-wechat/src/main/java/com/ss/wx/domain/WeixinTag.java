package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 粉丝标签对象 weixin_tag
 * 
 * @author shishuai
 * @date 2020-07-13
 */
public class WeixinTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    @Excel(name = "null")
    private String id;

    /** 标签id */
    @Excel(name = "标签id")
    private String tagid;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String name;

    /** 标签归属公众号原始id */
    @Excel(name = "标签归属公众号原始id")
    private String jwid;

    /** 添加时间 */
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date addtime;

    /** 同步时间 */
    @Excel(name = "同步时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date synctime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTagid(String tagid) 
    {
        this.tagid = tagid;
    }

    public String getTagid() 
    {
        return tagid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setAddtime(Date addtime) 
    {
        this.addtime = addtime;
    }

    public Date getAddtime() 
    {
        return addtime;
    }
    public void setSynctime(Date synctime) 
    {
        this.synctime = synctime;
    }

    public Date getSynctime() 
    {
        return synctime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tagid", getTagid())
            .append("name", getName())
            .append("jwid", getJwid())
            .append("addtime", getAddtime())
            .append("synctime", getSynctime())
            .toString();
    }
}

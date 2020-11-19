package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 图文模板素材对象 weixin_newsitem
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public class WeixinNewsitem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 图文id */
    private String newstemplateId;

    /** 图文缩略图的media_id */
    private String thumbMediaId;

    /** 标题 */
    private String title;

    /** 作者 */
    private String author;

    /** 图片路径 */
    private String imagePath;

    /** 内容 */
    private String content;

    /** 摘要 */
    private String description;

    /** 素材顺序 */
    private String orderNo;

    /** 图文：news；外部url：url */
    private String newType;

    /** 原文链接 */
    private String url;

    /** 外部链接 */
    private String externalUrl;

    /** 是否显示封面： */
    private String showCoverPic;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setNewstemplateId(String newstemplateId) 
    {
        this.newstemplateId = newstemplateId;
    }

    public String getNewstemplateId() 
    {
        return newstemplateId;
    }
    public void setThumbMediaId(String thumbMediaId) 
    {
        this.thumbMediaId = thumbMediaId;
    }

    public String getThumbMediaId() 
    {
        return thumbMediaId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setImagePath(String imagePath) 
    {
        this.imagePath = imagePath;
    }

    public String getImagePath() 
    {
        return imagePath;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setNewType(String newType) 
    {
        this.newType = newType;
    }

    public String getNewType() 
    {
        return newType;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setExternalUrl(String externalUrl) 
    {
        this.externalUrl = externalUrl;
    }

    public String getExternalUrl() 
    {
        return externalUrl;
    }
    public void setShowCoverPic(String showCoverPic) 
    {
        this.showCoverPic = showCoverPic;
    }

    public String getShowCoverPic() 
    {
        return showCoverPic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("newstemplateId", getNewstemplateId())
            .append("thumbMediaId", getThumbMediaId())
            .append("title", getTitle())
            .append("author", getAuthor())
            .append("imagePath", getImagePath())
            .append("content", getContent())
            .append("description", getDescription())
            .append("orderNo", getOrderNo())
            .append("newType", getNewType())
            .append("url", getUrl())
            .append("externalUrl", getExternalUrl())
            .append("showCoverPic", getShowCoverPic())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

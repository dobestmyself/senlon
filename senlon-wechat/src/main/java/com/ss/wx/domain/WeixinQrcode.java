package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;

import java.util.Date;

/**
 * 二维码对象 weixin_qrcode
 * 
 * @author shishuai
 * @date 2020-08-06
 */
public class WeixinQrcode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private String id;

    /** 二维码标题（详情） */
    @Excel(name = "二维码标题", readConverterExp = "详=情")
    private String actionInfo;

    /** 二维码类型：QR_SCENE：临时整型，QR_STR_SCENE：临时字符串，QR_LIMIT_SCENE：永久整型，QR_LIMIT_STR_SCENE：永久字符串 */
    @Excel(name = "二维码类型：QR_SCENE：临时整型，QR_STR_SCENE：临时字符串，QR_LIMIT_SCENE：永久整型，QR_LIMIT_STR_SCENE：永久字符串")
    private String actionName;

    /** 整型场景值ID */
    @Excel(name = "整型场景值ID")
    private Integer sceneId;

    /** 字符串场景值ID */
    @Excel(name = "字符串场景值ID")
    private String sceneStr;

    /** 二维码ticket */
    @Excel(name = "二维码ticket")
    private String ticket;

    /** 有效时间(秒) */
    @Excel(name = "有效时间(秒)")
    private Long expireSeconds;

    /** 过期时间 */
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date expireDate;

    /** 二维码地址 */
    @Excel(name = "二维码地址")
    private String qrcodeUrl;

    /** 公众帐号ID */
    @Excel(name = "公众帐号ID")
    private String jwid;

    /** 触发类型：text文本/news图文 */
    @Excel(name = "触发类型：text文本/news图文")
    private String msgType;

    /** 文本内容 */
    @Excel(name = "文本内容")
    private String textContent;

    /** 图文选择类型（1：自定义，2：选择模板） */
    @Excel(name = "图文选择类型", readConverterExp = "1=：自定义，2：选择模板")
    private String actionNewsType;

    /** 图文标题 */
    @Excel(name = "图文标题")
    private String newsTitle;

    /** 图文摘要 */
    @Excel(name = "图文摘要")
    private String newsDesc;

    /** 图文封面图 */
    @Excel(name = "图文封面图")
    private String newsImg;

    /** 图文跳转链接 */
    @Excel(name = "图文跳转链接")
    private String newsUrl;

    /** 图文选择模板ID */
    @Excel(name = "图文选择模板ID")
    private String newsTemplateid;

    /** 标签，逗号隔开 */
    @Excel(name = "标签，逗号隔开")
    private String tags;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setActionInfo(String actionInfo) 
    {
        this.actionInfo = actionInfo;
    }

    public String getActionInfo() 
    {
        return actionInfo;
    }
    public void setActionName(String actionName) 
    {
        this.actionName = actionName;
    }

    public String getActionName() 
    {
        return actionName;
    }

    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    public void setSceneStr(String sceneStr)
    {
        this.sceneStr = sceneStr;
    }

    public String getSceneStr() 
    {
        return sceneStr;
    }
    public void setTicket(String ticket) 
    {
        this.ticket = ticket;
    }

    public String getTicket() 
    {
        return ticket;
    }
    public void setExpireSeconds(Long expireSeconds) 
    {
        this.expireSeconds = expireSeconds;
    }

    public Long getExpireSeconds() 
    {
        return expireSeconds;
    }
    public void setExpireDate(Date expireDate) 
    {
        this.expireDate = expireDate;
    }

    public Date getExpireDate() 
    {
        return expireDate;
    }
    public void setQrcodeUrl(String qrcodeUrl) 
    {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getQrcodeUrl() 
    {
        return qrcodeUrl;
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
    public void setTextContent(String textContent) 
    {
        this.textContent = textContent;
    }

    public String getTextContent() 
    {
        return textContent;
    }
    public void setActionNewsType(String actionNewsType) 
    {
        this.actionNewsType = actionNewsType;
    }

    public String getActionNewsType() 
    {
        return actionNewsType;
    }
    public void setNewsTitle(String newsTitle) 
    {
        this.newsTitle = newsTitle;
    }

    public String getNewsTitle() 
    {
        return newsTitle;
    }
    public void setNewsDesc(String newsDesc) 
    {
        this.newsDesc = newsDesc;
    }

    public String getNewsDesc() 
    {
        return newsDesc;
    }
    public void setNewsImg(String newsImg) 
    {
        this.newsImg = newsImg;
    }

    public String getNewsImg() 
    {
        return newsImg;
    }
    public void setNewsUrl(String newsUrl) 
    {
        this.newsUrl = newsUrl;
    }

    public String getNewsUrl() 
    {
        return newsUrl;
    }
    public void setNewsTemplateid(String newsTemplateid) 
    {
        this.newsTemplateid = newsTemplateid;
    }

    public String getNewsTemplateid() 
    {
        return newsTemplateid;
    }
    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("actionInfo", getActionInfo())
            .append("actionName", getActionName())
            .append("sceneId", getSceneId())
            .append("sceneStr", getSceneStr())
            .append("ticket", getTicket())
            .append("expireSeconds", getExpireSeconds())
            .append("expireDate", getExpireDate())
            .append("qrcodeUrl", getQrcodeUrl())
            .append("jwid", getJwid())
            .append("msgType", getMsgType())
            .append("textContent", getTextContent())
            .append("actionNewsType", getActionNewsType())
            .append("newsTitle", getNewsTitle())
            .append("newsDesc", getNewsDesc())
            .append("newsImg", getNewsImg())
            .append("newsUrl", getNewsUrl())
            .append("newsTemplateid", getNewsTemplateid())
            .append("tags", getTags())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

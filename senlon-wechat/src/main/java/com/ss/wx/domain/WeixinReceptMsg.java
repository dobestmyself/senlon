package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 客服消息记录对象 weixin_recept_msg
 * 
 * @author shishuai
 * @date 2020-11-06
 */
public class WeixinReceptMsg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 发送人 */
    @Excel(name = "发送人")
    private String fromUsername;

    /** 接收人 */
    @Excel(name = "接收人")
    private String toUsername;

    /** 消息类型 */
    @Excel(name = "消息类型")
    private String msgtype;

    /** 多媒体id */
    @Excel(name = "多媒体id")
    private String mediaid;

    /** 素材ID */
    @Excel(name = "素材ID")
    private String templateId;

    /** 发送状态，1：成功：2:失败 */
    @Excel(name = "发送状态，1：成功：2:失败")
    private String sendStauts;

    /** 公众号ID */
    @Excel(name = "公众号ID")
    private String jwid;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setFromUsername(String fromUsername) 
    {
        this.fromUsername = fromUsername;
    }

    public String getFromUsername() 
    {
        return fromUsername;
    }
    public void setToUsername(String toUsername) 
    {
        this.toUsername = toUsername;
    }

    public String getToUsername() 
    {
        return toUsername;
    }
    public void setMsgtype(String msgtype) 
    {
        this.msgtype = msgtype;
    }

    public String getMsgtype() 
    {
        return msgtype;
    }
    public void setMediaid(String mediaid) 
    {
        this.mediaid = mediaid;
    }

    public String getMediaid() 
    {
        return mediaid;
    }
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setSendStauts(String sendStauts) 
    {
        this.sendStauts = sendStauts;
    }

    public String getSendStauts() 
    {
        return sendStauts;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("fromUsername", getFromUsername())
            .append("toUsername", getToUsername())
            .append("msgtype", getMsgtype())
            .append("mediaid", getMediaid())
            .append("templateId", getTemplateId())
            .append("sendStauts", getSendStauts())
            .append("jwid", getJwid())
            .toString();
    }
}

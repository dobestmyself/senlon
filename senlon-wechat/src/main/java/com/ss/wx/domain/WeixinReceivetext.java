package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 消息存储对象 weixin_receivetext
 * 
 * @author shishuai
 * @date 2020-11-06
 */
public class WeixinReceivetext extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 发送方帐号（OpenId） */
    @Excel(name = "发送方帐号", readConverterExp = "O=penId")
    private String fromUserName;

    /** 消息ID */
    @Excel(name = "消息ID")
    private String msgId;

    /** 消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)消息类型 */
    @Excel(name = "消息类型(text:文本消息,news:图文消息,voice:音频消息,video:视频消息,image,图片消息)消息类型")
    private String msgType;

    /** JWID */
    @Excel(name = "JWID")
    private String toUserName;

    /** 公众号原始ID */
    @Excel(name = "公众号原始ID")
    private String jwid;

    /** 多媒体ID */
    @Excel(name = "多媒体ID")
    private String mediaId;

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
    public void setFromUserName(String fromUserName) 
    {
        this.fromUserName = fromUserName;
    }

    public String getFromUserName() 
    {
        return fromUserName;
    }
    public void setMsgId(String msgId) 
    {
        this.msgId = msgId;
    }

    public String getMsgId() 
    {
        return msgId;
    }
    public void setMsgType(String msgType) 
    {
        this.msgType = msgType;
    }

    public String getMsgType() 
    {
        return msgType;
    }
    public void setToUserName(String toUserName) 
    {
        this.toUserName = toUserName;
    }

    public String getToUserName() 
    {
        return toUserName;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setMediaId(String mediaId) 
    {
        this.mediaId = mediaId;
    }

    public String getMediaId() 
    {
        return mediaId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("fromUserName", getFromUserName())
            .append("msgId", getMsgId())
            .append("msgType", getMsgType())
            .append("toUserName", getToUserName())
            .append("jwid", getJwid())
            .append("mediaId", getMediaId())
            .toString();
    }
}

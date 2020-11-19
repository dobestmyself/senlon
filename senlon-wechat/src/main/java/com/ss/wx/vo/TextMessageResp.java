package com.ss.wx.vo;

public class TextMessageResp {
    //回复的消息内容
    private String Content;
    public String getContent(){return Content;}
    public void setContent(String content){Content = content;}
    //接收方账号
    private String ToUserName;
    //开发者微信号
    private String FromUserName;
    //消息创建时间
    private long CreateTime;
    //消息类型
    private String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}

package com.ss.wx.vo;

/**
 * 视频消息
 * @author Administrator
 *
 */
public class VideoMessageResp{
	//update-begin--Author:zhangweijian  Date: 20181105 for：改成不继承的
	// 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/music/news）
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
	//update-end--Author:zhangweijian  Date: 20181105 for：改成不继承的
	// 视频
    private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video Video) {
		this.Video = Video;
	}
    
}

package com.ss.wx.model;

public class SendGroupMessageText extends BaseMessage{
	
	private Text text;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
}

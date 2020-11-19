package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 二维码扫码记录对象 weixin_qrcode_scan_record
 * 
 * @author shishuai
 * @date 2020-08-06
 */
public class WeixinQrcodeScanRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** openid */
    @Excel(name = "openid")
    private String openid;

    /** 扫码时间 */
    @Excel(name = "扫码时间", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date scanTime;

    /** 场景值ID */
    @Excel(name = "场景值ID")
    private String sceneId;

    /** 公众号ID */
    @Excel(name = "公众号ID")
    private String jwid;

    /** 是否扫码关注 0:非扫码关注,1:扫码关注 */
    @Excel(name = "是否扫码关注 0:非扫码关注,1:扫码关注")
    private String isScanSubscribe;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setScanTime(Date scanTime) 
    {
        this.scanTime = scanTime;
    }

    public Date getScanTime() 
    {
        return scanTime;
    }
    public void setSceneId(String sceneId) 
    {
        this.sceneId = sceneId;
    }

    public String getSceneId() 
    {
        return sceneId;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setIsScanSubscribe(String isScanSubscribe) 
    {
        this.isScanSubscribe = isScanSubscribe;
    }

    public String getIsScanSubscribe() 
    {
        return isScanSubscribe;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openid", getOpenid())
            .append("scanTime", getScanTime())
            .append("sceneId", getSceneId())
            .append("jwid", getJwid())
            .append("isScanSubscribe", getIsScanSubscribe())
            .toString();
    }
}

package com.ss.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 粉丝对象 weixin_gzuser
 * 
 * @author shishuai
 * @date 2020-07-15
 */
public class WeixinGzuser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private String id;

    /** openid */
    @Excel(name = "openid")
    private String openid;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 过滤后昵称 */
    @Excel(name = "过滤后昵称")
    private String nicknameTxt;

    /** 备注名称 */
    @Excel(name = "备注名称")
    private String bzname;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String headimgurl;

    /** 性别： */
    @Excel(name = "性别：")
    private String sex;

    /** 是否关注: */
    @Excel(name = "是否关注:")
    private String subscribe;

    /** 关注时间 */
    @Excel(name = "关注时间")
    private String subscribeTime;

    /** 用户关注渠道来源 */
    @Excel(name = "用户关注渠道来源")
    private String subscribeScene;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 绑定状态： */
    @Excel(name = "绑定状态：")
    private String bindStatus;

    /** 绑定时间 */
    @Excel(name = "绑定时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Asia/Shanghai")
    private Date bindTime;

    /** 标签id */
    @Excel(name = "标签id")
    private String tagidList;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 地区 */
    @Excel(name = "地区")
    private String country;

    /** 二维码扫码场景 */
    @Excel(name = "二维码扫码场景")
    private String qrScene;

    /** 二维码扫码常见描述 */
    @Excel(name = "二维码扫码常见描述")
    private String qrSceneStr;

    /** 用户所在分组id */
    @Excel(name = "用户所在分组id")
    private String groupid;

    /** 用户的语言，简体中文为zh_CN */
    @Excel(name = "用户的语言，简体中文为zh_CN")
    private String language;

    /** null */
    @Excel(name = "null")
    private String unionid;

    /** 公众号原始id */
    @Excel(name = "公众号原始id")
    private String jwid;

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
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setNicknameTxt(String nicknameTxt) 
    {
        this.nicknameTxt = nicknameTxt;
    }

    public String getNicknameTxt() 
    {
        return nicknameTxt;
    }
    public void setBzname(String bzname) 
    {
        this.bzname = bzname;
    }

    public String getBzname() 
    {
        return bzname;
    }
    public void setHeadimgurl(String headimgurl) 
    {
        this.headimgurl = headimgurl;
    }

    public String getHeadimgurl() 
    {
        return headimgurl;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setSubscribe(String subscribe) 
    {
        this.subscribe = subscribe;
    }

    public String getSubscribe() 
    {
        return subscribe;
    }
    public void setSubscribeTime(String subscribeTime) 
    {
        this.subscribeTime = subscribeTime;
    }

    public String getSubscribeTime() 
    {
        return subscribeTime;
    }
    public void setSubscribeScene(String subscribeScene) 
    {
        this.subscribeScene = subscribeScene;
    }

    public String getSubscribeScene() 
    {
        return subscribeScene;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setBindStatus(String bindStatus) 
    {
        this.bindStatus = bindStatus;
    }

    public String getBindStatus() 
    {
        return bindStatus;
    }
    public void setBindTime(Date bindTime) 
    {
        this.bindTime = bindTime;
    }

    public Date getBindTime() 
    {
        return bindTime;
    }
    public void setTagidList(String tagidList) 
    {
        this.tagidList = tagidList;
    }

    public String getTagidList() 
    {
        return tagidList;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCountry(String country) 
    {
        this.country = country;
    }

    public String getCountry() 
    {
        return country;
    }
    public void setQrScene(String qrScene) 
    {
        this.qrScene = qrScene;
    }

    public String getQrScene() 
    {
        return qrScene;
    }
    public void setQrSceneStr(String qrSceneStr) 
    {
        this.qrSceneStr = qrSceneStr;
    }

    public String getQrSceneStr() 
    {
        return qrSceneStr;
    }
    public void setGroupid(String groupid) 
    {
        this.groupid = groupid;
    }

    public String getGroupid() 
    {
        return groupid;
    }
    public void setLanguage(String language) 
    {
        this.language = language;
    }

    public String getLanguage() 
    {
        return language;
    }
    public void setUnionid(String unionid) 
    {
        this.unionid = unionid;
    }

    public String getUnionid() 
    {
        return unionid;
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
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("nicknameTxt", getNicknameTxt())
            .append("bzname", getBzname())
            .append("headimgurl", getHeadimgurl())
            .append("sex", getSex())
            .append("subscribe", getSubscribe())
            .append("subscribeTime", getSubscribeTime())
            .append("subscribeScene", getSubscribeScene())
            .append("mobile", getMobile())
            .append("bindStatus", getBindStatus())
            .append("bindTime", getBindTime())
            .append("tagidList", getTagidList())
            .append("province", getProvince())
            .append("city", getCity())
            .append("country", getCountry())
            .append("qrScene", getQrScene())
            .append("qrSceneStr", getQrSceneStr())
            .append("groupid", getGroupid())
            .append("language", getLanguage())
            .append("unionid", getUnionid())
            .append("jwid", getJwid())
            .append("createTime", getCreateTime())
            .toString();
    }
}

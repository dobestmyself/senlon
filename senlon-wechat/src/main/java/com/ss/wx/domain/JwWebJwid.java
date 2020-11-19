package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 系统公众号对象 jw_web_jwid
 * 
 * @author shishuai
 * @date 2020-07-05
 */
public class JwWebJwid extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 公众号 */
    @Excel(name = "公众号")
    private String jwid;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 应用类型 */
    @Excel(name = "应用类型")
    private String applicationType;

    /** 微信二维码图片 */
    @Excel(name = "微信二维码图片")
    private String qrcodeimg;

    /** 微信号 */
    @Excel(name = "微信号")
    private String weixinNumber;

    /** 微信AppId */
    @Excel(name = "微信AppId")
    private String weixinAppid;

    /** 微信AppSecret */
    private String weixinAppsecret;

    /** 公众号类型 */
    @Excel(name = "公众号类型")
    private String accountType;

    /** 是否认证 */
    @Excel(name = "是否认证")
    private String authStatus;

    /** Access_Token */
    private String accessToken;

    /** token获取的时间 */
    private Date tokenGettime;

    /** api凭证 */
    private String apiticket;

    /** apiticket获取时间 */
    private Date apiticketGettime;

    /** jsapiticket */
    private String jsapiticket;

    /** jsapiticket获取时间 */
    private Date jsapiticketGettime;

    /** 类型：1手动授权，2扫码授权 */
    private String authType;

    /** 功能的开通状况：1代表已开通 */
    private String businessInfo;

    /** 公众号授权给开发者的权限集 */
    private String funcInfo;

    /** 授权方昵称 */
    private String nickName;

    /** 授权方头像 */
    private String headimgurl;

    /** 授权信息 */
    private String authorizationInfo;

    /** 刷新token */
    private String authorizerRefreshToken;

    /** 令牌 */
    private String token;

    /** 授权状态（1正常，2已取消） */
    private String authorizationStatus;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setApplicationType(String applicationType) 
    {
        this.applicationType = applicationType;
    }

    public String getApplicationType() 
    {
        return applicationType;
    }
    public void setQrcodeimg(String qrcodeimg) 
    {
        this.qrcodeimg = qrcodeimg;
    }

    public String getQrcodeimg() 
    {
        return qrcodeimg;
    }
    public void setWeixinNumber(String weixinNumber) 
    {
        this.weixinNumber = weixinNumber;
    }

    public String getWeixinNumber() 
    {
        return weixinNumber;
    }
    public void setWeixinAppid(String weixinAppid) 
    {
        this.weixinAppid = weixinAppid;
    }

    public String getWeixinAppid() 
    {
        return weixinAppid;
    }
    public void setWeixinAppsecret(String weixinAppsecret) 
    {
        this.weixinAppsecret = weixinAppsecret;
    }

    public String getWeixinAppsecret() 
    {
        return weixinAppsecret;
    }
    public void setAccountType(String accountType) 
    {
        this.accountType = accountType;
    }

    public String getAccountType() 
    {
        return accountType;
    }
    public void setAuthStatus(String authStatus) 
    {
        this.authStatus = authStatus;
    }

    public String getAuthStatus() 
    {
        return authStatus;
    }
    public void setAccessToken(String accessToken) 
    {
        this.accessToken = accessToken;
    }

    public String getAccessToken() 
    {
        return accessToken;
    }
    public void setTokenGettime(Date tokenGettime) 
    {
        this.tokenGettime = tokenGettime;
    }

    public Date getTokenGettime() 
    {
        return tokenGettime;
    }
    public void setApiticket(String apiticket) 
    {
        this.apiticket = apiticket;
    }

    public String getApiticket() 
    {
        return apiticket;
    }
    public void setApiticketGettime(Date apiticketGettime) 
    {
        this.apiticketGettime = apiticketGettime;
    }

    public Date getApiticketGettime() 
    {
        return apiticketGettime;
    }
    public void setJsapiticket(String jsapiticket) 
    {
        this.jsapiticket = jsapiticket;
    }

    public String getJsapiticket() 
    {
        return jsapiticket;
    }
    public void setJsapiticketGettime(Date jsapiticketGettime) 
    {
        this.jsapiticketGettime = jsapiticketGettime;
    }

    public Date getJsapiticketGettime() 
    {
        return jsapiticketGettime;
    }
    public void setAuthType(String authType) 
    {
        this.authType = authType;
    }

    public String getAuthType() 
    {
        return authType;
    }
    public void setBusinessInfo(String businessInfo) 
    {
        this.businessInfo = businessInfo;
    }

    public String getBusinessInfo() 
    {
        return businessInfo;
    }
    public void setFuncInfo(String funcInfo) 
    {
        this.funcInfo = funcInfo;
    }

    public String getFuncInfo() 
    {
        return funcInfo;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setHeadimgurl(String headimgurl) 
    {
        this.headimgurl = headimgurl;
    }

    public String getHeadimgurl() 
    {
        return headimgurl;
    }
    public void setAuthorizationInfo(String authorizationInfo) 
    {
        this.authorizationInfo = authorizationInfo;
    }

    public String getAuthorizationInfo() 
    {
        return authorizationInfo;
    }
    public void setAuthorizerRefreshToken(String authorizerRefreshToken) 
    {
        this.authorizerRefreshToken = authorizerRefreshToken;
    }

    public String getAuthorizerRefreshToken() 
    {
        return authorizerRefreshToken;
    }
    public void setToken(String token) 
    {
        this.token = token;
    }

    public String getToken() 
    {
        return token;
    }
    public void setAuthorizationStatus(String authorizationStatus) 
    {
        this.authorizationStatus = authorizationStatus;
    }

    public String getAuthorizationStatus() 
    {
        return authorizationStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jwid", getJwid())
            .append("name", getName())
            .append("applicationType", getApplicationType())
            .append("qrcodeimg", getQrcodeimg())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("weixinNumber", getWeixinNumber())
            .append("weixinAppid", getWeixinAppid())
            .append("weixinAppsecret", getWeixinAppsecret())
            .append("accountType", getAccountType())
            .append("authStatus", getAuthStatus())
            .append("accessToken", getAccessToken())
            .append("tokenGettime", getTokenGettime())
            .append("apiticket", getApiticket())
            .append("apiticketGettime", getApiticketGettime())
            .append("jsapiticket", getJsapiticket())
            .append("jsapiticketGettime", getJsapiticketGettime())
            .append("authType", getAuthType())
            .append("businessInfo", getBusinessInfo())
            .append("funcInfo", getFuncInfo())
            .append("nickName", getNickName())
            .append("headimgurl", getHeadimgurl())
            .append("authorizationInfo", getAuthorizationInfo())
            .append("authorizerRefreshToken", getAuthorizerRefreshToken())
            .append("token", getToken())
            .append("authorizationStatus", getAuthorizationStatus())
            .toString();
    }
}

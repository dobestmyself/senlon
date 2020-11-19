package com.ss.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.TreeEntity;

/**
 * 微信菜单对象 weixin_menu
 * 
 * @author shishuai
 * @date 2020-07-14
 */
public class WeixinMenu extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private Long id;

    /** 父id */
    @Excel(name = "父id")
    private Long fatherId;

    /** 菜单KEY */
    @Excel(name = "菜单KEY")
    private String menuKey;

    /** 菜单类型 */
    @Excel(name = "菜单类型")
    private String menuType;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String menuName;

    /** 网页链接 */
    @Excel(name = "网页链接")
    private String url;

    /** 相应消息类型 */
    @Excel(name = "相应消息类型")
    private String msgtype;

    /** 菜单位置 */
    @Excel(name = "菜单位置")
    private String orders;

    /** 关联素材id */
    @Excel(name = "关联素材id")
    private String templateId;

    /** 公众号原始id */
    @Excel(name = "公众号原始id")
    private String jwid;

    /** 小程序appid */
    @Excel(name = "小程序appid")
    private String miniprogramAppid;

    /** 小程序页面路径 */
    @Excel(name = "小程序页面路径")
    private String miniprogramPagepath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFatherId() {
        return fatherId;
    }

    public void setFatherId(Long fatherId) {
        this.fatherId = fatherId;
    }

    public void setMenuKey(String menuKey)
    {
        this.menuKey = menuKey;
    }

    public String getMenuKey() 
    {
        return menuKey;
    }
    public void setMenuType(String menuType) 
    {
        this.menuType = menuType;
    }

    public String getMenuType() 
    {
        return menuType;
    }
    public void setMenuName(String menuName) 
    {
        this.menuName = menuName;
    }

    public String getMenuName() 
    {
        return menuName;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setMsgtype(String msgtype) 
    {
        this.msgtype = msgtype;
    }

    public String getMsgtype() 
    {
        return msgtype;
    }
    public void setOrders(String orders) 
    {
        this.orders = orders;
    }

    public String getOrders() 
    {
        return orders;
    }
    public void setTemplateId(String templateId) 
    {
        this.templateId = templateId;
    }

    public String getTemplateId() 
    {
        return templateId;
    }
    public void setJwid(String jwid) 
    {
        this.jwid = jwid;
    }

    public String getJwid() 
    {
        return jwid;
    }
    public void setMiniprogramAppid(String miniprogramAppid) 
    {
        this.miniprogramAppid = miniprogramAppid;
    }

    public String getMiniprogramAppid() 
    {
        return miniprogramAppid;
    }
    public void setMiniprogramPagepath(String miniprogramPagepath) 
    {
        this.miniprogramPagepath = miniprogramPagepath;
    }

    public String getMiniprogramPagepath() 
    {
        return miniprogramPagepath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fatherId", getFatherId())
            .append("menuKey", getMenuKey())
            .append("menuType", getMenuType())
            .append("menuName", getMenuName())
            .append("url", getUrl())
            .append("msgtype", getMsgtype())
            .append("orders", getOrders())
            .append("templateId", getTemplateId())
            .append("jwid", getJwid())
            .append("miniprogramAppid", getMiniprogramAppid())
            .append("miniprogramPagepath", getMiniprogramPagepath())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

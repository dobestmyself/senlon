package com.ss.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统权限菜单对象 jw_system_auth
 * 
 * @author shishuai
 * @date 2020-07-03
 */
public class JwSystemAuth extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 权限编码 */
    @Excel(name = "权限编码")
    private Long authId;

    /** 权限名称 */
    @Excel(name = "权限名称")
    private String authName;

    /** 是否记录日志 0不记录 1记录 */
    private String isLogs;

    /** 权限类型 0:菜单;1:功能 */
    @Excel(name = "权限类型 0:菜单;1:功能")
    private String authType;

    /** 权限说明 */
    private String authDesc;

    /** 权限控制 */
    @Excel(name = "权限控制")
    private String authContr;

    /** 上一级权限编码 */
    private Long parentAuthId;

    private String parentAuthName;

    private List<JwSystemAuth> children = new ArrayList<>();

    /** 排序 */
    @Excel(name = "排序")
    private Long sortNo;

    /** 层级 */
    private String bizLevel;

    /** 是否叶子节点 */
    private String leafInd;

    /** 删除标志 0未删除 1已删除 */
    private String delStat;

    /** 图标类型 */
    @Excel(name = "图标类型")
    private String iconType;


    public List<JwSystemAuth> getChildren() {
        return children;
    }

    public void setChildren(List<JwSystemAuth> children) {
        this.children = children;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public void setParentAuthId(Long parentAuthId) {
        this.parentAuthId = parentAuthId;
    }

    public Long getParentAuthId() {
        return parentAuthId;
    }


    public String getParentAuthName() {
        return parentAuthName;
    }

    public void setParentAuthName(String parentAuthName) {
        this.parentAuthName = parentAuthName;
    }

    public void setAuthName(String authName)
    {
        this.authName = authName;
    }

    public String getAuthName() 
    {
        return authName;
    }
    public void setIsLogs(String isLogs) 
    {
        this.isLogs = isLogs;
    }

    public String getIsLogs() 
    {
        return isLogs;
    }
    public void setAuthType(String authType) 
    {
        this.authType = authType;
    }

    public String getAuthType() 
    {
        return authType;
    }
    public void setAuthDesc(String authDesc) 
    {
        this.authDesc = authDesc;
    }

    public String getAuthDesc() 
    {
        return authDesc;
    }
    public void setAuthContr(String authContr) 
    {
        this.authContr = authContr;
    }

    public String getAuthContr() 
    {
        return authContr;
    }

    public void setSortNo(Long sortNo) 
    {
        this.sortNo = sortNo;
    }

    public Long getSortNo() 
    {
        return sortNo;
    }
    public void setBizLevel(String bizLevel) 
    {
        this.bizLevel = bizLevel;
    }

    public String getBizLevel() 
    {
        return bizLevel;
    }
    public void setLeafInd(String leafInd) 
    {
        this.leafInd = leafInd;
    }

    public String getLeafInd() 
    {
        return leafInd;
    }
    public void setDelStat(String delStat) 
    {
        this.delStat = delStat;
    }

    public String getDelStat() 
    {
        return delStat;
    }
    public void setIconType(String iconType) 
    {
        this.iconType = iconType;
    }

    public String getIconType() 
    {
        return iconType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("authId", getAuthId())
            .append("authName", getAuthName())
            .append("isLogs", getIsLogs())
            .append("authType", getAuthType())
            .append("authDesc", getAuthDesc())
            .append("authContr", getAuthContr())
            .append("parentAuthId", getParentAuthId())
            .append("sortNo", getSortNo())
            .append("bizLevel", getBizLevel())
            .append("leafInd", getLeafInd())
            .append("delStat", getDelStat())
            .append("iconType", getIconType())
            .toString();
    }
}

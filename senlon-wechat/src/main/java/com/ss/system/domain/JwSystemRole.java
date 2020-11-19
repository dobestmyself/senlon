package com.ss.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 系统角色对象 jw_system_role
 * 
 * @author shishuai
 * @date 2020-07-03
 */
public class JwSystemRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 角色编码 */
    @Excel(name = "角色编码")
    private String roleId;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 创建人 */
    private String crtOperator;

    /** 创建日期 */
    private Date crtDt;

    /** 角色种类 */
    private String roleTyp;

    /** 删除标志 */
    private String delStat;

    /** 建立者 */
    private String creator;

    /** 编辑者 */
    private String editor;

    /** 建立日期 */
    private Date createDt;

    /** 编辑日期 */
    private Date editDt;

    private String[] authIds;

    public String[] getAuthIds() {
        return authIds;
    }

    public void setAuthIds(String[] authIds) {
        this.authIds = authIds;
    }

    /** 上次修改日期 */
    @Excel(name = "上次修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date lastEditDt;

    /** 版本号 */
    private String recordVersion;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRoleId(String roleId) 
    {
        this.roleId = roleId;
    }

    public String getRoleId() 
    {
        return roleId;
    }
    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }
    public void setCrtOperator(String crtOperator) 
    {
        this.crtOperator = crtOperator;
    }

    public String getCrtOperator() 
    {
        return crtOperator;
    }
    public void setCrtDt(Date crtDt) 
    {
        this.crtDt = crtDt;
    }

    public Date getCrtDt() 
    {
        return crtDt;
    }
    public void setRoleTyp(String roleTyp) 
    {
        this.roleTyp = roleTyp;
    }

    public String getRoleTyp() 
    {
        return roleTyp;
    }
    public void setDelStat(String delStat) 
    {
        this.delStat = delStat;
    }

    public String getDelStat() 
    {
        return delStat;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setEditor(String editor) 
    {
        this.editor = editor;
    }

    public String getEditor() 
    {
        return editor;
    }
    public void setCreateDt(Date createDt) 
    {
        this.createDt = createDt;
    }

    public Date getCreateDt() 
    {
        return createDt;
    }
    public void setEditDt(Date editDt) 
    {
        this.editDt = editDt;
    }

    public Date getEditDt() 
    {
        return editDt;
    }
    public void setLastEditDt(Date lastEditDt) 
    {
        this.lastEditDt = lastEditDt;
    }

    public Date getLastEditDt() 
    {
        return lastEditDt;
    }
    public void setRecordVersion(String recordVersion) 
    {
        this.recordVersion = recordVersion;
    }

    public String getRecordVersion() 
    {
        return recordVersion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("roleName", getRoleName())
            .append("crtOperator", getCrtOperator())
            .append("crtDt", getCrtDt())
            .append("roleTyp", getRoleTyp())
            .append("delStat", getDelStat())
            .append("creator", getCreator())
            .append("editor", getEditor())
            .append("createDt", getCreateDt())
            .append("editDt", getEditDt())
            .append("lastEditDt", getLastEditDt())
            .append("recordVersion", getRecordVersion())
            .toString();
    }
}

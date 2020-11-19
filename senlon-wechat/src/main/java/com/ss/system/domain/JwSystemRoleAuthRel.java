package com.ss.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 系统角色权限关联对象 jw_system_role_auth_rel
 * 
 * @author shishuai
 * @date 2020-07-04
 */
public class JwSystemRoleAuthRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 角色编码 */
    private String roleId;

    /** 权限编码 */
    private String authId;

    public void setRoleId(String roleId) 
    {
        this.roleId = roleId;
    }

    public String getRoleId() 
    {
        return roleId;
    }
    public void setAuthId(String authId) 
    {
        this.authId = authId;
    }

    public String getAuthId() 
    {
        return authId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId", getRoleId())
            .append("authId", getAuthId())
            .toString();
    }
}

package com.ss.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;

/**
 * 系统用户角色关联对象 jw_system_user_role_rel
 * 
 * @author shishuai
 * @date 2020-07-04
 */
public class JwSystemUserRoleRel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;

    /** 角色id */
    private String roleId;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setRoleId(String roleId) 
    {
        this.roleId = roleId;
    }

    public String getRoleId() 
    {
        return roleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("roleId", getRoleId())
            .toString();
    }
}

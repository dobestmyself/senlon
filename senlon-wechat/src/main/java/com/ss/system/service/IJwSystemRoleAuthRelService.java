package com.ss.system.service;

import com.ss.system.domain.JwSystemRoleAuthRel;
import java.util.List;

/**
 * 系统角色权限关联Service接口
 * 
 * @author shishuai
 * @date 2020-07-04
 */
public interface IJwSystemRoleAuthRelService 
{
    /**
     * 查询系统角色权限关联
     * 
     * @param roleId 系统角色权限关联ID
     * @return 系统角色权限关联
     */
    public JwSystemRoleAuthRel selectJwSystemRoleAuthRelById(String roleId);

    /**
     * 查询系统角色权限关联列表
     * 
     * @param jwSystemRoleAuthRel 系统角色权限关联
     * @return 系统角色权限关联集合
     */
    public List<JwSystemRoleAuthRel> selectJwSystemRoleAuthRelList(JwSystemRoleAuthRel jwSystemRoleAuthRel);

    /**
     * 新增系统角色权限关联
     * 
     * @param jwSystemRoleAuthRel 系统角色权限关联
     * @return 结果
     */
    public int insertJwSystemRoleAuthRel(JwSystemRoleAuthRel jwSystemRoleAuthRel);

    /**
     * 修改系统角色权限关联
     * 
     * @param jwSystemRoleAuthRel 系统角色权限关联
     * @return 结果
     */
    public int updateJwSystemRoleAuthRel(JwSystemRoleAuthRel jwSystemRoleAuthRel);

    /**
     * 批量删除系统角色权限关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwSystemRoleAuthRelByIds(String ids);

    /**
     * 删除系统角色权限关联信息
     * 
     * @param roleId 系统角色权限关联ID
     * @return 结果
     */
    public int deleteJwSystemRoleAuthRelById(String roleId);
}

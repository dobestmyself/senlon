package com.ss.system.mapper;

import com.ss.system.domain.JwSystemRoleAuthRel;
import java.util.List;

/**
 * 系统角色权限关联Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-04
 */
public interface JwSystemRoleAuthRelMapper 
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
     * 删除系统角色权限关联
     * 
     * @param roleId 系统角色权限关联ID
     * @return 结果
     */
    public int deleteJwSystemRoleAuthRelById(String roleId);

    /**
     * 批量删除系统角色权限关联
     * 
     * @param roleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwSystemRoleAuthRelByIds(String[] roleIds);

    /**
     * 批量新增角色菜单信息
     * @param jwSystemRoleAuthRelList
     * @return
     */
    public int batchRoleAuth(List<JwSystemRoleAuthRel> jwSystemRoleAuthRelList);

    /**
     * 根据菜单id查询该菜单是否已分配
     * @param authId
     * @return
     */
    public int selectCountRoleAuthByAuthId(Long authId);
}

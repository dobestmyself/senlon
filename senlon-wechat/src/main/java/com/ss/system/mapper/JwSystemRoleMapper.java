package com.ss.system.mapper;

import com.ss.system.domain.JwSystemRole;
import java.util.List;

/**
 * 系统角色Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-03
 */
public interface JwSystemRoleMapper 
{
    /**
     * 查询系统角色
     * 
     * @param id 系统角色ID
     * @return 系统角色
     */
    public JwSystemRole selectJwSystemRoleById(Long id);

    /**
     * 查询系统角色列表
     * 
     * @param jwSystemRole 系统角色
     * @return 系统角色集合
     */
    public List<JwSystemRole> selectJwSystemRoleList(JwSystemRole jwSystemRole);

    /**
     * 新增系统角色
     * 
     * @param jwSystemRole 系统角色
     * @return 结果
     */
    public int insertJwSystemRole(JwSystemRole jwSystemRole);

    /**
     * 修改系统角色
     * 
     * @param jwSystemRole 系统角色
     * @return 结果
     */
    public int updateJwSystemRole(JwSystemRole jwSystemRole);

    /**
     * 删除系统角色
     * 
     * @param id 系统角色ID
     * @return 结果
     */
    public int deleteJwSystemRoleById(Long id);

    /**
     * 批量删除系统角色
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwSystemRoleByIds(String[] ids);

    /**
     * 检查角色编码的唯一性
     * @param roleId
     * @return
     */
    public JwSystemRole checkRoleIdUnique(String roleId);

    /**
     * 检测角色名称的唯一性
     * @param roleName
     * @return
     */
    public JwSystemRole checkRoleNameUnique(String roleName);

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    public List<JwSystemRole> selectRolesByUserId(Long userId);
}

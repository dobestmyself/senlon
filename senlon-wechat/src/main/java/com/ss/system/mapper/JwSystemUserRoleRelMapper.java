package com.ss.system.mapper;

import com.ss.system.domain.JwSystemUserRoleRel;
import java.util.List;

/**
 * 系统用户角色关联Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-04
 */
public interface JwSystemUserRoleRelMapper 
{
    /**
     * 查询系统用户角色关联
     * 
     * @param userId 系统用户角色关联ID
     * @return 系统用户角色关联
     */
    public List<JwSystemUserRoleRel> selectJwSystemUserRoleRelById(Long userId);

    /**
     * 查询系统用户角色关联列表
     * 
     * @param jwSystemUserRoleRel 系统用户角色关联
     * @return 系统用户角色关联集合
     */
    public List<JwSystemUserRoleRel> selectJwSystemUserRoleRelList(JwSystemUserRoleRel jwSystemUserRoleRel);

    /**
     * 新增系统用户角色关联
     * 
     * @param jwSystemUserRoleRel 系统用户角色关联
     * @return 结果
     */
    public int insertJwSystemUserRoleRel(JwSystemUserRoleRel jwSystemUserRoleRel);

    /**
     * 修改系统用户角色关联
     * 
     * @param jwSystemUserRoleRel 系统用户角色关联
     * @return 结果
     */
    public int updateJwSystemUserRoleRel(JwSystemUserRoleRel jwSystemUserRoleRel);

    /**
     * 删除系统用户角色关联
     * 
     * @param userId 系统用户角色关联ID
     * @return 结果
     */
    public int deleteJwSystemUserRoleRelById(Long userId);

    /**
     * 批量删除系统用户角色关联
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwSystemUserRoleRelByIds(String[] userIds);

    public int batchUserRole(List<JwSystemUserRoleRel> userRoleRelList);

    public int deleteAuthUser(JwSystemUserRoleRel userRole);
}

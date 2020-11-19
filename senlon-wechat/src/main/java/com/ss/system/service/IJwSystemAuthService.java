package com.ss.system.service;

import com.ss.system.domain.JwSystemAuth;
import java.util.List;
import com.ss.common.core.domain.Ztree;
import com.ss.system.domain.JwSystemRole;

/**
 * 系统权限菜单Service接口
 * 
 * @author shishuai
 * @date 2020-07-03
 */
public interface IJwSystemAuthService 
{
    /**
     * 查询系统权限菜单
     * 
     * @param id 系统权限菜单ID
     * @return 系统权限菜单
     */
    public JwSystemAuth selectJwSystemAuthById(Long id);

    public JwSystemAuth selectJwSystemAuthByAuthId(Long authId);

    public List<JwSystemAuth> findJwSystemAuthList();

    /**
     * 查询系统权限菜单列表
     * 
     * @param jwSystemAuth 系统权限菜单
     * @return 系统权限菜单集合
     */
    public List<JwSystemAuth> selectJwSystemAuthList(JwSystemAuth jwSystemAuth);

    /**
     * 新增系统权限菜单
     * 
     * @param jwSystemAuth 系统权限菜单
     * @return 结果
     */
    public int insertJwSystemAuth(JwSystemAuth jwSystemAuth);

    /**
     * 修改系统权限菜单
     * 
     * @param jwSystemAuth 系统权限菜单
     * @return 结果
     */
    public int updateJwSystemAuth(JwSystemAuth jwSystemAuth);

    /**
     * 批量删除系统权限菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwSystemAuthByIds(String ids);

    /**
     * 删除系统权限菜单信息
     * 
     * @param id 系统权限菜单ID
     * @return 结果
     */
    public int deleteJwSystemAuthById(Long id);

    /**
     * 查询系统权限菜单树列表
     * 
     * @return 所有系统权限菜单信息
     */
    public List<Ztree> selectJwSystemAuthTree();

    public List<Ztree> roleAuthTreeData(JwSystemRole role,Long userId);

    /**
     * 查询菜单集合
     * @param userId
     * @return
     */
    public List<JwSystemAuth> selectAuthAll(Long userId);

    /**
     * 对象菜单树
     * @param authList
     * @param roleAuthList
     * @param permsFlag
     * @return
     */
    public List<Ztree> initZtree(List<JwSystemAuth> authList,List<String> roleAuthList,boolean permsFlag);

    /**
     * 检测菜单名称的唯一性
     * @param authName
     * @return
     */
    public String checkAuthNameUnique(String authName);
    /**
     * 查询当前目录下是否还有子类
     * @param parentAuthId
     * @return
     */
    public int selectCountChildByParentId(Long parentAuthId);

    /**
     * 根据菜单id查询该菜单是否已分配
     * @param authId
     * @return
     */
    public int selectCountRoleAuthByAuthId(Long authId);
}

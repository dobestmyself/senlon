package com.ss.system.mapper;

import com.ss.system.domain.JwSystemAuth;
import java.util.List;

/**
 * 系统权限菜单Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-03
 */
public interface JwSystemAuthMapper 
{
    /**
     * 查询系统权限菜单
     * 
     * @param id 系统权限菜单ID
     * @return 系统权限菜单
     */
    public JwSystemAuth selectJwSystemAuthById(Long id);

    /**
     * 根据权限编码查询权限
     * @param authId
     * @return
     */
    public JwSystemAuth selectJwSystemAuthByAuthId(Long authId);

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
     * 删除系统权限菜单
     * 
     * @param id 系统权限菜单ID
     * @return 结果
     */
    public int deleteJwSystemAuthById(Long id);

    /**
     * 批量删除系统权限菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwSystemAuthByIds(String[] ids);


    public List<JwSystemAuth> selectAuthAllByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * @param roleId
     * @return
     */
    public List<String> selectAuthTree(String roleId);


    /**
     * 检测菜单名称的唯一性
     * @param authName
     * @return
     */
    public List<JwSystemAuth> checkAuthNameUnique(String authName);


    /**
     * 查询当前目录下是否还有子类
     * @param parentAuthId
     * @return
     */
    public int selectCountChildByParentId(Long parentAuthId);
}

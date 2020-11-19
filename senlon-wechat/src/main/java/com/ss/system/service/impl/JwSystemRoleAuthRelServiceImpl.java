package com.ss.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.system.mapper.JwSystemRoleAuthRelMapper;
import com.ss.system.domain.JwSystemRoleAuthRel;
import com.ss.system.service.IJwSystemRoleAuthRelService;
import com.ss.common.core.text.Convert;

/**
 * 系统角色权限关联Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-04
 */
@Service
public class JwSystemRoleAuthRelServiceImpl implements IJwSystemRoleAuthRelService 
{
    @Autowired
    private JwSystemRoleAuthRelMapper jwSystemRoleAuthRelMapper;

    /**
     * 查询系统角色权限关联
     * 
     * @param roleId 系统角色权限关联ID
     * @return 系统角色权限关联
     */
    @Override
    public JwSystemRoleAuthRel selectJwSystemRoleAuthRelById(String roleId)
    {
        return jwSystemRoleAuthRelMapper.selectJwSystemRoleAuthRelById(roleId);
    }

    /**
     * 查询系统角色权限关联列表
     * 
     * @param jwSystemRoleAuthRel 系统角色权限关联
     * @return 系统角色权限关联
     */
    @Override
    public List<JwSystemRoleAuthRel> selectJwSystemRoleAuthRelList(JwSystemRoleAuthRel jwSystemRoleAuthRel)
    {
        return jwSystemRoleAuthRelMapper.selectJwSystemRoleAuthRelList(jwSystemRoleAuthRel);
    }

    /**
     * 新增系统角色权限关联
     * 
     * @param jwSystemRoleAuthRel 系统角色权限关联
     * @return 结果
     */
    @Override
    public int insertJwSystemRoleAuthRel(JwSystemRoleAuthRel jwSystemRoleAuthRel)
    {
        return jwSystemRoleAuthRelMapper.insertJwSystemRoleAuthRel(jwSystemRoleAuthRel);
    }

    /**
     * 修改系统角色权限关联
     * 
     * @param jwSystemRoleAuthRel 系统角色权限关联
     * @return 结果
     */
    @Override
    public int updateJwSystemRoleAuthRel(JwSystemRoleAuthRel jwSystemRoleAuthRel)
    {
        return jwSystemRoleAuthRelMapper.updateJwSystemRoleAuthRel(jwSystemRoleAuthRel);
    }

    /**
     * 删除系统角色权限关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemRoleAuthRelByIds(String ids)
    {
        return jwSystemRoleAuthRelMapper.deleteJwSystemRoleAuthRelByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系统角色权限关联信息
     * 
     * @param roleId 系统角色权限关联ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemRoleAuthRelById(String roleId)
    {
        return jwSystemRoleAuthRelMapper.deleteJwSystemRoleAuthRelById(roleId);
    }
}

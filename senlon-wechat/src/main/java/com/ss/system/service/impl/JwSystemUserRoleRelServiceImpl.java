package com.ss.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.system.mapper.JwSystemUserRoleRelMapper;
import com.ss.system.domain.JwSystemUserRoleRel;
import com.ss.system.service.IJwSystemUserRoleRelService;
import com.ss.common.core.text.Convert;

/**
 * 系统用户角色关联Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-04
 */
@Service
public class JwSystemUserRoleRelServiceImpl implements IJwSystemUserRoleRelService 
{
    @Autowired
    private JwSystemUserRoleRelMapper jwSystemUserRoleRelMapper;

    /**
     * 查询系统用户角色关联
     * 
     * @param userId 系统用户角色关联ID
     * @return 系统用户角色关联
     */
    @Override
    public List<JwSystemUserRoleRel> selectJwSystemUserRoleRelById(Long userId)
    {
        return jwSystemUserRoleRelMapper.selectJwSystemUserRoleRelById(userId);
    }

    /**
     * 查询系统用户角色关联列表
     * 
     * @param jwSystemUserRoleRel 系统用户角色关联
     * @return 系统用户角色关联
     */
    @Override
    public List<JwSystemUserRoleRel> selectJwSystemUserRoleRelList(JwSystemUserRoleRel jwSystemUserRoleRel)
    {
        return jwSystemUserRoleRelMapper.selectJwSystemUserRoleRelList(jwSystemUserRoleRel);
    }

    /**
     * 新增系统用户角色关联
     * 
     * @param jwSystemUserRoleRel 系统用户角色关联
     * @return 结果
     */
    @Override
    public int insertJwSystemUserRoleRel(JwSystemUserRoleRel jwSystemUserRoleRel)
    {
        return jwSystemUserRoleRelMapper.insertJwSystemUserRoleRel(jwSystemUserRoleRel);
    }

    /**
     * 修改系统用户角色关联
     * 
     * @param jwSystemUserRoleRel 系统用户角色关联
     * @return 结果
     */
    @Override
    public int updateJwSystemUserRoleRel(JwSystemUserRoleRel jwSystemUserRoleRel)
    {
        return jwSystemUserRoleRelMapper.updateJwSystemUserRoleRel(jwSystemUserRoleRel);
    }

    /**
     * 删除系统用户角色关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemUserRoleRelByIds(String ids)
    {
        return jwSystemUserRoleRelMapper.deleteJwSystemUserRoleRelByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系统用户角色关联信息
     * 
     * @param userId 系统用户角色关联ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemUserRoleRelById(Long userId)
    {
        return jwSystemUserRoleRelMapper.deleteJwSystemUserRoleRelById(userId);
    }
}

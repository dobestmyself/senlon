package com.ss.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.system.mapper.JwSystemUserMapper;
import com.ss.system.domain.JwSystemUser;
import com.ss.system.service.IJwSystemUserService;
import com.ss.common.core.text.Convert;

/**
 * 系统用户Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-02
 */
@Service
public class JwSystemUserServiceImpl implements IJwSystemUserService 
{
    @Autowired
    private JwSystemUserMapper jwSystemUserMapper;

    /**
     * 查询系统用户
     * 
     * @param id 系统用户ID
     * @return 系统用户
     */
    @Override
    public JwSystemUser selectJwSystemUserById(Long id)
    {
        return jwSystemUserMapper.selectJwSystemUserById(id);
    }

    /**
     * 查询系统用户列表
     * 
     * @param jwSystemUser 系统用户
     * @return 系统用户
     */
    @Override
    public List<JwSystemUser> selectJwSystemUserList(JwSystemUser jwSystemUser)
    {
        return jwSystemUserMapper.selectJwSystemUserList(jwSystemUser);
    }

    /**
     * 新增系统用户
     * 
     * @param jwSystemUser 系统用户
     * @return 结果
     */
    @Override
    public int insertJwSystemUser(JwSystemUser jwSystemUser)
    {
        return jwSystemUserMapper.insertJwSystemUser(jwSystemUser);
    }

    /**
     * 修改系统用户
     * 
     * @param jwSystemUser 系统用户
     * @return 结果
     */
    @Override
    public int updateJwSystemUser(JwSystemUser jwSystemUser)
    {
        return jwSystemUserMapper.updateJwSystemUser(jwSystemUser);
    }

    /**
     * 删除系统用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemUserByIds(String ids)
    {
        return jwSystemUserMapper.deleteJwSystemUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系统用户信息
     * 
     * @param id 系统用户ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemUserById(Long id)
    {
        return jwSystemUserMapper.deleteJwSystemUserById(id);
    }
}

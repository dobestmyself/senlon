package com.ss.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ss.common.constant.UserConstants;
import com.ss.common.utils.StringUtils;
import com.ss.system.domain.JwSystemAuth;
import com.ss.system.domain.JwSystemRoleAuthRel;
import com.ss.system.domain.JwSystemUserRoleRel;
import com.ss.system.mapper.JwSystemRoleAuthRelMapper;
import com.ss.system.mapper.JwSystemUserRoleRelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.system.mapper.JwSystemRoleMapper;
import com.ss.system.domain.JwSystemRole;
import com.ss.system.service.IJwSystemRoleService;
import com.ss.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统角色Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-03
 */
@Service
public class JwSystemRoleServiceImpl implements IJwSystemRoleService 
{
    @Autowired
    private JwSystemRoleMapper jwSystemRoleMapper;

    @Autowired
    private JwSystemRoleAuthRelMapper jwSystemRoleAuthRelMapper;

    @Autowired
    private JwSystemUserRoleRelMapper userRoleMapper;

    @Override
    public String checkRoleIdUnique(JwSystemRole jwSystemRole) {
        String roleId = StringUtils.isNull(jwSystemRole.getRoleId()) ? "" : jwSystemRole.getRoleId();
        JwSystemRole info = jwSystemRoleMapper.checkRoleIdUnique(jwSystemRole.getRoleId());
        if(StringUtils.isNotNull(info) && info.getRoleId() != roleId){
            return UserConstants.ROLE_KEY_NOT_UNIQUE;
        }
        return UserConstants.ROLE_KEY_UNIQUE;
    }

    @Override
    public String checkRoleNameUnique(JwSystemRole jwSystemRole) {
        String roleId = StringUtils.isNull(jwSystemRole.getRoleId()) ? "" : jwSystemRole.getRoleId();
        JwSystemRole info = jwSystemRoleMapper.checkRoleNameUnique(jwSystemRole.getRoleName());
        if(StringUtils.isNotNull(info) && info.getRoleId() != roleId){
            return UserConstants.ROLE_NAME_NOT_UNIQUE;
        }
        return UserConstants.ROLE_NAME_UNIQUE;
    }

    /**
     * 查询系统角色
     * 
     * @param id 系统角色ID
     * @return 系统角色
     */
    @Override
    public JwSystemRole selectJwSystemRoleById(Long id)
    {
        return jwSystemRoleMapper.selectJwSystemRoleById(id);
    }

    /**
     * 查询系统角色列表
     * 
     * @param jwSystemRole 系统角色
     * @return 系统角色
     */
    @Override
    public List<JwSystemRole> selectJwSystemRoleList(JwSystemRole jwSystemRole)
    {
        return jwSystemRoleMapper.selectJwSystemRoleList(jwSystemRole);
    }

    @Override
    public List<JwSystemRole> selectRolesByUserId(Long userId) {
        return jwSystemRoleMapper.selectRolesByUserId(userId);
    }

    /**
     * 新增系统角色
     * 
     * @param jwSystemRole 系统角色
     * @return 结果
     */
    @Override
    @Transactional
    public int insertJwSystemRole(JwSystemRole jwSystemRole)
    {
        jwSystemRole.setDelStat("0");
        jwSystemRoleMapper.insertJwSystemRole(jwSystemRole);
        return batchInsertRoleAuth(jwSystemRole);
    }

    private int batchInsertRoleAuth(JwSystemRole role){
        int rows = 1;
        List<JwSystemRoleAuthRel> jwSystemRoleAuthRelList = new ArrayList<>();
        for(String menu : role.getAuthIds()){
            JwSystemRoleAuthRel jwSystemRoleAuthRel = new JwSystemRoleAuthRel();
            jwSystemRoleAuthRel.setRoleId(role.getRoleId());
            jwSystemRoleAuthRel.setAuthId(menu);
            jwSystemRoleAuthRelList.add(jwSystemRoleAuthRel);
        }
        if(jwSystemRoleAuthRelList.size()>0){
            rows = jwSystemRoleAuthRelMapper.batchRoleAuth(jwSystemRoleAuthRelList);
        }
        return rows;
    }

    /**
     * 修改系统角色
     * 
     * @param jwSystemRole 系统角色
     * @return 结果
     */
    @Override
    @Transactional
    public int updateJwSystemRole(JwSystemRole jwSystemRole)
    {
        JwSystemRole role = jwSystemRoleMapper.selectJwSystemRoleById(jwSystemRole.getId());
        jwSystemRoleAuthRelMapper.deleteJwSystemRoleAuthRelById(role.getRoleId());
        jwSystemRoleMapper.updateJwSystemRole(jwSystemRole);
        return batchInsertRoleAuth(jwSystemRole);
    }

    /**
     * 删除系统角色对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteJwSystemRoleByIds(String ids)
    {
        Long[] rIds = Convert.toLongArray(ids);
        for(Long id : rIds){
            JwSystemRole role = jwSystemRoleMapper.selectJwSystemRoleById(id);
            if(StringUtils.isNotNull(role)){
                jwSystemRoleAuthRelMapper.deleteJwSystemRoleAuthRelById(role.getRoleId());
            }
        }
        return jwSystemRoleMapper.deleteJwSystemRoleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系统角色信息
     * 
     * @param id 系统角色ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteJwSystemRoleById(Long id)
    {
        JwSystemRole role = jwSystemRoleMapper.selectJwSystemRoleById(id);
        if(StringUtils.isNotNull(role)){
            jwSystemRoleAuthRelMapper.deleteJwSystemRoleAuthRelById(role.getRoleId());
        }
        return jwSystemRoleMapper.deleteJwSystemRoleById(id);
    }

    @Override
    @Transactional
    public int insertAuthUsers(String userIds, String roleId) {
        Long[] users = Convert.toLongArray(userIds);
        List<JwSystemUserRoleRel> userRoleList = new ArrayList<>();
        for(Long userId : users){
            JwSystemUserRoleRel userRole = new JwSystemUserRoleRel();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleList.add(userRole);
        }
        return userRoleMapper.batchUserRole(userRoleList);
    }

    @Override
    @Transactional
    public int deleteAuthUser(JwSystemUserRoleRel userRole) {
        return userRoleMapper.deleteAuthUser(userRole);
    }

    @Override
    @Transactional
    public int deleteAuthUsers(String roleId, String userIds) {
        Long[] users = Convert.toLongArray(userIds);
        int rows = 1;
        for(Long userId : users){
            JwSystemUserRoleRel userRole = new JwSystemUserRoleRel();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            rows = userRoleMapper.deleteAuthUser(userRole);
        }
        return rows;
    }
}

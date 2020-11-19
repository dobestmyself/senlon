package com.ss.system.mapper;

import com.ss.system.domain.JwSystemUser;
import java.util.List;

/**
 * 系统用户Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-02
 */
public interface JwSystemUserMapper 
{
    /**
     * 查询系统用户
     * 
     * @param id 系统用户ID
     * @return 系统用户
     */
    public JwSystemUser selectJwSystemUserById(Long id);

    /**
     * 查询系统用户列表
     * 
     * @param jwSystemUser 系统用户
     * @return 系统用户集合
     */
    public List<JwSystemUser> selectJwSystemUserList(JwSystemUser jwSystemUser);

    /**
     * 新增系统用户
     * 
     * @param jwSystemUser 系统用户
     * @return 结果
     */
    public int insertJwSystemUser(JwSystemUser jwSystemUser);

    /**
     * 修改系统用户
     * 
     * @param jwSystemUser 系统用户
     * @return 结果
     */
    public int updateJwSystemUser(JwSystemUser jwSystemUser);

    /**
     * 删除系统用户
     * 
     * @param id 系统用户ID
     * @return 结果
     */
    public int deleteJwSystemUserById(Long id);

    /**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwSystemUserByIds(String[] ids);
}

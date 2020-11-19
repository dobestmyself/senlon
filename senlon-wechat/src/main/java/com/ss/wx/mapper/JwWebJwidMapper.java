package com.ss.wx.mapper;

import com.ss.wx.domain.JwWebJwid;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 系统公众号Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-05
 */
public interface JwWebJwidMapper 
{
    /**
     * 查询系统公众号
     * 
     * @param id 系统公众号ID
     * @return 系统公众号
     */
    public JwWebJwid selectJwWebJwidById(String id);

    /**
     * 查询系统公众号列表
     * 
     * @param jwWebJwid 系统公众号
     * @return 系统公众号集合
     */
    public List<JwWebJwid> selectJwWebJwidList(JwWebJwid jwWebJwid);

    /**
     * 新增系统公众号
     * 
     * @param jwWebJwid 系统公众号
     * @return 结果
     */
    public int insertJwWebJwid(JwWebJwid jwWebJwid);

    /**
     * 修改系统公众号
     * 
     * @param jwWebJwid 系统公众号
     * @return 结果
     */
    public int updateJwWebJwid(JwWebJwid jwWebJwid);

    /**
     * 删除系统公众号
     * 
     * @param id 系统公众号ID
     * @return 结果
     */
    public int deleteJwWebJwidById(String id);

    /**
     * 批量删除系统公众号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwWebJwidByIds(String[] ids);

    /**
     * 根据创建人查询微信公众号信息
     * @param createBy
     * @return
     */
    public JwWebJwid queryOneByCreateBy(String createBy);

    /**
     * 根据公众号插叙微信公众号信息
     * @param jwid
     * @return
     */
    public JwWebJwid queryByJwid(String jwid);

    /**
     * 定时重启Token
     * @return
     */
    public List<JwWebJwid> queryResetTokenList(@Param("refDate") Date refDate);

    /**
     *  向userjwid表中添加数据(在当前xml操作别的库)
     * @param id
     * @param jwid
     * @param createBy
     */
    public void doAddSystemUserJwid(@Param("id")String id,@Param("jwid")String jwid,@Param("createBy")String createBy);

    /**
     * update-by-alex-----Date:20170317---for:删除jwid数据时，同步删除该jwid与用户的关联关系---
     * 根据Jwid删除当前jwid所有相关绑定关系
     * @param jwid
     * @param
     */
    public void doDelSystemUserJwid(@Param("jwid") String jwid);


}

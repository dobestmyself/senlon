package com.ss.wx.service;

import com.ss.wx.domain.JwWebJwid;

import java.util.Date;
import java.util.List;

/**
 * 系统公众号Service接口
 * 
 * @author shishuai
 * @date 2020-07-05
 */
public interface IJwWebJwidService 
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
     * 批量删除系统公众号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJwWebJwidByIds(String ids);

    /**
     * 删除系统公众号信息
     * 
     * @param id 系统公众号ID
     * @return 结果
     */
    public int deleteJwWebJwidById(String id);

    /**
     * 根据创建人查询微信公众号
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
    public List<JwWebJwid> queryResetTokenList(Date refDate);

    public void doAddSystemUserJwid(String id,String jwid,String createBy);

    /**
     * 重置ACCESS_TOKEN
     */
    public String resetAccessToken(String id);

}

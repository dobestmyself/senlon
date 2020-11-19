package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinOpenAccount;
import java.util.List;

/**
 * 微信第三方平台账号Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-13
 */
public interface WeixinOpenAccountMapper 
{
    /**
     * 查询微信第三方平台账号
     * 
     * @param id 微信第三方平台账号ID
     * @return 微信第三方平台账号
     */
    public WeixinOpenAccount selectWeixinOpenAccountById(String id);

    /**
     * 查询微信第三方平台账号列表
     * 
     * @param weixinOpenAccount 微信第三方平台账号
     * @return 微信第三方平台账号集合
     */
    public List<WeixinOpenAccount> selectWeixinOpenAccountList(WeixinOpenAccount weixinOpenAccount);

    /**
     * 新增微信第三方平台账号
     * 
     * @param weixinOpenAccount 微信第三方平台账号
     * @return 结果
     */
    public int insertWeixinOpenAccount(WeixinOpenAccount weixinOpenAccount);

    /**
     * 修改微信第三方平台账号
     * 
     * @param weixinOpenAccount 微信第三方平台账号
     * @return 结果
     */
    public int updateWeixinOpenAccount(WeixinOpenAccount weixinOpenAccount);

    /**
     * 删除微信第三方平台账号
     * 
     * @param id 微信第三方平台账号ID
     * @return 结果
     */
    public int deleteWeixinOpenAccountById(String id);

    /**
     * 批量删除微信第三方平台账号
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinOpenAccountByIds(String[] ids);

    WeixinOpenAccount queryOneByAppid(String appid);
}

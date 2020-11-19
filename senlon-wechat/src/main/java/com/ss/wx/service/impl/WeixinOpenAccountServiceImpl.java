package com.ss.wx.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinOpenAccountMapper;
import com.ss.wx.domain.WeixinOpenAccount;
import com.ss.wx.service.IWeixinOpenAccountService;
import com.ss.common.core.text.Convert;

/**
 * 微信第三方平台账号Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-13
 */
@Service
public class WeixinOpenAccountServiceImpl implements IWeixinOpenAccountService 
{
    @Autowired
    private WeixinOpenAccountMapper weixinOpenAccountMapper;

    /**
     * 查询微信第三方平台账号
     * 
     * @param id 微信第三方平台账号ID
     * @return 微信第三方平台账号
     */
    @Override
    public WeixinOpenAccount selectWeixinOpenAccountById(String id)
    {
        return weixinOpenAccountMapper.selectWeixinOpenAccountById(id);
    }

    /**
     * 查询微信第三方平台账号列表
     * 
     * @param weixinOpenAccount 微信第三方平台账号
     * @return 微信第三方平台账号
     */
    @Override
    public List<WeixinOpenAccount> selectWeixinOpenAccountList(WeixinOpenAccount weixinOpenAccount)
    {
        return weixinOpenAccountMapper.selectWeixinOpenAccountList(weixinOpenAccount);
    }

    /**
     * 新增微信第三方平台账号
     * 
     * @param weixinOpenAccount 微信第三方平台账号
     * @return 结果
     */
    @Override
    public int insertWeixinOpenAccount(WeixinOpenAccount weixinOpenAccount)
    {
        return weixinOpenAccountMapper.insertWeixinOpenAccount(weixinOpenAccount);
    }

    /**
     * 修改微信第三方平台账号
     * 
     * @param weixinOpenAccount 微信第三方平台账号
     * @return 结果
     */
    @Override
    public int updateWeixinOpenAccount(WeixinOpenAccount weixinOpenAccount)
    {
        return weixinOpenAccountMapper.updateWeixinOpenAccount(weixinOpenAccount);
    }

    /**
     * 删除微信第三方平台账号对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinOpenAccountByIds(String ids)
    {
        return weixinOpenAccountMapper.deleteWeixinOpenAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信第三方平台账号信息
     * 
     * @param id 微信第三方平台账号ID
     * @return 结果
     */
    @Override
    public int deleteWeixinOpenAccountById(String id)
    {
        return weixinOpenAccountMapper.deleteWeixinOpenAccountById(id);
    }

    @Override
    public WeixinOpenAccount queryOneByAppid(String appid) {
        return weixinOpenAccountMapper.queryOneByAppid(appid);
    }
}

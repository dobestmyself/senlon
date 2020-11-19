package com.ss.wx.service.impl;

import java.util.List;
import java.util.UUID;

import com.ss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinSubscribeMapper;
import com.ss.wx.domain.WeixinSubscribe;
import com.ss.wx.service.IWeixinSubscribeService;
import com.ss.common.core.text.Convert;

/**
 * 关注欢迎语Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Service
public class WeixinSubscribeServiceImpl implements IWeixinSubscribeService 
{
    @Autowired
    private WeixinSubscribeMapper weixinSubscribeMapper;

    /**
     * 查询关注欢迎语
     * 
     * @param id 关注欢迎语ID
     * @return 关注欢迎语
     */
    @Override
    public WeixinSubscribe selectWeixinSubscribeById(String id)
    {
        return weixinSubscribeMapper.selectWeixinSubscribeById(id);
    }

    /**
     * 查询关注欢迎语列表
     * 
     * @param weixinSubscribe 关注欢迎语
     * @return 关注欢迎语
     */
    @Override
    public List<WeixinSubscribe> selectWeixinSubscribeList(WeixinSubscribe weixinSubscribe)
    {
        return weixinSubscribeMapper.selectWeixinSubscribeList(weixinSubscribe);
    }

    /**
     * 新增关注欢迎语
     * 
     * @param weixinSubscribe 关注欢迎语
     * @return 结果
     */
    @Override
    public int insertWeixinSubscribe(WeixinSubscribe weixinSubscribe)
    {
        String id = UUID.randomUUID().toString().replace("-","");
        weixinSubscribe.setId(id);
        weixinSubscribe.setCreateTime(DateUtils.getNowDate());
        return weixinSubscribeMapper.insertWeixinSubscribe(weixinSubscribe);
    }

    /**
     * 修改关注欢迎语
     * 
     * @param weixinSubscribe 关注欢迎语
     * @return 结果
     */
    @Override
    public int updateWeixinSubscribe(WeixinSubscribe weixinSubscribe)
    {
        weixinSubscribe.setUpdateTime(DateUtils.getNowDate());
        return weixinSubscribeMapper.updateWeixinSubscribe(weixinSubscribe);
    }

    /**
     * 删除关注欢迎语对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinSubscribeByIds(String ids)
    {
        return weixinSubscribeMapper.deleteWeixinSubscribeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除关注欢迎语信息
     * 
     * @param id 关注欢迎语ID
     * @return 结果
     */
    @Override
    public int deleteWeixinSubscribeById(String id)
    {
        return weixinSubscribeMapper.deleteWeixinSubscribeById(id);
    }
}

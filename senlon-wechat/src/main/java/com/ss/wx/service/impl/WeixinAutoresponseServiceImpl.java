package com.ss.wx.service.impl;

import java.util.List;
import java.util.UUID;

import com.ss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinAutoresponseMapper;
import com.ss.wx.domain.WeixinAutoresponse;
import com.ss.wx.service.IWeixinAutoresponseService;
import com.ss.common.core.text.Convert;

/**
 * 关键字Service业务层处理
 * 
 * @author shishuai
 * @date 2020-08-05
 */
@Service
public class WeixinAutoresponseServiceImpl implements IWeixinAutoresponseService 
{
    @Autowired
    private WeixinAutoresponseMapper weixinAutoresponseMapper;

    /**
     * 查询关键字
     * 
     * @param id 关键字ID
     * @return 关键字
     */
    @Override
    public WeixinAutoresponse selectWeixinAutoresponseById(String id)
    {
        return weixinAutoresponseMapper.selectWeixinAutoresponseById(id);
    }

    /**
     * 查询关键字列表
     * 
     * @param weixinAutoresponse 关键字
     * @return 关键字
     */
    @Override
    public List<WeixinAutoresponse> selectWeixinAutoresponseList(WeixinAutoresponse weixinAutoresponse)
    {
        return weixinAutoresponseMapper.selectWeixinAutoresponseList(weixinAutoresponse);
    }

    /**
     * 新增关键字
     * 
     * @param weixinAutoresponse 关键字
     * @return 结果
     */
    @Override
    public int insertWeixinAutoresponse(WeixinAutoresponse weixinAutoresponse)
    {
        weixinAutoresponse.setId(UUID.randomUUID().toString().replace("-",""));
        weixinAutoresponse.setCreateTime(DateUtils.getNowDate());
        return weixinAutoresponseMapper.insertWeixinAutoresponse(weixinAutoresponse);
    }

    /**
     * 修改关键字
     * 
     * @param weixinAutoresponse 关键字
     * @return 结果
     */
    @Override
    public int updateWeixinAutoresponse(WeixinAutoresponse weixinAutoresponse)
    {
        weixinAutoresponse.setUpdateTime(DateUtils.getNowDate());
        return weixinAutoresponseMapper.updateWeixinAutoresponse(weixinAutoresponse);
    }

    /**
     * 删除关键字对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinAutoresponseByIds(String ids)
    {
        return weixinAutoresponseMapper.deleteWeixinAutoresponseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除关键字信息
     * 
     * @param id 关键字ID
     * @return 结果
     */
    @Override
    public int deleteWeixinAutoresponseById(String id)
    {
        return weixinAutoresponseMapper.deleteWeixinAutoresponseById(id);
    }
}

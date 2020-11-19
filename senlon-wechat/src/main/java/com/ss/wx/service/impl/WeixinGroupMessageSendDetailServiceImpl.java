package com.ss.wx.service.impl;

import java.util.List;

import com.ss.common.utils.Guid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinGroupMessageSendDetailMapper;
import com.ss.wx.domain.WeixinGroupMessageSendDetail;
import com.ss.wx.service.IWeixinGroupMessageSendDetailService;
import com.ss.common.core.text.Convert;

/**
 * 群发日志明细Service业务层处理
 * 
 * @author shishuai
 * @date 2020-11-10
 */
@Service
public class WeixinGroupMessageSendDetailServiceImpl implements IWeixinGroupMessageSendDetailService 
{
    @Autowired
    private WeixinGroupMessageSendDetailMapper weixinGroupMessageSendDetailMapper;

    /**
     * 查询群发日志明细
     * 
     * @param id 群发日志明细ID
     * @return 群发日志明细
     */
    @Override
    public WeixinGroupMessageSendDetail selectWeixinGroupMessageSendDetailById(String id)
    {
        return weixinGroupMessageSendDetailMapper.selectWeixinGroupMessageSendDetailById(id);
    }

    /**
     * 查询群发日志明细列表
     * 
     * @param weixinGroupMessageSendDetail 群发日志明细
     * @return 群发日志明细
     */
    @Override
    public List<WeixinGroupMessageSendDetail> selectWeixinGroupMessageSendDetailList(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail)
    {
        return weixinGroupMessageSendDetailMapper.selectWeixinGroupMessageSendDetailList(weixinGroupMessageSendDetail);
    }

    /**
     * 新增群发日志明细
     * 
     * @param weixinGroupMessageSendDetail 群发日志明细
     * @return 结果
     */
    @Override
    public int insertWeixinGroupMessageSendDetail(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail)
    {
        weixinGroupMessageSendDetail.setId(Guid.get());
        return weixinGroupMessageSendDetailMapper.insertWeixinGroupMessageSendDetail(weixinGroupMessageSendDetail);
    }

    /**
     * 修改群发日志明细
     * 
     * @param weixinGroupMessageSendDetail 群发日志明细
     * @return 结果
     */
    @Override
    public int updateWeixinGroupMessageSendDetail(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail)
    {
        return weixinGroupMessageSendDetailMapper.updateWeixinGroupMessageSendDetail(weixinGroupMessageSendDetail);
    }

    /**
     * 删除群发日志明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinGroupMessageSendDetailByIds(String ids)
    {
        return weixinGroupMessageSendDetailMapper.deleteWeixinGroupMessageSendDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除群发日志明细信息
     * 
     * @param id 群发日志明细ID
     * @return 结果
     */
    @Override
    public int deleteWeixinGroupMessageSendDetailById(String id)
    {
        return weixinGroupMessageSendDetailMapper.deleteWeixinGroupMessageSendDetailById(id);
    }
}

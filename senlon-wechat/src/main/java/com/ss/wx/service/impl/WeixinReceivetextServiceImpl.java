package com.ss.wx.service.impl;

import java.util.List;
import com.ss.common.utils.DateUtils;
import com.ss.common.utils.Guid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinReceivetextMapper;
import com.ss.wx.domain.WeixinReceivetext;
import com.ss.wx.service.IWeixinReceivetextService;
import com.ss.common.core.text.Convert;

/**
 * 消息存储Service业务层处理
 * 
 * @author shishuai
 * @date 2020-11-06
 */
@Service
public class WeixinReceivetextServiceImpl implements IWeixinReceivetextService 
{
    @Autowired
    private WeixinReceivetextMapper weixinReceivetextMapper;

    /**
     * 查询消息存储
     * 
     * @param id 消息存储ID
     * @return 消息存储
     */
    @Override
    public WeixinReceivetext selectWeixinReceivetextById(String id)
    {
        return weixinReceivetextMapper.selectWeixinReceivetextById(id);
    }

    /**
     * 查询消息存储列表
     * 
     * @param weixinReceivetext 消息存储
     * @return 消息存储
     */
    @Override
    public List<WeixinReceivetext> selectWeixinReceivetextList(WeixinReceivetext weixinReceivetext)
    {
        return weixinReceivetextMapper.selectWeixinReceivetextList(weixinReceivetext);
    }

    /**
     * 新增消息存储
     * 
     * @param weixinReceivetext 消息存储
     * @return 结果
     */
    @Override
    public int insertWeixinReceivetext(WeixinReceivetext weixinReceivetext)
    {
        weixinReceivetext.setCreateTime(DateUtils.getNowDate());
        weixinReceivetext.setId(Guid.get());
        return weixinReceivetextMapper.insertWeixinReceivetext(weixinReceivetext);
    }

    /**
     * 修改消息存储
     * 
     * @param weixinReceivetext 消息存储
     * @return 结果
     */
    @Override
    public int updateWeixinReceivetext(WeixinReceivetext weixinReceivetext)
    {
        return weixinReceivetextMapper.updateWeixinReceivetext(weixinReceivetext);
    }

    /**
     * 删除消息存储对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinReceivetextByIds(String ids)
    {
        return weixinReceivetextMapper.deleteWeixinReceivetextByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除消息存储信息
     * 
     * @param id 消息存储ID
     * @return 结果
     */
    @Override
    public int deleteWeixinReceivetextById(String id)
    {
        return weixinReceivetextMapper.deleteWeixinReceivetextById(id);
    }
}

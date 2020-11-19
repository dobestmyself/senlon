package com.ss.wx.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinTmessageSendlogMapper;
import com.ss.wx.domain.WeixinTmessageSendlog;
import com.ss.wx.service.IWeixinTmessageSendlogService;
import com.ss.common.core.text.Convert;

/**
 * 发送模板消息日志Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-20
 */
@Service
public class WeixinTmessageSendlogServiceImpl implements IWeixinTmessageSendlogService 
{
    @Autowired
    private WeixinTmessageSendlogMapper weixinTmessageSendlogMapper;

    /**
     * 查询发送模板消息日志
     * 
     * @param id 发送模板消息日志ID
     * @return 发送模板消息日志
     */
    @Override
    public WeixinTmessageSendlog selectWeixinTmessageSendlogById(String id)
    {
        return weixinTmessageSendlogMapper.selectWeixinTmessageSendlogById(id);
    }

    /**
     * 查询发送模板消息日志列表
     * 
     * @param weixinTmessageSendlog 发送模板消息日志
     * @return 发送模板消息日志
     */
    @Override
    public List<WeixinTmessageSendlog> selectWeixinTmessageSendlogList(WeixinTmessageSendlog weixinTmessageSendlog)
    {
        return weixinTmessageSendlogMapper.selectWeixinTmessageSendlogList(weixinTmessageSendlog);
    }

    /**
     * 新增发送模板消息日志
     * 
     * @param weixinTmessageSendlog 发送模板消息日志
     * @return 结果
     */
    @Override
    public int insertWeixinTmessageSendlog(WeixinTmessageSendlog weixinTmessageSendlog)
    {
        weixinTmessageSendlog.setId(UUID.randomUUID().toString().replace("-",""));
        return weixinTmessageSendlogMapper.insertWeixinTmessageSendlog(weixinTmessageSendlog);
    }

    /**
     * 修改发送模板消息日志
     * 
     * @param weixinTmessageSendlog 发送模板消息日志
     * @return 结果
     */
    @Override
    public int updateWeixinTmessageSendlog(WeixinTmessageSendlog weixinTmessageSendlog)
    {
        return weixinTmessageSendlogMapper.updateWeixinTmessageSendlog(weixinTmessageSendlog);
    }

    /**
     * 删除发送模板消息日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTmessageSendlogByIds(String ids)
    {
        return weixinTmessageSendlogMapper.deleteWeixinTmessageSendlogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发送模板消息日志信息
     * 
     * @param id 发送模板消息日志ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTmessageSendlogById(String id)
    {
        return weixinTmessageSendlogMapper.deleteWeixinTmessageSendlogById(id);
    }
}

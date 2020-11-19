package com.ss.wx.service;

import com.ss.wx.domain.WeixinTmessageSendlog;
import java.util.List;

/**
 * 发送模板消息日志Service接口
 * 
 * @author shishuai
 * @date 2020-07-20
 */
public interface IWeixinTmessageSendlogService 
{
    /**
     * 查询发送模板消息日志
     * 
     * @param id 发送模板消息日志ID
     * @return 发送模板消息日志
     */
    public WeixinTmessageSendlog selectWeixinTmessageSendlogById(String id);

    /**
     * 查询发送模板消息日志列表
     * 
     * @param weixinTmessageSendlog 发送模板消息日志
     * @return 发送模板消息日志集合
     */
    public List<WeixinTmessageSendlog> selectWeixinTmessageSendlogList(WeixinTmessageSendlog weixinTmessageSendlog);

    /**
     * 新增发送模板消息日志
     * 
     * @param weixinTmessageSendlog 发送模板消息日志
     * @return 结果
     */
    public int insertWeixinTmessageSendlog(WeixinTmessageSendlog weixinTmessageSendlog);

    /**
     * 修改发送模板消息日志
     * 
     * @param weixinTmessageSendlog 发送模板消息日志
     * @return 结果
     */
    public int updateWeixinTmessageSendlog(WeixinTmessageSendlog weixinTmessageSendlog);

    /**
     * 批量删除发送模板消息日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinTmessageSendlogByIds(String ids);

    /**
     * 删除发送模板消息日志信息
     * 
     * @param id 发送模板消息日志ID
     * @return 结果
     */
    public int deleteWeixinTmessageSendlogById(String id);
}

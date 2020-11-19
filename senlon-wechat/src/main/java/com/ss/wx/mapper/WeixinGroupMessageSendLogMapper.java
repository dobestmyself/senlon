package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinGroupMessageSendLog;
import java.util.List;

/**
 * 群发消息日志Mapper接口
 * 
 * @author shishuai
 * @date 2020-11-10
 */
public interface WeixinGroupMessageSendLogMapper 
{
    /**
     * 查询群发消息日志
     * 
     * @param id 群发消息日志ID
     * @return 群发消息日志
     */
    public WeixinGroupMessageSendLog selectWeixinGroupMessageSendLogById(String id);

    /**
     * 查询群发消息日志列表
     * 
     * @param weixinGroupMessageSendLog 群发消息日志
     * @return 群发消息日志集合
     */
    public List<WeixinGroupMessageSendLog> selectWeixinGroupMessageSendLogList(WeixinGroupMessageSendLog weixinGroupMessageSendLog);

    /**
     * 新增群发消息日志
     * 
     * @param weixinGroupMessageSendLog 群发消息日志
     * @return 结果
     */
    public int insertWeixinGroupMessageSendLog(WeixinGroupMessageSendLog weixinGroupMessageSendLog);

    /**
     * 修改群发消息日志
     * 
     * @param weixinGroupMessageSendLog 群发消息日志
     * @return 结果
     */
    public int updateWeixinGroupMessageSendLog(WeixinGroupMessageSendLog weixinGroupMessageSendLog);

    /**
     * 删除群发消息日志
     * 
     * @param id 群发消息日志ID
     * @return 结果
     */
    public int deleteWeixinGroupMessageSendLogById(String id);

    /**
     * 批量删除群发消息日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinGroupMessageSendLogByIds(String[] ids);
}

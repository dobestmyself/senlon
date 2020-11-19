package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinGroupMessageSendDetail;
import java.util.List;

/**
 * 群发日志明细Mapper接口
 * 
 * @author shishuai
 * @date 2020-11-10
 */
public interface WeixinGroupMessageSendDetailMapper 
{
    /**
     * 查询群发日志明细
     * 
     * @param id 群发日志明细ID
     * @return 群发日志明细
     */
    public WeixinGroupMessageSendDetail selectWeixinGroupMessageSendDetailById(String id);

    /**
     * 查询群发日志明细列表
     * 
     * @param weixinGroupMessageSendDetail 群发日志明细
     * @return 群发日志明细集合
     */
    public List<WeixinGroupMessageSendDetail> selectWeixinGroupMessageSendDetailList(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail);

    /**
     * 新增群发日志明细
     * 
     * @param weixinGroupMessageSendDetail 群发日志明细
     * @return 结果
     */
    public int insertWeixinGroupMessageSendDetail(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail);

    /**
     * 修改群发日志明细
     * 
     * @param weixinGroupMessageSendDetail 群发日志明细
     * @return 结果
     */
    public int updateWeixinGroupMessageSendDetail(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail);

    /**
     * 删除群发日志明细
     * 
     * @param id 群发日志明细ID
     * @return 结果
     */
    public int deleteWeixinGroupMessageSendDetailById(String id);

    /**
     * 批量删除群发日志明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinGroupMessageSendDetailByIds(String[] ids);
}

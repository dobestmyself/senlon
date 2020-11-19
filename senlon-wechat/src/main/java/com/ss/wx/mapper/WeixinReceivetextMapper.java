package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinReceivetext;
import java.util.List;

/**
 * 消息存储Mapper接口
 * 
 * @author shishuai
 * @date 2020-11-06
 */
public interface WeixinReceivetextMapper 
{
    /**
     * 查询消息存储
     * 
     * @param id 消息存储ID
     * @return 消息存储
     */
    public WeixinReceivetext selectWeixinReceivetextById(String id);

    /**
     * 查询消息存储列表
     * 
     * @param weixinReceivetext 消息存储
     * @return 消息存储集合
     */
    public List<WeixinReceivetext> selectWeixinReceivetextList(WeixinReceivetext weixinReceivetext);

    /**
     * 新增消息存储
     * 
     * @param weixinReceivetext 消息存储
     * @return 结果
     */
    public int insertWeixinReceivetext(WeixinReceivetext weixinReceivetext);

    /**
     * 修改消息存储
     * 
     * @param weixinReceivetext 消息存储
     * @return 结果
     */
    public int updateWeixinReceivetext(WeixinReceivetext weixinReceivetext);

    /**
     * 删除消息存储
     * 
     * @param id 消息存储ID
     * @return 结果
     */
    public int deleteWeixinReceivetextById(String id);

    /**
     * 批量删除消息存储
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinReceivetextByIds(String[] ids);
}

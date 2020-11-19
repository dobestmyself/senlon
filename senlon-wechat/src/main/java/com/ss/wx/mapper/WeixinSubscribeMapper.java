package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinSubscribe;
import java.util.List;

/**
 * 关注欢迎语Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public interface WeixinSubscribeMapper 
{
    /**
     * 查询关注欢迎语
     * 
     * @param id 关注欢迎语ID
     * @return 关注欢迎语
     */
    public WeixinSubscribe selectWeixinSubscribeById(String id);

    /**
     * 查询关注欢迎语列表
     * 
     * @param weixinSubscribe 关注欢迎语
     * @return 关注欢迎语集合
     */
    public List<WeixinSubscribe> selectWeixinSubscribeList(WeixinSubscribe weixinSubscribe);

    /**
     * 新增关注欢迎语
     * 
     * @param weixinSubscribe 关注欢迎语
     * @return 结果
     */
    public int insertWeixinSubscribe(WeixinSubscribe weixinSubscribe);

    /**
     * 修改关注欢迎语
     * 
     * @param weixinSubscribe 关注欢迎语
     * @return 结果
     */
    public int updateWeixinSubscribe(WeixinSubscribe weixinSubscribe);

    /**
     * 删除关注欢迎语
     * 
     * @param id 关注欢迎语ID
     * @return 结果
     */
    public int deleteWeixinSubscribeById(String id);

    /**
     * 批量删除关注欢迎语
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinSubscribeByIds(String[] ids);
}

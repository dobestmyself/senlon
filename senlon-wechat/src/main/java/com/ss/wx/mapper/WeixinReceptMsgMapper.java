package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinReceptMsg;
import java.util.List;

/**
 * 客服消息记录Mapper接口
 * 
 * @author shishuai
 * @date 2020-11-06
 */
public interface WeixinReceptMsgMapper 
{
    /**
     * 查询客服消息记录
     * 
     * @param id 客服消息记录ID
     * @return 客服消息记录
     */
    public WeixinReceptMsg selectWeixinReceptMsgById(String id);

    /**
     * 查询客服消息记录列表
     * 
     * @param weixinReceptMsg 客服消息记录
     * @return 客服消息记录集合
     */
    public List<WeixinReceptMsg> selectWeixinReceptMsgList(WeixinReceptMsg weixinReceptMsg);

    /**
     * 新增客服消息记录
     * 
     * @param weixinReceptMsg 客服消息记录
     * @return 结果
     */
    public int insertWeixinReceptMsg(WeixinReceptMsg weixinReceptMsg);

    /**
     * 修改客服消息记录
     * 
     * @param weixinReceptMsg 客服消息记录
     * @return 结果
     */
    public int updateWeixinReceptMsg(WeixinReceptMsg weixinReceptMsg);

    /**
     * 删除客服消息记录
     * 
     * @param id 客服消息记录ID
     * @return 结果
     */
    public int deleteWeixinReceptMsgById(String id);

    /**
     * 批量删除客服消息记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinReceptMsgByIds(String[] ids);
}

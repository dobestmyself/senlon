package com.ss.wx.service;

import com.ss.wx.domain.WeixinTmessage;
import java.util.List;

/**
 * 消息模板Service接口
 * 
 * @author shishuai
 * @date 2020-07-16
 */
public interface IWeixinTmessageService 
{
    /**
     * 查询消息模板
     * 
     * @param id 消息模板ID
     * @return 消息模板
     */
    public WeixinTmessage selectWeixinTmessageById(String id);

    /**
     * 查询消息模板列表
     * 
     * @param weixinTmessage 消息模板
     * @return 消息模板集合
     */
    public List<WeixinTmessage> selectWeixinTmessageList(WeixinTmessage weixinTmessage);

    /**
     * 新增消息模板
     * 
     * @param weixinTmessage 消息模板
     * @return 结果
     */
    public int insertWeixinTmessage(WeixinTmessage weixinTmessage);

    /**
     * 修改消息模板
     * 
     * @param weixinTmessage 消息模板
     * @return 结果
     */
    public int updateWeixinTmessage(WeixinTmessage weixinTmessage);

    /**
     * 批量删除消息模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinTmessageByIds(String ids);

    /**
     * 删除消息模板信息
     * 
     * @param id 消息模板ID
     * @return 结果
     */
    public int deleteWeixinTmessageById(String id);

    /**
     * 根据模板id查询模板信息
     * @param templateId
     * @return
     */
    public WeixinTmessage selectTmessageByTemplateId(String templateId);
}

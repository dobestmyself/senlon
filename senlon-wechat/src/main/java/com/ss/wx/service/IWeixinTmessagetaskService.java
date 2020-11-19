package com.ss.wx.service;

import com.ss.wx.domain.WeixinTmessagetask;
import com.ss.wx.vo.TmessageTaskSendVo;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 模板消息发送Service接口
 * 
 * @author shishuai
 * @date 2020-07-17
 */
public interface IWeixinTmessagetaskService 
{
    /**
     * 查询模板消息发送
     * 
     * @param id 模板消息发送ID
     * @return 模板消息发送
     */
    public WeixinTmessagetask selectWeixinTmessagetaskById(String id);

    /**
     * 查询模板消息发送列表
     * 
     * @param weixinTmessagetask 模板消息发送
     * @return 模板消息发送集合
     */
    public List<WeixinTmessagetask> selectWeixinTmessagetaskList(WeixinTmessagetask weixinTmessagetask);

    /**
     * 新增模板消息发送
     * 
     * @param weixinTmessagetask 模板消息发送
     * @return 结果
     */
    public int insertWeixinTmessagetask(WeixinTmessagetask weixinTmessagetask);

    /**
     * 修改模板消息发送
     * 
     * @param weixinTmessagetask 模板消息发送
     * @return 结果
     */
    public int updateWeixinTmessagetask(WeixinTmessagetask weixinTmessagetask);

    /**
     * 批量删除模板消息发送
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinTmessagetaskByIds(String ids);

    /**
     * 删除模板消息发送信息
     * 
     * @param id 模板消息发送ID
     * @return 结果
     */
    public int deleteWeixinTmessagetaskById(String id);

    public List<WeixinTmessagetask> selectTmessagetaskList(WeixinTmessagetask weixinTmessagetask);

    public JSONObject sendTemplateMsg(TmessageTaskSendVo taskSendVo);
}

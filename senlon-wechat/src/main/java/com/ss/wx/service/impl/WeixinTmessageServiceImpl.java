package com.ss.wx.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinTmessageMapper;
import com.ss.wx.domain.WeixinTmessage;
import com.ss.wx.service.IWeixinTmessageService;
import com.ss.common.core.text.Convert;

/**
 * 消息模板Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-16
 */
@Service
public class WeixinTmessageServiceImpl implements IWeixinTmessageService 
{
    @Autowired
    private WeixinTmessageMapper weixinTmessageMapper;

    /**
     * 查询消息模板
     * 
     * @param id 消息模板ID
     * @return 消息模板
     */
    @Override
    public WeixinTmessage selectWeixinTmessageById(String id)
    {
        return weixinTmessageMapper.selectWeixinTmessageById(id);
    }

    /**
     * 查询消息模板列表
     * 
     * @param weixinTmessage 消息模板
     * @return 消息模板
     */
    @Override
    public List<WeixinTmessage> selectWeixinTmessageList(WeixinTmessage weixinTmessage)
    {
        return weixinTmessageMapper.selectWeixinTmessageList(weixinTmessage);
    }

    /**
     * 新增消息模板
     * 
     * @param weixinTmessage 消息模板
     * @return 结果
     */
    @Override
    public int insertWeixinTmessage(WeixinTmessage weixinTmessage)
    {
        return weixinTmessageMapper.insertWeixinTmessage(weixinTmessage);
    }

    /**
     * 修改消息模板
     * 
     * @param weixinTmessage 消息模板
     * @return 结果
     */
    @Override
    public int updateWeixinTmessage(WeixinTmessage weixinTmessage)
    {
        return weixinTmessageMapper.updateWeixinTmessage(weixinTmessage);
    }

    /**
     * 删除消息模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTmessageByIds(String ids)
    {
        return weixinTmessageMapper.deleteWeixinTmessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除消息模板信息
     * 
     * @param id 消息模板ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTmessageById(String id)
    {
        return weixinTmessageMapper.deleteWeixinTmessageById(id);
    }

    /**
     * 根据模板id查询模板信息
     * @param templateId
     * @return
     */
    @Override
    public WeixinTmessage selectTmessageByTemplateId(String templateId) {
        return weixinTmessageMapper.selectTmessageByTemplateId(templateId);
    }
}

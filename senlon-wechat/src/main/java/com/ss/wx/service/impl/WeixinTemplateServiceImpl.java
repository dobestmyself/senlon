package com.ss.wx.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinTemplateMapper;
import com.ss.wx.domain.WeixinTemplate;
import com.ss.wx.service.IWeixinTemplateService;
import com.ss.common.core.text.Convert;

/**
 * 微信文章元素模板Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-11
 */
@Service
public class WeixinTemplateServiceImpl implements IWeixinTemplateService 
{
    @Autowired
    private WeixinTemplateMapper weixinTemplateMapper;

    /**
     * 查询微信文章元素模板
     * 
     * @param id 微信文章元素模板ID
     * @return 微信文章元素模板
     */
    @Override
    public WeixinTemplate selectWeixinTemplateById(String id)
    {
        return weixinTemplateMapper.selectWeixinTemplateById(id);
    }

    /**
     * 查询微信文章元素模板列表
     * 
     * @param weixinTemplate 微信文章元素模板
     * @return 微信文章元素模板
     */
    @Override
    public List<WeixinTemplate> selectWeixinTemplateList(WeixinTemplate weixinTemplate)
    {
        return weixinTemplateMapper.selectWeixinTemplateList(weixinTemplate);
    }

    /**
     * 新增微信文章元素模板
     * 
     * @param weixinTemplate 微信文章元素模板
     * @return 结果
     */
    @Override
    public int insertWeixinTemplate(WeixinTemplate weixinTemplate)
    {
        return weixinTemplateMapper.insertWeixinTemplate(weixinTemplate);
    }

    /**
     * 修改微信文章元素模板
     * 
     * @param weixinTemplate 微信文章元素模板
     * @return 结果
     */
    @Override
    public int updateWeixinTemplate(WeixinTemplate weixinTemplate)
    {
        return weixinTemplateMapper.updateWeixinTemplate(weixinTemplate);
    }

    /**
     * 删除微信文章元素模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTemplateByIds(String ids)
    {
        return weixinTemplateMapper.deleteWeixinTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信文章元素模板信息
     * 
     * @param id 微信文章元素模板ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTemplateById(String id)
    {
        return weixinTemplateMapper.deleteWeixinTemplateById(id);
    }
}

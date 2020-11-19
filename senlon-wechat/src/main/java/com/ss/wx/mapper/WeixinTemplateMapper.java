package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinTemplate;
import java.util.List;

/**
 * 微信文章元素模板Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-11
 */
public interface WeixinTemplateMapper 
{
    /**
     * 查询微信文章元素模板
     * 
     * @param id 微信文章元素模板ID
     * @return 微信文章元素模板
     */
    public WeixinTemplate selectWeixinTemplateById(String id);

    /**
     * 查询微信文章元素模板列表
     * 
     * @param weixinTemplate 微信文章元素模板
     * @return 微信文章元素模板集合
     */
    public List<WeixinTemplate> selectWeixinTemplateList(WeixinTemplate weixinTemplate);

    /**
     * 新增微信文章元素模板
     * 
     * @param weixinTemplate 微信文章元素模板
     * @return 结果
     */
    public int insertWeixinTemplate(WeixinTemplate weixinTemplate);

    /**
     * 修改微信文章元素模板
     * 
     * @param weixinTemplate 微信文章元素模板
     * @return 结果
     */
    public int updateWeixinTemplate(WeixinTemplate weixinTemplate);

    /**
     * 删除微信文章元素模板
     * 
     * @param id 微信文章元素模板ID
     * @return 结果
     */
    public int deleteWeixinTemplateById(String id);

    /**
     * 批量删除微信文章元素模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinTemplateByIds(String[] ids);
}

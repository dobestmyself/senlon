package com.ss.wx.service;

import com.ss.wx.domain.WeixinTexttemplate;
import java.util.List;

/**
 * 文本模板Service接口
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public interface IWeixinTexttemplateService 
{
    /**
     * 查询文本模板
     * 
     * @param id 文本模板ID
     * @return 文本模板
     */
    public WeixinTexttemplate selectWeixinTexttemplateById(String id);

    /**
     * 查询文本模板列表
     * 
     * @param weixinTexttemplate 文本模板
     * @return 文本模板集合
     */
    public List<WeixinTexttemplate> selectWeixinTexttemplateList(WeixinTexttemplate weixinTexttemplate);

    /**
     * 新增文本模板
     * 
     * @param weixinTexttemplate 文本模板
     * @return 结果
     */
    public int insertWeixinTexttemplate(WeixinTexttemplate weixinTexttemplate);

    /**
     * 修改文本模板
     * 
     * @param weixinTexttemplate 文本模板
     * @return 结果
     */
    public int updateWeixinTexttemplate(WeixinTexttemplate weixinTexttemplate);

    /**
     * 批量删除文本模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinTexttemplateByIds(String ids);

    /**
     * 删除文本模板信息
     * 
     * @param id 文本模板ID
     * @return 结果
     */
    public int deleteWeixinTexttemplateById(String id);

    public List<WeixinTexttemplate> queryTexttemplateByJwid(String jwid);

    public WeixinTexttemplate findTexttemplateByTemplateId(String templateId);
}

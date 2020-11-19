package com.ss.wx.service;

import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinNewstemplate;
import java.util.List;

/**
 * 图文模板Service接口
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public interface IWeixinNewstemplateService 
{
    /**
     * 查询图文模板
     * 
     * @param id 图文模板ID
     * @return 图文模板
     */
    public WeixinNewstemplate selectWeixinNewstemplateById(String id);

    /**
     * 查询图文模板列表
     * 
     * @param weixinNewstemplate 图文模板
     * @return 图文模板集合
     */
    public List<WeixinNewstemplate> selectWeixinNewstemplateList(WeixinNewstemplate weixinNewstemplate);

    /**
     * 新增图文模板
     * 
     * @param weixinNewstemplate 图文模板
     * @return 结果
     */
    public int insertWeixinNewstemplate(WeixinNewstemplate weixinNewstemplate);

    /**
     * 修改图文模板
     * 
     * @param weixinNewstemplate 图文模板
     * @return 结果
     */
    public int updateWeixinNewstemplate(WeixinNewstemplate weixinNewstemplate);

    /**
     * 批量删除图文模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinNewstemplateByIds(String ids);

    /**
     * 删除图文模板信息
     * 
     * @param id 图文模板ID
     * @return 结果
     */
    public int deleteWeixinNewstemplateById(String id);

    public List<WeixinNewstemplate> queryNewstemplateList(String jwid);

    /**
     * 上传图文素材
     * @param id
     * @param jwWebJwid
     * @return
     */
    String uploadNewsTemplate(String id, JwWebJwid jwWebJwid);

    public WeixinNewstemplate selectNewstemplateByTemplateId(String templateId);

}

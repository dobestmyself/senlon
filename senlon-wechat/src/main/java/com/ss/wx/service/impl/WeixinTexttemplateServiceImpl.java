package com.ss.wx.service.impl;

import java.util.List;
import com.ss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinTexttemplateMapper;
import com.ss.wx.domain.WeixinTexttemplate;
import com.ss.wx.service.IWeixinTexttemplateService;
import com.ss.common.core.text.Convert;

/**
 * 文本模板Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Service
public class WeixinTexttemplateServiceImpl implements IWeixinTexttemplateService 
{
    @Autowired
    private WeixinTexttemplateMapper weixinTexttemplateMapper;

    /**
     * 查询文本模板
     * 
     * @param id 文本模板ID
     * @return 文本模板
     */
    @Override
    public WeixinTexttemplate selectWeixinTexttemplateById(String id)
    {
        return weixinTexttemplateMapper.selectWeixinTexttemplateById(id);
    }

    /**
     * 查询文本模板列表
     * 
     * @param weixinTexttemplate 文本模板
     * @return 文本模板
     */
    @Override
    public List<WeixinTexttemplate> selectWeixinTexttemplateList(WeixinTexttemplate weixinTexttemplate)
    {
        return weixinTexttemplateMapper.selectWeixinTexttemplateList(weixinTexttemplate);
    }

    /**
     * 新增文本模板
     * 
     * @param weixinTexttemplate 文本模板
     * @return 结果
     */
    @Override
    public int insertWeixinTexttemplate(WeixinTexttemplate weixinTexttemplate)
    {

        weixinTexttemplate.setCreateTime(DateUtils.getNowDate());
        return weixinTexttemplateMapper.insertWeixinTexttemplate(weixinTexttemplate);
    }

    /**
     * 修改文本模板
     * 
     * @param weixinTexttemplate 文本模板
     * @return 结果
     */
    @Override
    public int updateWeixinTexttemplate(WeixinTexttemplate weixinTexttemplate)
    {
        weixinTexttemplate.setUpdateTime(DateUtils.getNowDate());
        return weixinTexttemplateMapper.updateWeixinTexttemplate(weixinTexttemplate);
    }

    /**
     * 删除文本模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTexttemplateByIds(String ids)
    {
        return weixinTexttemplateMapper.deleteWeixinTexttemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文本模板信息
     * 
     * @param id 文本模板ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTexttemplateById(String id)
    {
        return weixinTexttemplateMapper.deleteWeixinTexttemplateById(id);
    }

    @Override
    public List<WeixinTexttemplate> queryTexttemplateByJwid(String jwid) {
        return weixinTexttemplateMapper.queryTexttemplateByJwid(jwid);
    }

    @Override
    public WeixinTexttemplate findTexttemplateByTemplateId(String templateId) {
        return weixinTexttemplateMapper.findTexttemplateByTemplateId(templateId);
    }
}

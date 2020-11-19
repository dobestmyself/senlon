package com.ss.wx.service.impl;

import java.util.List;
import java.util.UUID;

import com.ss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinNewsitemMapper;
import com.ss.wx.domain.WeixinNewsitem;
import com.ss.wx.service.IWeixinNewsitemService;
import com.ss.common.core.text.Convert;

/**
 * 图文模板素材Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Service
public class WeixinNewsitemServiceImpl implements IWeixinNewsitemService 
{
    @Autowired
    private WeixinNewsitemMapper weixinNewsitemMapper;

    /**
     * 查询图文模板素材
     * 
     * @param id 图文模板素材ID
     * @return 图文模板素材
     */
    @Override
    public WeixinNewsitem selectWeixinNewsitemById(String id)
    {
        return weixinNewsitemMapper.selectWeixinNewsitemById(id);
    }

    /**
     * 查询图文模板素材列表
     * 
     * @param weixinNewsitem 图文模板素材
     * @return 图文模板素材
     */
    @Override
    public List<WeixinNewsitem> selectWeixinNewsitemList(WeixinNewsitem weixinNewsitem)
    {
        return weixinNewsitemMapper.selectWeixinNewsitemList(weixinNewsitem);
    }

    /**
     * 新增图文模板素材
     * 
     * @param weixinNewsitem 图文模板素材
     * @return 结果
     */
    @Override
    public int insertWeixinNewsitem(WeixinNewsitem weixinNewsitem)
    {
        String id = UUID.randomUUID().toString().replace("-","");
        weixinNewsitem.setId(id);
        weixinNewsitem.setCreateTime(DateUtils.getNowDate());
        return weixinNewsitemMapper.insertWeixinNewsitem(weixinNewsitem);
    }

    /**
     * 修改图文模板素材
     * 
     * @param weixinNewsitem 图文模板素材
     * @return 结果
     */
    @Override
    public int updateWeixinNewsitem(WeixinNewsitem weixinNewsitem)
    {
        weixinNewsitem.setUpdateTime(DateUtils.getNowDate());
        return weixinNewsitemMapper.updateWeixinNewsitem(weixinNewsitem);
    }

    /**
     * 删除图文模板素材对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinNewsitemByIds(String ids)
    {
        return weixinNewsitemMapper.deleteWeixinNewsitemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除图文模板素材信息
     * 
     * @param id 图文模板素材ID
     * @return 结果
     */
    @Override
    public int deleteWeixinNewsitemById(String id)
    {
        return weixinNewsitemMapper.deleteWeixinNewsitemById(id);
    }
}

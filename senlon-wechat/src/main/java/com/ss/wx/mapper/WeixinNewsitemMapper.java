package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinNewsitem;
import java.util.List;

/**
 * 图文模板素材Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-09
 */
public interface WeixinNewsitemMapper 
{
    /**
     * 查询图文模板素材
     * 
     * @param id 图文模板素材ID
     * @return 图文模板素材
     */
    public WeixinNewsitem selectWeixinNewsitemById(String id);

    /**
     * 查询图文模板素材列表
     * 
     * @param weixinNewsitem 图文模板素材
     * @return 图文模板素材集合
     */
    public List<WeixinNewsitem> selectWeixinNewsitemList(WeixinNewsitem weixinNewsitem);

    /**
     * 新增图文模板素材
     * 
     * @param weixinNewsitem 图文模板素材
     * @return 结果
     */
    public int insertWeixinNewsitem(WeixinNewsitem weixinNewsitem);

    /**
     * 修改图文模板素材
     * 
     * @param weixinNewsitem 图文模板素材
     * @return 结果
     */
    public int updateWeixinNewsitem(WeixinNewsitem weixinNewsitem);

    /**
     * 删除图文模板素材
     * 
     * @param id 图文模板素材ID
     * @return 结果
     */
    public int deleteWeixinNewsitemById(String id);

    /**
     * 批量删除图文模板素材
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinNewsitemByIds(String[] ids);
}

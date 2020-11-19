package com.ss.wx.service;

import com.ss.wx.domain.WeixinMenu;
import java.util.List;
import com.ss.common.core.domain.Ztree;

/**
 * 微信菜单Service接口
 * 
 * @author shishuai
 * @date 2020-07-14
 */
public interface IWeixinMenuService 
{
    /**
     * 查询微信菜单
     * 
     * @param id 微信菜单ID
     * @return 微信菜单
     */
    public WeixinMenu selectWeixinMenuById(Long id);

    /**
     * 查询微信菜单列表
     * 
     * @param weixinMenu 微信菜单
     * @return 微信菜单集合
     */
    public List<WeixinMenu> selectWeixinMenuList(WeixinMenu weixinMenu);

    /**
     * 新增微信菜单
     * 
     * @param weixinMenu 微信菜单
     * @return 结果
     */
    public int insertWeixinMenu(WeixinMenu weixinMenu);

    /**
     * 修改微信菜单
     * 
     * @param weixinMenu 微信菜单
     * @return 结果
     */
    public int updateWeixinMenu(WeixinMenu weixinMenu);

    /**
     * 批量删除微信菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinMenuByIds(String ids);

    /**
     * 删除微信菜单信息
     * 
     * @param id 微信菜单ID
     * @return 结果
     */
    public int deleteWeixinMenuById(Long id);

    /**
     * 查询微信菜单树列表
     * 
     * @return 所有微信菜单信息
     */
    public List<Ztree> selectWeixinMenuTree(WeixinMenu menu);

    public WeixinMenu queryByOrders(String orders,String jwid);

    public Long getFatherIdByorders(String orders,String jwid);

    public WeixinMenu queryMenuByIdAndJwid(Long id,String jwid);

    public List<WeixinMenu> queryMenuByFatherId(Long fatherId);
}

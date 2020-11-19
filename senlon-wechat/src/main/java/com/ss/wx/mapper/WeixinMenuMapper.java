package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 微信菜单Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-14
 */
public interface WeixinMenuMapper 
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
     * 删除微信菜单
     * 
     * @param id 微信菜单ID
     * @return 结果
     */
    public int deleteWeixinMenuById(Long id);

    /**
     * 批量删除微信菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinMenuByIds(String[] ids);

    public WeixinMenu queryByOrders(@Param("orders") String orders,@Param("jwid") String jwid);

    public Long getFatherIdByorders(@Param("orders") String orders,@Param("jwid") String jwid);

    public WeixinMenu queryMenuByIdAndJwid(@Param("id") Long id,@Param("jwid") String jwid);

    public List<WeixinMenu> queryMenuByFatherId(Long fatherId);
}

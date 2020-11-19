package com.ss.wx.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ss.common.core.domain.Ztree;
import com.ss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinMenuMapper;
import com.ss.wx.domain.WeixinMenu;
import com.ss.wx.service.IWeixinMenuService;
import com.ss.common.core.text.Convert;

/**
 * 微信菜单Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-14
 */
@Service
public class WeixinMenuServiceImpl implements IWeixinMenuService 
{
    @Autowired
    private WeixinMenuMapper weixinMenuMapper;

    /**
     * 查询微信菜单
     * 
     * @param id 微信菜单ID
     * @return 微信菜单
     */
    @Override
    public WeixinMenu selectWeixinMenuById(Long id)
    {
        return weixinMenuMapper.selectWeixinMenuById(id);
    }

    /**
     * 查询微信菜单列表
     * 
     * @param weixinMenu 微信菜单
     * @return 微信菜单
     */
    @Override
    public List<WeixinMenu> selectWeixinMenuList(WeixinMenu weixinMenu)
    {
        return weixinMenuMapper.selectWeixinMenuList(weixinMenu);
    }

    /**
     * 新增微信菜单
     * 
     * @param weixinMenu 微信菜单
     * @return 结果
     */
    @Override
    public int insertWeixinMenu(WeixinMenu weixinMenu)
    {
        weixinMenu.setCreateTime(DateUtils.getNowDate());
        return weixinMenuMapper.insertWeixinMenu(weixinMenu);
    }

    /**
     * 修改微信菜单
     * 
     * @param weixinMenu 微信菜单
     * @return 结果
     */
    @Override
    public int updateWeixinMenu(WeixinMenu weixinMenu)
    {
        weixinMenu.setUpdateTime(DateUtils.getNowDate());
        return weixinMenuMapper.updateWeixinMenu(weixinMenu);
    }

    /**
     * 删除微信菜单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinMenuByIds(String ids)
    {
        return weixinMenuMapper.deleteWeixinMenuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信菜单信息
     * 
     * @param id 微信菜单ID
     * @return 结果
     */
    @Override
    public int deleteWeixinMenuById(Long id)
    {
        return weixinMenuMapper.deleteWeixinMenuById(id);
    }

    /**
     * 查询微信菜单树列表
     * 
     * @return 所有微信菜单信息
     */
    @Override
    public List<Ztree> selectWeixinMenuTree(WeixinMenu menu)
    {

        List<WeixinMenu> weixinMenuList = weixinMenuMapper.selectWeixinMenuList(menu);
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (WeixinMenu weixinMenu : weixinMenuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(weixinMenu.getId());
            ztree.setpId(weixinMenu.getFatherId());
            ztree.setName(weixinMenu.getMenuName());
            ztree.setTitle(weixinMenu.getMenuName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public WeixinMenu queryByOrders(String orders, String jwid) {
        return weixinMenuMapper.queryByOrders(orders,jwid);
    }

    @Override
    public Long getFatherIdByorders(String orders, String jwid) {
        return weixinMenuMapper.getFatherIdByorders(orders,jwid);
    }

    @Override
    public WeixinMenu queryMenuByIdAndJwid(Long id, String jwid) {
        return weixinMenuMapper.queryMenuByIdAndJwid(id,jwid);
    }

    @Override
    public List<WeixinMenu> queryMenuByFatherId(Long fatherId) {
        return weixinMenuMapper.queryMenuByFatherId(fatherId);
    }
}

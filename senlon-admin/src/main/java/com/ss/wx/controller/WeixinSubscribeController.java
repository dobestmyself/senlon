package com.ss.wx.controller;

import java.util.List;

import com.ss.framework.util.ShiroUtils;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.service.IJwWebJwidService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinSubscribe;
import com.ss.wx.service.IWeixinSubscribeService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 关注欢迎语Controller
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/wx/subscribe")
public class WeixinSubscribeController extends BaseController
{
    private String prefix = "wx/subscribe";

    @Autowired
    private IWeixinSubscribeService weixinSubscribeService;
    @Autowired
    private IJwWebJwidService jwidService;

    @RequiresPermissions("wx:subscribe:view")
    @GetMapping()
    public String subscribe()
    {
        return prefix + "/subscribe";
    }

    /**
     * 查询关注欢迎语列表
     */
    @RequiresPermissions("wx:subscribe:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinSubscribe weixinSubscribe)
    {
        startPage();
        List<WeixinSubscribe> list = weixinSubscribeService.selectWeixinSubscribeList(weixinSubscribe);
        return getDataTable(list);
    }

    /**
     * 导出关注欢迎语列表
     */
    @RequiresPermissions("wx:subscribe:export")
    @Log(title = "关注欢迎语", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinSubscribe weixinSubscribe)
    {
        List<WeixinSubscribe> list = weixinSubscribeService.selectWeixinSubscribeList(weixinSubscribe);
        ExcelUtil<WeixinSubscribe> util = new ExcelUtil<WeixinSubscribe>(WeixinSubscribe.class);
        return util.exportExcel(list, "subscribe");
    }

    /**
     * 新增关注欢迎语
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存关注欢迎语
     */
    @RequiresPermissions("wx:subscribe:add")
    @Log(title = "关注欢迎语", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinSubscribe weixinSubscribe)
    {
        String username = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(username);
        weixinSubscribe.setJwid(jwWebJwid.getJwid());
        weixinSubscribe.setCreateBy(username);
        return toAjax(weixinSubscribeService.insertWeixinSubscribe(weixinSubscribe));
    }

    /**
     * 修改关注欢迎语
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinSubscribe weixinSubscribe = weixinSubscribeService.selectWeixinSubscribeById(id);
        mmap.put("weixinSubscribe", weixinSubscribe);
        return prefix + "/edit";
    }

    /**
     * 修改保存关注欢迎语
     */
    @RequiresPermissions("wx:subscribe:edit")
    @Log(title = "关注欢迎语", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinSubscribe weixinSubscribe)
    {
        weixinSubscribe.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(weixinSubscribeService.updateWeixinSubscribe(weixinSubscribe));
    }

    /**
     * 删除关注欢迎语
     */
    @RequiresPermissions("wx:subscribe:remove")
    @Log(title = "关注欢迎语", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinSubscribeService.deleteWeixinSubscribeByIds(ids));
    }
}

package com.ss.wx.controller;

import java.util.List;

import com.ss.framework.util.ShiroUtils;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinNewstemplate;
import com.ss.wx.domain.WeixinTexttemplate;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.wx.service.IWeixinNewstemplateService;
import com.ss.wx.service.IWeixinTexttemplateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinAutoresponse;
import com.ss.wx.service.IWeixinAutoresponseService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 关键字Controller
 * 
 * @author shishuai
 * @date 2020-08-05
 */
@Controller
@RequestMapping("/wx/autoresponse")
public class WeixinAutoresponseController extends BaseController
{
    private String prefix = "wx/autoresponse";

    @Autowired
    private IWeixinAutoresponseService weixinAutoresponseService;
    @Autowired
    private IWeixinNewstemplateService newstemplateService;
    @Autowired
    private IWeixinTexttemplateService texttemplateService;
    @Autowired
    private IJwWebJwidService jwidService;

    @RequiresPermissions("wx:autoresponse:view")
    @GetMapping()
    public String autoresponse()
    {
        return prefix + "/autoresponse";
    }

    /**
     * 查询关键字列表
     */
    @RequiresPermissions("wx:autoresponse:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinAutoresponse weixinAutoresponse)
    {
        startPage();
        List<WeixinAutoresponse> list = weixinAutoresponseService.selectWeixinAutoresponseList(weixinAutoresponse);
        return getDataTable(list);
    }

    /**
     * 导出关键字列表
     */
    @RequiresPermissions("wx:autoresponse:export")
    @Log(title = "关键字", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinAutoresponse weixinAutoresponse)
    {
        List<WeixinAutoresponse> list = weixinAutoresponseService.selectWeixinAutoresponseList(weixinAutoresponse);
        ExcelUtil<WeixinAutoresponse> util = new ExcelUtil<WeixinAutoresponse>(WeixinAutoresponse.class);
        return util.exportExcel(list, "autoresponse");
    }

    /**
     * 新增关键字
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存关键字
     */
    @RequiresPermissions("wx:autoresponse:add")
    @Log(title = "关键字", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinAutoresponse weixinAutoresponse)
    {
        String username = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(username);
        weixinAutoresponse.setCreateBy(username);
        weixinAutoresponse.setJwid(jwWebJwid.getJwid());
        return toAjax(weixinAutoresponseService.insertWeixinAutoresponse(weixinAutoresponse));
    }

    /**
     * 修改关键字
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinAutoresponse weixinAutoresponse = weixinAutoresponseService.selectWeixinAutoresponseById(id);
        mmap.put("weixinAutoresponse", weixinAutoresponse);
        return prefix + "/edit";
    }

    /**
     * 修改保存关键字
     */
    @RequiresPermissions("wx:autoresponse:edit")
    @Log(title = "关键字", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinAutoresponse weixinAutoresponse)
    {
        weixinAutoresponse.setUpdateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(weixinAutoresponseService.updateWeixinAutoresponse(weixinAutoresponse));
    }

    /**
     * 删除关键字
     */
    @RequiresPermissions("wx:autoresponse:remove")
    @Log(title = "关键字", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinAutoresponseService.deleteWeixinAutoresponseByIds(ids));
    }

    @RequestMapping(value="/loadTemplate",method= {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult loadTemplate(String msgType){
        if("text".equals(msgType)){
            List<WeixinTexttemplate> textTemplates = texttemplateService.selectWeixinTexttemplateList(new WeixinTexttemplate());
            return AjaxResult.success(textTemplates);
        }else if("news".equals(msgType)){
            List<WeixinNewstemplate> newstemplates = newstemplateService.selectWeixinNewstemplateList(new WeixinNewstemplate());
            return AjaxResult.success(newstemplates);
        }
        return AjaxResult.error("该消息类型模板不存在!");
    }
}

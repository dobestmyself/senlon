package com.ss.wx.controller;

import java.util.Date;
import java.util.List;

import com.ss.common.utils.Guid;
import com.ss.framework.util.ShiroUtils;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinNewstemplate;
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
import com.ss.wx.domain.WeixinTexttemplate;
import com.ss.wx.service.IWeixinTexttemplateService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 文本模板Controller
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/wx/texttemplate")
public class WeixinTexttemplateController extends BaseController
{
    private String prefix = "wx/texttemplate";

    @Autowired
    private IWeixinTexttemplateService weixinTexttemplateService;
    @Autowired
    private IJwWebJwidService jwWebJwidService;

    @RequiresPermissions("wx:texttemplate:view")
    @GetMapping()
    public String texttemplate()
    {
        return prefix + "/texttemplate";
    }

    /**
     * 查询文本模板列表
     */
    @RequiresPermissions("wx:texttemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinTexttemplate weixinTexttemplate)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        weixinTexttemplate.setJwid(jwWebJwid.getJwid());
        startPage();
        List<WeixinTexttemplate> list = weixinTexttemplateService.selectWeixinTexttemplateList(weixinTexttemplate);
        return getDataTable(list);
    }

    /**
     * 导出文本模板列表
     */
    @RequiresPermissions("wx:texttemplate:export")
    @Log(title = "文本模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinTexttemplate weixinTexttemplate)
    {
        List<WeixinTexttemplate> list = weixinTexttemplateService.selectWeixinTexttemplateList(weixinTexttemplate);
        ExcelUtil<WeixinTexttemplate> util = new ExcelUtil<WeixinTexttemplate>(WeixinTexttemplate.class);
        return util.exportExcel(list, "texttemplate");
    }

    /**
     * 新增文本模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文本模板
     */
    @RequiresPermissions("wx:texttemplate:add")
    @Log(title = "文本模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinTexttemplate weixinTexttemplate)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        weixinTexttemplate.setJwid(jwWebJwid.getJwid());
        weixinTexttemplate.setCreateBy(createBy);
        weixinTexttemplate.setCreateTime(new Date());
        weixinTexttemplate.setId(Guid.get());
        weixinTexttemplate.setTemplateId(Guid.get());
        return toAjax(weixinTexttemplateService.insertWeixinTexttemplate(weixinTexttemplate));
    }

    /**
     * 修改文本模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinTexttemplate weixinTexttemplate = weixinTexttemplateService.selectWeixinTexttemplateById(id);
        mmap.put("weixinTexttemplate", weixinTexttemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存文本模板
     */
    @RequiresPermissions("wx:texttemplate:edit")
    @Log(title = "文本模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinTexttemplate weixinTexttemplate)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        weixinTexttemplate.setCreateBy(createBy);
        weixinTexttemplate.setCreateTime(new Date());
        return toAjax(weixinTexttemplateService.updateWeixinTexttemplate(weixinTexttemplate));
    }

    /**
     * 删除文本模板
     */
    @RequiresPermissions("wx:texttemplate:remove")
    @Log(title = "文本模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinTexttemplateService.deleteWeixinTexttemplateByIds(ids));
    }

    @RequestMapping("/detail/{id}")
    @ResponseBody
    public WeixinTexttemplate toDetail(@PathVariable("id") String id){
        WeixinTexttemplate weixinTexttemplate = weixinTexttemplateService.selectWeixinTexttemplateById(id);
        return weixinTexttemplate;
    }

    @RequestMapping("/loadTexttemplateByJwid")
    @ResponseBody
    public List<WeixinTexttemplate> loadTexttemplateByJwid(){
        String username = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(username);
        List<WeixinTexttemplate> weixinTexttemplates = weixinTexttemplateService.queryTexttemplateByJwid(jwWebJwid.getJwid());
        return weixinTexttemplates;
    }

}

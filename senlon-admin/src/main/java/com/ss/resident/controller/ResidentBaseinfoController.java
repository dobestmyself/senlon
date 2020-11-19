package com.ss.resident.controller;

import com.ss.common.annotation.Log;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.core.page.TableDataInfo;
import com.ss.common.enums.BusinessType;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.resident.domain.ResidentBaseinfo;
import com.ss.resident.service.IResidentBaseinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 住院医师基本信息Controller
 * 
 * @author shishuai
 * @date 2020-05-27
 */
@Controller
@RequestMapping("/resident/baseinfo")
public class ResidentBaseinfoController extends BaseController
{
    private String prefix = "resident/baseinfo";

    @Autowired
    private IResidentBaseinfoService residentBaseinfoService;

    @RequiresPermissions("resident:baseinfo:view")
    @GetMapping()
    public String baseinfo()
    {
        return prefix + "/baseinfo";
    }

    /**
     * 查询住院医师基本信息列表
     */
    @RequiresPermissions("resident:baseinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ResidentBaseinfo residentBaseinfo)
    {
        startPage();
        List<ResidentBaseinfo> list = residentBaseinfoService.selectResidentBaseinfoList(residentBaseinfo);
        return getDataTable(list);
    }

    /**
     * 导出住院医师基本信息列表
     */
    @RequiresPermissions("resident:baseinfo:export")
    @Log(title = "住院医师基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ResidentBaseinfo residentBaseinfo)
    {
        List<ResidentBaseinfo> list = residentBaseinfoService.selectResidentBaseinfoList(residentBaseinfo);
        ExcelUtil<ResidentBaseinfo> util = new ExcelUtil<ResidentBaseinfo>(ResidentBaseinfo.class);
        return util.exportExcel(list, "baseinfo");
    }

    /**
     * 新增住院医师基本信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存住院医师基本信息
     */
    @RequiresPermissions("resident:baseinfo:add")
    @Log(title = "住院医师基本信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ResidentBaseinfo residentBaseinfo)
    {
        return toAjax(residentBaseinfoService.insertResidentBaseinfo(residentBaseinfo));
    }

    /**
     * 修改住院医师基本信息
     */
    @GetMapping("/edit/{residentId}")
    public String edit(@PathVariable("residentId") Long residentId, ModelMap mmap)
    {
        ResidentBaseinfo residentBaseinfo = residentBaseinfoService.selectResidentBaseinfoById(residentId);
        mmap.put("residentBaseinfo", residentBaseinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存住院医师基本信息
     */
    @RequiresPermissions("resident:baseinfo:edit")
    @Log(title = "住院医师基本信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated ResidentBaseinfo residentBaseinfo)
    {
        return toAjax(residentBaseinfoService.updateResidentBaseinfo(residentBaseinfo));
    }

    /**
     * 删除住院医师基本信息
     */
    @RequiresPermissions("resident:baseinfo:remove")
    @Log(title = "住院医师基本信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(residentBaseinfoService.deleteResidentBaseinfoByIds(ids));
    }
}

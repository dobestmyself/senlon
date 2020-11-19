package com.ss.resident.controller;

import com.ss.common.annotation.Log;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.core.page.TableDataInfo;
import com.ss.common.enums.BusinessType;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.resident.domain.ResidentBaseinfo;
import com.ss.resident.domain.ResidentEducation;
import com.ss.resident.service.IResidentBaseinfoService;
import com.ss.resident.service.IResidentEducationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 住院医师教育经历Controller
 * 
 * @author shishuai
 * @date 2020-05-28
 */
@Controller
@RequestMapping("/resident/education")
public class ResidentEducationController extends BaseController
{
    private String prefix = "resident/education";

    @Autowired
    private IResidentEducationService residentEducationService;
    @Autowired
    private IResidentBaseinfoService residentBaseinfoService;

    @RequiresPermissions("resident:education:view")
    @GetMapping()
    public String education()
    {
        return prefix + "/base";
    }

    @RequiresPermissions("resident:base:list")
    @RequestMapping("/baseList")
    @ResponseBody
    public TableDataInfo baseList(ResidentBaseinfo residentBaseinfo){
        startPage();
        List<ResidentBaseinfo> residentBaseinfoList = residentBaseinfoService.selectResidentBaseinfoList(residentBaseinfo);
        return getDataTable(residentBaseinfoList);
    }

    /**
     * 查询住院医师教育经历列表
     */
    @RequiresPermissions("resident:education:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ResidentEducation residentEducation)
    {
        startPage();
        List<ResidentEducation> list = residentEducationService.selectResidentEducationList(residentEducation);
        return getDataTable(list);
    }

    @GetMapping("/view/{residentId}")
    public String view(@PathVariable("residentId") Long residentId, ModelMap mmap){
        ResidentBaseinfo residentBaseinfo = residentBaseinfoService.selectResidentBaseinfoById(residentId);
        mmap.put("residentBaseinfo",residentBaseinfo);

        return prefix + "/education";
    }

    /**
     * 导出住院医师教育经历列表
     */
    @RequiresPermissions("resident:education:export")
    @Log(title = "住院医师教育经历", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ResidentEducation residentEducation)
    {
        List<ResidentEducation> list = residentEducationService.selectResidentEducationList(residentEducation);
        ExcelUtil<ResidentEducation> util = new ExcelUtil<ResidentEducation>(ResidentEducation.class);
        return util.exportExcel(list, "education");
    }

    /**
     * 新增住院医师教育经历
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long residentId, ModelMap modelMap)
    {
        modelMap.put("residentId",residentId);
        ResidentBaseinfo residentBaseinfo = residentBaseinfoService.selectResidentBaseinfoById(residentId);
        modelMap.put("residentBaseinfo",residentBaseinfo);
        return prefix + "/add";
    }

    /**
     * 新增保存住院医师教育经历
     */
    @RequiresPermissions("resident:education:add")
    @Log(title = "住院医师教育经历", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ResidentEducation residentEducation)
    {
        return toAjax(residentEducationService.insertResidentEducation(residentEducation));
    }

    /**
     * 修改住院医师教育经历
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ResidentEducation residentEducation = residentEducationService.selectResidentEducationById(id);
        mmap.put("residentEducation", residentEducation);
        return prefix + "/edit";
    }

    /**
     * 修改保存住院医师教育经历
     */
    @RequiresPermissions("resident:education:edit")
    @Log(title = "住院医师教育经历", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated ResidentEducation residentEducation)
    {
        return toAjax(residentEducationService.updateResidentEducation(residentEducation));
    }

    /**
     * 删除住院医师教育经历
     */
    @RequiresPermissions("resident:education:remove")
    @Log(title = "住院医师教育经历", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(residentEducationService.deleteResidentEducationByIds(ids));
    }
}

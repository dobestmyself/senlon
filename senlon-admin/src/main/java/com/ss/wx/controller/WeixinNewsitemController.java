package com.ss.wx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.enums.WeixinSheetTypeEnum;
import com.ss.framework.util.ShiroUtils;
import com.ss.wx.domain.WeixinTemplate;
import com.ss.wx.service.IWeixinTemplateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinNewsitem;
import com.ss.wx.service.IWeixinNewsitemService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 图文模板素材Controller
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/wx/newsitem")
public class WeixinNewsitemController extends BaseController
{
    private String prefix = "wx/newsitem";

    @Autowired
    private IWeixinNewsitemService weixinNewsitemService;
    @Autowired
    private IWeixinTemplateService templateService;

    @RequiresPermissions("wx:newsitem:view")
    @GetMapping()
    public String newsitem()
    {
        return prefix + "/newsitem";
    }

    /**
     * 查询图文模板素材列表
     */
    @RequiresPermissions("wx:newsitem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinNewsitem weixinNewsitem)
    {
        startPage();
        List<WeixinNewsitem> list = weixinNewsitemService.selectWeixinNewsitemList(weixinNewsitem);
        return getDataTable(list);
    }

    @GetMapping("/listNewsitem/{id}")
    public String listNewstemplateId(@PathVariable("id") String id,ModelMap mmap){
        mmap.put("newstemplateId",id);
        return "wx/newsitem/newsitem";
    }

 //   @RequiresPermissions("wx:newstemplate:edit")
    @PostMapping("/listNewsitem/{id}")
    @ResponseBody
    public TableDataInfo listByNewstemplateId(@PathVariable("id") String id,ModelMap mmap){
        WeixinNewsitem newsitem = new WeixinNewsitem();
        newsitem.setNewstemplateId(id);
        mmap.put("newstemplateId",id);
        startPage();
        List<WeixinNewsitem> weixinNewsitems = weixinNewsitemService.selectWeixinNewsitemList(newsitem);
        return getDataTable(weixinNewsitems);
    }

    /**
     * 导出图文模板素材列表
     */
//    @RequiresPermissions("wx:newsitem:export")
    @Log(title = "图文模板素材", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinNewsitem weixinNewsitem)
    {
        List<WeixinNewsitem> list = weixinNewsitemService.selectWeixinNewsitemList(weixinNewsitem);
        ExcelUtil<WeixinNewsitem> util = new ExcelUtil<WeixinNewsitem>(WeixinNewsitem.class);
        return util.exportExcel(list, "newsitem");
    }

    /**
     * 新增图文模板素材
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id,ModelMap mmap)
    {
        mmap.put("newstemplateId",id);
        return prefix + "/add";
    }

    /**
     * 新增保存图文模板素材
     */
   // @RequiresPermissions("wx:newsitem:add")
    @Log(title = "图文模板素材", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinNewsitem weixinNewsitem)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        weixinNewsitem.setCreateBy(createBy);
        return toAjax(weixinNewsitemService.insertWeixinNewsitem(weixinNewsitem));
    }

    /**
     * 修改图文模板素材
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinNewsitem weixinNewsitem = weixinNewsitemService.selectWeixinNewsitemById(id);
        mmap.put("weixinNewsitem", weixinNewsitem);
        return prefix + "/edit";
    }

    /**
     * 修改保存图文模板素材
     */
//    @RequiresPermissions("wx:newsitem:edit")
    @Log(title = "图文模板素材", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinNewsitem weixinNewsitem)
    {
        return toAjax(weixinNewsitemService.updateWeixinNewsitem(weixinNewsitem));
    }

    /**
     * 删除图文模板素材
     */
//    @RequiresPermissions("wx:newsitem:remove")
    @Log(title = "图文模板素材", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinNewsitemService.deleteWeixinNewsitemByIds(ids));
    }


    @RequestMapping("/getSheet")
    @ResponseBody
    public Map<String,String> getSheet(){
        Map<String,String> map = new HashMap<>();
        WeixinSheetTypeEnum[] values = WeixinSheetTypeEnum.values();
        for(WeixinSheetTypeEnum sheetType : values){
            map.put(sheetType.getCode(),sheetType.getValue());
        }
        return map;
    }

    @PostMapping("/getContentTemplate/{type}")
    @ResponseBody
    public TableDataInfo getContentTemplate(@PathVariable("type") String type){
        WeixinTemplate template = new WeixinTemplate();
        template.setType(type);
        List<WeixinTemplate> templates = templateService.selectWeixinTemplateList(template);
        return getDataTable(templates);
    }

    @GetMapping("/findall")
    public String listAllNewsItem(){
        return "wx/log/newsitem";
    }

    @PostMapping("/findall")
    @ResponseBody
    public TableDataInfo listAllNewsItem(WeixinNewsitem newsitem){
        List<WeixinNewsitem> weixinNewsitems = weixinNewsitemService.selectWeixinNewsitemList(newsitem);
        return getDataTable(weixinNewsitems);
    }
}

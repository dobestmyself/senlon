package com.ss.wx.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ss.common.utils.Guid;
import com.ss.framework.util.ShiroUtils;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinNewsitem;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.wx.service.IWeixinNewsitemService;
import jdk.nashorn.api.scripting.AbstractJSObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinNewstemplate;
import com.ss.wx.service.IWeixinNewstemplateService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图文模板Controller
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/wx/newstemplate")
public class WeixinNewstemplateController extends BaseController
{
    private String prefix = "wx/newstemplate";

    @Autowired
    private IWeixinNewstemplateService weixinNewstemplateService;
    @Autowired
    private IWeixinNewsitemService newsitemService;
    @Autowired
    private IJwWebJwidService jwWebJwidService;

    @RequiresPermissions("wx:newstemplate:view")
    @GetMapping()
    public String newstemplate()
    {
        return prefix + "/newstemplate";
    }

    /**
     * 查询图文模板列表
     */
    @RequiresPermissions("wx:newstemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinNewstemplate weixinNewstemplate)
    {
        startPage();
        List<WeixinNewstemplate> list = weixinNewstemplateService.selectWeixinNewstemplateList(weixinNewstemplate);
        return getDataTable(list);
    }

    /**
     * 导出图文模板列表
     */
    @RequiresPermissions("wx:newstemplate:export")
    @Log(title = "图文模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinNewstemplate weixinNewstemplate)
    {
        List<WeixinNewstemplate> list = weixinNewstemplateService.selectWeixinNewstemplateList(weixinNewstemplate);
        ExcelUtil<WeixinNewstemplate> util = new ExcelUtil<WeixinNewstemplate>(WeixinNewstemplate.class);
        return util.exportExcel(list, "newstemplate");
    }

    /**
     * 新增图文模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存图文模板
     */
    @RequiresPermissions("wx:newstemplate:add")
    @Log(title = "图文模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinNewstemplate weixinNewstemplate)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        weixinNewstemplate.setJwid(jwWebJwid.getJwid());
        weixinNewstemplate.setCreateBy(createBy);
        weixinNewstemplate.setId(Guid.get());
        weixinNewstemplate.setTemplateId(Guid.get());
        return toAjax(weixinNewstemplateService.insertWeixinNewstemplate(weixinNewstemplate));
    }

    /**
     * 修改图文模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinNewstemplate weixinNewstemplate = weixinNewstemplateService.selectWeixinNewstemplateById(id);
        mmap.put("weixinNewstemplate", weixinNewstemplate);
        return prefix + "/edit";
    }

    /**
     * 修改保存图文模板
     */
    @RequiresPermissions("wx:newstemplate:edit")
    @Log(title = "图文模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinNewstemplate weixinNewstemplate)
    {
        weixinNewstemplate.setCreateBy(ShiroUtils.getSysUser().getUserName());
        return toAjax(weixinNewstemplateService.updateWeixinNewstemplate(weixinNewstemplate));
    }

    /**
     * 删除图文模板
     */
    @RequiresPermissions("wx:newstemplate:remove")
    @Log(title = "图文模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinNewstemplateService.deleteWeixinNewstemplateByIds(ids));
    }

    @PostMapping("/findNewstmeplateByJwidAndUploadType")
    @ResponseBody
    public TableDataInfo findNewstmeplateByJwidAndUploadType(WeixinNewstemplate newstemplate){
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        List<WeixinNewstemplate> weixinNewstemplates = weixinNewstemplateService.queryNewstemplateList(jwWebJwid.getJwid());
        return getDataTable(weixinNewstemplates);
    }

    @GetMapping("/toDetail/{newstemplateId}")
    public String goDetailPage(@PathVariable("newstemplateId") String newstemplateId,ModelMap mmap){
        WeixinNewsitem newsitem = new WeixinNewsitem();
        newsitem.setNewstemplateId(newstemplateId);
        List<WeixinNewsitem> weixinNewsitems = newsitemService.selectWeixinNewsitemList(newsitem);
        mmap.put("newsitems",weixinNewsitems);
        return "wx/newstemplate/preview";
    }


    /**
     * 上传图文素材
     * @param id
     * @return
     */
    @RequestMapping(value="/uploadNewsTemplate",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult uploadNewsTemplate(@RequestParam("id") String id){
        AjaxResult result = null;
        try{
            String createBy = ShiroUtils.getSysUser().getUserName();
            JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
            String message = weixinNewstemplateService.uploadNewsTemplate(id,jwWebJwid);
            result = AjaxResult.success(message);
        }catch (Exception e){
            result = AjaxResult.error("上传失败");
        }
        return result;
    }

    @PostMapping("/loadNewsTemplateByJwid")
    @ResponseBody
    public List<WeixinNewstemplate> loadNewsTemplateByJwid(){
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        List<WeixinNewstemplate> weixinNewstemplates = weixinNewstemplateService.queryNewstemplateList(jwWebJwid.getJwid());
        return weixinNewstemplates;
    }

    @PostMapping("/loadTemplateName")
    @ResponseBody
    public WeixinNewstemplate loadTemplateName(String templateId){
        WeixinNewstemplate weixinNewstemplate = weixinNewstemplateService.selectNewstemplateByTemplateId(templateId);
        return weixinNewstemplate;
    }

}

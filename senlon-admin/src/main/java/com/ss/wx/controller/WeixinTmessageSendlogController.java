package com.ss.wx.controller;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinTmessageSendlog;
import com.ss.wx.service.IWeixinTmessageSendlogService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 发送模板消息日志Controller
 * 
 * @author shishuai
 * @date 2020-07-20
 */
@Controller
@RequestMapping("/wx/sendlog")
public class WeixinTmessageSendlogController extends BaseController
{
    private String prefix = "wx/sendlog";

    @Autowired
    private IWeixinTmessageSendlogService weixinTmessageSendlogService;

    @RequiresPermissions("wx:sendlog:view")
    @GetMapping("/{taskId}")
    public String sendlog(@PathVariable("taskId") String taskId, ModelMap mmap)
    {
        mmap.put("taskId",taskId);
        return prefix + "/sendlog";
    }

    /**
     * 查询发送模板消息日志列表
     */
    @RequiresPermissions("wx:sendlog:list")
    @PostMapping("/list/{taskId}")
    @ResponseBody
    public TableDataInfo list(WeixinTmessageSendlog weixinTmessageSendlog,@PathVariable("taskId") String taskId)
    {
        weixinTmessageSendlog.setTaskId(taskId);
        startPage();
        List<WeixinTmessageSendlog> list = weixinTmessageSendlogService.selectWeixinTmessageSendlogList(weixinTmessageSendlog);
        return getDataTable(list);
    }

    /**
     * 导出发送模板消息日志列表
     */
    @RequiresPermissions("wx:sendlog:export")
    @Log(title = "发送模板消息日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinTmessageSendlog weixinTmessageSendlog)
    {
        List<WeixinTmessageSendlog> list = weixinTmessageSendlogService.selectWeixinTmessageSendlogList(weixinTmessageSendlog);
        ExcelUtil<WeixinTmessageSendlog> util = new ExcelUtil<WeixinTmessageSendlog>(WeixinTmessageSendlog.class);
        return util.exportExcel(list, "sendlog");
    }

    /**
     * 新增发送模板消息日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存发送模板消息日志
     */
    @RequiresPermissions("wx:sendlog:add")
    @Log(title = "发送模板消息日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinTmessageSendlog weixinTmessageSendlog)
    {
        return toAjax(weixinTmessageSendlogService.insertWeixinTmessageSendlog(weixinTmessageSendlog));
    }

    /**
     * 修改发送模板消息日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinTmessageSendlog weixinTmessageSendlog = weixinTmessageSendlogService.selectWeixinTmessageSendlogById(id);
        mmap.put("weixinTmessageSendlog", weixinTmessageSendlog);
        return prefix + "/edit";
    }

    /**
     * 修改保存发送模板消息日志
     */
    @RequiresPermissions("wx:sendlog:edit")
    @Log(title = "发送模板消息日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinTmessageSendlog weixinTmessageSendlog)
    {
        return toAjax(weixinTmessageSendlogService.updateWeixinTmessageSendlog(weixinTmessageSendlog));
    }

    /**
     * 删除发送模板消息日志
     */
    @RequiresPermissions("wx:sendlog:remove")
    @Log(title = "发送模板消息日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinTmessageSendlogService.deleteWeixinTmessageSendlogByIds(ids));
    }
}

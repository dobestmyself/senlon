package com.ss.wx.controller;

import java.util.List;
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
import com.ss.wx.domain.WeixinReceivetext;
import com.ss.wx.service.IWeixinReceivetextService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 消息存储Controller
 * 
 * @author shishuai
 * @date 2020-11-06
 */
@Controller
@RequestMapping("/wx/receivetext")
public class WeixinReceivetextController extends BaseController
{
    private String prefix = "wx/receivetext";

    @Autowired
    private IWeixinReceivetextService weixinReceivetextService;

    @RequiresPermissions("wx:receivetext:view")
    @GetMapping()
    public String receivetext()
    {
        return prefix + "/receivetext";
    }

    /**
     * 查询消息存储列表
     */
    @RequiresPermissions("wx:receivetext:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinReceivetext weixinReceivetext)
    {
        startPage();
        List<WeixinReceivetext> list = weixinReceivetextService.selectWeixinReceivetextList(weixinReceivetext);
        return getDataTable(list);
    }

    /**
     * 导出消息存储列表
     */
    @RequiresPermissions("wx:receivetext:export")
    @Log(title = "消息存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinReceivetext weixinReceivetext)
    {
        List<WeixinReceivetext> list = weixinReceivetextService.selectWeixinReceivetextList(weixinReceivetext);
        ExcelUtil<WeixinReceivetext> util = new ExcelUtil<WeixinReceivetext>(WeixinReceivetext.class);
        return util.exportExcel(list, "receivetext");
    }

    /**
     * 新增消息存储
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存消息存储
     */
    @RequiresPermissions("wx:receivetext:add")
    @Log(title = "消息存储", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinReceivetext weixinReceivetext)
    {
        return toAjax(weixinReceivetextService.insertWeixinReceivetext(weixinReceivetext));
    }

    /**
     * 修改消息存储
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinReceivetext weixinReceivetext = weixinReceivetextService.selectWeixinReceivetextById(id);
        mmap.put("weixinReceivetext", weixinReceivetext);
        return prefix + "/edit";
    }

    /**
     * 修改保存消息存储
     */
    @RequiresPermissions("wx:receivetext:edit")
    @Log(title = "消息存储", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinReceivetext weixinReceivetext)
    {
        return toAjax(weixinReceivetextService.updateWeixinReceivetext(weixinReceivetext));
    }

    /**
     * 删除消息存储
     */
    @RequiresPermissions("wx:receivetext:remove")
    @Log(title = "消息存储", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinReceivetextService.deleteWeixinReceivetextByIds(ids));
    }
}

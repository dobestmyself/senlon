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
import com.ss.wx.domain.WeixinReceptMsg;
import com.ss.wx.service.IWeixinReceptMsgService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 客服消息记录Controller
 * 
 * @author shishuai
 * @date 2020-11-06
 */
@Controller
@RequestMapping("/wx/msg")
public class WeixinReceptMsgController extends BaseController
{
    private String prefix = "wx/msg";

    @Autowired
    private IWeixinReceptMsgService weixinReceptMsgService;

    @RequiresPermissions("wx:msg:view")
    @GetMapping()
    public String msg()
    {
        return prefix + "/msg";
    }

    /**
     * 查询客服消息记录列表
     */
    @RequiresPermissions("wx:msg:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinReceptMsg weixinReceptMsg)
    {
        startPage();
        List<WeixinReceptMsg> list = weixinReceptMsgService.selectWeixinReceptMsgList(weixinReceptMsg);
        return getDataTable(list);
    }

    /**
     * 导出客服消息记录列表
     */
    @RequiresPermissions("wx:msg:export")
    @Log(title = "客服消息记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinReceptMsg weixinReceptMsg)
    {
        List<WeixinReceptMsg> list = weixinReceptMsgService.selectWeixinReceptMsgList(weixinReceptMsg);
        ExcelUtil<WeixinReceptMsg> util = new ExcelUtil<WeixinReceptMsg>(WeixinReceptMsg.class);
        return util.exportExcel(list, "msg");
    }

    /**
     * 新增客服消息记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客服消息记录
     */
    @RequiresPermissions("wx:msg:add")
    @Log(title = "客服消息记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinReceptMsg weixinReceptMsg)
    {
        return toAjax(weixinReceptMsgService.insertWeixinReceptMsg(weixinReceptMsg));
    }

    /**
     * 修改客服消息记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinReceptMsg weixinReceptMsg = weixinReceptMsgService.selectWeixinReceptMsgById(id);
        mmap.put("weixinReceptMsg", weixinReceptMsg);
        return prefix + "/edit";
    }

    /**
     * 修改保存客服消息记录
     */
    @RequiresPermissions("wx:msg:edit")
    @Log(title = "客服消息记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinReceptMsg weixinReceptMsg)
    {
        return toAjax(weixinReceptMsgService.updateWeixinReceptMsg(weixinReceptMsg));
    }

    /**
     * 删除客服消息记录
     */
    @RequiresPermissions("wx:msg:remove")
    @Log(title = "客服消息记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinReceptMsgService.deleteWeixinReceptMsgByIds(ids));
    }
}

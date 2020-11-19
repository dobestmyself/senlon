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
import com.ss.wx.domain.WeixinGroupMessageSendDetail;
import com.ss.wx.service.IWeixinGroupMessageSendDetailService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 群发日志明细Controller
 * 
 * @author shishuai
 * @date 2020-11-10
 */
@Controller
@RequestMapping("/wx/detail")
public class WeixinGroupMessageSendDetailController extends BaseController
{
    private String prefix = "wx/detail";

    @Autowired
    private IWeixinGroupMessageSendDetailService weixinGroupMessageSendDetailService;

    @RequiresPermissions("wx:detail:view")
    @GetMapping()
    public String detail()
    {
        return prefix + "/detail";
    }

    /**
     * 查询群发日志明细列表
     */
    @RequiresPermissions("wx:detail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail)
    {
        startPage();
        List<WeixinGroupMessageSendDetail> list = weixinGroupMessageSendDetailService.selectWeixinGroupMessageSendDetailList(weixinGroupMessageSendDetail);
        return getDataTable(list);
    }

    /**
     * 导出群发日志明细列表
     */
    @RequiresPermissions("wx:detail:export")
    @Log(title = "群发日志明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinGroupMessageSendDetail weixinGroupMessageSendDetail)
    {
        List<WeixinGroupMessageSendDetail> list = weixinGroupMessageSendDetailService.selectWeixinGroupMessageSendDetailList(weixinGroupMessageSendDetail);
        ExcelUtil<WeixinGroupMessageSendDetail> util = new ExcelUtil<WeixinGroupMessageSendDetail>(WeixinGroupMessageSendDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 新增群发日志明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存群发日志明细
     */
    @RequiresPermissions("wx:detail:add")
    @Log(title = "群发日志明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinGroupMessageSendDetail weixinGroupMessageSendDetail)
    {
        return toAjax(weixinGroupMessageSendDetailService.insertWeixinGroupMessageSendDetail(weixinGroupMessageSendDetail));
    }

    /**
     * 修改群发日志明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinGroupMessageSendDetail weixinGroupMessageSendDetail = weixinGroupMessageSendDetailService.selectWeixinGroupMessageSendDetailById(id);
        mmap.put("weixinGroupMessageSendDetail", weixinGroupMessageSendDetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存群发日志明细
     */
    @RequiresPermissions("wx:detail:edit")
    @Log(title = "群发日志明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinGroupMessageSendDetail weixinGroupMessageSendDetail)
    {
        return toAjax(weixinGroupMessageSendDetailService.updateWeixinGroupMessageSendDetail(weixinGroupMessageSendDetail));
    }

    /**
     * 删除群发日志明细
     */
    @RequiresPermissions("wx:detail:remove")
    @Log(title = "群发日志明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinGroupMessageSendDetailService.deleteWeixinGroupMessageSendDetailByIds(ids));
    }
}

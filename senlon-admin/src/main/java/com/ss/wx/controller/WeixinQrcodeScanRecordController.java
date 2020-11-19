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
import com.ss.wx.domain.WeixinQrcodeScanRecord;
import com.ss.wx.service.IWeixinQrcodeScanRecordService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 二维码扫码记录Controller
 * 
 * @author shishuai
 * @date 2020-08-06
 */
@Controller
@RequestMapping("/wx/record")
public class WeixinQrcodeScanRecordController extends BaseController
{
    private String prefix = "wx/record";

    @Autowired
    private IWeixinQrcodeScanRecordService weixinQrcodeScanRecordService;

    @RequiresPermissions("wx:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询二维码扫码记录列表
     */
    @RequiresPermissions("wx:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinQrcodeScanRecord weixinQrcodeScanRecord)
    {
        startPage();
        List<WeixinQrcodeScanRecord> list = weixinQrcodeScanRecordService.selectWeixinQrcodeScanRecordList(weixinQrcodeScanRecord);
        return getDataTable(list);
    }

    /**
     * 导出二维码扫码记录列表
     */
    @RequiresPermissions("wx:record:export")
    @Log(title = "二维码扫码记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinQrcodeScanRecord weixinQrcodeScanRecord)
    {
        List<WeixinQrcodeScanRecord> list = weixinQrcodeScanRecordService.selectWeixinQrcodeScanRecordList(weixinQrcodeScanRecord);
        ExcelUtil<WeixinQrcodeScanRecord> util = new ExcelUtil<WeixinQrcodeScanRecord>(WeixinQrcodeScanRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增二维码扫码记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存二维码扫码记录
     */
    @RequiresPermissions("wx:record:add")
    @Log(title = "二维码扫码记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinQrcodeScanRecord weixinQrcodeScanRecord)
    {
        return toAjax(weixinQrcodeScanRecordService.insertWeixinQrcodeScanRecord(weixinQrcodeScanRecord));
    }

    /**
     * 修改二维码扫码记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinQrcodeScanRecord weixinQrcodeScanRecord = weixinQrcodeScanRecordService.selectWeixinQrcodeScanRecordById(id);
        mmap.put("weixinQrcodeScanRecord", weixinQrcodeScanRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码扫码记录
     */
    @RequiresPermissions("wx:record:edit")
    @Log(title = "二维码扫码记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinQrcodeScanRecord weixinQrcodeScanRecord)
    {
        return toAjax(weixinQrcodeScanRecordService.updateWeixinQrcodeScanRecord(weixinQrcodeScanRecord));
    }

    /**
     * 删除二维码扫码记录
     */
    @RequiresPermissions("wx:record:remove")
    @Log(title = "二维码扫码记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinQrcodeScanRecordService.deleteWeixinQrcodeScanRecordByIds(ids));
    }
}

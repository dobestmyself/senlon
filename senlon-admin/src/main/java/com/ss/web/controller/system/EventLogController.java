package com.ss.web.controller.system;

import com.ss.common.annotation.Log;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.core.page.TableDataInfo;
import com.ss.common.enums.BusinessType;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.system.domain.EventLog;
import com.ss.system.service.IEventLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 事件日志Controller
 * 
 * @author shishuai
 * @date 2019-12-30
 */
@Controller
@RequestMapping("/system/eventLog")
public class EventLogController extends BaseController
{
    private String prefix = "system/eventLog";

    @Autowired
    private IEventLogService eventLogService;

    @RequiresPermissions("system:eventLog:view")
    @GetMapping()
    public String eventLog()
    {
        return prefix + "/eventLog";
    }

    /**
     * 查询事件日志列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EventLog eventLog)
    {
        startPage();
        List<EventLog> list = eventLogService.selectEventLogList(eventLog);
        return getDataTable(list);
    }

    /**
     * 导出事件日志列表
     */
    @Log(title = "事件日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EventLog eventLog)
    {
        List<EventLog> list = eventLogService.selectEventLogList(eventLog);
        ExcelUtil<EventLog> util = new ExcelUtil<EventLog>(EventLog.class);
        return util.exportExcel(list, "eventLog");
    }



    /**
     * 修改事件日志
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        EventLog eventLog = eventLogService.selectEventLogById(id);
        mmap.put("eventLog", eventLog);
        return prefix + "/detail";
    }

    /**
     * 删除事件日志
     */
    //@RequiresPermissions("system:eventLog:remove")
    @Log(title = "事件日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eventLogService.deleteEventLogByIds(ids));
    }
}

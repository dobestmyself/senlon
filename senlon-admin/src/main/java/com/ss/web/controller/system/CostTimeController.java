package com.ss.web.controller.system;


import com.ss.common.annotation.Log;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.core.page.TableDataInfo;
import com.ss.common.enums.BusinessType;
import com.ss.framework.aspectj.CountResponseTimeAspect;
import com.ss.system.domain.CostTime;
import com.ss.system.service.ICostTimeService;
import com.ss.system.service.ISysOperLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统响应时间统计记录（方便对响应时间长的进行优化） 信息操作处理
 *
 * @author lws
 * @date 2018-10-25
 */
@Controller
@RequestMapping("/system/costTime")
public class CostTimeController extends BaseController {
    private String prefix = "system/costTime";

    @Autowired
    private ICostTimeService costTimeService;
    @Autowired
    private ISysOperLogService operLogService;

    @RequiresPermissions("system:costTime:view")
    @GetMapping()
    public String costTime(ModelMap modelMap) {
        modelMap.put("CONDITION_TIME", CountResponseTimeAspect.CONDITION_TIME);
        return prefix + "/costTime";
    }

    /**
     * 查询系统响应时间统计记录（方便对响应时间长的进行优化）列表
     */
    @RequiresPermissions("system:costTime:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CostTime costTime) {
        startPage();
        List<CostTime> list = costTimeService.selectCostTimeList(costTime);
        return getDataTable(list);
    }

    /**
     * 删除系统响应时间统计记录（方便对响应时间长的进行优化）
     */
    @RequiresPermissions("system:costTime:remove")
    @Log(title = "系统响应时间统计记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(costTimeService.deleteCostTimeByIds(ids));
    }

    /**
     * 删除系统响应时间统计记录（方便对响应时间长的进行优化）
     */
    @RequiresPermissions("system:costTime:remove")
    @Log(title = "系统响应时间统计记录", businessType = BusinessType.CLEAN)
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean(String ids) {
        operLogService.cleanTable("sys_cost_time");
        return toAjax(1);
    }

}

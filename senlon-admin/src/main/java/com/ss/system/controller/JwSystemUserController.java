package com.ss.system.controller;

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
import com.ss.system.domain.JwSystemUser;
import com.ss.system.service.IJwSystemUserService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 系统用户Controller
 * 
 * @author shishuai
 * @date 2020-07-02
 */
@Controller
@RequestMapping("/sys/user")
public class JwSystemUserController extends BaseController
{
    private String prefix = "sys/user";

    @Autowired
    private IJwSystemUserService jwSystemUserService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    /**
     * 查询系统用户列表
     */
    @RequiresPermissions("system:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JwSystemUser jwSystemUser)
    {

        startPage();
        List<JwSystemUser> list = jwSystemUserService.selectJwSystemUserList(jwSystemUser);
        return getDataTable(list);
    }

    /**
     * 导出系统用户列表
     */
    @RequiresPermissions("system:user:export")
    @Log(title = "系统用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JwSystemUser jwSystemUser)
    {
        List<JwSystemUser> list = jwSystemUserService.selectJwSystemUserList(jwSystemUser);
        ExcelUtil<JwSystemUser> util = new ExcelUtil<JwSystemUser>(JwSystemUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 新增系统用户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存系统用户
     */
    @RequiresPermissions("system:user:add")
    @Log(title = "系统用户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated JwSystemUser jwSystemUser)
    {
        return toAjax(jwSystemUserService.insertJwSystemUser(jwSystemUser));
    }

    /**
     * 修改系统用户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        JwSystemUser jwSystemUser = jwSystemUserService.selectJwSystemUserById(id);
        mmap.put("jwSystemUser", jwSystemUser);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统用户
     */
    @RequiresPermissions("system:user:edit")
    @Log(title = "系统用户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated JwSystemUser jwSystemUser)
    {
        return toAjax(jwSystemUserService.updateJwSystemUser(jwSystemUser));
    }

    /**
     * 删除系统用户
     */
    @RequiresPermissions("system:user:remove")
    @Log(title = "系统用户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jwSystemUserService.deleteJwSystemUserByIds(ids));
    }
}

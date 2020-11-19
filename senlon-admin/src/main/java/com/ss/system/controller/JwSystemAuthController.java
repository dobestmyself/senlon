package com.ss.system.controller;

import java.util.List;

import com.ss.common.constant.UserConstants;
import com.ss.framework.util.ShiroUtils;
import com.ss.system.domain.JwSystemRole;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
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
import com.ss.system.domain.JwSystemAuth;
import com.ss.system.service.IJwSystemAuthService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.utils.StringUtils;
import com.ss.common.core.domain.Ztree;

/**
 * 系统权限菜单Controller
 * 
 * @author shishuai
 * @date 2020-07-03
 */
@Controller
@RequestMapping("/system/auth")
public class JwSystemAuthController extends BaseController
{
    private String prefix = "sys/auth";

    @Autowired
    private IJwSystemAuthService jwSystemAuthService;

    @RequiresPermissions("system:auth:view")
    @GetMapping()
    public String auth()
    {
        return prefix + "/auth";
    }

    /**
     * 查询系统权限菜单树列表
     */
    @RequiresPermissions("system:auth:list")
    @PostMapping("/list")
    @ResponseBody
    public List<JwSystemAuth> list(JwSystemAuth jwSystemAuth)
    {
        List<JwSystemAuth> list = jwSystemAuthService.selectJwSystemAuthList(jwSystemAuth);
        return list;
    }

    /**
     * 导出系统权限菜单列表
     */
    @RequiresPermissions("system:auth:export")
    @Log(title = "系统权限菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JwSystemAuth jwSystemAuth)
    {
        List<JwSystemAuth> list = jwSystemAuthService.selectJwSystemAuthList(jwSystemAuth);
        ExcelUtil<JwSystemAuth> util = new ExcelUtil<JwSystemAuth>(JwSystemAuth.class);
        return util.exportExcel(list, "auth");
    }

    /**
     * 新增系统权限菜单
     */
    @GetMapping(value = {"/add" })
    public String add(ModelMap mmap)
    {
        JwSystemAuth jwSystemAuth = new JwSystemAuth();
        jwSystemAuth.setAuthId(0L);
        jwSystemAuth.setAuthName("主目录");
        mmap.put("jwSystemAuth",jwSystemAuth);
        return prefix + "/add";
    }

    /**
     * 新增保存系统权限菜单
     */
    @RequiresPermissions("system:auth:add")
    @Log(title = "系统权限菜单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated JwSystemAuth jwSystemAuth)
    {
        if(UserConstants.MENU_NAME_NOT_UNIQUE.equals(jwSystemAuthService.checkAuthNameUnique(jwSystemAuth.getAuthName()))){
            return error("新增菜单"+ jwSystemAuth.getAuthName() + "失败，菜单名称已存在");
        }
        if("主目录".equals(jwSystemAuth.getParentName())){
            jwSystemAuth.setParentAuthId(0L);
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(jwSystemAuthService.insertJwSystemAuth(jwSystemAuth));
    }

    /**
     * 修改系统权限菜单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        JwSystemAuth jwSystemAuth = jwSystemAuthService.selectJwSystemAuthById(id);
        mmap.put("jwSystemAuth", jwSystemAuth);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统权限菜单
     */
    @RequiresPermissions("system:auth:edit")
    @Log(title = "系统权限菜单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated JwSystemAuth jwSystemAuth)
    {
        if(UserConstants.MENU_NAME_NOT_UNIQUE.equals(jwSystemAuthService.checkAuthNameUnique(jwSystemAuth.getAuthName()))){
            return error("修改菜单"+ jwSystemAuth.getAuthName() + "失败，菜单名称已存在");
        }
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(jwSystemAuthService.updateJwSystemAuth(jwSystemAuth));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:auth:remove")
    @Log(title = "系统权限菜单", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        JwSystemAuth auth = jwSystemAuthService.selectJwSystemAuthById(id);
        if(jwSystemAuthService.selectCountChildByParentId(auth.getAuthId()) > 0){
            return AjaxResult.warn("存在子菜单，不允许删除");
        }
        if(jwSystemAuthService.selectCountRoleAuthByAuthId(auth.getAuthId()) > 0){
            return AjaxResult.warn("菜单已分配，不允许删除");
        }
        return toAjax(jwSystemAuthService.deleteJwSystemAuthById(id));
    }

    /**
     * 选择系统权限菜单树
     */
    @GetMapping(value = { "/selectAuthTree/{id}", "/selectAuthTree/" })
    public String selectAuthTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("jwSystemAuth", jwSystemAuthService.selectJwSystemAuthById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载系统权限菜单树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = jwSystemAuthService.selectJwSystemAuthTree();
        return ztrees;
    }

    /**
     * 加载角色菜单列表树
     * @param role
     * @return
     */
    @GetMapping("/roleAuthTreeData")
    @ResponseBody
    public List<Ztree> roleAuthTreeData(JwSystemRole role){
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = jwSystemAuthService.roleAuthTreeData(role,userId);
        return ztrees;
    }
}

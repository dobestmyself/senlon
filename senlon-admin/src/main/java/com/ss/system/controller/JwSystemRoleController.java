package com.ss.system.controller;

import java.util.List;

import com.ss.system.domain.JwSystemUserRoleRel;
import com.ss.system.domain.SysUser;
import com.ss.system.service.ISysUserService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
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
import com.ss.system.domain.JwSystemRole;
import com.ss.system.service.IJwSystemRoleService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

import javax.annotation.security.PermitAll;

/**
 * 系统角色Controller
 * 
 * @author shishuai
 * @date 2020-07-03
 */
@Controller
@RequestMapping("/sys/role")
public class JwSystemRoleController extends BaseController
{
    private String prefix = "sys/role";

    @Autowired
    private IJwSystemRoleService jwSystemRoleService;

    @Autowired
    private ISysUserService userService;

    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String role()
    {
        return prefix + "/role";
    }

    /**
     * 查询系统角色列表
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JwSystemRole jwSystemRole)
    {
        startPage();
        List<JwSystemRole> list = jwSystemRoleService.selectJwSystemRoleList(jwSystemRole);
        return getDataTable(list);
    }

    /**
     * 导出系统角色列表
     */
    @RequiresPermissions("system:role:export")
    @Log(title = "系统角色", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JwSystemRole jwSystemRole)
    {
        List<JwSystemRole> list = jwSystemRoleService.selectJwSystemRoleList(jwSystemRole);
        ExcelUtil<JwSystemRole> util = new ExcelUtil<JwSystemRole>(JwSystemRole.class);
        return util.exportExcel(list, "role");
    }

    /**
     * 新增系统角色
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存系统角色
     */
    @RequiresPermissions("system:role:add")
    @Log(title = "系统角色", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated JwSystemRole jwSystemRole)
    {
        return toAjax(jwSystemRoleService.insertJwSystemRole(jwSystemRole));
    }

    /**
     * 修改系统角色
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        JwSystemRole jwSystemRole = jwSystemRoleService.selectJwSystemRoleById(id);
        mmap.put("jwSystemRole", jwSystemRole);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统角色
     */
    @RequiresPermissions("system:role:edit")
    @Log(title = "系统角色", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated JwSystemRole jwSystemRole)
    {
        return toAjax(jwSystemRoleService.updateJwSystemRole(jwSystemRole));
    }

    /**
     * 删除系统角色
     */
    @RequiresPermissions("system:role:remove")
    @Log(title = "系统角色", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jwSystemRoleService.deleteJwSystemRoleByIds(ids));
    }

    @PostMapping("/checkRoleIdUnique")
    @ResponseBody
    public String checkRoleIdUnique(JwSystemRole role){
        return jwSystemRoleService.checkRoleIdUnique(role);
    }

    @PostMapping("/checkRoleNameUnique")
    @ResponseBody
    public String checkRoleNameUnique(JwSystemRole role){
        return jwSystemRoleService.checkRoleNameUnique(role);
    }

    /**
     * 分配用户
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("system:role:edit")
    @GetMapping("/authUser/{id}")
    public String authUser(@PathVariable("id") Long id,ModelMap mmap){
        mmap.put("role",jwSystemRoleService.selectJwSystemRoleById(id));
        return prefix + "/authUser";
    }

    /**
     * 查询已分配用户角色列表
     * @param user
     * @return
     */
    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SysUser user){
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 选择用户
     * @param
     * @param mmap
     * @return
     */
    @GetMapping("/authUser/selectUser/{roleId}")
    public String selectUser(@PathVariable("roleId") String roleId,ModelMap mmap){

        mmap.put("roleId",roleId);
        return prefix + "/selectUser";
    }

    @RequiresPermissions("system:role:list")
    @PostMapping("/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SysUser user){
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    /**
     * 取消授权
     * @param userRole
     * @return
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(JwSystemUserRoleRel userRole){
        return toAjax(jwSystemRoleService.deleteAuthUser(userRole));
    }

    /**
     * 批量取消授权
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @PostMapping("/authUser/cancelAll")
    @ResponseBody
    public AjaxResult cancelAuthUserAll(String roleId, String userIds)
    {
        return toAjax(jwSystemRoleService.deleteAuthUsers(roleId, userIds));
    }

    /**
     * 批量选择用户授权
     * @param userIds
     * @param roleId
     * @return
     */
    @Log(title="角色管理",businessType = BusinessType.GRANT)
    @PostMapping("/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(String userIds,String roleId){
        return toAjax(jwSystemRoleService.insertAuthUsers(userIds,roleId));
    }

}

package com.ss.wx.controller;

import java.util.*;

import com.jeecg.qywx.api.media.JwMediaAPI;
import com.ss.framework.util.ShiroUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WxErrCodeUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinNewsitem;
import com.ss.wx.service.IJwWebJwidService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeewx.api.core.req.model.menu.WeixinButton;
import org.jeewx.api.wxmenu.JwMenuAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinMenu;
import com.ss.wx.service.IWeixinMenuService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.utils.StringUtils;
import com.ss.common.core.domain.Ztree;

import javax.servlet.http.HttpServletRequest;

/**
 * 微信菜单Controller
 * 
 * @author shishuai
 * @date 2020-07-14
 */
@Controller
@RequestMapping("/wx/menu")
public class WeixinMenuController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(WeixinMenuController.class);
    private String prefix = "wx/menu";

    @Autowired
    private IWeixinMenuService weixinMenuService;
    @Autowired
    private IJwWebJwidService jwidService;

    @RequiresPermissions("wx:menu:view")
    @GetMapping()
    public String menu()
    {
        return prefix + "/menu";
    }

    /**
     * 查询微信菜单树列表
     */
    @RequiresPermissions("wx:menu:list")
    @PostMapping("/list")
    @ResponseBody
    public List<WeixinMenu> list(WeixinMenu weixinMenu)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        weixinMenu.setJwid(jwWebJwid.getJwid());
        List<WeixinMenu> list = weixinMenuService.selectWeixinMenuList(weixinMenu);
        return list;
    }

    /**
     * 导出微信菜单列表
     */
    @RequiresPermissions("wx:menu:export")
    @Log(title = "微信菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinMenu weixinMenu)
    {
        List<WeixinMenu> list = weixinMenuService.selectWeixinMenuList(weixinMenu);
        ExcelUtil<WeixinMenu> util = new ExcelUtil<WeixinMenu>(WeixinMenu.class);
        return util.exportExcel(list, "menu");
    }

    @GetMapping("/add")
    public String add(ModelMap mmap){
        WeixinMenu weixinMenu = new WeixinMenu();
        weixinMenu.setId(0L);
        weixinMenu.setMenuName("主目录");
        mmap.put("weixinMenu",weixinMenu);
        return prefix + "/add";
    }


    /**
     * 新增微信菜单
     */
    @GetMapping("/add/{fatherId}")
    public String add(@PathVariable("fatherId") Long fatherId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(fatherId))
        {
            mmap.put("weixinMenu", weixinMenuService.selectWeixinMenuById(fatherId));
        }else{
            WeixinMenu weixinMenu = new WeixinMenu();
            weixinMenu.setId(0L);
            weixinMenu.setMenuName("主目录");
            mmap.put("weixinMenu",weixinMenu);
        }

        return prefix + "/add";
    }

    /**
     * 新增保存微信菜单
     */
    @RequiresPermissions("wx:menu:add")
    @Log(title = "微信菜单", businessType = BusinessType.INSERT)
    @PostMapping("/add/{fatherName}")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinMenu weixinMenu, @PathVariable("fatherName") String fatherName)
    {
        AjaxResult result = new AjaxResult();
        try{
            String createBy = ShiroUtils.getSysUser().getUserName();
            JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
            //添加创建时间
            weixinMenu.setCreateTime(new Date());
            weixinMenu.setCreateBy(createBy);
            //1、判断当前级是否存在
            WeixinMenu menu = weixinMenuService.queryByOrders(weixinMenu.getOrders(), jwWebJwid.getJwid());
            //若存在，则提示该菜单位置已存在
            if(menu != null){
                result = AjaxResult.warn("该菜单位置已存在");
            }else{
                //否则 获取父类id
                Long fatherId = weixinMenuService.getFatherIdByorders(weixinMenu.getOrders(),jwWebJwid.getJwid());
                //判断是不是父级
                if(weixinMenu.getOrders().length()>1 && StringUtils.isNotEmpty(String.valueOf(fatherId)) || weixinMenu.getOrders().length()==1){

                    //不是父级，有父级，直接添加，是父级直接添加
                    if("主目录".equals(fatherName)){
                        weixinMenu.setFatherId(0L);
                    }else{
                        weixinMenu.setFatherId(fatherId);
                    }
                    //菜单KEY改为时间戳
                    weixinMenu.setMenuKey(String.valueOf(System.currentTimeMillis()));
                    weixinMenu.setJwid(jwWebJwid.getJwid());
                    //保存
                    weixinMenuService.insertWeixinMenu(weixinMenu);
                    result = AjaxResult.success("保存成功");
                }else{
                    result = AjaxResult.warn("当前菜单无上级菜单，请先添加上级菜单");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            result = AjaxResult.error("保存失败");
        }
        return result;
    }

    /**
     * 修改微信菜单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WeixinMenu weixinMenu = weixinMenuService.selectWeixinMenuById(id);
        mmap.put("weixinMenu", weixinMenu);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信菜单
     */
    @RequiresPermissions("wx:menu:edit")
    @Log(title = "微信菜单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinMenu weixinMenu)
    {

        AjaxResult result = new AjaxResult();
        try{
            String updateBy = ShiroUtils.getSysUser().getUpdateBy();
            weixinMenu.setUpdateBy(updateBy);
            weixinMenu.setUpdateTime(new Date());
            WeixinMenu menu = weixinMenuService.selectWeixinMenuById(weixinMenu.getId());
            List<WeixinMenu> menus = weixinMenuService.queryMenuByFatherId(weixinMenu.getId());
            //当前级是父级且存在子级
            if(menu.getOrders().length()==1&& menus.size()>0){
                //父级存在子级，
                if(weixinMenu.getOrders().equals(menu.getOrders())){
                    //位置未发生变化，可编辑
                    weixinMenuService.updateWeixinMenu(weixinMenu);
                    result = AjaxResult.success("编辑成功");
                }else{
                    //位置发生变化，不能编辑
                    result = AjaxResult.warn("当前菜单存在下级菜单，不能被编辑");
                }
            }else{
                //当前级是父级但不存在子级（或者当前级不存在父级）
                String createBy = ShiroUtils.getSysUser().getUserName();
                JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
                String jwid = jwWebJwid.getJwid();
                //查询新位置上原来是否存在菜单
                WeixinMenu wMenu = weixinMenuService.queryByOrders(weixinMenu.getOrders(), jwid);
                //判断当前级是否存在
                if(wMenu!=null && weixinMenu.getId().equals(wMenu.getId()) || wMenu == null){
                    //存在 是本身，能编辑，不存在，能编辑
                    Long fatherId = weixinMenuService.getFatherIdByorders(weixinMenu.getOrders(), jwid);
                    if(weixinMenu.getOrders().length()>1 && StringUtils.isEmpty(String.valueOf(fatherId))){
                        result = AjaxResult.warn("当前菜单位置的父级不存在，请先添加一级菜单");
                    }else{
                        if(StringUtils.isEmpty(String.valueOf(fatherId))){
                            fatherId = 0L;
                        }
                        weixinMenu.setFatherId(fatherId);
                        weixinMenuService.updateWeixinMenu(weixinMenu);
                        result = AjaxResult.success("编辑成功");
                    }

                }else{
                    //存在，不是本身，不能编辑
                    result = AjaxResult.warn("当前菜单位置已存在不能被编辑");
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            result = AjaxResult.error("编辑失败");
        }
        return result;
    }

    /**
     * 删除
     */
    @RequiresPermissions("wx:menu:remove")
    @Log(title = "微信菜单", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        AjaxResult result = new AjaxResult();
        try{
            //查询被删除的菜单信息
            WeixinMenu oldMenu = weixinMenuService.selectWeixinMenuById(id);
            //查询当前菜单的子级个数
            List<WeixinMenu> weixinMenus = weixinMenuService.queryMenuByFatherId(id);
            if(oldMenu.getOrders().length()==1&& weixinMenus.size()>0){
                //是父级并且存在子级，不能够被删除
                result = AjaxResult.warn("当前菜单存在下级菜单，不能删除");
            }else{
                //是父级不存在子级(或者不是父级)
                weixinMenuService.deleteWeixinMenuById(id);
                result = AjaxResult.success("删除成功");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            result = AjaxResult.error("删除失败");
        }

        return result;
    }

    /**
     * 选择微信菜单树
     */
    @GetMapping(value = { "/selectMenuTree/{id}", "/selectMenuTree/" })
    public String selectMenuTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        if (StringUtils.isNotNull(id))
        {
            mmap.put("weixinMenu", weixinMenuService.queryMenuByIdAndJwid(id,jwWebJwid.getJwid()));
        }
        return prefix + "/tree";
    }

    /**
     * 加载微信菜单树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        WeixinMenu menu = new WeixinMenu();
        menu.setJwid(jwWebJwid.getJwid());
        List<Ztree> ztrees = weixinMenuService.selectWeixinMenuTree(menu);
        return ztrees;
    }

    @RequestMapping(value="doSyncMenu",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult doSyncMenu(HttpServletRequest request){
        AjaxResult result = new AjaxResult();
        //获取jwid
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        String jwid = jwWebJwid.getJwid();
        //根据jwid获取一级菜单
        WeixinMenu firstMenu = new WeixinMenu();
        firstMenu.setFatherId(0L);
        firstMenu.setJwid(jwid);
        List<WeixinMenu> firstMenus = weixinMenuService.selectWeixinMenuList(firstMenu);
        Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        String accessToken = map.get("accessToken").toString();
        if(oConvertUtils.isEmpty(accessToken)){
            result = AjaxResult.warn("未获取到公众号accessToken");
        }
        //判断如果菜单为空的话，则调用删除菜单的接口
        if(firstMenus.size() == 0){
            try{
                JwMenuAPI.deleteMenu(accessToken);
                return AjaxResult.success("同步微信菜单成功");
            }catch (Exception e){
                e.printStackTrace();
                JSONObject jsonObj = JSONObject.fromObject(e.getMessage());
                String errcode = jsonObj.getString("errcode");
                String msg = WxErrCodeUtil.testErrCode(errcode);
                result = AjaxResult.error("微信菜单同步失败！"+msg);
                return result;
            }
        }
        //获取二级菜单
        List<WeixinButton> resultList = new ArrayList<>();
        for(int i=0;i<firstMenus.size();i++){
            WeixinMenu childMenu = new WeixinMenu();
            childMenu.setJwid(jwid);
            childMenu.setFatherId(firstMenus.get(i).getId());
            List<WeixinMenu> childMenus = weixinMenuService.selectWeixinMenuList(childMenu);
            if(childMenus.size()==0){
                //组装菜单接口的参数结构体
                resultList.add(combineBtn(firstMenus.get(i)));
            }else{
                //组装一级菜单名称
                WeixinButton wxButton = new WeixinButton();
                wxButton.setName(firstMenus.get(i).getMenuName());
                //组装二级菜单接口的参数结构体
                List<WeixinButton> childlist=new ArrayList<WeixinButton>();
                for(int m=0;m<childMenus.size();m++){
                    childlist.add(combineBtn(childMenus.get(m)));
                }
                wxButton.setSub_button(childlist);
                resultList.add(wxButton);
            }
        }
        try{
            JwMenuAPI.createMenu(accessToken,resultList);
            result = AjaxResult.success("同步微信成功！");
        }catch (Exception e){
            JSONObject  jsonObj = JSONObject.fromObject(e.getMessage());
            String errcode = jsonObj.getString("errcode");
            String msg = WxErrCodeUtil.testErrCode(errcode);
            result = AjaxResult.error("微信菜单同步失败!"+msg);
        }
        return result;
    }

    /**
     * @功能：组装菜单接口的参数结构体
     * @param weixinMenu
     */
    private WeixinButton combineBtn(WeixinMenu weixinMenu) {
        WeixinButton wxButton=new WeixinButton();
        //网页链接类
        if("view".equals(weixinMenu.getMenuType())){
            wxButton.setName(weixinMenu.getMenuName());
            wxButton.setType(weixinMenu.getMenuType());
            wxButton.setUrl(weixinMenu.getUrl());
        }
        //消息触发类
        if("click".equals(weixinMenu.getMenuType())){
            wxButton.setName(weixinMenu.getMenuName());
            wxButton.setType(weixinMenu.getMenuType());
            wxButton.setKey(weixinMenu.getMenuKey());
        }
        //小程序类
        if("miniprogram".equals(weixinMenu.getMenuType())){
            wxButton.setName(weixinMenu.getMenuName());
            wxButton.setType(weixinMenu.getMenuType());
            wxButton.setUrl(weixinMenu.getUrl());
            wxButton.setAppid(weixinMenu.getMiniprogramAppid());
            wxButton.setPagepath(weixinMenu.getMiniprogramPagepath());
        }
        return wxButton;
    }

}

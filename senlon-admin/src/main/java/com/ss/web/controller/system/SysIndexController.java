package com.ss.web.controller.system;

import com.ss.common.config.Global;
import com.ss.common.constant.Constants;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.framework.util.ShiroUtils;
import com.ss.system.domain.JwSystemAuth;
import com.ss.system.domain.SysMenu;
import com.ss.system.domain.SysUser;
import com.ss.system.service.IJwSystemAuthService;
import com.ss.system.service.ISysConfigService;
import com.ss.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author shishuai
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private IJwSystemAuthService jwSystemAuthService;

    @Autowired
    private ISysConfigService configService;

    private String getAdminIndex(){
        return configService.selectConfigByKey(Constants.KEY_ADMIN_INDEX);
    }

    @RequestMapping("/admin")
    public String admin() {
        return "forward:/index";
    }

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
    //    List<JwSystemAuth> menus = jwSystemAuthService.findJwSystemAuthList();
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("demoEnabled", Global.isDemoEnabled());

        String indePage = this.getAdminIndex();
        if(Constants.ADMIN_INDEX_TOP_MENU.equals(indePage)){
            return Constants.ADMIN_INDEX_TOP_MENU; // index_topMenu
        }else{
            return "index";
        }
    }

    // 系统首页顶部菜单
    @PostMapping("/index/getMenu")
    @ResponseBody
    public AjaxResult getMenu(HttpServletRequest request)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        return AjaxResult.success(menus);
    }
    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap)
    {
        return "skin";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        mmap.put("version", Global.getVersion());
        return "main";
    }
}

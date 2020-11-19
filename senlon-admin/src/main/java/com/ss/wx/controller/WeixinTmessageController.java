package com.ss.wx.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.ss.common.utils.StringUtils;
import com.ss.framework.util.ShiroUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.HttpUtil;
import com.ss.utils.WeixinUtil;
import com.ss.utils.WxErrCodeUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.service.IJwWebJwidService;


import com.sun.jna.platform.win32.WinNT;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinTmessage;
import com.ss.wx.service.IWeixinTmessageService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 消息模板Controller
 * 
 * @author shishuai
 * @date 2020-07-16
 */
@Controller
@RequestMapping("/wx/tmessage")
public class WeixinTmessageController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(WeixinTmessageController.class);
    private static final String get_template_id_url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
    private static final String get_template_list_url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    private static final String delete_template_url = "https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN";
    private String prefix = "wx/tmessage";

    @Autowired
    private IWeixinTmessageService weixinTmessageService;
    @Autowired
    private IJwWebJwidService jwWebJwidService;

    @RequiresPermissions("wx:tmessage:view")
    @GetMapping()
    public String tmessage()
    {
        return prefix + "/tmessage";
    }

    /**
     * 查询消息模板列表
     */
    @RequiresPermissions("wx:tmessage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinTmessage weixinTmessage)
    {
        String username = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(username);
        weixinTmessage.setJwid(jwWebJwid.getJwid());
        startPage();
        List<WeixinTmessage> list = weixinTmessageService.selectWeixinTmessageList(weixinTmessage);
        return getDataTable(list);
    }

    /**
     * 导出消息模板列表
     */
    @RequiresPermissions("wx:tmessage:export")
    @Log(title = "消息模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinTmessage weixinTmessage)
    {
        List<WeixinTmessage> list = weixinTmessageService.selectWeixinTmessageList(weixinTmessage);
        ExcelUtil<WeixinTmessage> util = new ExcelUtil<WeixinTmessage>(WeixinTmessage.class);
        return util.exportExcel(list, "tmessage");
    }

    /**
     * 新增消息模板
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存消息模板
     */
    @RequiresPermissions("wx:tmessage:add")
    @Log(title = "消息模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinTmessage weixinTmessage)
    {
        return toAjax(weixinTmessageService.insertWeixinTmessage(weixinTmessage));
    }

    /**
     * 修改消息模板
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinTmessage weixinTmessage = weixinTmessageService.selectWeixinTmessageById(id);
        mmap.put("weixinTmessage", weixinTmessage);
        return prefix + "/edit";
    }

    /**
     * 修改保存消息模板
     */
    @RequiresPermissions("wx:tmessage:edit")
    @Log(title = "消息模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinTmessage weixinTmessage)
    {
        return toAjax(weixinTmessageService.updateWeixinTmessage(weixinTmessage));
    }

    /**
     * 删除消息模板
     */
    @RequiresPermissions("wx:tmessage:remove")
    @Log(title = "消息模板", businessType = BusinessType.DELETE)
    @PostMapping( "/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") String id)
    {
        AjaxResult result = new AjaxResult();
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        String accessToken = map.get("accessToken").toString();
        WeixinTmessage tmessage = weixinTmessageService.selectWeixinTmessageById(id);
        String templateId = tmessage.getTemplateId();
        String name = "{\"template_id\":\""+templateId+"\"}";
        String requestUrl = delete_template_url.replace("ACCESS_TOKEN",accessToken);
        com.alibaba.fastjson.JSONObject jsonObject = HttpUtil.httpRequest(requestUrl, "POST", name);
        if(jsonObject.getIntValue("errcode") == 0){
            weixinTmessageService.deleteWeixinTmessageById(id);
            result = AjaxResult.success("删除成功");
        }else{
            result = AjaxResult.warn("删除失败");
        }
        return result;
    }

    /**
     * 根据所选行业获取模板id
     * @param industryCode
     * @return
     */
    @GetMapping("/getTemplateId/{industryCode}")
    @ResponseBody
    public AjaxResult getTemplateId(@PathVariable("industryCode") int industryCode){
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        JSONObject jsonObj = null;
        AjaxResult result = new AjaxResult();
        try{
            Map<String,Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(),jwWebJwid.getWeixinAppsecret());
            String accessToken = map.get("accessToken").toString();
            String requestUrl = get_template_id_url.replace("ACCESS_TOKEN",accessToken);
            String template_id_short = null;
            if(industryCode<10){
                template_id_short = "TM0000"+String.valueOf(industryCode);
            }else{
                template_id_short = "TM000"+String.valueOf(industryCode);
            }
            String name = "{\"template_id_short\":\""+template_id_short+"\"}";
            jsonObj = WeixinUtil.httpRequest(requestUrl,"POST",name);
            if(jsonObj.containsKey("errcode")){
                result = AjaxResult.error("获取模板ID失败");
            }else{
                result = AjaxResult.success(jsonObj);
            }
        }catch(Exception e){
            String errcode = jsonObj.getString("errcode");
            String msg = WxErrCodeUtil.testErrCode(errcode);
            result = AjaxResult.error("获取模板ID失败:"+msg);
        }
        return result;
    }

    /**
     * 同步消息模板
     * @return
     */
    @RequestMapping(value = "syncTmessage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult syncTmessage(){
        AjaxResult result = new AjaxResult();
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwWebJwidService.queryOneByCreateBy(createBy);
        Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        String accessToken = map.get("accessToken").toString();
        try {
            String requestUrl = get_template_list_url.replace("ACCESS_TOKEN", accessToken);
            com.alibaba.fastjson.JSONObject jsonObj = HttpUtil.httpRequest(requestUrl, "GET", null);
            if (jsonObj.containsKey("template_list")) {
                com.alibaba.fastjson.JSONArray jsonArray = jsonObj.getJSONArray("template_list");
                for (int i = 0; i < jsonArray.size(); i++) {
                    com.alibaba.fastjson.JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String templateId = jsonObject.getString("template_id");
                    WeixinTmessage tmessage = weixinTmessageService.selectTmessageByTemplateId(templateId);
                    if (tmessage == null) {
                        tmessage = new WeixinTmessage();
                    }
                    tmessage.setTemplateId(jsonObject.getString("template_id"));
                    tmessage.setTitle(jsonObject.getString("title"));
                    tmessage.setIndustry(jsonObject.getString("primary_industry"));
                    tmessage.setSubIndustry(jsonObject.getString("deputy_industry"));
                    tmessage.setContent(jsonObject.getString("content"));
                    tmessage.setExample(jsonObject.getString("example"));
                    tmessage.setCreateDate(new Date());
                    tmessage.setJwid(jwWebJwid.getJwid());
                    if (tmessage != null && StringUtils.isNotEmpty(tmessage.getId())) {
                        weixinTmessageService.updateWeixinTmessage(tmessage);
                    } else {
                        tmessage.setId(UUID.randomUUID().toString().replace("-",""));
                        weixinTmessageService.insertWeixinTmessage(tmessage);
                    }
                    result = AjaxResult.success("同步消息模板成功");
                }
            }else{
                result = AjaxResult.success("同步消息模板失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            result = AjaxResult.error("同步消息模板失败");
        }
        return result;
    }

    /**
     * 根据模板id查询模板信息
     * @param templateId
     * @return
     */
    @GetMapping("/getTemplate/{templateId}")
    @ResponseBody
    public AjaxResult getTemplate(@PathVariable("templateId") String templateId){
        WeixinTmessage tmessage = weixinTmessageService.selectTmessageByTemplateId(templateId);
        return AjaxResult.success(tmessage);
    }

}

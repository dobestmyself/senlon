package com.ss.wx.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ss.framework.util.ShiroUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WeixinHttpUtil;
import com.ss.utils.WeixinUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.service.IJwWebJwidService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.json.AbstractJackson2Decoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinTag;
import com.ss.wx.service.IWeixinTagService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * 粉丝标签Controller
 * 
 * @author shishuai
 * @date 2020-07-13
 */
@Controller
@RequestMapping("/wx/tag")
public class WeixinTagController extends BaseController
{

    public final static Logger log = LoggerFactory.getLogger(WeixinTagController.class);
    //创建标签
    public final static String create_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN";
    //编辑标签
    public final static String update_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN";
    //删除标签
    public final static String delete_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN";
    //获取公众号已创建的标签
    public final static String get_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN";
    //批量为用户打标签
    public final static String batchtagging_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN";
    //获取用户标签列表
    public final static String getidlist_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN";
    //批量取消用户标签
    public final static String batchuntagging_tag_url = "https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN";


    private String prefix = "wx/tag";

    @Autowired
    private IWeixinTagService weixinTagService;
    @Autowired
    private IJwWebJwidService jwidService;

    @RequiresPermissions("wx:tag:view")
    @GetMapping()
    public String tag()
    {
        return prefix + "/tag";
    }

    /**
     * 查询粉丝标签列表
     */
    @RequiresPermissions("wx:tag:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinTag weixinTag)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        weixinTag.setJwid(jwWebJwid.getJwid());
        startPage();
        List<WeixinTag> list = weixinTagService.selectWeixinTagList(weixinTag);
        return getDataTable(list);
    }

    /**
     * 导出粉丝标签列表
     */
    @RequiresPermissions("wx:tag:export")
    @Log(title = "粉丝标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinTag weixinTag)
    {
        List<WeixinTag> list = weixinTagService.selectWeixinTagList(weixinTag);
        ExcelUtil<WeixinTag> util = new ExcelUtil<WeixinTag>(WeixinTag.class);
        return util.exportExcel(list, "tag");
    }

    /**
     * 新增粉丝标签
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存粉丝标签
     */
    @RequiresPermissions("wx:tag:add")
    @Log(title = "粉丝标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinTag weixinTag, HttpServletRequest request)
    {
        AjaxResult result = null;
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        JSONObject jsonObj = null;
        try{
            //获取accessToken
        //    String accessToken = WeixinHttpUtil.getRedisWeixinToken(jwid);
            Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
            String accessToken = map.get("accessToken").toString();
            //调用创建标签接口
            String requestUrl = create_tag_url.replace("ACCESS_TOKEN",accessToken);
            String name = "{\"tag\":{\"name\":\""+weixinTag.getName()+"\"}}";
            jsonObj = WeixinUtil.httpRequest(requestUrl,"POST",name);
            if(jsonObj.containsKey("tag")){
                JSONObject obj = jsonObj.getJSONObject("tag");
                //成功则将创建的标签添加到标签表
                weixinTag.setTagid(obj.getString("id"));
                weixinTag.setName(obj.getString("name"));
                weixinTag.setJwid(jwWebJwid.getJwid());
                weixinTag.setCreateTime(new Date());
                weixinTag.setAddtime(new Date());
                weixinTag.setCreateBy(createBy);
                weixinTagService.insertWeixinTag(weixinTag);
                result = AjaxResult.success("标签创建成功!");
            }else{
                if(jsonObj.getString("errcode").equals("45157")){
                    result = AjaxResult.warn("标签已存在，请勿重复创建!");
                }else{
                    String errcode = jsonObj.getString("errcode");
                    result = AjaxResult.error("标签创建失败!错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            String errcode = jsonObj.getString("errcode");
            result = AjaxResult.error("标签创建失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
        }


        return result;
    }

    /**
     * 修改粉丝标签
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinTag weixinTag = weixinTagService.selectWeixinTagById(id);
        mmap.put("weixinTag", weixinTag);
        return prefix + "/edit";
    }

    /**
     * 修改保存粉丝标签
     */
    @RequiresPermissions("wx:tag:edit")
    @Log(title = "粉丝标签", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinTag weixinTag)
    {
        AjaxResult result = null;
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        JSONObject jsonObj = null;
        try{
            //获取accesstoken
            //String accessToken = WeixinHttpUtil.getRedisWeixinToken(jwWebJwid.getJwid());
            Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
            String accessToken = map.get("accessToken").toString();
            //调用编辑标签接口
            String requestUrl = update_tag_url.replace("ACCESS_TOKEN",accessToken);
            String name = "{\"tag\":{\"id\":\""+weixinTag.getTagid()+"\",\"name\":\""+weixinTag.getName()+"\"}}";
            jsonObj = WeixinUtil.httpRequest(requestUrl, "POST", name);
            if(jsonObj.getString("errmsg").equals("ok")){
                String updateBy = ShiroUtils.getSysUser().getUserName();
                weixinTag.setUpdateBy(updateBy);
                weixinTagService.updateWeixinTag(weixinTag);
                result = AjaxResult.success("标签修改成功");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            String errcode = jsonObj.getString("errcode");
            result = AjaxResult.error("标签编辑失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
        }

        return result;
    }

    /**
     * 删除粉丝标签
     */
    @RequiresPermissions("wx:tag:remove")
    @Log(title = "粉丝标签", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinTagService.deleteWeixinTagByIds(ids));
    }

    @RequiresPermissions("wx:tag:remove")
    @PostMapping("doDelete")
    @ResponseBody
    public AjaxResult doDelete(@RequestParam(required = true,value = "id") String id){
        AjaxResult result = null;
        JSONObject jsonObj = null;
        try{
            WeixinTag weixinTag = weixinTagService.selectWeixinTagById(id);
            String createBy = ShiroUtils.getSysUser().getUserName();
            JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
            //获取accessToken
            Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
            String accessToken = map.get("accessToken").toString();
            //String accessToken = WeixinHttpUtil.getRedisWeixinToken(jwWebJwid.getJwid());
            //调用删除标签接口
            String requestUrl = delete_tag_url.replace("ACCESS_TOKEN",accessToken);
            String name = "{\"tag\":{\"id\":\""+weixinTag.getTagid()+"\"}}";
            jsonObj = WeixinUtil.httpRequest(requestUrl,"POST",name);
            if(jsonObj.getString("errcode").equals("ok")){
                result = AjaxResult.success("标签删除成功");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            String errcode = jsonObj.getString("errcode");
            result = AjaxResult.error("标签删除失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
        }
        return result;
    }

    /**
     * @功能：同步标签
     * @return
     */
    @RequestMapping(value="syncTags",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult syncTags(){
        AjaxResult result = null;
        JSONObject jsonObj = null;
        try{
            String createBy = ShiroUtils.getSysUser().getUserName();
            JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
            //获取accessToken
            //TODO
            Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
            String accessToken = map.get("accessToken").toString();
        //    String accessToken = WeixinHttpUtil.getRedisWeixinToken(jwWebJwid.getJwid());
            //调用获取公众号标签接口
            String requestUrl = get_tag_url.replace("ACCESS_TOKEN",accessToken);
            jsonObj = WeixinUtil.httpRequest(requestUrl,"GET","");
            JSONArray Obj = (JSONArray)jsonObj.get("tags");
            //将获取公众号已创建的标签插入标签表
            if(Obj.size()>0){
                //根据jwid清空该公众号创建的标签
                weixinTagService.deleteTagsByJwid(jwWebJwid.getJwid());
                for(int i=0;i<Obj.size();i++){
                    JSONObject tag = (JSONObject)Obj.get(i);
                    WeixinTag weixinTag = new WeixinTag();
                    weixinTag.setJwid(jwWebJwid.getJwid());
                    weixinTag.setTagid(tag.getString("id"));
                    weixinTag.setName(tag.getString("name"));
                    weixinTag.setAddtime(new Date());
                    weixinTag.setSynctime(new Date());
                    weixinTagService.insertWeixinTag(weixinTag);
                    result = AjaxResult.success("标签同步成功");
                }
            }
        }catch (Exception e){
            log.error(e.getMessage());
            String errcode = jsonObj.getString("errcode");
            result = AjaxResult.error("标签同步失败！错误码：<a target='_blank' href='https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433747234'>"+errcode+"</a>");
        }
        return result;
    }

    /**
     * 根据标签id批量查询标签列表
     * @param tagid
     * @return
     */
    @GetMapping("/getTagByTagid/{tagid}")
    @ResponseBody
    public AjaxResult selectTagByTagids(@PathVariable("tagid") String tagid){
        List<WeixinTag> weixinTags = weixinTagService.selectTagByTagId(tagid);
        return AjaxResult.success(weixinTags);
    }

}

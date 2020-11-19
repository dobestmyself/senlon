package com.ss.wx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jeecg.p3.redis.JedisPoolUtil;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;
import com.ss.common.config.Global;
import com.ss.common.config.ServerConfig;
import com.ss.common.constant.Constants;
import com.ss.common.utils.StringUtils;
import com.ss.common.utils.file.FileUploadUtils;
import com.ss.framework.util.ShiroUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WxErrCodeUtil;
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
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统公众号Controller
 * 
 * @author shishuai
 * @date 2020-07-05
 */
@Controller
@RequestMapping("/wx/jwid")
public class JwWebJwidController extends BaseController
{
    private static final Logger log  = LoggerFactory.getLogger(JwWebJwidController.class);
    private String prefix = "wx/jwid";

    @Autowired
    private IJwWebJwidService jwWebJwidService;
    @Autowired
    private ServerConfig serverConfig;

    @RequiresPermissions("wx:jwid:view")
    @GetMapping()
    public String jwid()
    {
        return prefix + "/jwid";
    }

    /**
     * 查询系统公众号列表
     */
    @RequiresPermissions("wx:jwid:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(JwWebJwid jwWebJwid)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        jwWebJwid.setCreateBy(createBy);
        startPage();
        List<JwWebJwid> list = jwWebJwidService.selectJwWebJwidList(jwWebJwid);
        return getDataTable(list);
    }

    /**
     * 导出系统公众号列表
     */
    @RequiresPermissions("wx:jwid:export")
    @Log(title = "系统公众号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JwWebJwid jwWebJwid)
    {
        List<JwWebJwid> list = jwWebJwidService.selectJwWebJwidList(jwWebJwid);
        ExcelUtil<JwWebJwid> util = new ExcelUtil<JwWebJwid>(JwWebJwid.class);
        return util.exportExcel(list, "jwid");
    }

    /**
     * 新增系统公众号
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存系统公众号
     */
    @RequiresPermissions("wx:jwid:add")
    @Log(title = "系统公众号", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated JwWebJwid jwWebJwid, HttpServletRequest request)
    {
        AjaxResult result = new AjaxResult();
        String createBy = ShiroUtils.getSysUser().getUserName();
        try{
            //先验证用户是否已经创建
            if(!"admin".equals(createBy)){
                JwWebJwid webJwid = jwWebJwidService.queryOneByCreateBy(createBy);
                if(webJwid != null){
                    return AjaxResult.warn("每个用户只能创建一个公众号");
                }
            }
            Map<String,Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(),jwWebJwid.getWeixinAppsecret());
            if(map.get("accessToken") != null){
                jwWebJwid.setAccessToken(map.get("accessToken").toString());
                jwWebJwid.setTokenGettime((Date) map.get("accessTokenTime"));
                jwWebJwid.setApiticket(map.get("apiTicket").toString());
                jwWebJwid.setApiticketGettime((Date) map.get("apiTicketTime"));
                jwWebJwid.setJsapiticket(map.get("jsApiTicket").toString());
                jwWebJwid.setJsapiticketGettime((Date) map.get("jsApiTicketTime"));
                result = AjaxResult.success("公众号授权成功");
                WeixinAccount po = new WeixinAccount();
                po.setAccountappid(jwWebJwid.getWeixinAppid());
                po.setAccountappsecret(jwWebJwid.getWeixinAppsecret());
                po.setAccountaccesstoken(jwWebJwid.getAccessToken());
                po.setAddtoekntime(jwWebJwid.getTokenGettime());
                po.setAccountnumber(jwWebJwid.getWeixinNumber());
                po.setApiticket(jwWebJwid.getApiticket());
                po.setApiticketttime(jwWebJwid.getApiticketGettime());
                po.setAccounttype(jwWebJwid.getAccountType());
                po.setWeixinAccountid(jwWebJwid.getJwid());//原始ID
                po.setJsapiticket(jwWebJwid.getJsapiticket());
                po.setJsapitickettime(jwWebJwid.getJsapiticketGettime());
                try{
                    JedisPoolUtil.putWxAccount(po);
                }catch (Exception e){
                    log.error(e.toString());
                }
            }else{
                if(map.get("errcode").equals("40164")){
                    result = AjaxResult.warn(WxErrCodeUtil.ERROR_40164+"&nbsp;&nbsp;<a target='_blank' href='http://www.h5huodong.com/h5/detail.html?id=ff80808165e062030165e6451e6d1d58'>操作指南</a>");
                }else{
                    result = AjaxResult.warn("AppId或AppSecret配置不正确，请注意检查。");
                }
                return result;
            }
            JwWebJwid jwid = jwWebJwidService.queryByJwid(jwWebJwid.getJwid());
            if(jwid != null){
                return AjaxResult.warn("该微信公众号已存在!");
            }
            jwWebJwid.setCreateBy(createBy);
            jwWebJwidService.insertJwWebJwid(jwWebJwid);
            request.getSession().setAttribute(Constants.SYSTEM_JWID,jwWebJwid.getJwid());
            request.getSession().setAttribute(Constants.SYSTEM_JWIDNAME,jwWebJwid.getName());
        }catch(Exception e){
            e.printStackTrace();
            return AjaxResult.error("保存失败!");
        }

        return result;
    }



    /**
     * 修改系统公众号
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        JwWebJwid jwWebJwid = jwWebJwidService.selectJwWebJwidById(id);
        mmap.put("jwWebJwid", jwWebJwid);
        return prefix + "/edit";
    }

    /**
     * 修改保存系统公众号
     */
    @RequiresPermissions("wx:jwid:edit")
    @Log(title = "系统公众号", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated JwWebJwid jwWebJwid)
    {
        return toAjax(jwWebJwidService.updateJwWebJwid(jwWebJwid));
    }

    /**
     * 删除系统公众号
     */
    @RequiresPermissions("wx:jwid:remove")
    @Log(title = "系统公众号", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jwWebJwidService.deleteJwWebJwidByIds(ids));
    }

    /**
     * 上传二维码图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadQrcodeImg(MultipartFile file){
        try{
            String realPath = Global.getUploadPath();
            //上传并返回新文件名称
            //TODO
            String fileName = FileUploadUtils.upload(Global.getUPath(),file);
            String url = serverConfig.getUrl()+fileName;
            Map<String,Object> data = new HashMap<>();
            int width = FileUploadUtils.getImgWidth(file);
            int height = FileUploadUtils.getImgHeight(file);
            data.put("path",fileName);
            data.put("url",url);
            data.put("width",width);
            data.put("suffix",FileUploadUtils.getExtension(file));
            data.put("height",height);
            data.put("size",file.getSize());
            data.put("name",file.getOriginalFilename());
            return AjaxResult.success(data);
        }catch(Exception e){
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequestMapping("/preview/{id}")
    public void pdfPreview(@PathVariable(value = "id") String id, HttpServletRequest req, HttpServletResponse resp){
        JwWebJwid jwWebJwid = jwWebJwidService.selectJwWebJwidById(id);
        String savePath = jwWebJwid.getQrcodeimg();
        String path = Global.getProfile()+"/"+savePath.substring(savePath.lastIndexOf("/profile/")+9);
        File file = new File(path);
        if(file.exists()){
            byte[] data = null;
            FileInputStream input = null;
            try{
                input = new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                resp.getOutputStream().write(data);
            }catch(Exception e){
                System.out.println("图片处理异常:"+e);
            }finally {
                try{
                    if(input!=null){
                        input.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 重置AccessToken
     * @param id
     * @return
     */
    @RequestMapping(value = "reset",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult resetAccessToken(@RequestParam(required = true,value = "id") String id){
        AjaxResult result = new AjaxResult();
        try{
            String resetAccessToken = jwWebJwidService.resetAccessToken(id);
            if(StringUtils.isNotEmpty(resetAccessToken)){
                if("success".equals(resetAccessToken)){
                    result = AjaxResult.success("重置token成功");
                }else{
                    result = AjaxResult.error("重置token失败："+resetAccessToken);
                }
            }else{
                result = AjaxResult.error("重置token失败，系统异常");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            result = AjaxResult.error("重置token失败：系统异常");
        }
        return result;
    }

}

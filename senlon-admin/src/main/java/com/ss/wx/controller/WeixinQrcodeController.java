package com.ss.wx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ss.common.config.Global;
import com.ss.common.config.ServerConfig;
import com.ss.common.utils.StringUtils;
import com.ss.common.utils.file.FileUploadUtils;
import com.ss.framework.util.ShiroUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WeixinUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinNewstemplate;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.wx.service.IWeixinNewstemplateService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinQrcode;
import com.ss.wx.service.IWeixinQrcodeService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 二维码Controller
 * 
 * @author shishuai
 * @date 2020-08-06
 */
@Controller
@RequestMapping("/wx/qrcode")
public class WeixinQrcodeController extends BaseController
{
    private final static Logger log = LoggerFactory.getLogger(WeixinQrcodeController.class);
    private String prefix = "wx/qrcode";
    //创建二维码ticket请求
    public static String qrcode_ticket_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
    //通过ticket换取二维码
    public static String get_qrcode_url =  "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

    @Autowired
    private IWeixinQrcodeService weixinQrcodeService;
    @Autowired
    private IWeixinNewstemplateService newstemplateService;
    @Autowired
    private IJwWebJwidService jwidService;
    @Autowired
    private ServerConfig serverConfig;

    @RequiresPermissions("wx:qrcode:view")
    @GetMapping()
    public String qrcode()
    {
        return prefix + "/qrcode";
    }

    /**
     * 查询二维码列表
     */
    @RequiresPermissions("wx:qrcode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinQrcode weixinQrcode)
    {
        startPage();
        List<WeixinQrcode> list = weixinQrcodeService.selectWeixinQrcodeList(weixinQrcode);
        return getDataTable(list);
    }

    /**
     * 导出二维码列表
     */
    @RequiresPermissions("wx:qrcode:export")
    @Log(title = "二维码", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinQrcode weixinQrcode)
    {
        List<WeixinQrcode> list = weixinQrcodeService.selectWeixinQrcodeList(weixinQrcode);
        ExcelUtil<WeixinQrcode> util = new ExcelUtil<WeixinQrcode>(WeixinQrcode.class);
        return util.exportExcel(list, "qrcode");
    }

    /**
     * 新增二维码
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存二维码
     */
    @RequiresPermissions("wx:qrcode:add")
    @Log(title = "二维码", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinQrcode weixinQrcode)
    {
        AjaxResult result = new AjaxResult();
        String username = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(username);
        int scenceId = weixinQrcodeService.getScence(jwWebJwid.getJwid(),weixinQrcode.getActionName());
        weixinQrcode.setSceneId(scenceId);
        weixinQrcode.setJwid(jwWebJwid.getJwid());
        Date date = new Date();
        if(weixinQrcode.getExpireSeconds() != null){
            date.setTime(System.currentTimeMillis()+(weixinQrcode.getExpireSeconds()*1000));
            weixinQrcode.setExpireDate(date);
        }
        weixinQrcode.setCreateTime(new Date());
        try{
            weixinQrcodeService.insertWeixinQrcode(weixinQrcode);
            result = AjaxResult.success("保存成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result = AjaxResult.error("保存失败!");
        }
        return result;
    }

    /**
     * 修改二维码
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinQrcode weixinQrcode = weixinQrcodeService.selectWeixinQrcodeById(id);
        mmap.put("weixinQrcode", weixinQrcode);
        return prefix + "/edit";
    }

    /**
     * 修改保存二维码
     */
    @RequiresPermissions("wx:qrcode:edit")
    @Log(title = "二维码", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinQrcode weixinQrcode)
    {
        AjaxResult result = new AjaxResult();
        Date date = new Date();
        date.setTime(System.currentTimeMillis()+(weixinQrcode.getExpireSeconds()*1000));
        weixinQrcode.setExpireDate(date);
        weixinQrcode.setUpdateTime(new Date());
        try{
            weixinQrcodeService.updateWeixinQrcode(weixinQrcode);
            result = AjaxResult.success("保存成功");
        }catch (Exception e){
            log.error(e.getMessage());
            result = AjaxResult.error("保存失败");
        }
        return result;
    }

    /**
     * 删除二维码
     */
    @RequiresPermissions("wx:qrcode:remove")
    @Log(title = "二维码", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinQrcodeService.deleteWeixinQrcodeByIds(ids));
    }

    @RequestMapping(value="/loadTemplate",method= {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult loadTemplate(){
        List<WeixinNewstemplate> newstemplates = newstemplateService.selectWeixinNewstemplateList(new WeixinNewstemplate());
        return AjaxResult.success(newstemplates);
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
            String fileName = FileUploadUtils.upload(Global.getUploadPath(),file);
            String url = serverConfig.getUrl()+fileName;
            Map<String,Object> data = new HashMap<>();
            int width = FileUploadUtils.getImgWidth(file);
            int height = FileUploadUtils.getImgHeight(file);
            data.put("path","/"+fileName.substring(fileName.lastIndexOf("/profile")+1));
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
    public void preview(@PathVariable("id") String id, HttpServletResponse resp){
        WeixinQrcode weixinQrcode = weixinQrcodeService.selectWeixinQrcodeById(id);
        String savePath = weixinQrcode.getNewsImg();
        String path = Global.getProfile()+"/"+savePath;
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
            }finally{
                try{
                    if(input!=null){
                        input.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 生成二维码
     * @param weixinQrcode
     * @return
     */
    @RequestMapping(value="/generateQrcode",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult generateQrcode(WeixinQrcode weixinQrcode){
        AjaxResult result = new AjaxResult();
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        //获取token方法
        Map<String, Object> token = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        String accessToken = token.get("accessToken").toString();
        if(StringUtils.isEmpty(accessToken)){
            result = AjaxResult.error("未获取到公众号accessToken");
            return result;
        }
        String ticketurl = qrcode_ticket_url.replace("ACCESS_TOKEN",accessToken);
        Map<String,Object> map = new HashMap<>();
        Map<String,String> sceneMap = new HashMap<>();
        sceneMap.put("scene_id",weixinQrcode.getSceneId().toString());
        map.put("scene",sceneMap);
        JSONObject jsonQrcode = new JSONObject();
        jsonQrcode.put("action_name",weixinQrcode.getActionName());
        jsonQrcode.put("action_info",JSONObject.fromObject(map));
        //有效期
        if(weixinQrcode.getActionName().equals("QR_SCENE")){
            jsonQrcode.put("expire_seconds",String.valueOf(weixinQrcode.getExpireSeconds()));
        }
        JSONObject ticketJson = WeixinUtil.httpRequest(ticketurl,"POST",jsonQrcode.toString());
        //判断是否执行成功
        //判断返回值中是否包含errcode键
        if(!ticketJson.containsKey("errcode")){
            //若不包含errcode，则取到ticket
            String ticket = ticketJson.getString("ticket");
            weixinQrcode.setTicket(ticket);
            //通过ticket获取图片
            String qrcodeimgurl = get_qrcode_url.replace("TICKET",ticket);
            weixinQrcode.setQrcodeUrl(qrcodeimgurl);
            //临时二维码
            if(weixinQrcode.getActionName().equals("QR_SCENE")){
                weixinQrcode.setExpireSeconds(weixinQrcode.getExpireSeconds());
                //获取到期时间
                Date date = new Date();
                date.setTime(System.currentTimeMillis()+(weixinQrcode.getExpireSeconds()*1000));
                weixinQrcode.setExpireDate(date);
            }
            weixinQrcodeService.updateWeixinQrcode(weixinQrcode);
            result = AjaxResult.success("生成二维码成功");
        }else{
            result = AjaxResult.error("二维码生成失败");
        }
        return result;
    }
}

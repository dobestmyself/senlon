package com.ss.wx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ss.common.core.text.Convert;
import com.ss.common.utils.StringUtils;
import com.ss.framework.util.ShiroUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WeixinHttpUtil;
import com.ss.utils.WeixinUtil;
import com.ss.utils.WxErrCodeUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinTag;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.wx.service.IWeixinTagService;
import com.ss.wx.task.GzUserInfoTimer;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeewx.api.core.exception.WexinReqException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.wx.domain.WeixinGzuser;
import com.ss.wx.service.IWeixinGzuserService;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

/**
 * 粉丝Controller
 * 
 * @author shishuai
 * @date 2020-07-15
 */
@Controller
@RequestMapping("/wx/gzuser")
public class WeixinGzuserController extends BaseController
{
    private static final String user_list_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    private String prefix = "wx/gzuser";

    @Autowired
    private IWeixinGzuserService weixinGzuserService;
    @Autowired
    private IJwWebJwidService jwidService;
    @Autowired
    private GzUserInfoTimer gzUserInfoTimer;
    @Autowired
    private IWeixinTagService tagService;

    @RequiresPermissions("wx:gzuser:view")
    @GetMapping()
    public String gzuser()
    {
        return prefix + "/gzuser";
    }

    @GetMapping("/list")
    public String task(){
        return prefix + "/task";
    }


    /**
     * 查询粉丝列表
     */
    @RequiresPermissions("wx:gzuser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinGzuser weixinGzuser)
    {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        weixinGzuser.setJwid(jwWebJwid.getJwid());
        startPage();
        List<WeixinGzuser> list = weixinGzuserService.selectWeixinGzuserList(weixinGzuser);
        return getDataTable(list);
    }

    /**
     * 导出粉丝列表
     */
    @RequiresPermissions("wx:gzuser:export")
    @Log(title = "粉丝", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinGzuser weixinGzuser)
    {
        List<WeixinGzuser> list = weixinGzuserService.selectWeixinGzuserList(weixinGzuser);
        ExcelUtil<WeixinGzuser> util = new ExcelUtil<WeixinGzuser>(WeixinGzuser.class);
        return util.exportExcel(list, "gzuser");
    }

    /**
     * 新增粉丝
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存粉丝
     */
    @RequiresPermissions("wx:gzuser:add")
    @Log(title = "粉丝", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinGzuser weixinGzuser)
    {

        return toAjax(weixinGzuserService.insertWeixinGzuser(weixinGzuser));
    }

    /**
     * 粉丝分组
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinGzuser weixinGzuser = weixinGzuserService.selectWeixinGzuserById(id);
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        WeixinTag tag = new WeixinTag();
        tag.setJwid(jwWebJwid.getJwid());
        List<WeixinTag> tags = tagService.selectWeixinTagList(tag);
        mmap.put("tags",tags);
        mmap.put("weixinGzuser", weixinGzuser);
        return prefix + "/group";
    }

    /**
     * 粉丝分组保存
     */
    @RequiresPermissions("wx:gzuser:edit")
    @Log(title = "粉丝", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinGzuser weixinGzuser)
    {
        return toAjax(weixinGzuserService.updateWeixinGzuser(weixinGzuser));
    }

    /**
     * 删除粉丝
     */
    @RequiresPermissions("wx:gzuser:remove")
    @Log(title = "粉丝", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinGzuserService.deleteWeixinGzuserByIds(ids));
    }

    @RequestMapping(value="syncFans",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult syncFans(HttpServletRequest request){
        AjaxResult result = new AjaxResult();
        try{
            //获取jwid
            String createBy = ShiroUtils.getSysUser().getUserName();
            JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
            String message = "";
            //获取token
            int total = 0;
         //   String accessToken = WeixinHttpUtil.getRedisWeixinToken(jwWebJwid.getJwid());
            Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
            String accessToken = map.get("accessToken").toString();
            //调用一次测试微信接口以验证服务接口是否正常，并返回粉丝总数
            if(StringUtils.isNotEmpty(accessToken)){
                String requestUrl = user_list_url.replace("NEXT_OPENID","").replace("ACCESS_TOKEN",accessToken);
                JSONObject jsonObj = WeixinUtil.httpRequest(requestUrl, "GET", "");
                if(jsonObj==null){
                    return AjaxResult.error("微信服务器访问异常，请稍后重试");
                }
                if(!jsonObj.containsKey("errmsg")){
                    total = jsonObj.getInt("total");
                }else{
                    String errcode = jsonObj.getString("errcode");
                    String msg = WxErrCodeUtil.testErrCode(errcode);
                    return AjaxResult.error("同步粉丝列表失败"+msg);
                }
            }
            message = "同步粉丝任务已启动，请稍后刷新，关注用户总数："+total;
            //开启线程，同步粉丝数据
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    weixinGzuserService.getFansListTask("0",jwWebJwid.getJwid(),jwWebJwid.getWeixinAppid(),jwWebJwid.getWeixinAppsecret());

                    JwWebJwid jwid = jwidService.queryByJwid(jwWebJwid.getJwid());
                    gzUserInfoTimer.batchInitAccountFensi(jwid);
                }
            });
            t.start();
            result = AjaxResult.success(message);

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    @GetMapping("/listGzuser/{tagId}")
    public String listGzuser(@PathVariable("tagId") String tagId,ModelMap mmap){
        mmap.put("tagId",tagId);
        return "wx/gzuser/task";
    }


    /**
     * 查询未在该标签下的所有粉丝列表
     * @param tagId
     * @return
     */
    @PostMapping("/listGzuser/{tagId}")
    @ResponseBody
    public TableDataInfo listGzuser(@PathVariable("tagId") String tagId){
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        startPage();
        List<WeixinGzuser> weixinGzusers = weixinGzuserService.selectGzuserByTagIdAndJwid(tagId, jwWebJwid.getJwid());
        return getDataTable(weixinGzusers);
    }

    @PostMapping("/updateTagValue")
    @ResponseBody
    public AjaxResult batchUpdateTagValue(String openids,String tagid){
        String[] oIds = Convert.toStrArray(openids);
        List<WeixinGzuser> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for(String oid : oIds){
            WeixinGzuser gzuser = new WeixinGzuser();
            gzuser.setOpenid(oid);
            List<WeixinGzuser> gzusers = weixinGzuserService.selectWeixinGzuserList(gzuser);
            String tagidList = gzusers.get(0).getTagidList();
            if(StringUtils.isEmpty(tagidList)){
                gzuser.setTagidList(tagid);
            }else{
                String tId = sb.append(gzusers.get(0).getTagidList()).append(",").append(tagid).toString();
                gzuser.setTagidList(tId);
            }

            list.add(gzuser);
        }

        return toAjax(weixinGzuserService.updateBatchTagidList(list));
    }

}

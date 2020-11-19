package com.ss.wx.controller;

import java.util.Date;
import java.util.List;

import com.ss.common.utils.StringUtils;
import com.ss.framework.util.ShiroUtils;

import com.ss.utils.WxErrCodeUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinGroupMessageSendDetail;
import com.ss.wx.domain.WeixinNewstemplate;
import com.ss.wx.model.*;
import com.ss.wx.service.*;
import com.ss.wx.vo.GroupSendMessageCheckVo;
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
import com.ss.wx.domain.WeixinGroupMessageSendLog;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;

/**
 * 群发消息日志Controller
 * 
 * @author shishuai
 * @date 2020-11-10
 */
@Controller
@RequestMapping("/wx/log")
public class WeixinGroupMessageSendLogController extends BaseController
{
    private final static Logger log = LoggerFactory.getLogger(WeixinGroupMessageSendLogController.class);

    //群发消息预览
    private static String message_preview_url="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
    private String prefix = "wx/log";

    @Autowired
    private IWeixinGroupMessageSendLogService weixinGroupMessageSendLogService;

    @Autowired
    private IWeixinGroupMessageSendDetailService messageSendDetailService;

    @Autowired
    private IJwWebJwidService jwidService;
    @Autowired
    private IWeixinNewstemplateService newstemplateService;
    @Autowired
    private SendGroupMessageService sendGroupMessageService;

    @RequiresPermissions("wx:log:view")
    @GetMapping()
    public String log()
    {
        return prefix + "/log";
    }

    /**
     * 查询群发消息日志列表
     */
    @RequiresPermissions("wx:log:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinGroupMessageSendLog weixinGroupMessageSendLog)
    {
        startPage();
        List<GroupSendMessageCheckVo> list = weixinGroupMessageSendLogService.selectWeixinGroupMessageSendLogList(weixinGroupMessageSendLog);
        return getDataTable(list);
    }

    /**
     * 导出群发消息日志列表
     */
    @RequiresPermissions("wx:log:export")
    @Log(title = "群发消息日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinGroupMessageSendLog weixinGroupMessageSendLog)
    {
        List<WeixinGroupMessageSendLog> list = weixinGroupMessageSendLogService.selectGroupMessageSendLogList(weixinGroupMessageSendLog);
        ExcelUtil<WeixinGroupMessageSendLog> util = new ExcelUtil<WeixinGroupMessageSendLog>(WeixinGroupMessageSendLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增群发消息日志
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") String id,ModelMap mmap)
    {
        mmap.put("id",id);
        return prefix + "/check";
    }

    /**
     * 新增保存群发消息日志
     */
    @RequiresPermissions("wx:log:add")
    @Log(title = "群发消息日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinGroupMessageSendLog weixinGroupMessageSendLog)
    {
        return toAjax(weixinGroupMessageSendLogService.insertWeixinGroupMessageSendLog(weixinGroupMessageSendLog));
    }

    /**
     * 修改群发消息日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WeixinGroupMessageSendLog weixinGroupMessageSendLog = weixinGroupMessageSendLogService.selectWeixinGroupMessageSendLogById(id);
        mmap.put("weixinGroupMessageSendLog", weixinGroupMessageSendLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存群发消息日志
     */
    @RequiresPermissions("wx:log:edit")
    @Log(title = "群发消息日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinGroupMessageSendLog weixinGroupMessageSendLog)
    {
        return toAjax(weixinGroupMessageSendLogService.updateWeixinGroupMessageSendLog(weixinGroupMessageSendLog));
    }

    /**
     * 删除群发消息日志
     */
    @RequiresPermissions("wx:log:remove")
    @Log(title = "群发消息日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(weixinGroupMessageSendLogService.deleteWeixinGroupMessageSendLogByIds(ids));
    }

    /**
     * 群发消息功能
     * @param weixinGroupMessageSendLog
     * @return
     */
    @RequestMapping(value="/sendGroupMessage",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxResult sendGroupMessage(WeixinGroupMessageSendLog weixinGroupMessageSendLog){
        try{
            String username = ShiroUtils.getSysUser().getUserName();
            JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(username);
            weixinGroupMessageSendLog.setSendJwidName(jwWebJwid.getName());
            weixinGroupMessageSendLog.setJwid(jwWebJwid.getJwid());
            //默认待审核状态：0：待审核；1:审核通过   2：审核未通过
            weixinGroupMessageSendLog.setAuditStatus("0");
            weixinGroupMessageSendLog.setCreateBy(username);
            weixinGroupMessageSendLogService.insertWeixinGroupMessageSendLog(weixinGroupMessageSendLog);
            return AjaxResult.success("已成功提交群发申请，请等待审核！");
        }catch (Exception e){
            return AjaxResult.error("群发申请失败！");
        }

    }
    @GetMapping("/check/{id}")
    public String checkGroupMessage(@PathVariable("id") String id,ModelMap mmap){
         mmap.put("id",id);
        return prefix + "/check";
    }

    @PostMapping("/check")
    @ResponseBody
    public AjaxResult checkGroupMessage(WeixinGroupMessageSendLog weixinGroupMessageSendLog){
        final AjaxResult result = new AjaxResult();
        try{

            String id = weixinGroupMessageSendLog.getId();
            String auditStatus = weixinGroupMessageSendLog.getAuditStatus();
            final WeixinGroupMessageSendLog messageSendLog = weixinGroupMessageSendLogService.selectWeixinGroupMessageSendLogById(id);
            messageSendLog.setAuditName(ShiroUtils.getSysUser().getUserName());
            messageSendLog.setAuditDate(new Date());
            messageSendLog.setAuditRemark("");
            messageSendLog.setSendIgnoreReprint(weixinGroupMessageSendLog.getSendIgnoreReprint());
            messageSendLog.setAuditStatus(auditStatus);
            weixinGroupMessageSendLogService.updateWeixinGroupMessageSendLog(messageSendLog);
            if(auditStatus == "2"){
                result.put("msg","审核未通过，群发消息被取消");
            }else{
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try{
                            JSONObject jsonObject = doSendGroupMessage(messageSendLog);
                            if(jsonObject.getString("errmsg").equals("0")){
                                result.put("msg","发送成功");
                            }else{
                                String errcode = jsonObject.getString("errcode");
                                String msg = WxErrCodeUtil.testErrCode(errcode);
                                result.put("msg","发送失败"+msg);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.toString());
        }
        return result;
    }

    /**
     * 群发消息
     * @param groupMessage
     * @return
     */
    private JSONObject doSendGroupMessage(WeixinGroupMessageSendLog groupMessage){
        //群发参数数据
        String username = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(username);
        String jwid = jwWebJwid.getJwid();
        String msgType = groupMessage.getMsgType();
        String groupId = groupMessage.getGroupId();
        String isToAll = groupMessage.getIsToAll();
        String tagId = groupMessage.getTagId();
        String templateId = groupMessage.getTemplateId();
        JSONObject jsonObj = null;
        if(StringUtils.isNotEmpty(groupMessage.getJwid())){
            //设置图文消息的接收者
            Filter filter = new Filter();
            if("false".equals(isToAll)){
                filter.setIs_to_all(false);
            }else if("tag".equals(isToAll)){
                filter.setIs_to_all(false);
                filter.setTag_id(tagId);
            }else{
                filter.setIs_to_all(true);
            }
            //获取媒体id
            String newsparams = null;
            WeixinNewstemplate weixinNewstemplate = newstemplateService.selectNewstemplateByTemplateId(templateId);
            if("text".equals(msgType)){
                newsparams = groupMessage.getParam();
            }else{
                newsparams = weixinNewstemplate.getMediaId();
            }

            //群发图文增加原创校验
            if(StringUtils.isNotEmpty(newsparams)){
                if("mpnews".equals(msgType)){
                    SendGroupMessageNews messageNews = new SendGroupMessageNews();
                    messageNews.setFilter(filter);
                    Media media = new Media();
                    media.setMedia_id(newsparams);
                    messageNews.setMpnews(media);
                    messageNews.setMsgtype("mpnews");
                    messageNews.setSend_ignore_reprint(Integer.parseInt(groupMessage.getSendIgnoreReprint()));
                    jsonObj = JSONObject.fromObject(messageNews);
                }else if("text".equals(msgType)){
                    Text text = new Text();
                    text.setContent(newsparams);
                    SendGroupMessageText messageText = new SendGroupMessageText();
                    messageText.setMsgtype("text");
                    messageText.setText(text);
                    messageText.setFilter(filter);
                    messageText.setSend_ignore_reprint(Integer.parseInt(groupMessage.getSendIgnoreReprint()));
                    jsonObj = JSONObject.fromObject(messageText);
                }else if("image".equals(msgType)){

                }else if("voice".equals(msgType)){

                }else if("video".equals(msgType)){

                }
            }
            try{
                if("false".equals(isToAll)){
                    Object object = jsonObj.get("filter");
                    JSONObject obj = JSONObject.fromObject(object);
                    obj.remove("tag_id");
                    jsonObj.put("filter", obj);
                }else{
                    Object object = jsonObj.get("filter");
                    JSONObject obj = JSONObject.fromObject(object);
                    obj.remove("group_id");
                    jsonObj.put("filter", obj);
                }
                log.info("-----群发消息调用微信接口参数：------------------"+jsonObj.toString());

                jsonObj = sendGroupMessageService.sendGroupMessage(jsonObj,jwWebJwid);
                log.info("-----群发消息微信返回结果：------------------"+jsonObj);
            }catch (Exception e){
                log.info("-----公众号【ID="+jwid+"】素材【ID="+templateId+"】群发API调用异常-----"+e.toString());
            }finally {
                //记录群发返回日志
                WeixinGroupMessageSendDetail sendDetail=new WeixinGroupMessageSendDetail();
                sendDetail.setSendLogId(groupMessage.getId());
                sendDetail.setSendJwid(jwid);
                sendDetail.setSendJwidName(groupMessage.getSendJwidName());
                sendDetail.setMsgType(msgType);
                if(!"text".equals(msgType)){
                    sendDetail.setTemplateId(templateId);
                    sendDetail.setTemplateName(weixinNewstemplate.getTemplateName());
                }
                sendDetail.setMediaId(newsparams);
                sendDetail.setSendTime(new Date());
                if(jsonObj!=null){
                    if(jsonObj.containsKey("errcode")&&jsonObj.getInt("errcode")==0){
                        sendDetail.setSendStatus("1");//发送成功
                        sendDetail.setReturnErrcode(jsonObj.getString("errcode"));
                        sendDetail.setReturnErrmsg(jsonObj.getString("errmsg"));
                        sendDetail.setReturnMsgId(jsonObj.getString("msg_id"));
                        sendDetail.setReturnMsgDataId(jsonObj.containsKey("msg_data_id")?jsonObj.getString("msg_data_id"):null);
                        //修改发送状态
                    }else{
                        sendDetail.setSendStatus("0");//发送失败
                        sendDetail.setReturnErrcode(jsonObj.getString("errcode"));
                        sendDetail.setReturnErrmsg(jsonObj.getString("errmsg"));
                    }
                }else{
                    sendDetail.setSendStatus("0");//发送失败
                    sendDetail.setReturnErrmsg("微信无响应");
                }
                messageSendDetailService.insertWeixinGroupMessageSendDetail(sendDetail);
            }
        }
        return jsonObj;
    }
}

package com.ss.wx.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.ss.framework.util.ShiroUtils;
import com.ss.wx.domain.*;
import com.ss.wx.service.*;
import com.ss.wx.vo.TmessageTaskSendVo;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeewx.api.wxstore.shelf.model.ShelfRInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ss.common.annotation.Log;
import com.ss.common.enums.BusinessType;
import com.ss.common.core.controller.BaseController;
import com.ss.common.core.domain.AjaxResult;
import com.ss.common.utils.poi.ExcelUtil;
import com.ss.common.core.page.TableDataInfo;
import sun.awt.SunHints;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 模板消息发送Controller
 *
 * @author shishuai
 * @date 2020-07-17
 */
@Controller
@RequestMapping("/wx/tmessagetask")
public class WeixinTmessagetaskController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(WeixinTmessagetaskController.class);
    private String prefix = "wx/tmessagetask";

    @Autowired
    private IWeixinTmessagetaskService weixinTmessagetaskService;
    @Autowired
    private IWeixinTmessageService tmessageService;
    @Autowired
    private IJwWebJwidService jwidService;
    @Autowired
    private IWeixinTagService tagService;
    @Autowired
    private IWeixinGzuserService gzuserService;
    @Autowired
    private IWeixinTmessageSendlogService sendlogService;

    @RequiresPermissions("wx:tmessagetask:view")
    @GetMapping()
    public String tmessagetask() {
        return prefix + "/tmessagetask";
    }

    /**
     * 查询模板消息发送列表
     */
    @RequiresPermissions("wx:tmessagetask:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WeixinTmessagetask weixinTmessagetask) {
        String username = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(username);
        weixinTmessagetask.setJwid(jwWebJwid.getJwid());
        startPage();
        List<WeixinTmessagetask> list = weixinTmessagetaskService.selectTmessagetaskList(weixinTmessagetask);
        return getDataTable(list);
    }

    /**
     * 导出模板消息发送列表
     */
    @RequiresPermissions("wx:tmessagetask:export")
    @Log(title = "模板消息发送", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinTmessagetask weixinTmessagetask) {
        List<WeixinTmessagetask> list = weixinTmessagetaskService.selectWeixinTmessagetaskList(weixinTmessagetask);
        ExcelUtil<WeixinTmessagetask> util = new ExcelUtil<WeixinTmessagetask>(WeixinTmessagetask.class);
        return util.exportExcel(list, "tmessagetask");
    }

    /**
     * 新增模板消息发送
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存模板消息发送
     */
    @RequiresPermissions("wx:tmessagetask:add")
    @Log(title = "模板消息发送", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated WeixinTmessagetask weixinTmessagetask) {
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        weixinTmessagetask.setJwid(jwWebJwid.getJwid());
        return toAjax(weixinTmessagetaskService.insertWeixinTmessagetask(weixinTmessagetask));
    }

    /**
     * 修改模板消息发送
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        WeixinTmessagetask weixinTmessagetask = weixinTmessagetaskService.selectWeixinTmessagetaskById(id);
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        WeixinTmessage tmessage = new WeixinTmessage();
        tmessage.setJwid(jwWebJwid.getJwid());
        List<WeixinTmessage> weixinTmessages = tmessageService.selectWeixinTmessageList(tmessage);
        WeixinTag tag = new WeixinTag();
        tag.setJwid(jwWebJwid.getJwid());
        List<WeixinTag> weixinTags = tagService.selectWeixinTagList(tag);
        mmap.put("tmessages", weixinTmessages);
        mmap.put("tags", weixinTags);
        mmap.put("weixinTmessagetask", weixinTmessagetask);
        return prefix + "/edit";
    }

    /**
     * 修改保存模板消息发送
     */
    @RequiresPermissions("wx:tmessagetask:edit")
    @Log(title = "模板消息发送", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated WeixinTmessagetask weixinTmessagetask) {
        return toAjax(weixinTmessagetaskService.updateWeixinTmessagetask(weixinTmessagetask));
    }

    /**
     * 删除模板消息发送
     */
    @RequiresPermissions("wx:tmessagetask:remove")
    @Log(title = "模板消息发送", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(weixinTmessagetaskService.deleteWeixinTmessagetaskByIds(ids));
    }

    /**
     * 发送模板消息获取模板内容解析返回前台
     *
     * @param templateId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "getTemplateInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxJson getTemplateInfo(String templateId, HttpServletRequest request, HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        try {
            WeixinTmessage tmessage = tmessageService.selectTmessageByTemplateId(templateId);
            String content = tmessage.getContent();
            if (content.indexOf(".DATA") > 0) {
                List<String> list = new ArrayList<String>();
                //循环所有的.DATA
                while (content.indexOf(".DATA") > 0) {
                    //添加截取的key到list中
                    list.add(content.substring(content.indexOf("{{") + 2, content.indexOf(".DATA")));
                    //截取content去掉已经添加到list中的key
                    content = content.substring(content.indexOf(".DATA") + 5, content.length());
                }
                j.setObj(list);
                j.setSuccess(true);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            j.setSuccess(false);
            j.setMsg("获取模板消息异常");
        }
        return j;
    }

    @RequestMapping(value = "/sendTemplateMsg", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public AjaxResult sendTemplateMsg(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        try {
            String taskId = request.getParameter("taskId");
            WeixinTmessagetask tmessagetask = weixinTmessagetaskService.selectWeixinTmessagetaskById(taskId);
            //标签模式
            List<String> openids = new ArrayList<>();
            if ("0".equals(tmessagetask.getSendType())) {
                List<WeixinGzuser> weixinGzusers = gzuserService.queryGzuserByTagId(tmessagetask.getTagId());
                for (WeixinGzuser gzuser : weixinGzusers) {
                    openids.add(gzuser.getOpenid());
                }
                //列表模式
            } else {
                String openid = tmessagetask.getOpenid();
                String[] oids = openid.split(",");
                openids = Arrays.asList(oids);
            }
            final String[] openIds = openids.toArray(new String[openids.size()]);
            if (openIds.length > 0) {
                String createBy = ShiroUtils.getSysUser().getUserName();
                JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
                final String jwid = jwWebJwid.getJwid();
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < openIds.length; i++) {
                            TmessageTaskSendVo tmessageTaskSendVo = new TmessageTaskSendVo();
                            tmessageTaskSendVo.setTouser(openIds[i]);//OPENID
                            tmessageTaskSendVo.setTemplateId(tmessagetask.getTemplateId());//模板ID
                            tmessageTaskSendVo.setData(tmessagetask.getDataJson());//JSON数据
                            tmessageTaskSendVo.setUrl(tmessagetask.getUrl());//跳转链接
                            tmessageTaskSendVo.setMiniprogram(tmessagetask.getPagepath());//小程序跳转链接
                            tmessageTaskSendVo.setAppid(tmessagetask.getMiniAppid());//小程序APPID
                            tmessageTaskSendVo.setJwid(jwid);//JWID
                            JSONObject jsonObj = weixinTmessagetaskService.sendTemplateMsg(tmessageTaskSendVo);
                            //调用接口返回结果，保存消息日志表中
                            WeixinTmessageSendlog sendlog = new WeixinTmessageSendlog();
                            sendlog.setCreateDate(new Date());
                            sendlog.setOpenid(openIds[i]);
                            sendlog.setDataJson(tmessagetask.getDataJson());
                            sendlog.setTaskId(taskId);
                            String errcode = jsonObj.getString("errcode");
                            if("0".equals(errcode)){
                                sendlog.setStatus("0");
                                sendlog.setMsgId(jsonObj.getString("msgid"));
                                tmessagetask.setTaskSendStatus("1");//已发送
                            }else{
                                sendlog.setStatus("1");
                                tmessagetask.setTaskSendStatus("2");//发送失败
                            }

                            sendlogService.insertWeixinTmessageSendlog(sendlog);

                            //更新发送状态
                            weixinTmessagetaskService.updateWeixinTmessagetask(tmessagetask);
                        }
                    }
                };
                new Thread(run).start();
                WeixinTmessagetask task = tmessagetask;
                task.setTaskSendStatus("3");//发送中
                //更新发送状态
                weixinTmessagetaskService.updateWeixinTmessagetask(task);
                result = AjaxResult.success("消息发送已启动，请稍后刷新");
            }
        }catch (Exception e){
            result = AjaxResult.error("发送模板消息失败");
        }
        return result;
    }

}

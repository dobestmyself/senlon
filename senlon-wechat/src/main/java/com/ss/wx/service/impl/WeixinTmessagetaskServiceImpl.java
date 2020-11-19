package com.ss.wx.service.impl;

import java.util.*;

import com.ss.utils.AccessTokenUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.mapper.JwWebJwidMapper;
import com.ss.wx.vo.TmessageTaskSendVo;
import net.sf.json.JSONObject;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeewx.api.core.common.WxstoreUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinTmessagetaskMapper;
import com.ss.wx.domain.WeixinTmessagetask;
import com.ss.wx.service.IWeixinTmessagetaskService;
import com.ss.common.core.text.Convert;

/**
 * 模板消息发送Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-17
 */
@Service
public class WeixinTmessagetaskServiceImpl implements IWeixinTmessagetaskService 
{
    private static final Logger log = LoggerFactory.getLogger(WeixinTmessagetaskServiceImpl.class);
    private static final String send_template_message_url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    @Autowired
    private WeixinTmessagetaskMapper weixinTmessagetaskMapper;
    @Autowired
    private JwWebJwidMapper jwidMapper;

    /**
     * 查询模板消息发送
     * 
     * @param id 模板消息发送ID
     * @return 模板消息发送
     */
    @Override
    public WeixinTmessagetask selectWeixinTmessagetaskById(String id)
    {
        return weixinTmessagetaskMapper.selectWeixinTmessagetaskById(id);
    }

    /**
     * 查询模板消息发送列表
     * 
     * @param weixinTmessagetask 模板消息发送
     * @return 模板消息发送
     */
    @Override
    public List<WeixinTmessagetask> selectWeixinTmessagetaskList(WeixinTmessagetask weixinTmessagetask)
    {
        return weixinTmessagetaskMapper.selectWeixinTmessagetaskList(weixinTmessagetask);
    }

    /**
     * 新增模板消息发送
     * 
     * @param weixinTmessagetask 模板消息发送
     * @return 结果
     */
    @Override
    public int insertWeixinTmessagetask(WeixinTmessagetask weixinTmessagetask)
    {
        weixinTmessagetask.setId(UUID.randomUUID().toString());
        weixinTmessagetask.setCreateDate(new Date());
        weixinTmessagetask.setTaskSendStatus("0");
        return weixinTmessagetaskMapper.insertWeixinTmessagetask(weixinTmessagetask);
    }

    /**
     * 修改模板消息发送
     * 
     * @param weixinTmessagetask 模板消息发送
     * @return 结果
     */
    @Override
    public int updateWeixinTmessagetask(WeixinTmessagetask weixinTmessagetask)
    {
        return weixinTmessagetaskMapper.updateWeixinTmessagetask(weixinTmessagetask);
    }

    /**
     * 删除模板消息发送对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTmessagetaskByIds(String ids)
    {
        return weixinTmessagetaskMapper.deleteWeixinTmessagetaskByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除模板消息发送信息
     * 
     * @param id 模板消息发送ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTmessagetaskById(String id)
    {
        return weixinTmessagetaskMapper.deleteWeixinTmessagetaskById(id);
    }

    @Override
    public List<WeixinTmessagetask> selectTmessagetaskList(WeixinTmessagetask weixinTmessagetask) {
        return weixinTmessagetaskMapper.selectTmessagetaskList(weixinTmessagetask);
    }

    /**
     * 调用发送模板消息接口
     * @param taskSendVo
     * @return
     */
    @Override
    public JSONObject sendTemplateMsg(TmessageTaskSendVo taskSendVo) {
        JSONObject result = null;
        if(oConvertUtils.isEmpty(taskSendVo.getTouser())) {
            log.info("发送模板消息---接收者openid为空");
            return result;
        }
        if(oConvertUtils.isEmpty(taskSendVo.getTemplateId())) {
            log.info("发送模板消息---模板ID为空");
            return result;
        }
        if(oConvertUtils.isEmpty(taskSendVo.getJwid())) {
            log.info("发送模板消息---JWID为空");
            return result;
        }
        if(oConvertUtils.isEmpty(taskSendVo.getData())) {
            log.info("发送模板消息---模板数据为空");
            return result;
        }
        Map<String,Object> param = new HashMap<String,Object>();
        //接收者openid
        param.put("touser", taskSendVo.getTouser());
        //模板ID
        param.put("template_id", taskSendVo.getTemplateId());
        //模板数据
        JSONObject dataJson = JSONObject.fromObject(taskSendVo.getData());
        param.put("data",(Map) dataJson);
        //模板跳转链接
        if(oConvertUtils.isNotEmpty(taskSendVo.getUrl())) {
            param.put("url", taskSendVo.getUrl());
        }
        //跳小程序所需数据
        if(oConvertUtils.isNotEmpty(taskSendVo.getMiniprogram())) {
            param.put("miniprogram", taskSendVo.getMiniprogram());
        }
        //所需跳转到的小程序appid
        if(oConvertUtils.isNotEmpty(taskSendVo.getAppid())) {
            param.put("appid", taskSendVo.getAppid());
        }
        //所需跳转到小程序的具体页面路径
        if(oConvertUtils.isNotEmpty(taskSendVo.getPagepath())) {
            param.put("pagepath", taskSendVo.getPagepath());
        }
        JwWebJwid jwWebJwid = jwidMapper.queryByJwid(taskSendVo.getJwid());
        Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        String accessToken = map.get("accessToken").toString();
        if(accessToken != null){
            String requestUrl = send_template_message_url.replace("ACCESS_TOKEN",accessToken);
            JSONObject obj = JSONObject.fromObject(param);
            log.info("发送模板消息方法执行前json参数：{obj:"+obj.toString()+"}");
            result = WxstoreUtils.httpRequest(requestUrl,"POST",obj.toString());
            log.info("发送模板消息方法执行后就送参数：{result："+result.toString()+"}");
        }
        return result;
    }
}

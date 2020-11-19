package com.ss.wx.web.core;

import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import com.jeecg.qywx.api.conversation.vo.TextMessage;
import com.ss.common.utils.StringUtils;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.wx.service.WechatService;
import com.ss.wx.utils.MessageUtil;
import com.ss.wx.utils.SignUtil;
import com.ss.wx.vo.TextMessageResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 微信客户端，请求处理核心类
 */
@Controller
@RequestMapping("/wechatController")
@SkipAuth(auth = SkipPerm.SKIP_SIGN)
public class WechatController {
    public final static Logger log = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private WechatService wechatService;
    @Autowired
    private IJwWebJwidService webJwidService;

    @RequestMapping(value="/wechat",method = {RequestMethod.GET})     //params="wechat"
    public void wechatGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        log.info("微信公众号响应消息---wechat");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        List<JwWebJwid> jwWebJwids = webJwidService.selectJwWebJwidList(new JwWebJwid());
        if(jwWebJwids != null && jwWebJwids.size()>0){
            log.info("jwWebJwids size"+jwWebJwids.size());
            for(JwWebJwid jwWebJwid :jwWebJwids){
                if(SignUtil.checkSignature(jwWebJwid.getToken(),signature,timestamp,nonce)){
                    try{
                        response.getWriter().print(echostr);
                        break;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    @RequestMapping(value = "wechat",method = RequestMethod.POST)
    public void wechatPost(HttpServletRequest request,HttpServletResponse response)throws IOException{
        log.info("----------------微信公众号响应消息----------wechatPost");
        String respMessage = null;
        try{
            respMessage = wechatService.coreService(request);
        }catch (Exception e){
            e.printStackTrace();
            if(StringUtils.isEmpty(respMessage)){
                respMessage = getWeixinMsg(request);
            }
        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

    /**
     * 异常情况下回复此消息
     * @param request
     * @return
     */
    private String getWeixinMsg(HttpServletRequest request){
        //异常情况下回复此文本消息
        TextMessageResp textMessage = new TextMessageResp();
        try{
            //默认返回的文本消息内容
            String respContent = "系统网络繁忙，请稍后再试！";
            //xml请求解析
            Map<String ,String> requestMap = MessageUtil.parseXml(request);
            //发送方账号
            String fromUserName = requestMap.get("FromUserName");
            //公众账号
            String toUserName = requestMap.get("ToUserName");
            //消息类型
            String msgType = requestMap.get("MsgType");
            //消息id
            String msgId = requestMap.get("MsgId");
            //消息内容
            String content = requestMap.get("Content");
            //多媒体ID
            String mediaId = requestMap.get("MediaId");
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
            textMessage.setContent(respContent);
        }catch (Exception e){

        }
        //将文本消息对象转换成xml字符串
        return MessageUtil.textMessageToXml(textMessage);
    }
}

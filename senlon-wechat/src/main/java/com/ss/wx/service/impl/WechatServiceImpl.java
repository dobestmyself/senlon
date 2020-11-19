package com.ss.wx.service.impl;

import com.jeecg.p3.baseApi.util.EmojiFilter;
import com.ss.common.utils.StringUtils;
import com.ss.utils.WeixinUtil;
import com.ss.wx.WeixinMessageDTO;
import com.ss.wx.domain.*;
import com.ss.wx.service.*;
import com.ss.wx.utils.MessageUtil;
import com.ss.wx.utils.WeixinConstants;
import com.ss.wx.vo.Article;
import com.ss.wx.vo.NewsMessageResp;
import com.ss.wx.vo.TextMessageResp;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.util.DateUtils;
import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class WechatServiceImpl implements WechatService {

    public final static Logger log = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private IWeixinAutoresponseService weixinAutoresponseService;
    @Autowired
    private IWeixinTexttemplateService weixinTexttemplateService;
    @Autowired
    private IWeixinNewstemplateService weixinNewstemplateService;
    @Autowired
    private IWeixinNewsitemService weixinNewsitemService;
    @Autowired
    private IJwWebJwidService myJwWebJwidService;
    @Autowired
    private IWeixinAutoresponseService weixinAutoresponseDefaultService;
    @Autowired
    private IWeixinMenuService weixinMenuService;
    @Autowired
    private IWeixinSubscribeService weixinSubscribeService;
    @Autowired
    private IWeixinReceivetextService weixinReceivetextService;
    @Autowired
    private IWeixinGzuserService weixinGzuserService;
    @Autowired
    private IWeixinQrcodeService weixinQrcodeService;
    @Autowired
    private IWeixinQrcodeScanRecordService weixinQrcodeScanRecordService;
    /*@Autowired
    private WeixinGroupMessageSendDetailService weixinGroupMessageSendDetailService;*/

    @Override
    public String coreService(HttpServletRequest request) {
        log.info("-----------接受微信客户端消息，进入逻辑处理开始--------------");
        String respMessage = "";
        try{
            //xml请求解析
            Map<String,String> requestMap = MessageUtil.parseXml(request);
            log.info("--------解析微信客户端消息完成---MessageUtil.parseXml--------");
            //消息存储-非事件消息
            if(!MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(requestMap.get("MsgType"))){
                log.info("----保存微信客户端消息进数据库");
                saveReceiveMessage(requestMap);
            }
            log.info(" -----微信客户端消息------： {}", requestMap.toString());
            //写入通用DTO类中方便传递参数
            WeixinMessageDTO weixinMessageDTO = new WeixinMessageDTO();
            // 发送方帐号(OpenId)
            weixinMessageDTO.setFromUserName(requestMap.get("FromUserName"));
            // 公众帐号(JWID)
            weixinMessageDTO.setToUserName(requestMap.get("ToUserName"));
            // 消息类型
            weixinMessageDTO.setMsgType(requestMap.get("MsgType"));
            //消息ID
            weixinMessageDTO.setMsgId(requestMap.get("MsgId"));
            //消息内容
            weixinMessageDTO.setContent(requestMap.get("Content"));
            //多媒体ID
            weixinMessageDTO.setMediaId(requestMap.get("MediaId"));
            //菜单key
            weixinMessageDTO.setKey(requestMap.get("EventKey"));
            //Event(事件)
            weixinMessageDTO.setEvent(requestMap.get("Event"));

            //微信触发类型  文本消息
            if(weixinMessageDTO.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
                respMessage = doTextResponse(weixinMessageDTO,request);
            }
            //微信触发；类型 事件推送
            else if(weixinMessageDTO.getMsgType().equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
                //事件类型
                String eventType = weixinMessageDTO.getEvent();
                //订阅
                if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
                    try{
                        respMessage = doSubscribeEventResponse(weixinMessageDTO,request);
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("------处理订阅事件异常--------"+e.toString());
                    }
                }
                //扫描二维码
                else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
                    try{
                        String sceneId = weixinMessageDTO.getKey();
                        respMessage = doScanEventResponse(weixinMessageDTO,sceneId,request);
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("-------处理扫描二维码事件异常--------"+e.toString());
                    }
                //取消订阅
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
                    try{
                        respMessage = doUnsubscribeEventResponse(weixinMessageDTO,request);
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("--------处理取消订阅事件异常：-----"+e.toString());
                    }
                //上报地理位置事件
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
                    try{
                        respMessage = doLocationEventResponse(weixinMessageDTO,request);
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("-------处理上报地理位置事件异常-----"+e.toString());
                    }
                //自定义菜点击事件（CLick）
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
                    try{
                        respMessage = doMyMenuEventResponse(weixinMessageDTO,request);
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("-------处理微信请求click事件异常------"+e.toString());
                    }
                //自定义菜单点击事件（VIEW）
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){
                    try{
                        respMessage = doMyMenuViewEventResponse(weixinMessageDTO,request);
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("——---------处理微信请求View事件异常====="+e.toString());
                    }
                }else{
                    try{
                        respMessage = doOtherEventResponse(eventType,requestMap);
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("-----处理其他事件异常------"+e.toString());
                    }
                }
            }
        }catch (Exception e){
            log.error("-----处理微信请求异常----------"+e.toString());
            e.printStackTrace();
            respMessage = "";
        }
        return respMessage;
    }

    /**
     * 其他事件处理
     * @param eventType
     * @param requestMap
     * @return
     */
    private String doOtherEventResponse(String eventType, Map<String, String> requestMap) {
        if (oConvertUtils.isEmpty(eventType)) {
            return "";
        }
        eventType = eventType.trim();
        if (eventType.equals(MessageUtil.EVENT_CARD_PASS_CHECK)) {
            // 卡劵： 审核事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_GET_CARD)) {
            // 卡劵： 领取事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_CARD_NOT_PASS_CHECK)) {
            // 卡劵： 领取失败事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_GIFTING_CARD)) {
            // 卡劵： 转赠事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_DEL_CARD)) {
            // 卡劵： 删除事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_CONSUME_CARD)) {
            // 卡劵： 核销事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_PAY_FROM_PAY_CELL)) {
            // 卡劵： 买单事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_VIEW_CARD)) {
            // 卡劵： 进入会员卡事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_ENTER_SESSION_FROM_CARD)) {
            // 卡劵：从卡券进入公众号会话事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_UPDATE_MEMBER_CARD)) {
            // 卡劵： 会员卡内容更新事件
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_CARD_SKU_REMIND)) {
            // 卡劵： 库存报警事件
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_CARD_PAY_ORDER)) {
            // 卡劵： 券点流水详情事件
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_SUBMIT_MEMBERCARD_USER_INFO)) {
            // 卡劵： 会员卡激活事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_MASSSENDJOBFINISH)) {
            // 群发： 事件推送群发结果
            //update-begin--Author:zhangweijian  Date: 20180903 for：记录事件推送群发结果
            /*if(requestMap.containsKey("MsgID")){
                String msgid = requestMap.get("MsgID");
                String jwid= requestMap.get("ToUserName");
                List<WeixinGroupMessageSendDetail> sendDetails=weixinGroupMessageSendDetailService.queryByMsgId(msgid,jwid);
                if(sendDetails!=null&&sendDetails.size()>0){
                    WeixinGroupMessageSendDetail sendDetail = sendDetails.get(0);
                    String totalCount = requestMap.get("TotalCount");
                    String filterCount = requestMap.get("FilterCount");
                    String sendCount = requestMap.get("SentCount");
                    String errorCount = requestMap.get("ErrorCount");
                    String Status = requestMap.get("Status");
                    if(requestMap.containsKey("TotalCount") && StringUtil.isNotEmpty(requestMap.get("TotalCount"))){
                        sendDetail.setPushErrorcount(Integer.parseInt(errorCount));
                        sendDetail.setPushTotalcount(Integer.parseInt(totalCount));
                        sendDetail.setPushFiltercount(Integer.parseInt(filterCount));
                        sendDetail.setPushSendcount(Integer.parseInt(sendCount));
                        sendDetail.setPushStatus(Status);
                    }
                    weixinGroupMessageSendDetailService.doEdit(sendDetail);
                }
            }*/
            //update-end--Author:zhangweijian  Date: 20180903 for：记录事件推送群发结果
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_SHAKEAROUNDUSERSHAKE)) {
            // 摇一摇： 事件通知
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_SHAKEAROUNDLOTTERYBIND)) {
            // 红包： 事件通知
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_SCAN_PRODUCT)) {
            // 扫一扫： 打开商品主页事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_SCAN_PRODUCT_ENTER_SESSION)) {
            // 扫一扫： 进入公众号事件推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_SCAN_PRODUCT_ASYNC)) {
            // 扫一扫： 地理位置信息异步推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_USER_SCAN_PRODUCT_VERIFY_ACTION)) {
            // 扫一扫： 商品审核结果推送
            return "";
        } else if (eventType.equals(MessageUtil.EVENT_TEMPLATESENDJOBFINISH)) {
            // 模版消息： 模版消息发送任务完成后
            return "";
        } else if (requestMap.containsKey(MessageUtil.EVENT_TRANSACTION_ID)) {
            // 对支付事件进行处理。
            return "";
        }
        return "";
    }

    /**
     * 【微信触发类型】事件推送-自定义菜单点击事件（VIEW）
     * @param weixinMessageDTO, request
     * @return respMessage
     */
    private String doMyMenuViewEventResponse(WeixinMessageDTO weixinMessageDTO, HttpServletRequest request) {
        //TODO
        String respMessage = "";
        return respMessage;
    }

    /**
     * 微信触发类型 事件推送 接收地理位置信息
     * @param weixinMessageDTO
     * @param request
     * @return
     */
    private String doLocationEventResponse(WeixinMessageDTO weixinMessageDTO,HttpServletRequest request){
        //TODO
        String respMessage = "";
        return respMessage;
    }


    /**
     * 微信触发类型  事件推送--自定义菜单点击事件
     * @param weixinMessageDTO
     * @param request
     * @return
     */
    private String doMyMenuEventResponse(WeixinMessageDTO weixinMessageDTO,HttpServletRequest request){
        String respMessage = "";
        //根据菜单KEY和JWID查询菜单信息
        WeixinMenu menu = new WeixinMenu();
        menu.setMenuKey(weixinMessageDTO.getKey());
        menu.setJwid(weixinMessageDTO.getToUserName());
        List<WeixinMenu> weixinMenus = weixinMenuService.selectWeixinMenuList(menu);
        if(weixinMenus!= null && weixinMenus.size()>0){
            WeixinMenu weixinMenu = weixinMenus.get(0);
            if(weixinMenu != null && StringUtils.isNotEmpty(weixinMenu.getTemplateId())){
                 weixinMessageDTO.setTemplateId(weixinMenu.getTemplateId());
                 //调用共通方法回复语
                respMessage = getRespMessage(weixinMessageDTO,weixinMenu.getMsgtype(),request);
            }
        }
        return respMessage;
    }

    /**
     * 微信触发类型  事件推送-取消订阅
     * @param weixinMessageDTO
     * @param request
     * @return
     */
    private String doUnsubscribeEventResponse(WeixinMessageDTO weixinMessageDTO,HttpServletRequest request){
        String respMessage = "";
        WeixinGzuser gzuser = new WeixinGzuser();
        gzuser.setOpenid(weixinMessageDTO.getFromUserName());
        gzuser.setJwid(weixinMessageDTO.getToUserName());
        List<WeixinGzuser> weixinGzusers = weixinGzuserService.selectWeixinGzuserList(gzuser);
        if(weixinGzusers != null && weixinGzusers.size()>0){
            WeixinGzuser weixinGzuser = weixinGzusers.get(0);
            if(weixinGzuser != null){
                weixinGzuser.setSubscribe("0");//未关注
                weixinGzuser.setTagidList("");//标签ID置空
                weixinGzuserService.updateWeixinGzuser(weixinGzuser);
            }
            return respMessage;
        }
        return respMessage;
    }

    /**
     *微信触发类型  事件推送-订阅
     * @param weixinMessageDTO
     * @param request
     * @return
     */
    private String doSubscribeEventResponse(WeixinMessageDTO weixinMessageDTO,HttpServletRequest request){
        String respMessage = "";
        WeixinSubscribe subscribe = new WeixinSubscribe();
        subscribe.setJwid(weixinMessageDTO.getToUserName());
        List<WeixinSubscribe> weixinSubscribes = weixinSubscribeService.selectWeixinSubscribeList(subscribe);

        if(weixinSubscribes != null && weixinSubscribes.size()>0){
            WeixinSubscribe weixinSubscribe = weixinSubscribes.get(0);
            weixinMessageDTO.setTemplateId(weixinSubscribe.getTemplateId());
            //调用共通方法回复语
            respMessage = getRespMessage(weixinMessageDTO,weixinSubscribe.getMsgType(),request);
        }
        //关注，根据用户OPENID和jwid添加关注用户
        saveGzUserInfoByOpenID(weixinMessageDTO.getFromUserName(),weixinMessageDTO.getToUserName());
        //获取关注后参数内的scenceId
        if(weixinMessageDTO.getKey().indexOf("qrscene")!= -1){
            String sceneId = weixinMessageDTO.getKey().substring(weixinMessageDTO.getKey().indexOf("_")+1);
            respMessage = this.doScanEventResponse(weixinMessageDTO,sceneId,request);
        }
        return respMessage;
    }

    /**
     * 微信触发类型  事件推送-扫描二维码
     * @param weixinMessageDTO
     * @param sceneId
     * @param request
     * @return
     */
    private String doScanEventResponse(WeixinMessageDTO weixinMessageDTO,String sceneId,HttpServletRequest request){
        //保存扫码记录
        WeixinQrcodeScanRecord qrcodeScanRecord = new WeixinQrcodeScanRecord();
        qrcodeScanRecord.setSceneId(sceneId);
        qrcodeScanRecord.setScanTime(new Date());
        qrcodeScanRecord.setOpenid(weixinMessageDTO.getFromUserName());
        qrcodeScanRecord.setJwid(weixinMessageDTO.getToUserName());
        String event = weixinMessageDTO.getEvent();
        if(event.equals("SCAN")){
            qrcodeScanRecord.setIsScanSubscribe("0");
        }else{
            qrcodeScanRecord.setIsScanSubscribe("1");
        }
        weixinQrcodeScanRecordService.insertWeixinQrcodeScanRecord(qrcodeScanRecord);
        //如果二维码存在，开始返回文本或图文消息
        String respMessage = "";
        TextMessageResp textMessage = new TextMessageResp();
        //查询二维码是否存在
        WeixinQrcode qrcode = new WeixinQrcode();
        qrcode.setSceneId(Integer.parseInt(sceneId));
        qrcode.setJwid(weixinMessageDTO.getToUserName());
        List<WeixinQrcode> weixinQrcodes = weixinQrcodeService.selectWeixinQrcodeList(qrcode);
        if(weixinQrcodes != null && weixinQrcodes.size()>0){
            WeixinQrcode weixinQrcode = weixinQrcodes.get(0);
            //判断二维码的消息返回类型
            if(weixinQrcode.getMsgType().equals("text")){
                textMessage.setContent(weixinQrcode.getTextContent());
                textMessage.setCreateTime(new Date().getTime());
                textMessage.setFromUserName(weixinMessageDTO.getToUserName());
                textMessage.setToUserName(weixinMessageDTO.getFromUserName());
                textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }else if(weixinQrcode.getMsgType().equals("news")){
                if(weixinQrcode.getActionNewsType().equals("2")){
                    //扫码返回素材模板
                    weixinMessageDTO.setTemplateId(weixinQrcode.getNewsTemplateid());
                    respMessage = getNewsRespMessage(weixinMessageDTO, request);
                }else if(weixinQrcode.getActionNewsType().equals("1")){
                    //扫码返回自定义素材模板
                    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                    NewsMessageResp newsMessageResp = new NewsMessageResp();
                    List<Article> articles = new ArrayList<Article>();
                    Article article = new Article();
                    article.setTitle(weixinQrcode.getNewsTitle());
                    article.setDescription(weixinQrcode.getNewsDesc());
                    String url = weixinQrcode.getNewsUrl();
                    //图文外部链接参数替换
                    if(oConvertUtils.isNotEmpty(url)) {
                        if (url.indexOf("${openid}") != -1) {
                            url = url.replace("${openid}", weixinMessageDTO.getFromUserName());
                        }
                        if (url.indexOf("${wxid}") != -1) {
                            url = url.replace("${wxid}", weixinMessageDTO.getToUserName());
                        }
                        if (url.indexOf("${appid}") != -1) {
                            JwWebJwid jwWeb = myJwWebJwidService.queryByJwid(weixinMessageDTO.getToUserName());
                            url = url.replace("${appid}", jwWeb.getWeixinAppid());
                        }
                    }
                    article.setUrl(url);
                    if(weixinQrcode.getNewsImg()!=null&& (weixinQrcode.getNewsImg().indexOf("http://")!=-1||weixinQrcode.getNewsImg().indexOf("https://")!=-1)){
                        article.setPicUrl(weixinQrcode.getNewsImg());
                    }else{
                        article.setPicUrl(basePath + "/upload/img/qrcode/"+weixinQrcode.getJwid()+"/"+ weixinQrcode.getNewsImg());
                    }
                    articles.add(article);
                    newsMessageResp.setArticles(articles);
                    newsMessageResp.setArticleCount(articles.size());
                    newsMessageResp.setCreateTime(new Date().getTime());
                    newsMessageResp.setFromUserName(weixinMessageDTO.getToUserName());
                    newsMessageResp.setToUserName(weixinMessageDTO.getFromUserName());
                    newsMessageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                    //把图文素材转换为XML返回
                    respMessage = MessageUtil.newsMessageToXml(newsMessageResp);
                }
            }
        }
        return respMessage;
    }

    /**
     * 图文素材消息返回-根据图文素材ID
     * @param weixinMessageDTO, request
     * @return respMessage
     */
    private String getNewsRespMessage(WeixinMessageDTO weixinMessageDTO, HttpServletRequest request) {
        String respMessage = "";
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        //根据TemplateID查询图文记录
    //    WeixinNewstemplate weixinNewstemplate = weixinNewstemplateService.selectWeixinNewstemplateById(weixinMessageDTO.getTemplateId());
        WeixinNewstemplate weixinNewstemplate = weixinNewstemplateService.selectNewstemplateByTemplateId(weixinMessageDTO.getTemplateId());
        //根据图文ID查询关联表记录
        WeixinNewsitem newsitem = new WeixinNewsitem();
        newsitem.setNewstemplateId(weixinNewstemplate.getTemplateId());
        List<WeixinNewsitem> weixinNewsitems = weixinNewsitemService.selectWeixinNewsitemList(newsitem);
        //包装为NewMessageResp类
        NewsMessageResp newsMessageResp = new NewsMessageResp();
        if(weixinNewsitems != null && weixinNewsitems.size() > 0) {
            newsMessageResp.setArticleCount(weixinNewsitems.size());
            List<Article> articles = new ArrayList<Article>();
            for (WeixinNewsitem weixinNewsitem : weixinNewsitems) {
                Article article = new Article();
                article.setTitle(weixinNewsitem.getTitle());
                article.setDescription(weixinNewsitem.getDescription());
                String url = "";
                if ("news".equals(weixinNewsitem.getNewType())) {
                    //---update-begin-Alex----Date:20181010---for:替换文章访问链接
                    url = basePath + "/weixinNewsController/goContent.do?id=" + weixinNewsitem.getId() + "&jwid=" + weixinMessageDTO.getToUserName();
                    //---update-end-Alex----Date:20181010---for:替换文章访问链接
                } else {
                    //图文外部链接参数替换
                    url = weixinNewsitem.getExternalUrl();
                    if(oConvertUtils.isNotEmpty(url)) {
                        if (url.indexOf("${openid}") != -1) {
                            url = url.replace("${openid}", weixinMessageDTO.getFromUserName());
                        }
                        if (url.indexOf("${wxid}") != -1) {
                            url = url.replace("${wxid}", weixinMessageDTO.getToUserName());
                        }
                        if (url.indexOf("${appid}") != -1) {
                            JwWebJwid jwWeb = myJwWebJwidService.queryByJwid(weixinMessageDTO.getToUserName());
                            url = url.replace("${appid}", jwWeb.getWeixinAppid());
                        }
                    }
                }
                article.setUrl(url);
                if(weixinNewsitem.getImagePath()!=null&&weixinNewsitem.getImagePath().indexOf("http")!=-1){
                    article.setPicUrl(weixinNewsitem.getImagePath());
                }else{
                    article.setPicUrl(basePath + "/"+ weixinNewsitem.getImagePath());
                }
                articles.add(article);
            }
            newsMessageResp.setArticles(articles);
            newsMessageResp.setCreateTime(new Date().getTime());
            newsMessageResp.setFromUserName(weixinMessageDTO.getToUserName());
            newsMessageResp.setToUserName(weixinMessageDTO.getFromUserName());
            newsMessageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
            //把图文素材转换为XML返回
            respMessage = MessageUtil.newsMessageToXml(newsMessageResp);
        }
        return respMessage;
    }

    public void saveGzUserInfoByOpenID(String openId,String jwid){
        WeixinGzuser gzuser = new WeixinGzuser();
        gzuser.setOpenid(openId);
        gzuser.setJwid(jwid);
        List<WeixinGzuser> weixinGzusers = weixinGzuserService.selectWeixinGzuserList(gzuser);
        WeixinGzuser weixinGzuser = weixinGzusers.get(0);
        //获取请求的微信公众账号信息
        JwWebJwid jwWebJwid = myJwWebJwidService.queryByJwid(jwid);
        //通过微信接口，抓取关注用户信息
        WeixinGzuser zuser = weixinGzuserService.getGzUserInfo(openId, jwid, jwWebJwid.getAccessToken());
        if(weixinGzuser == null){
            if(zuser != null){
                WeixinGzuser gzuserInfo = new WeixinGzuser();
                gzuserInfo.setCity(EmojiFilter.filterEmoji(zuser.getCity()));
                gzuserInfo.setCountry(EmojiFilter.filterEmoji(zuser.getCountry()));
                String nickName = WeixinUtil.encode(zuser.getNickname().getBytes());
                gzuserInfo.setNickname(nickName);
                gzuserInfo.setNicknameTxt(EmojiFilter.filterEmoji(zuser.getNickname()));
                gzuserInfo.setOpenid(zuser.getOpenid());
                gzuserInfo.setProvince(EmojiFilter.filterEmoji(zuser.getProvince()));
                gzuserInfo.setSex(zuser.getSex());
                gzuserInfo.setSubscribe(zuser.getSubscribe());
                //update-begin--Author:zhangweijian  Date: 20180807 for：关注时间修改
                gzuserInfo.setSubscribeTime(zuser.getSubscribeTime());
                //update-end--Author:zhangweijian  Date: 20180807 for：关注时间修改
                gzuserInfo.setSubscribeScene(zuser.getSubscribeScene());
                gzuserInfo.setQrScene(zuser.getQrScene());
                gzuserInfo.setQrSceneStr(zuser.getQrSceneStr());
                gzuserInfo.setGroupid(zuser.getGroupid());
                gzuserInfo.setLanguage(zuser.getLanguage());
                gzuserInfo.setBzname(zuser.getBzname());
                gzuserInfo.setTagidList(zuser.getTagidList());
                gzuserInfo.setCreateTime(new Date());
                gzuserInfo.setJwid(jwid);
                weixinGzuserService.insertWeixinGzuser(gzuserInfo);
            }else{
                try{
                    //获取不到用户详细信息情况下
                    WeixinGzuser temp = new WeixinGzuser();
                    temp.setOpenid(openId);
                    // 默认设为关注
                    temp.setSubscribe(WeixinConstants.WEIXIN_SUBSCRIBE_TYPE);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    temp.setSubscribeTime(sdf.format(new Date()));
                    temp.setCreateTime(new Date());
                    temp.setJwid(jwWebJwid.getJwid());
                    weixinGzuserService.insertWeixinGzuser(temp);
                }catch (Exception e){
                    log.info("----------保存粉丝信息异常--------"+e.toString());
                }
            }
        }else{
            if (zuser != null) {
                String nickName = WeixinUtil.encode(zuser.getNickname().getBytes());
                weixinGzuser.setNickname(nickName);
                weixinGzuser.setNicknameTxt(EmojiFilter.filterEmoji(zuser.getNickname()));
                weixinGzuser.setHeadimgurl(zuser.getHeadimgurl());
            }
            // 默认设为关注
            weixinGzuser.setSubscribe(WeixinConstants.WEIXIN_SUBSCRIBE_TYPE);
            weixinGzuser.setSubscribeTime(DateUtils.getDataString(DateUtils.datetimeFormat));
            weixinGzuserService.updateWeixinGzuser(weixinGzuser);
        }
    }

    /**
     * 微信触发类型 输入内容为文本消息的返回处理
     * @param weixinMessageDTO
     * @param request
     * @return
     */
    private String doTextResponse(WeixinMessageDTO weixinMessageDTO,HttpServletRequest request){
        String respMessage = "";
        //将当前输入内容匹配关键字，如果找到则走关键字回复
        WeixinAutoresponse autoResponse = findKey(weixinMessageDTO.getContent(), weixinMessageDTO.getToUserName());
        if(autoResponse != null){
            weixinMessageDTO.setTemplateId(autoResponse.getTemplateId());
            //调用共通方法回复语
            respMessage = getRespMessage(weixinMessageDTO,autoResponse.getMsgType(),request);
        }else{
            //调用未识别回复语通用方法
            respMessage = unrecognizedReply(weixinMessageDTO, request);
        }
        return respMessage;
    }

    /**
     * 共通方法-微信触发类型  未识别回复语
     * @param weixinMessageDTO
     * @param request
     * @return
     */
    private String unrecognizedReply(WeixinMessageDTO weixinMessageDTO,HttpServletRequest request){
        String respMessage = "";
        //根据输入内容和微信ID查询未识别回复语记录
        WeixinAutoresponse autoresponse = new WeixinAutoresponse();
        autoresponse.setMsgType(weixinMessageDTO.getMsgType());
        autoresponse.setJwid(weixinMessageDTO.getToUserName());
        List<WeixinAutoresponse> autoresponses = weixinAutoresponseDefaultService.selectWeixinAutoresponseList(autoresponse);
        if(autoresponses != null && autoresponses.size()>0){
            WeixinAutoresponse weixinAutoresponse = autoresponses.get(0);
            if(weixinAutoresponse != null && StringUtils.isNotEmpty(weixinAutoresponse.getTemplateId())){
                String templateId = weixinAutoresponse.getTemplateId();
                weixinMessageDTO.setTemplateId(templateId);
                //调用共通回复语
                respMessage = getRespMessage(weixinMessageDTO,weixinAutoresponse.getMsgType(),request);
            }
        }
        return respMessage;
    }

    /**
     * 共通方法，根据平台设置返回不同类型的回复语
     * @param messageDTO
     * @param msgType
     * @param request
     * @return
     */
    private String getRespMessage(WeixinMessageDTO messageDTO,String msgType,HttpServletRequest request){
        String respMessage = "";
        if(MessageUtil.RESP_MESSAGE_TYPE_TEXT.equals(msgType)){
            //调用文本素材消息回复
            respMessage = getTextRespMessage(messageDTO);
        }else if(MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(msgType)) {
            //调用图文素材消息回复
            respMessage = getNewsRespMessage(messageDTO, request);
        } else if(MessageUtil.RESP_MESSAGE_TYPE_IMAGE.equals(msgType)) {
            //调用图片素材消息回复
            respMessage = getImageRespMessage(messageDTO, request);
        } else if(MessageUtil.RESP_MESSAGE_TYPE_VOICE.equals(msgType)) {
            //调用语音素材消息回复
            respMessage = getVoiceRespMessage(messageDTO, request);
        } else if(MessageUtil.RESP_MESSAGE_TYPE_VIDEO.equals(msgType)) {
            //调用视频素材消息回复
            respMessage = getVideoRespMessage(messageDTO, request);
        } else if(MessageUtil.RESP_MESSAGE_TYPE_EXPAND.equals(msgType)) {
            //调用扩展接口业务素材消息回复
            respMessage = getExpandRespMessage(messageDTO, request);
        }
        return respMessage;
    }

    /**
     * 扩展接口业务消息返回- 扩展接口业务 (自定义菜单使用)
     * @param weixinMessageDTO, request
     * @return respMessage
     */
    private String getExpandRespMessage(WeixinMessageDTO weixinMessageDTO, HttpServletRequest request) {
        //TODO
        String respMessage = "";
        return respMessage;
    }

    /**
     * 语音素材消息返回-根据语音素材ID
     * @param weixinMessageDTO, request
     * @return respMessage
     */
    private String getVoiceRespMessage(WeixinMessageDTO weixinMessageDTO, HttpServletRequest request) {
        //TODO
        String respMessage = "";
        return respMessage;
    }

    /**
     * 图片素材消息返回-根据图片素材ID
     * @param weixinMessageDTO, request
     * @return respMessage
     */
    private String getImageRespMessage(WeixinMessageDTO weixinMessageDTO, HttpServletRequest request) {
        //TODO
        String respMessage = "";
        return respMessage;
    }

    /**
     * 视频素材消息返回-根据视频素材ID
     * @param weixinMessageDTO, request
     * @return respMessage
     */
    private String getVideoRespMessage(WeixinMessageDTO weixinMessageDTO, HttpServletRequest request) {
        //TODO
        String respMessage = "";
        return respMessage;
    }

    /**
     * 文本素材消息返回，根据文本素材ID
     * @param weixinMessageDTO
     * @return
     */
    private String getTextRespMessage(WeixinMessageDTO weixinMessageDTO){
        String respMessage = "";
        //根据TemplateID 查询数据返回TemplateContent文本内容
    //    WeixinTexttemplate weixinTexttemplate = weixinTexttemplateService.selectWeixinTexttemplateById(weixinMessageDTO.getTemplateId());
        WeixinTexttemplate texttemplate = weixinTexttemplateService.findTexttemplateByTemplateId(weixinMessageDTO.getTemplateId());
        TextMessageResp textMessage = new TextMessageResp();
        if(texttemplate != null){
            String content = texttemplate.getTemplateContent();
            textMessage.setContent(content);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setFromUserName(weixinMessageDTO.getToUserName());
            textMessage.setToUserName(weixinMessageDTO.getFromUserName());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            //把文本素材转换xml返回
            respMessage = MessageUtil.textMessageToXml(textMessage);
        }
        return respMessage;
    }

    /**
     * 遍历关键字管理中是否存在用户输入的关键字信息
     * @param content
     * @param toUserName
     * @return
     */
    private WeixinAutoresponse findKey(String content,String toUserName){
        WeixinAutoresponse autoresponse = new WeixinAutoresponse();
        autoresponse.setJwid(toUserName);
        List<WeixinAutoresponse> autoresponses  = weixinAutoresponseDefaultService.selectWeixinAutoresponseList(autoresponse);
        for(WeixinAutoresponse r : autoresponses){
            //关键字支持模糊匹配功能
            //全匹配
            if("1".equals(r.getKeywordType())){
                //如果包含关键字
                String kw = r.getKeyword();
                //通过写法，页面支持中文逗号
                kw = kw.replace("，",",");
                String[] allkw = kw.split(",");
                for(String k : allkw){
                    if(k.equalsIgnoreCase(content)){
                        return r;
                    }
                }
                //模糊匹配
            }else if("2".equals(r.getKeywordType())){
                String kw = r.getKeyword();
                //通用写法，页面支持中文逗号
                kw = kw.replace("，",",");
                String[] allkw = kw.split(",");
                for(String k : allkw){
                    Pattern p = Pattern.compile(".*?\"+k+\".*?");
                    if(p.matcher(content).matches()){
                        return r;
                    }
                }
            }
        }
        return null;
    }

    private void saveReceiveMessage(Map<String,String> requestMap){
        try{
            //消息类型
            String msgType = requestMap.get("MsgType");
            //拼装消息存储
            WeixinReceivetext weixinReceivetext = new WeixinReceivetext();
            weixinReceivetext.setCreateTime(new Date());
            weixinReceivetext.setFromUserName(requestMap.get("FromUserName"));
            weixinReceivetext.setToUserName(requestMap.get("ToUserName"));
            weixinReceivetext.setMsgId(requestMap.get("MsgId"));
            weixinReceivetext.setMsgType(msgType);
            weixinReceivetext.setJwid(requestMap.get("ToUserName"));
            weixinReceivetext.setMediaId(requestMap.get("MediaId"));
            //根据不同类型存储Conten不统一改为存储JSON格式
            Map<String,Object> map = new HashMap<>();
            boolean contentFlag = false;
            //微信触发类型：文本消息
            if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
                weixinReceivetext.setContent(requestMap.get("Content"));
                contentFlag = true;
            }

            //【微信触发类型】图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                map.put("PicUrl", requestMap.get("PicUrl"));
            }
            //【微信触发类型】地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                map.put("Location_X", requestMap.get("Location_X"));
                map.put("Location_Y", requestMap.get("Location_Y"));
                map.put("Scale", requestMap.get("Scale"));
                map.put("Label", requestMap.get("Label"));
            }
            //【微信触发类型】链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                map.put("Title", requestMap.get("Title"));
                map.put("Description", requestMap.get("Description"));
                map.put("Url", requestMap.get("Url"));
            }
            //【微信触发类型】音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                map.put("Format", requestMap.get("Format"));
                map.put("Recognition", requestMap.get("Recognition"));
            }
            //【微信触发类型】视频消息
            else if (msgType.equals(MessageUtil.RESP_MESSAGE_TYPE_VIDEO)) {
                map.put("ThumbMediaId", requestMap.get("ThumbMediaId"));
            }
            //【微信触发类型】小视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                map.put("ThumbMediaId", requestMap.get("ThumbMediaId"));
            }
            //如果不是文本消息则转换JSON存储
            if(!contentFlag) {
                JSONObject jsonObject = JSONObject.fromObject(map);
                weixinReceivetext.setContent(jsonObject.toString());
            }
            weixinReceivetextService.insertWeixinReceivetext(weixinReceivetext);
        }catch (Exception e){
            e.printStackTrace();
            log.error("-----消息存储异常------"+e.toString());
        }
    }
}

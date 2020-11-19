package com.ss.wx.controller;

import com.jeecg.p3.core.annotation.SkipAuth;
import com.jeecg.p3.core.enums.SkipPerm;
import com.ss.utils.WeixinHttpUtil;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinNewsitem;
import com.ss.wx.domain.WeixinNewstemplate;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.wx.service.IWeixinNewsitemService;
import com.ss.wx.service.IWeixinNewstemplateService;
import org.apache.velocity.VelocityContext;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/news")
public class WeixinNewsController {
    public final static Logger log = LoggerFactory.getLogger(WeixinNewsController.class);
    @Autowired
    private IWeixinNewsitemService newsitemService;
    @Autowired
    private IWeixinNewstemplateService newstemplateService;
    @Autowired
    private IJwWebJwidService jwidService;

    /**
     * 转向微信文章信息页面
     * @param request
     * @param response
     * @throws Exception
     */
    @SkipAuth(auth= SkipPerm.SKIP_SIGN)
    @RequestMapping(value="/goContent",method = {RequestMethod.GET,RequestMethod.POST})
    public String goContent(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)throws Exception{
    //    VelocityContext context = new VelocityContext();
        String id = request.getParameter("id");
        WeixinNewsitem newsItem = newsitemService.selectWeixinNewsitemById(id);
        WeixinNewstemplate weixinNewstemplate = newstemplateService.selectWeixinNewstemplateById(newsItem.getNewstemplateId());
        mmap.put("newsItem",newsItem);
        String jwid = weixinNewstemplate.getJwid();
        mmap.put("jwid",jwid);
        JwWebJwid jwWebJwid = jwidService.queryByJwid(jwid);
        mmap.put("jwWebJwid",jwWebJwid);
        //设置分享后用户点击的url
        StringBuffer requestURL = request.getRequestURL();
        StringBuffer tempContextUrl = requestURL.delete(requestURL.length()-request.getRequestURI().length(),requestURL.length()).append("");
        String basePath = tempContextUrl.toString()+request.getContextPath();
        String url = basePath + "/news/goContent.do?id="+id+"&jwid="+jwid;
        mmap.put("url",url);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createTime = sdf.format(newsItem.getCreateTime());
        //图文分享
        mmap.put("domain",basePath);
        mmap.put("appid",jwWebJwid.getWeixinAppid());
        mmap.put("nonceStr", WeixinHttpUtil.nonceStr);
        mmap.put("timestamp",WeixinHttpUtil.timestamp);
        mmap.put("signature",WeixinHttpUtil.getRedisSignature(request,jwid));
    //    String viewName = "wx/news/newsContent.html";
    //    ViewVelocity.view(request,response,viewName,context);
        return "content/wx/news/newsContent";
    }
}

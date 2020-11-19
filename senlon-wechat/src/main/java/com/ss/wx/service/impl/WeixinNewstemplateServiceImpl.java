package com.ss.wx.service.impl;

import java.io.File;
import java.util.*;

import com.ss.common.config.Global;
import com.ss.common.utils.DateUtils;
import com.ss.properties.CommonWeixinProperties;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WeixinUtil;
import com.ss.utils.WxErrCodeUtil;
import com.ss.wx.domain.*;
import com.ss.wx.mapper.WeixinNewsitemMapper;
import net.sf.json.JSONObject;
import org.jeewx.api.core.common.WxstoreUtils;
import org.jeewx.api.wxsendmsg.JwSendMessageAPI;
import org.jeewx.api.wxsendmsg.util.ReadImgUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinNewstemplateMapper;
import com.ss.wx.service.IWeixinNewstemplateService;
import com.ss.common.core.text.Convert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 图文模板Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-09
 */
@Service
public class WeixinNewstemplateServiceImpl implements IWeixinNewstemplateService 
{
    private static final String upload_group_news_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
    private static final String add_lasting_material_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=TYPE";

    @Autowired
    private WeixinNewstemplateMapper weixinNewstemplateMapper;
    @Autowired
    private WeixinNewsitemMapper newsitemMapper;

    /**
     * 查询图文模板
     * 
     * @param id 图文模板ID
     * @return 图文模板
     */
    @Override
    public WeixinNewstemplate selectWeixinNewstemplateById(String id)
    {
        return weixinNewstemplateMapper.selectWeixinNewstemplateById(id);
    }

    /**
     * 查询图文模板列表
     * 
     * @param weixinNewstemplate 图文模板
     * @return 图文模板
     */
    @Override
    public List<WeixinNewstemplate> selectWeixinNewstemplateList(WeixinNewstemplate weixinNewstemplate)
    {
        return weixinNewstemplateMapper.selectWeixinNewstemplateList(weixinNewstemplate);
    }

    /**
     * 新增图文模板
     * 
     * @param weixinNewstemplate 图文模板
     * @return 结果
     */
    @Override
    public int insertWeixinNewstemplate(WeixinNewstemplate weixinNewstemplate)
    {
        String id = UUID.randomUUID().toString().replace("-", "");
        weixinNewstemplate.setId(id);
        weixinNewstemplate.setTemplateType("news");
        weixinNewstemplate.setCreateTime(new Date());
        weixinNewstemplate.setUploadType("0");
        weixinNewstemplate.setCreateTime(DateUtils.getNowDate());
        return weixinNewstemplateMapper.insertWeixinNewstemplate(weixinNewstemplate);
    }

    /**
     * 修改图文模板
     * 
     * @param weixinNewstemplate 图文模板
     * @return 结果
     */
    @Override
    public int updateWeixinNewstemplate(WeixinNewstemplate weixinNewstemplate)
    {
        weixinNewstemplate.setUpdateTime(DateUtils.getNowDate());
        return weixinNewstemplateMapper.updateWeixinNewstemplate(weixinNewstemplate);
    }

    /**
     * 删除图文模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinNewstemplateByIds(String ids)
    {
        return weixinNewstemplateMapper.deleteWeixinNewstemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除图文模板信息
     * 
     * @param id 图文模板ID
     * @return 结果
     */
    @Override
    public int deleteWeixinNewstemplateById(String id)
    {
        return weixinNewstemplateMapper.deleteWeixinNewstemplateById(id);
    }

    @Override
    public List<WeixinNewstemplate> queryNewstemplateList(String jwid) {
        return weixinNewstemplateMapper.queryNewstemplateList(jwid);
    }

    /**
     * 上传图文素材
     * @param id
     * @param jwWebJwid
     * @return
     */
    @Override
    public String uploadNewsTemplate(String id, JwWebJwid jwWebJwid) {
        String message = "";
        //根据id查询图文模板信息
    //    WeixinNewstemplate weixinNewstemplate = weixinNewstemplateMapper.selectWeixinNewstemplateById(id);
        WeixinNewstemplate weixinNewstemplate = weixinNewstemplateMapper.selectNewstemplateByTemplateId(id);
        WeixinNewsitem newsitem = new WeixinNewsitem();
        newsitem.setNewstemplateId(weixinNewstemplate.getTemplateId());
        //根据图文模板id查询图文素材列表
        List<WeixinNewsitem> weixinNewsitems = newsitemMapper.selectWeixinNewsitemList(newsitem);
        if(weixinNewsitems.size()>0){
            try{
                //模板素材上传中
                weixinNewstemplate.setUploadType("1");
                weixinNewstemplateMapper.updateWeixinNewstemplate(weixinNewstemplate);
                List<BaseGraphic> graphicList = new ArrayList<>();
                for(WeixinNewsitem item : weixinNewsitems){
                    BaseGraphic graphic = new BaseGraphic();
                    graphic.setAuthor(item.getAuthor());
                    graphic.setContent(this.updateContent(item.getContent(),jwWebJwid));
                    graphic.setDigest(item.getDescription());
                    graphic.setContent_source_url(item.getUrl());
                    graphic.setTitle(item.getTitle());
                    graphic.setShow_cover_pic(item.getShowCoverPic());
                    graphic.setThumb_media_id(this.uploadPic(item.getImagePath(),jwWebJwid));
                    graphicList.add(graphic);
                    //更新图文素材信息
                    item.setThumbMediaId(graphic.getThumb_media_id());
                    item.setContent(graphic.getContent());
                    newsitemMapper.updateWeixinNewsitem(item);
                }
                UploadGraphic uploadGraphic = new UploadGraphic();
                uploadGraphic.setArticles(graphicList);
                JSONObject jsonObj = uploadGroupNewsTemplate(uploadGraphic,jwWebJwid);
                if(jsonObj.containsKey("media_id")){
                    message = "图文素材上传成功!";
                    weixinNewstemplate.setUploadType("2");
                    weixinNewstemplate.setUploadTime(new Date());
                    weixinNewstemplate.setMediaId(jsonObj.getString("media_id"));
                    weixinNewstemplateMapper.updateWeixinNewstemplate(weixinNewstemplate);
                }else{
                    weixinNewstemplate.setUploadType("3");
                    weixinNewstemplateMapper.updateWeixinNewstemplate(weixinNewstemplate);
                    String msg = WxErrCodeUtil.testErrCode(jsonObj.getString("errcode"));
                    message = "图文素材上传失败"+msg;
                }
            }catch(Exception e){
                e.printStackTrace();
                message = "图文素材上传失败";
                weixinNewstemplate.setUploadType("3");
                weixinNewstemplateMapper.updateWeixinNewstemplate(weixinNewstemplate);
            }
        }
        return message;
    }

    //上传图文素材
    private JSONObject uploadGroupNewsTemplate(UploadGraphic uploadGraphic, JwWebJwid jwWebJwid) {
        Map<String, Object> token = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        String accessToken = token.get("accessToken").toString();
        if(accessToken != null){
            //TODO
        //    String requestUrl = add_lasting_material_url.replace("ACCESS_TOKEN",accessToken).replace("TYPE","image");
            String requestUrl = upload_group_news_url.replace("ACCESS_TOKEN",accessToken);
            JSONObject jsonObj = JSONObject.fromObject(uploadGraphic);
            JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "POST", jsonObj.toString());
            return jsonObject;
        }
        return null;
    }

    //图片上传微信服务器
    private String uploadPic(String imagePath, JwWebJwid jwWebJwid) {
        String media_id = "";
        Map<String, Object> token = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        String accessToken = token.get("accessToken").toString();
    //    JSONObject jsonObj = WeixinUtil.sendMediaByUrl("image", imagePath, accessToken);
        imagePath = Global.getProfile() + imagePath.substring(imagePath.lastIndexOf("/profile")+8);
        JSONObject jsonObj = WeixinUtil.uploadMedia(new File(imagePath), accessToken, "image");
        if(jsonObj!=null){
            if(jsonObj.containsKey("errcode")){
                String msg = WxErrCodeUtil.testErrCode(jsonObj.getString("errcode"));
                System.out.println("图片同步微信服务器失败!"+msg);
            }else{
                System.out.println("图片同步微信服务器成功");
                media_id = jsonObj.getString("media_id");
            }
        }
        return media_id;
    }


    //替换微信图文
    private String updateContent(String content,JwWebJwid jwid) {
        if(content != null){
            //获取token方法替换
            Map<String, Object> map = AccessTokenUtil.getAccseeToken(jwid.getWeixinAppid(), jwid.getWeixinAppsecret());
            String accessToken = map.get("accessToken").toString();
            HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String baseImageUrl=request.getSession().getServletContext().getRealPath("/");
            String[] urls = ReadImgUrls.getImgs(content);
            if(urls!=null){
                for(String url : urls){
                    if(url.indexOf("mmbiz.gpic.cn")!=-1){
                        continue;
                    }
                    String relativeImgUrl = url.replace(CommonWeixinProperties.domain,"");
                    String tempimgurl = "";
                    if(relativeImgUrl.startsWith("http")){
                        tempimgurl = relativeImgUrl;
                    }else{
                        tempimgurl = baseImageUrl + relativeImgUrl;
                    }
                    JSONObject jsonObj = JwSendMessageAPI.uploadImgReturnObj(accessToken,tempimgurl);
                    if(jsonObj!=null && jsonObj.containsKey("url")){
                        String newUrl = jsonObj.getString("url");
                        content = content.replace(url,newUrl);
                        System.out.println("正文图片"+relativeImgUrl+"同步微信成功!\r\n");
                    }else{
                        System.out.println("正文图片"+relativeImgUrl+"同步微信成功!\r\n");
                    }
                }
            }
        }
        return content;
    }

    @Override
    public WeixinNewstemplate selectNewstemplateByTemplateId(String templateId) {
        return weixinNewstemplateMapper.selectNewstemplateByTemplateId(templateId);
    }
}

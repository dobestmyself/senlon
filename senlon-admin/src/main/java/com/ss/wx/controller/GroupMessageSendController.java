package com.ss.wx.controller;

import com.ss.framework.util.ShiroUtils;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.domain.WeixinTag;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.wx.service.IWeixinTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/wx/groupmessage")
public class GroupMessageSendController {
    @Autowired
    private IWeixinTagService tagService;
    @Autowired
    private IJwWebJwidService jwidService;

    @RequestMapping("/goSendPage")
    public String goGroupMessageSend(ModelMap mmap){
        String createBy = ShiroUtils.getSysUser().getUserName();
        JwWebJwid jwWebJwid = jwidService.queryOneByCreateBy(createBy);
        WeixinTag tag = new WeixinTag();
        tag.setJwid(jwWebJwid.getJwid());
        List<WeixinTag> tags = tagService.selectWeixinTagList(tag);
        mmap.put("tags",tags);
        return "wx/groupmessage/groupmessagesend";
    }
}

package com.ss.wx.web.core;

import com.ss.aes.WXBizMsgCrypt;
import com.ss.util.WeixinParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CpWeixinController {

    private static Logger log = LoggerFactory.getLogger(CpWeixinController.class);

    @GetMapping("/callback")
    public void cpWxGet(HttpServletRequest request, HttpServletResponse response)throws Exception {
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WeixinParamsUtil.sToken,WeixinParamsUtil.SEncodingAESKey,WeixinParamsUtil.corpId);
        String signature = request.getParameter("msg_signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        String sEchoStr;
        try{
            sEchoStr = wxcpt.VerifyURL(signature,timestamp,nonce,echostr);
            System.out.println("verifyurl echostr:"+sEchoStr);
            response.getWriter().print(sEchoStr);
        }catch (Exception e){
            e.printStackTrace();
            log.info("接口调试错误{}",e);
        }
    }

    /*@RequestMapping(value = "/cpwx",method = RequestMethod.POST)
    @ResponseBody
    public String cpWxPost(HttpServletRequest request,
                           @RequestParam(name="msg_signature") String msgsignature,
                           @RequestParam(name="timestamp") String timestamp,
                           @RequestParam(name="nonce") String nonce,
                           HttpServletResponse response)throws Exception{

        try{

            InputStream inputStream = request.getInputStream();
            String sPostData = IOUtils.toString(inputStream,"UTF-8");

            WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WeixinParamsUtil.sToken,WeixinParamsUtil.SEncodingAESKey,WeixinParamsUtil.corpId);
            String sMsg = wxcpt.DecryptMsg(msgsignature,timestamp,nonce,sPostData);
            System.out.println("after decrypt msg:"+sMsg);
            //将post数据转换为map
            Map<String, String> map = MessageUtil.parseXml(request);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "success";
    }*/
}

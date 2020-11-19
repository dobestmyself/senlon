package com.ss.util;

import com.alibaba.fastjson.JSONObject;
import com.ss.utils.JedisPoolUtil;
import com.ss.utils.WeixinHttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class AccessTokenUtil {
    private final static Logger log = LoggerFactory.getLogger(AccessTokenUtil.class);
    private final static String get_access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ID&corpsecret=SECRET";

    public static Map<String,Object> getAccessToken(String corpid, String corpsecret){
        Map<String,Object> map = new HashMap<>();
        try{
            String url = get_access_token_url.replace("ID",corpid).replace("SECRET",corpsecret);
            JSONObject jsonObject = WeixinHttpUtil.sendGet(url);
            String accessToken = "";
            if(jsonObject != null){
                if(jsonObject.containsKey("access_token")){
                    accessToken = jsonObject.getString("access_token");
                    map.put("accessToken",accessToken);
                    map.put("accessTokenTime",new Date().getTime());
                    JedisPoolUtil.setObject("data",map);
                    System.out.println("___________________"+JedisPoolUtil.getObject("data"));
                }
            }else{
                if(!"0".equals(jsonObject.get("errcode").toString())){
                    log.info("AccessToken request error={}.",new Object[]{jsonObject.getString("errmsg")});

                }
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            log.info("AccessToken error={}.",new Object[]{e});
        }
        return null;
    }
}

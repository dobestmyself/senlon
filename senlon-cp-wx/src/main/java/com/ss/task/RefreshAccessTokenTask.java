package com.ss.task;

import com.ss.common.utils.StringUtils;
import com.ss.domain.AccessToken;
import com.ss.util.AccessTokenUtil;
import com.ss.util.WeixinParamsUtil;
import com.ss.utils.JedisPoolUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
@EnableScheduling
public class RefreshAccessTokenTask {
    public final static Logger log = LoggerFactory.getLogger(RefreshAccessTokenTask.class);

    @Scheduled(cron="0 0/10 * * * ?")
    public void run(){
        log.info("___________________重置公众号AccessToken定时任务开启___________________");
        long start = System.currentTimeMillis();
        try{
            Map<String,Object> map = (Map<String,Object>)JedisPoolUtil.getObject("data");
            if(map == null || map.isEmpty()){
                Map<String,Object> objectMap = AccessTokenUtil.getAccessToken(WeixinParamsUtil.corpId, WeixinParamsUtil.secret);
                objectMap.put("accessTokenTime",new Date().getTime());
                JedisPoolUtil.setObject("data",objectMap);
                log.info("___________________重置AccessToken成功___________________"+JedisPoolUtil.getObject("data"));
            }else if(map != null){
                long tokenTime = (Long)map.get("accessTokenTime");
                if(tokenTime - new Date().getTime() >= 7000*1000){
                    Map<String,Object> objectMap = AccessTokenUtil.getAccessToken(WeixinParamsUtil.corpId, WeixinParamsUtil.secret);
                    objectMap.put("accessTokenTime",new Date().getTime());
                    JedisPoolUtil.setObject("data",objectMap);
                    log.info("___________________重置AccessToken成功___________________"+JedisPoolUtil.getObject("data"));
                }
            }


        }catch (Exception e){
            log.info("重置AccessToken定时任务异常e={}",new Object[]{e});
        }
        log.info("___________________重置AccseeToken定时任务结束，用时={}ms.___________________",new Object[]{System.currentTimeMillis()-start});
    }

}

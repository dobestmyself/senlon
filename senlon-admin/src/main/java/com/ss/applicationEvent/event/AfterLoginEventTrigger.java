package com.ss.applicationEvent.event;

import com.alibaba.fastjson.JSON;
import com.ss.applicationEvent.ApplicationEvent;
import com.ss.applicationEvent.ApplicationEventDefined;
import com.ss.applicationEvent.IApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 用户登录后触发
 */
@ApplicationEvent({ApplicationEventDefined.ON_AFTER_LOGIN})
@Component
public class AfterLoginEventTrigger implements IApplicationEvent {
    @Override
    public void onTrigger(Object source, Object params) {
        System.out.println("用户登陆后：系统任务被触发："+source.toString()+"\t\t"+ JSON.toJSONString(params));

    }
}
package com.ss.wx.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ss.common.utils.DateUtils;
import com.ss.common.utils.Guid;
import com.ss.wx.domain.WeixinTexttemplate;
import com.ss.wx.mapper.WeixinTexttemplateMapper;
import com.ss.wx.vo.GroupSendMessageCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinGroupMessageSendLogMapper;
import com.ss.wx.domain.WeixinGroupMessageSendLog;
import com.ss.wx.service.IWeixinGroupMessageSendLogService;
import com.ss.common.core.text.Convert;

/**
 * 群发消息日志Service业务层处理
 * 
 * @author shishuai
 * @date 2020-11-10
 */
@Service
public class WeixinGroupMessageSendLogServiceImpl implements IWeixinGroupMessageSendLogService 
{
    @Autowired
    private WeixinGroupMessageSendLogMapper weixinGroupMessageSendLogMapper;
    @Autowired
    private WeixinTexttemplateMapper texttemplateMapper;

    /**
     * 查询群发消息日志
     * 
     * @param id 群发消息日志ID
     * @return 群发消息日志
     */
    @Override
    public WeixinGroupMessageSendLog selectWeixinGroupMessageSendLogById(String id)
    {
        return weixinGroupMessageSendLogMapper.selectWeixinGroupMessageSendLogById(id);
    }

    /**
     * 查询群发消息日志列表
     * 
     * @param weixinGroupMessageSendLog 群发消息日志
     * @return 群发消息日志
     */
    @Override
    public List<GroupSendMessageCheckVo> selectWeixinGroupMessageSendLogList(WeixinGroupMessageSendLog weixinGroupMessageSendLog)
    {
        List<WeixinGroupMessageSendLog> weixinGroupMessageSendLogs = weixinGroupMessageSendLogMapper.selectWeixinGroupMessageSendLogList(weixinGroupMessageSendLog);
        List<GroupSendMessageCheckVo> voList = new ArrayList<>();
        if(weixinGroupMessageSendLogs != null && weixinGroupMessageSendLogs.size() > 0){
            for(WeixinGroupMessageSendLog messageSendLog : weixinGroupMessageSendLogs){
                GroupSendMessageCheckVo messageCheckVo = new GroupSendMessageCheckVo();
                messageCheckVo.setId(messageSendLog.getId());
                messageCheckVo.setCreateBy(messageSendLog.getCreateBy());
                messageCheckVo.setIsToAll(messageSendLog.getIsToAll());
                messageCheckVo.setMsgType(messageSendLog.getMsgType());
                messageCheckVo.setCheckStatus(messageSendLog.getAuditStatus());
                messageCheckVo.setCheckTime(messageSendLog.getAuditDate());
                WeixinTexttemplate texttemplate = texttemplateMapper.findTexttemplateByTemplateId(messageSendLog.getTemplateId());
                if(texttemplate != null && !"".equals(texttemplate)){
                    messageCheckVo.setTemplateName(texttemplate.getTemplateName());
                }
                voList.add(messageCheckVo);
            }
        }
        return voList;
    }

    /**
     * 新增群发消息日志
     * 
     * @param weixinGroupMessageSendLog 群发消息日志
     * @return 结果
     */
    @Override
    public int insertWeixinGroupMessageSendLog(WeixinGroupMessageSendLog weixinGroupMessageSendLog)
    {
        weixinGroupMessageSendLog.setId(UUID.randomUUID().toString().replace("-",""));
        weixinGroupMessageSendLog.setCreateTime(DateUtils.getNowDate());
        return weixinGroupMessageSendLogMapper.insertWeixinGroupMessageSendLog(weixinGroupMessageSendLog);
    }

    /**
     * 修改群发消息日志
     * 
     * @param weixinGroupMessageSendLog 群发消息日志
     * @return 结果
     */
    @Override
    public int updateWeixinGroupMessageSendLog(WeixinGroupMessageSendLog weixinGroupMessageSendLog)
    {
        return weixinGroupMessageSendLogMapper.updateWeixinGroupMessageSendLog(weixinGroupMessageSendLog);
    }

    /**
     * 删除群发消息日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinGroupMessageSendLogByIds(String ids)
    {
        return weixinGroupMessageSendLogMapper.deleteWeixinGroupMessageSendLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除群发消息日志信息
     * 
     * @param id 群发消息日志ID
     * @return 结果
     */
    @Override
    public int deleteWeixinGroupMessageSendLogById(String id)
    {
        return weixinGroupMessageSendLogMapper.deleteWeixinGroupMessageSendLogById(id);
    }


    @Override
    public List<WeixinGroupMessageSendLog> selectGroupMessageSendLogList(WeixinGroupMessageSendLog weixinGroupMessageSendLog) {
        return weixinGroupMessageSendLogMapper.selectWeixinGroupMessageSendLogList(weixinGroupMessageSendLog);
    }
}

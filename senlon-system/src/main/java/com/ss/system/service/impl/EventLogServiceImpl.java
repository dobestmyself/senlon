package com.ss.system.service.impl;

import com.ss.common.core.text.Convert;
import com.ss.common.utils.DateUtils;
import com.ss.system.domain.EventLog;
import com.ss.system.mapper.EventLogMapper;
import com.ss.system.service.IEventLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 事件日志Service业务层处理
 * 
 * @author shishuai
 * @date 2019-12-30
 */
@Service
public class EventLogServiceImpl implements IEventLogService 
{
    @Autowired
    private EventLogMapper eventLogMapper;

    /**
     * 查询事件日志
     * 
     * @param id 事件日志ID
     * @return 事件日志
     */
    @Override
    public EventLog selectEventLogById(Long id)
    {
        return eventLogMapper.selectEventLogById(id);
    }

    /**
     * 查询事件日志列表
     * 
     * @param eventLog 事件日志
     * @return 事件日志
     */
    @Override
    public List<EventLog> selectEventLogList(EventLog eventLog)
    {
        return eventLogMapper.selectEventLogList(eventLog);
    }

    /**
     * 新增事件日志
     * 
     * @param eventLog 事件日志
     * @return 结果
     */
    @Override
    public int insertEventLog(EventLog eventLog)
    {
        eventLog.setCreateTime(DateUtils.getNowDate());
        return eventLogMapper.insertEventLog(eventLog);
    }

    /**
     * 修改事件日志
     * 
     * @param eventLog 事件日志
     * @return 结果
     */
    @Override
    public int updateEventLog(EventLog eventLog)
    {
        return eventLogMapper.updateEventLog(eventLog);
    }

    /**
     * 删除事件日志对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEventLogByIds(String ids)
    {
        return eventLogMapper.deleteEventLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除事件日志信息
     * 
     * @param id 事件日志ID
     * @return 结果
     */
    @Override
    public int deleteEventLogById(Long id)
    {
        return eventLogMapper.deleteEventLogById(id);
    }
}

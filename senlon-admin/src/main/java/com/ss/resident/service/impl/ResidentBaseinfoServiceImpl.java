package com.ss.resident.service.impl;

import com.ss.common.core.text.Convert;
import com.ss.resident.domain.ResidentBaseinfo;
import com.ss.resident.mapper.ResidentBaseinfoMapper;
import com.ss.resident.service.IResidentBaseinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 住院医师基本信息Service业务层处理
 * 
 * @author shishuai
 * @date 2020-05-27
 */
@Service
public class ResidentBaseinfoServiceImpl implements IResidentBaseinfoService 
{
    @Autowired
    private ResidentBaseinfoMapper residentBaseinfoMapper;

    /**
     * 查询住院医师基本信息
     * 
     * @param residentId 住院医师基本信息ID
     * @return 住院医师基本信息
     */
    @Override
    public ResidentBaseinfo selectResidentBaseinfoById(Long residentId)
    {
        return residentBaseinfoMapper.selectResidentBaseinfoById(residentId);
    }

    /**
     * 查询住院医师基本信息列表
     * 
     * @param residentBaseinfo 住院医师基本信息
     * @return 住院医师基本信息
     */
    @Override
    public List<ResidentBaseinfo> selectResidentBaseinfoList(ResidentBaseinfo residentBaseinfo)
    {
        return residentBaseinfoMapper.selectResidentBaseinfoList(residentBaseinfo);
    }

    /**
     * 新增住院医师基本信息
     * 
     * @param residentBaseinfo 住院医师基本信息
     * @return 结果
     */
    @Override
    public int insertResidentBaseinfo(ResidentBaseinfo residentBaseinfo)
    {
        return residentBaseinfoMapper.insertResidentBaseinfo(residentBaseinfo);
    }

    /**
     * 修改住院医师基本信息
     * 
     * @param residentBaseinfo 住院医师基本信息
     * @return 结果
     */
    @Override
    public int updateResidentBaseinfo(ResidentBaseinfo residentBaseinfo)
    {
        return residentBaseinfoMapper.updateResidentBaseinfo(residentBaseinfo);
    }

    /**
     * 删除住院医师基本信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteResidentBaseinfoByIds(String ids)
    {
        return residentBaseinfoMapper.deleteResidentBaseinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除住院医师基本信息信息
     * 
     * @param residentId 住院医师基本信息ID
     * @return 结果
     */
    @Override
    public int deleteResidentBaseinfoById(Long residentId)
    {
        return residentBaseinfoMapper.deleteResidentBaseinfoById(residentId);
    }
}

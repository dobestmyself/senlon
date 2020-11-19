package com.ss.resident.mapper;

import com.ss.resident.domain.ResidentBaseinfo;

import java.util.List;

/**
 * 住院医师基本信息Mapper接口
 * 
 * @author shishuai
 * @date 2020-05-27
 */
public interface ResidentBaseinfoMapper 
{
    /**
     * 查询住院医师基本信息
     * 
     * @param residentId 住院医师基本信息ID
     * @return 住院医师基本信息
     */
    public ResidentBaseinfo selectResidentBaseinfoById(Long residentId);

    /**
     * 查询住院医师基本信息列表
     * 
     * @param residentBaseinfo 住院医师基本信息
     * @return 住院医师基本信息集合
     */
    public List<ResidentBaseinfo> selectResidentBaseinfoList(ResidentBaseinfo residentBaseinfo);

    /**
     * 新增住院医师基本信息
     * 
     * @param residentBaseinfo 住院医师基本信息
     * @return 结果
     */
    public int insertResidentBaseinfo(ResidentBaseinfo residentBaseinfo);

    /**
     * 修改住院医师基本信息
     * 
     * @param residentBaseinfo 住院医师基本信息
     * @return 结果
     */
    public int updateResidentBaseinfo(ResidentBaseinfo residentBaseinfo);

    /**
     * 删除住院医师基本信息
     * 
     * @param residentId 住院医师基本信息ID
     * @return 结果
     */
    public int deleteResidentBaseinfoById(Long residentId);

    /**
     * 批量删除住院医师基本信息
     * 
     * @param residentIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteResidentBaseinfoByIds(String[] residentIds);
}

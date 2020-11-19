package com.ss.resident.service;

import com.ss.resident.domain.ResidentEducation;

import java.util.List;

/**
 * 住院医师教育经历Service接口
 * 
 * @author shishuai
 * @date 2020-05-28
 */
public interface IResidentEducationService 
{
    /**
     * 查询住院医师教育经历
     * 
     * @param id 住院医师教育经历ID
     * @return 住院医师教育经历
     */
    public ResidentEducation selectResidentEducationById(Long id);

    /**
     * 查询住院医师教育经历列表
     * 
     * @param residentEducation 住院医师教育经历
     * @return 住院医师教育经历集合
     */
    public List<ResidentEducation> selectResidentEducationList(ResidentEducation residentEducation);

    /**
     * 新增住院医师教育经历
     * 
     * @param residentEducation 住院医师教育经历
     * @return 结果
     */
    public int insertResidentEducation(ResidentEducation residentEducation);

    /**
     * 修改住院医师教育经历
     * 
     * @param residentEducation 住院医师教育经历
     * @return 结果
     */
    public int updateResidentEducation(ResidentEducation residentEducation);

    /**
     * 批量删除住院医师教育经历
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteResidentEducationByIds(String ids);

    /**
     * 删除住院医师教育经历信息
     * 
     * @param id 住院医师教育经历ID
     * @return 结果
     */
    public int deleteResidentEducationById(Long id);
}

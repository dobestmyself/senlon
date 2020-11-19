package com.ss.resident.service.impl;

import com.ss.common.core.text.Convert;
import com.ss.resident.domain.ResidentEducation;
import com.ss.resident.mapper.ResidentEducationMapper;
import com.ss.resident.service.IResidentEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 住院医师教育经历Service业务层处理
 * 
 * @author shishuai
 * @date 2020-05-28
 */
@Service
public class ResidentEducationServiceImpl implements IResidentEducationService 
{
    @Autowired
    private ResidentEducationMapper residentEducationMapper;

    /**
     * 查询住院医师教育经历
     * 
     * @param id 住院医师教育经历ID
     * @return 住院医师教育经历
     */
    @Override
    public ResidentEducation selectResidentEducationById(Long id)
    {
        return residentEducationMapper.selectResidentEducationById(id);
    }

    /**
     * 查询住院医师教育经历列表
     * 
     * @param residentEducation 住院医师教育经历
     * @return 住院医师教育经历
     */
    @Override
    public List<ResidentEducation> selectResidentEducationList(ResidentEducation residentEducation)
    {
        return residentEducationMapper.selectResidentEducationList(residentEducation);
    }

    /**
     * 新增住院医师教育经历
     * 
     * @param residentEducation 住院医师教育经历
     * @return 结果
     */
    @Override
    public int insertResidentEducation(ResidentEducation residentEducation)
    {
        return residentEducationMapper.insertResidentEducation(residentEducation);
    }

    /**
     * 修改住院医师教育经历
     * 
     * @param residentEducation 住院医师教育经历
     * @return 结果
     */
    @Override
    public int updateResidentEducation(ResidentEducation residentEducation)
    {
        return residentEducationMapper.updateResidentEducation(residentEducation);
    }

    /**
     * 删除住院医师教育经历对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteResidentEducationByIds(String ids)
    {
        return residentEducationMapper.deleteResidentEducationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除住院医师教育经历信息
     * 
     * @param id 住院医师教育经历ID
     * @return 结果
     */
    @Override
    public int deleteResidentEducationById(Long id)
    {
        return residentEducationMapper.deleteResidentEducationById(id);
    }
}

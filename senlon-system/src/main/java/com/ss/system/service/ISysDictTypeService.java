package com.ss.system.service;

import com.ss.common.core.domain.Ztree;
import com.ss.system.domain.SysDictType;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author shishuai
 */
public interface ISysDictTypeService
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<SysDictType> selectDictTypeAll();

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public SysDictType selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    public SysDictType selectDictTypeByType(String dictType);

    /**
     * 通过字典ID删除字典信息
     * 
     * @param dictId 字典ID
     * @return 结果
     */
    public int deleteDictTypeById(Long dictId);

    /**
     * 批量删除字典类型
     * 
     * @param ids 需要删除的数据
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteDictTypeByIds(String ids) throws Exception;

    /**
     * 新增保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(SysDictType dictType);

    /**
     * 修改保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int updateDictType(SysDictType dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public String checkDictTypeUnique(SysDictType dictType);

    /**
     * 查询字典类型树
     * 
     * @param dictType 字典类型
     * @return 所有字典类型
     */
    public List<Ztree> selectDictTree(SysDictType dictType);

    /**
     * 导入字典类型数据
     * @param dictTypeList 字典类型列表
     * @param updateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName
     * @return
     */
    String importDictType(List<SysDictType> dictTypeList, boolean updateSupport, String operName);
}

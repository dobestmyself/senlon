package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinAutoresponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 关键字Mapper接口
 * 
 * @author shishuai
 * @date 2020-08-05
 */
@Component
public interface WeixinAutoresponseMapper 
{
    /**
     * 查询关键字
     * 
     * @param id 关键字ID
     * @return 关键字
     */
    public WeixinAutoresponse selectWeixinAutoresponseById(String id);

    /**
     * 查询关键字列表
     * 
     * @param weixinAutoresponse 关键字
     * @return 关键字集合
     */
    public List<WeixinAutoresponse> selectWeixinAutoresponseList(WeixinAutoresponse weixinAutoresponse);

    /**
     * 新增关键字
     * 
     * @param weixinAutoresponse 关键字
     * @return 结果
     */
    public int insertWeixinAutoresponse(WeixinAutoresponse weixinAutoresponse);

    /**
     * 修改关键字
     * 
     * @param weixinAutoresponse 关键字
     * @return 结果
     */
    public int updateWeixinAutoresponse(WeixinAutoresponse weixinAutoresponse);

    /**
     * 删除关键字
     * 
     * @param id 关键字ID
     * @return 结果
     */
    public int deleteWeixinAutoresponseById(String id);

    /**
     * 批量删除关键字
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinAutoresponseByIds(String[] ids);
}

package com.ss.wx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinTagMapper;
import com.ss.wx.domain.WeixinTag;
import com.ss.wx.service.IWeixinTagService;
import com.ss.common.core.text.Convert;

/**
 * 粉丝标签Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-13
 */
@Service
public class WeixinTagServiceImpl implements IWeixinTagService 
{
    @Autowired
    private WeixinTagMapper weixinTagMapper;

    /**
     * 查询粉丝标签
     * 
     * @param id 粉丝标签ID
     * @return 粉丝标签
     */
    @Override
    public WeixinTag selectWeixinTagById(String id)
    {
        return weixinTagMapper.selectWeixinTagById(id);
    }

    /**
     * 查询粉丝标签列表
     * 
     * @param weixinTag 粉丝标签
     * @return 粉丝标签
     */
    @Override
    public List<WeixinTag> selectWeixinTagList(WeixinTag weixinTag)
    {
        return weixinTagMapper.selectWeixinTagList(weixinTag);
    }

    /**
     * 新增粉丝标签
     * 
     * @param weixinTag 粉丝标签
     * @return 结果
     */
    @Override
    public int insertWeixinTag(WeixinTag weixinTag)
    {
        String id = UUID.randomUUID().toString().replace("-","");
        weixinTag.setId(id);
        weixinTag.setCreateTime(new Date());
        return weixinTagMapper.insertWeixinTag(weixinTag);
    }

    /**
     * 修改粉丝标签
     * 
     * @param weixinTag 粉丝标签
     * @return 结果
     */
    @Override
    public int updateWeixinTag(WeixinTag weixinTag)
    {
        weixinTag.setUpdateTime(new Date());
        return weixinTagMapper.updateWeixinTag(weixinTag);
    }

    /**
     * 删除粉丝标签对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTagByIds(String ids)
    {
        return weixinTagMapper.deleteWeixinTagByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除粉丝标签信息
     * 
     * @param id 粉丝标签ID
     * @return 结果
     */
    @Override
    public int deleteWeixinTagById(String id)
    {
        return weixinTagMapper.deleteWeixinTagById(id);
    }


    @Override
    public int deleteTagsByJwid(String jwid) {
        return weixinTagMapper.deleteTagsByJwid(jwid);
    }

    @Override
    public List<WeixinTag> selectTagByTagId(String tagid) {
        return weixinTagMapper.selectTagByTagId(tagid);
    }
}

package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinTag;
import java.util.List;

/**
 * 粉丝标签Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-13
 */
public interface WeixinTagMapper 
{
    /**
     * 查询粉丝标签
     * 
     * @param id 粉丝标签ID
     * @return 粉丝标签
     */
    public WeixinTag selectWeixinTagById(String id);

    /**
     * 查询粉丝标签列表
     * 
     * @param weixinTag 粉丝标签
     * @return 粉丝标签集合
     */
    public List<WeixinTag> selectWeixinTagList(WeixinTag weixinTag);

    /**
     * 新增粉丝标签
     * 
     * @param weixinTag 粉丝标签
     * @return 结果
     */
    public int insertWeixinTag(WeixinTag weixinTag);

    /**
     * 修改粉丝标签
     * 
     * @param weixinTag 粉丝标签
     * @return 结果
     */
    public int updateWeixinTag(WeixinTag weixinTag);

    /**
     * 删除粉丝标签
     * 
     * @param id 粉丝标签ID
     * @return 结果
     */
    public int deleteWeixinTagById(String id);

    /**
     * 批量删除粉丝标签
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinTagByIds(String[] ids);

    /**
     * 根据jwid清空该公众号创建的标签
     * @param jwid
     * @return
     */
    public int deleteTagsByJwid(String jwid);

    /**
     * 根据标签id批量查询标签列表
     * @param tagid
     * @return
     */
    public List<WeixinTag> selectTagByTagId(String tagid);


}

package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinGzuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 粉丝Mapper接口
 * 
 * @author shishuai
 * @date 2020-07-15
 */
@Mapper
public interface WeixinGzuserMapper 
{
    /**
     * 查询粉丝
     * 
     * @param id 粉丝ID
     * @return 粉丝
     */
    public WeixinGzuser selectWeixinGzuserById(String id);

    /**
     * 查询粉丝列表
     * 
     * @param weixinGzuser 粉丝
     * @return 粉丝集合
     */
    public List<WeixinGzuser> selectWeixinGzuserList(WeixinGzuser weixinGzuser);

    /**
     * 新增粉丝
     * 
     * @param weixinGzuser 粉丝
     * @return 结果
     */
    public int insertWeixinGzuser(WeixinGzuser weixinGzuser);

    /**
     * 修改粉丝
     * 
     * @param weixinGzuser 粉丝
     * @return 结果
     */
    public int updateWeixinGzuser(WeixinGzuser weixinGzuser);

    /**
     * 删除粉丝
     * 
     * @param id 粉丝ID
     * @return 结果
     */
    public int deleteWeixinGzuserById(String id);

    /**
     * 批量删除粉丝
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinGzuserByIds(String[] ids);

    public WeixinGzuser queryByOpenId(@Param("openid") String openid,@Param("jwid") String jwid);

    /**
     * 根据JWID分页查询粉丝信息
     * @param jwid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<WeixinGzuser> queryNumberByJwid(@Param("jwid")String jwid, @Param("pageNo")int pageNo, @Param("pageSize")int pageSize);

    /**
     * 根据用户id批量修改标签值
     * @param ids
     * @return
     */
    public int batchUpdateWeixinGzuser(String[] ids);

    /**
     * 根据tagId模糊查询用户信息
     * @param tagId
     * @return
     */
    public List<WeixinGzuser> queryGzuserByTagId(@Param("tagidList") String tagidList);

    /**
     * 根据标签id查询在该标签下不存在的粉丝列表
     * @param tagidList
     * @param jwid
     * @return
     */
    public List<WeixinGzuser> selectGzuserByTagIdAndJwid(@Param("tagidList") String tagidList,@Param("jwid") String jwid);

    /**
     * 根据openid修改粉丝的标签
     * @param list
     * @return
     */

    public int updateBatchTagidList(List<WeixinGzuser> list);

}

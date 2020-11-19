package com.ss.wx.service;

import com.ss.wx.domain.WeixinGzuser;

import java.util.List;
import java.util.Map;

/**
 * 粉丝Service接口
 * 
 * @author shishuai
 * @date 2020-07-15
 */
public interface IWeixinGzuserService 
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
     * 批量删除粉丝
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinGzuserByIds(String ids);

    /**
     * 删除粉丝信息
     * 
     * @param id 粉丝ID
     * @return 结果
     */
    public int deleteWeixinGzuserById(String id);

    /**
     * 获取公众号的关注粉丝
     * @param next_openid
     * @param jwid
     * @return
     */
    public String getFansListTask(String next_openid,String jwid,String appid,String appscecret);
    /**
     * 根据JWID分页查询粉丝信息
     * @param jwid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<WeixinGzuser> queryNumberByJwid(String jwid, int pageNo, int pageSize);
    /**
     * 根据微信用户openid和微信公众号，获取微信用户的昵称等信息
     */
    public List<WeixinGzuser> batchGetGzUserInfo(List<Map<String, String>> user_list, String accessToken) throws Exception;

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
    public List<WeixinGzuser> queryGzuserByTagId(String tagId);
    /**
     * 根据标签id查询在该标签下不存在的粉丝列表
     * @param tagId
     * @param jwid
     * @return
     */
    public List<WeixinGzuser> selectGzuserByTagIdAndJwid(String tagId,String jwid);


    public int updateBatchTagidList(List<WeixinGzuser> list);

    public WeixinGzuser getGzUserInfo(String openId,String jwid,String accessToken);
}

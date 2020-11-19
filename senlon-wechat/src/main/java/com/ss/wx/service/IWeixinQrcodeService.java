package com.ss.wx.service;

import com.ss.wx.domain.WeixinQrcode;
import java.util.List;

/**
 * 二维码Service接口
 * 
 * @author shishuai
 * @date 2020-08-06
 */
public interface IWeixinQrcodeService 
{
    /**
     * 查询二维码
     * 
     * @param id 二维码ID
     * @return 二维码
     */
    public WeixinQrcode selectWeixinQrcodeById(String id);

    /**
     * 查询二维码列表
     * 
     * @param weixinQrcode 二维码
     * @return 二维码集合
     */
    public List<WeixinQrcode> selectWeixinQrcodeList(WeixinQrcode weixinQrcode);

    /**
     * 新增二维码
     * 
     * @param weixinQrcode 二维码
     * @return 结果
     */
    public int insertWeixinQrcode(WeixinQrcode weixinQrcode);

    /**
     * 修改二维码
     * 
     * @param weixinQrcode 二维码
     * @return 结果
     */
    public int updateWeixinQrcode(WeixinQrcode weixinQrcode);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinQrcodeByIds(String ids);

    /**
     * 删除二维码信息
     * 
     * @param id 二维码ID
     * @return 结果
     */
    public int deleteWeixinQrcodeById(String id);

    /**
     * 查询数据库中场景值最大值
     * @param
     * @return
     */
    public Integer getScence(String jwid,String actionName);
}

package com.ss.wx.mapper;

import com.alipay.api.domain.InteligentGeneralMerchantPromo;
import com.ss.wx.domain.WeixinQrcode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 二维码Mapper接口
 * 
 * @author shishuai
 * @date 2020-08-06
 */
public interface WeixinQrcodeMapper 
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
     * 删除二维码
     * 
     * @param id 二维码ID
     * @return 结果
     */
    public int deleteWeixinQrcodeById(String id);

    /**
     * 批量删除二维码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinQrcodeByIds(String[] ids);


    /**
     * 查询查询场景值最大值
     * @param
     * @return
     */
    public Integer getScence(@Param("jwid") String jwid,@Param("actionName") String actionName);
}

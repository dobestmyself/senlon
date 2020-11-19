package com.ss.wx.mapper;

import com.ss.wx.domain.WeixinQrcodeScanRecord;
import java.util.List;

/**
 * 二维码扫码记录Mapper接口
 * 
 * @author shishuai
 * @date 2020-08-06
 */
public interface WeixinQrcodeScanRecordMapper 
{
    /**
     * 查询二维码扫码记录
     * 
     * @param id 二维码扫码记录ID
     * @return 二维码扫码记录
     */
    public WeixinQrcodeScanRecord selectWeixinQrcodeScanRecordById(String id);

    /**
     * 查询二维码扫码记录列表
     * 
     * @param weixinQrcodeScanRecord 二维码扫码记录
     * @return 二维码扫码记录集合
     */
    public List<WeixinQrcodeScanRecord> selectWeixinQrcodeScanRecordList(WeixinQrcodeScanRecord weixinQrcodeScanRecord);

    /**
     * 新增二维码扫码记录
     * 
     * @param weixinQrcodeScanRecord 二维码扫码记录
     * @return 结果
     */
    public int insertWeixinQrcodeScanRecord(WeixinQrcodeScanRecord weixinQrcodeScanRecord);

    /**
     * 修改二维码扫码记录
     * 
     * @param weixinQrcodeScanRecord 二维码扫码记录
     * @return 结果
     */
    public int updateWeixinQrcodeScanRecord(WeixinQrcodeScanRecord weixinQrcodeScanRecord);

    /**
     * 删除二维码扫码记录
     * 
     * @param id 二维码扫码记录ID
     * @return 结果
     */
    public int deleteWeixinQrcodeScanRecordById(String id);

    /**
     * 批量删除二维码扫码记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinQrcodeScanRecordByIds(String[] ids);
}

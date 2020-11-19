package com.ss.wx.service;

import com.ss.wx.domain.WeixinQrcodeScanRecord;
import java.util.List;

/**
 * 二维码扫码记录Service接口
 * 
 * @author shishuai
 * @date 2020-08-06
 */
public interface IWeixinQrcodeScanRecordService 
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
     * 批量删除二维码扫码记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWeixinQrcodeScanRecordByIds(String ids);

    /**
     * 删除二维码扫码记录信息
     * 
     * @param id 二维码扫码记录ID
     * @return 结果
     */
    public int deleteWeixinQrcodeScanRecordById(String id);
}

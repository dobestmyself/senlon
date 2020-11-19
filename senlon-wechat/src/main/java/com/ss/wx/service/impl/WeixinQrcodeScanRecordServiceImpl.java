package com.ss.wx.service.impl;

import java.util.List;

import com.ss.common.utils.Guid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinQrcodeScanRecordMapper;
import com.ss.wx.domain.WeixinQrcodeScanRecord;
import com.ss.wx.service.IWeixinQrcodeScanRecordService;
import com.ss.common.core.text.Convert;

/**
 * 二维码扫码记录Service业务层处理
 * 
 * @author shishuai
 * @date 2020-08-06
 */
@Service
public class WeixinQrcodeScanRecordServiceImpl implements IWeixinQrcodeScanRecordService 
{
    @Autowired
    private WeixinQrcodeScanRecordMapper weixinQrcodeScanRecordMapper;

    /**
     * 查询二维码扫码记录
     * 
     * @param id 二维码扫码记录ID
     * @return 二维码扫码记录
     */
    @Override
    public WeixinQrcodeScanRecord selectWeixinQrcodeScanRecordById(String id)
    {
        return weixinQrcodeScanRecordMapper.selectWeixinQrcodeScanRecordById(id);
    }

    /**
     * 查询二维码扫码记录列表
     * 
     * @param weixinQrcodeScanRecord 二维码扫码记录
     * @return 二维码扫码记录
     */
    @Override
    public List<WeixinQrcodeScanRecord> selectWeixinQrcodeScanRecordList(WeixinQrcodeScanRecord weixinQrcodeScanRecord)
    {
        return weixinQrcodeScanRecordMapper.selectWeixinQrcodeScanRecordList(weixinQrcodeScanRecord);
    }

    /**
     * 新增二维码扫码记录
     * 
     * @param weixinQrcodeScanRecord 二维码扫码记录
     * @return 结果
     */
    @Override
    public int insertWeixinQrcodeScanRecord(WeixinQrcodeScanRecord weixinQrcodeScanRecord)
    {
        weixinQrcodeScanRecord.setId(Guid.get());
        return weixinQrcodeScanRecordMapper.insertWeixinQrcodeScanRecord(weixinQrcodeScanRecord);
    }

    /**
     * 修改二维码扫码记录
     * 
     * @param weixinQrcodeScanRecord 二维码扫码记录
     * @return 结果
     */
    @Override
    public int updateWeixinQrcodeScanRecord(WeixinQrcodeScanRecord weixinQrcodeScanRecord)
    {
        return weixinQrcodeScanRecordMapper.updateWeixinQrcodeScanRecord(weixinQrcodeScanRecord);
    }

    /**
     * 删除二维码扫码记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinQrcodeScanRecordByIds(String ids)
    {
        return weixinQrcodeScanRecordMapper.deleteWeixinQrcodeScanRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除二维码扫码记录信息
     * 
     * @param id 二维码扫码记录ID
     * @return 结果
     */
    @Override
    public int deleteWeixinQrcodeScanRecordById(String id)
    {
        return weixinQrcodeScanRecordMapper.deleteWeixinQrcodeScanRecordById(id);
    }
}

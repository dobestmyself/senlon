package com.ss.wx.service.impl;

import java.util.List;
import java.util.UUID;

import com.ss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinQrcodeMapper;
import com.ss.wx.domain.WeixinQrcode;
import com.ss.wx.service.IWeixinQrcodeService;
import com.ss.common.core.text.Convert;

/**
 * 二维码Service业务层处理
 * 
 * @author shishuai
 * @date 2020-08-06
 */
@Service
public class WeixinQrcodeServiceImpl implements IWeixinQrcodeService 
{
    @Autowired
    private WeixinQrcodeMapper weixinQrcodeMapper;

    /**
     * 查询二维码
     * 
     * @param id 二维码ID
     * @return 二维码
     */
    @Override
    public WeixinQrcode selectWeixinQrcodeById(String id)
    {
        return weixinQrcodeMapper.selectWeixinQrcodeById(id);
    }

    /**
     * 查询二维码列表
     * 
     * @param weixinQrcode 二维码
     * @return 二维码
     */
    @Override
    public List<WeixinQrcode> selectWeixinQrcodeList(WeixinQrcode weixinQrcode)
    {
        return weixinQrcodeMapper.selectWeixinQrcodeList(weixinQrcode);
    }

    /**
     * 新增二维码
     * 
     * @param weixinQrcode 二维码
     * @return 结果
     */
    @Override
    public int insertWeixinQrcode(WeixinQrcode weixinQrcode)
    {
        weixinQrcode.setId(UUID.randomUUID().toString().replace("-",""));
        weixinQrcode.setCreateTime(DateUtils.getNowDate());
        return weixinQrcodeMapper.insertWeixinQrcode(weixinQrcode);
    }

    /**
     * 修改二维码
     * 
     * @param weixinQrcode 二维码
     * @return 结果
     */
    @Override
    public int updateWeixinQrcode(WeixinQrcode weixinQrcode)
    {
        weixinQrcode.setUpdateTime(DateUtils.getNowDate());
        return weixinQrcodeMapper.updateWeixinQrcode(weixinQrcode);
    }

    /**
     * 删除二维码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinQrcodeByIds(String ids)
    {
        return weixinQrcodeMapper.deleteWeixinQrcodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除二维码信息
     * 
     * @param id 二维码ID
     * @return 结果
     */
    @Override
    public int deleteWeixinQrcodeById(String id)
    {
        return weixinQrcodeMapper.deleteWeixinQrcodeById(id);
    }

    /**
     * 查询数据库中场景值最大值
     * @param
     * @return
     */
    @Override
    public Integer getScence(String jwid,String actionName) {
        Integer maxScenceKey = 0;
        Integer max = weixinQrcodeMapper.getScence(jwid,actionName);
        if(max == null){
            maxScenceKey = 1;
        }else{
            maxScenceKey = max.intValue()+1;
        }
        //临时二维码Scenceid从100000以后开始
        if(maxScenceKey<=100000 && "QR_SCENE".equals(actionName)){
            maxScenceKey = 100001;
        }
        return maxScenceKey;
    }
}

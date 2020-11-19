package com.ss.wx.service.impl;

import java.util.List;
import com.ss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinReceptMsgMapper;
import com.ss.wx.domain.WeixinReceptMsg;
import com.ss.wx.service.IWeixinReceptMsgService;
import com.ss.common.core.text.Convert;

/**
 * 客服消息记录Service业务层处理
 * 
 * @author shishuai
 * @date 2020-11-06
 */
@Service
public class WeixinReceptMsgServiceImpl implements IWeixinReceptMsgService 
{
    @Autowired
    private WeixinReceptMsgMapper weixinReceptMsgMapper;

    /**
     * 查询客服消息记录
     * 
     * @param id 客服消息记录ID
     * @return 客服消息记录
     */
    @Override
    public WeixinReceptMsg selectWeixinReceptMsgById(String id)
    {
        return weixinReceptMsgMapper.selectWeixinReceptMsgById(id);
    }

    /**
     * 查询客服消息记录列表
     * 
     * @param weixinReceptMsg 客服消息记录
     * @return 客服消息记录
     */
    @Override
    public List<WeixinReceptMsg> selectWeixinReceptMsgList(WeixinReceptMsg weixinReceptMsg)
    {
        return weixinReceptMsgMapper.selectWeixinReceptMsgList(weixinReceptMsg);
    }

    /**
     * 新增客服消息记录
     * 
     * @param weixinReceptMsg 客服消息记录
     * @return 结果
     */
    @Override
    public int insertWeixinReceptMsg(WeixinReceptMsg weixinReceptMsg)
    {
        weixinReceptMsg.setCreateTime(DateUtils.getNowDate());
        return weixinReceptMsgMapper.insertWeixinReceptMsg(weixinReceptMsg);
    }

    /**
     * 修改客服消息记录
     * 
     * @param weixinReceptMsg 客服消息记录
     * @return 结果
     */
    @Override
    public int updateWeixinReceptMsg(WeixinReceptMsg weixinReceptMsg)
    {
        return weixinReceptMsgMapper.updateWeixinReceptMsg(weixinReceptMsg);
    }

    /**
     * 删除客服消息记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinReceptMsgByIds(String ids)
    {
        return weixinReceptMsgMapper.deleteWeixinReceptMsgByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客服消息记录信息
     * 
     * @param id 客服消息记录ID
     * @return 结果
     */
    @Override
    public int deleteWeixinReceptMsgById(String id)
    {
        return weixinReceptMsgMapper.deleteWeixinReceptMsgById(id);
    }
}

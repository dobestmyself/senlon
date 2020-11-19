package com.ss.wx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jeecg.p3.weixinInterface.entity.WeixinAccount;
import com.ss.common.utils.DateUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.JedisPoolUtil;
import org.jeecgframework.p3.core.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.JwWebJwidMapper;
import com.ss.wx.domain.JwWebJwid;
import com.ss.wx.service.IJwWebJwidService;
import com.ss.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统公众号Service业务层处理
 *
 * @author shishuai
 * @date 2020-07-05
 */
@Service
public class JwWebJwidServiceImpl implements IJwWebJwidService {
    private static final Logger log = LoggerFactory.getLogger(JwWebJwidServiceImpl.class);
    @Autowired
    private JwWebJwidMapper jwWebJwidMapper;

    /**
     * 查询系统公众号
     *
     * @param id 系统公众号ID
     * @return 系统公众号
     */
    @Override
    public JwWebJwid selectJwWebJwidById(String id) {
        return jwWebJwidMapper.selectJwWebJwidById(id);
    }

    /**
     * 查询系统公众号列表
     *
     * @param jwWebJwid 系统公众号
     * @return 系统公众号
     */
    @Override
    public List<JwWebJwid> selectJwWebJwidList(JwWebJwid jwWebJwid) {
        return jwWebJwidMapper.selectJwWebJwidList(jwWebJwid);
    }

    /**
     * 新增系统公众号
     *
     * @param jwWebJwid 系统公众号
     * @return 结果
     */
    @Override
    public int insertJwWebJwid(JwWebJwid jwWebJwid) {
        String id = UUIDGenerator.generate();
        jwWebJwid.setId(id);
        jwWebJwid.setCreateTime(DateUtils.getNowDate());
        jwWebJwid.setAuthType("1");
        return jwWebJwidMapper.insertJwWebJwid(jwWebJwid);
    }

    /**
     * 修改系统公众号
     *
     * @param jwWebJwid 系统公众号
     * @return 结果
     */
    @Override
    public int updateJwWebJwid(JwWebJwid jwWebJwid) {
        return jwWebJwidMapper.updateJwWebJwid(jwWebJwid);
    }

    /**
     * 删除系统公众号对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJwWebJwidByIds(String ids) {
        return jwWebJwidMapper.deleteJwWebJwidByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系统公众号信息
     *
     * @param id 系统公众号ID
     * @return 结果
     */
    @Override
    public int deleteJwWebJwidById(String id) {
        return jwWebJwidMapper.deleteJwWebJwidById(id);
    }

    @Override
    public JwWebJwid queryByJwid(String jwid) {
        return jwWebJwidMapper.queryByJwid(jwid);
    }

    @Override
    public JwWebJwid queryOneByCreateBy(String createBy) {
        return jwWebJwidMapper.queryOneByCreateBy(createBy);
    }

    @Override
    public List<JwWebJwid> queryResetTokenList(Date refDate) {
        return jwWebJwidMapper.queryResetTokenList(refDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doAddSystemUserJwid(String id, String jwid, String createBy) {
        jwWebJwidMapper.doAddSystemUserJwid(id, jwid, createBy);
    }

    /**
     * 重置ACCESS_TOKEN
     *
     * @param id
     * @return
     */
    @Override
    public String resetAccessToken(String id) {
        JwWebJwid jwWebJwid = jwWebJwidMapper.selectJwWebJwidById(id);
        log.info("---本地授权公众号---jwWebJwid---" + jwWebJwid);
        return resetAccessTokenByType1(jwWebJwid);
    }

    /**
     * 手动录入获取ACCESSS_TOKEN
     */
    private String resetAccessTokenByType1(JwWebJwid jwWebJwid) {
        Map<String, Object> data = AccessTokenUtil.getAccseeToken(jwWebJwid.getWeixinAppid(), jwWebJwid.getWeixinAppsecret());
        if (data != null && "success".equals(data.get("status").toString())) {
            jwWebJwid.setAccessToken(data.get("accessToken").toString());
            jwWebJwid.setTokenGettime(new Date());
            jwWebJwid.setApiticket(data.get("apiTicket").toString());
            jwWebJwid.setApiticketGettime(new Date());
            jwWebJwid.setJsapiticket(data.get("jsApiTicket").toString());
            jwWebJwid.setJsapiticketGettime(new Date());
            jwWebJwidMapper.updateJwWebJwid(jwWebJwid);
            try {
                WeixinAccount po = new WeixinAccount();
                po.setAccountappid(jwWebJwid.getWeixinAppid());
                po.setAccountappsecret(jwWebJwid.getWeixinAppsecret());
                po.setAccountaccesstoken(jwWebJwid.getAccessToken());
                po.setAddtoekntime(jwWebJwid.getTokenGettime());
                po.setAccountnumber(jwWebJwid.getWeixinNumber());
                po.setApiticket(jwWebJwid.getApiticket());
                po.setApiticketttime(jwWebJwid.getApiticketGettime());
                po.setAccounttype(jwWebJwid.getAccountType());
                po.setWeixinAccountid(jwWebJwid.getJwid());//原始ID
                po.setJsapiticket(jwWebJwid.getJsapiticket());
                po.setJsapitickettime(jwWebJwid.getJsapiticketGettime());
                JedisPoolUtil.putWxAccount(po);
            }catch (Exception e){
                e.printStackTrace();
                log.error("---重置redis缓存token失败--"+e.toString());
                return " 没有配置Redis 缓存！";
            }
            return "success";
        }else if("responseErr".equals(data.get("status").toString())){
            return data.get("msg").toString();
        }else{
            return null;
        }
    }

}

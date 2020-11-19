package com.ss.wx.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import com.ss.common.utils.DateUtils;
import com.ss.common.utils.StringUtils;
import com.ss.utils.AccessTokenUtil;
import com.ss.utils.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.wx.mapper.WeixinGzuserMapper;
import com.ss.wx.domain.WeixinGzuser;
import com.ss.wx.service.IWeixinGzuserService;
import com.ss.common.core.text.Convert;

/**
 * 粉丝Service业务层处理
 * 
 * @author shishuai
 * @date 2020-07-15
 */
@Service
public class WeixinGzuserServiceImpl implements IWeixinGzuserService 
{

    private static final Logger log = LoggerFactory.getLogger(WeixinGzuserServiceImpl.class);
    //获取用户列表
    private static final String user_list_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
    //获取用户基本信息
    public final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    //批量获取用户基本信息接口
    public final static String batch_user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";

    @Autowired
    private WeixinGzuserMapper weixinGzuserMapper;

    /**
     * 查询粉丝
     * 
     * @param id 粉丝ID
     * @return 粉丝
     */
    @Override
    public WeixinGzuser selectWeixinGzuserById(String id)
    {
        return weixinGzuserMapper.selectWeixinGzuserById(id);
    }

    /**
     * 查询粉丝列表
     * 
     * @param weixinGzuser 粉丝
     * @return 粉丝
     */
    @Override
    public List<WeixinGzuser> selectWeixinGzuserList(WeixinGzuser weixinGzuser)
    {
        return weixinGzuserMapper.selectWeixinGzuserList(weixinGzuser);
    }

    /**
     * 新增粉丝
     * 
     * @param weixinGzuser 粉丝
     * @return 结果
     */
    @Override
    public int insertWeixinGzuser(WeixinGzuser weixinGzuser)
    {
        weixinGzuser.setId(UUID.randomUUID().toString());
        weixinGzuser.setCreateTime(DateUtils.getNowDate());
        return weixinGzuserMapper.insertWeixinGzuser(weixinGzuser);
    }

    /**
     * 修改粉丝
     * 
     * @param weixinGzuser 粉丝
     * @return 结果
     */
    @Override
    public int updateWeixinGzuser(WeixinGzuser weixinGzuser)
    {
        return weixinGzuserMapper.updateWeixinGzuser(weixinGzuser);
    }

    /**
     * 删除粉丝对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWeixinGzuserByIds(String ids)
    {
        return weixinGzuserMapper.deleteWeixinGzuserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除粉丝信息
     * 
     * @param id 粉丝ID
     * @return 结果
     */
    @Override
    public int deleteWeixinGzuserById(String id)
    {
        return weixinGzuserMapper.deleteWeixinGzuserById(id);
    }

    /**
     * 获取公众号的关注粉丝
     * @param next_openid
     * @param jwid
     * @return
     */
    @Override
    public String getFansListTask(String next_openid, String jwid,String appid,String appsecret) {
        String returnMsg;
        //获取微信公众号的关注粉丝(同步openid)
        int total = 0;
        try{
            returnMsg = "粉丝同步成功，同步粉丝条数：";
            //获取token
            Map<String,Object> map = AccessTokenUtil.getAccseeToken(appid,appsecret);
            String accessToken = map.get("accessToken").toString();
            if(StringUtils.isNotEmpty(accessToken)){
                //多线程处理数据
                ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,1, TimeUnit.SECONDS,new LinkedBlockingDeque<>());
                List<Future<Boolean>> futures = new ArrayList<>(2000);
                int k = 0;
                //获取粉丝列表信息
                String requestUrl = user_list_url.replace("NEXT_OPENID","").replace("ACCESS_TOKEN",accessToken);
                while(oConvertUtils.isNotEmpty(next_openid) && k<2000){
                    k++;
                    //调用接口获取粉丝列表(一次最多拉取10000)
                    JSONObject jsonObj = WeixinUtil.httpRequest(requestUrl, "GET", "");
                    next_openid = null; //防止死循环
                    if(jsonObj == null){
                        continue;
                    }
                    if(!jsonObj.containsKey("errmsg")){
                        total = jsonObj.getInt("total");
                        int count = jsonObj.getInt("count");
                        if(count!=0){
                            //获取拉取的粉丝的openid
                            JSONArray openIdArr = jsonObj.getJSONObject("data").getJSONArray("openid");
                            //将粉丝信息存到数据库
                            SyncFansInfo fansInfo = new SyncFansInfo(jwid,openIdArr);
                            Future<Boolean> submit = executor.submit(fansInfo);
                            futures.add(submit);
                        //    futures.add(executor.submit(new SyncFansInfo(jwid,openIDArr)));
                        }
                        next_openid = jsonObj.getString("next_openid");
                        //使用next_openid继续获取下一页粉丝数据（循环）
                        requestUrl = user_list_url.replace("ACCESS_TOKEN",accessToken).replace("NEXT_OPENID",next_openid);
                    }
                }
                executor.shutdown();
                while(true){
                    if(executor.isTerminated()){
                        break;
                    }
                    Thread.sleep(200);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "同步任务已启动，请稍后刷新，公众号粉丝总数："+total;
    }


    @Override
    public List<WeixinGzuser> queryNumberByJwid(String jwid, int pageNo, int pageSize) {
        return weixinGzuserMapper.queryNumberByJwid(jwid,pageNo,pageSize);
    }

    @Override
    public List<WeixinGzuser> batchGetGzUserInfo(List<Map<String, String>> user_list, String accessToken) throws Exception {
        if(accessToken == null) {
            return null;
        }
        // 调用微信接口
        JSONObject jsonList = batchGetGzUserInfoAPI(user_list, accessToken);

        List<WeixinGzuser> infoList = new ArrayList<WeixinGzuser>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 返回正常数据
        if (jsonList != null && !jsonList.containsKey("errcode")) {
            JSONArray jsonArray = jsonList.getJSONArray("user_info_list");
            List list = JSONArray.toList(jsonArray);
            for (Object object : list) {
                JSONObject jsonObj = JSONObject.fromObject(object);
                String openid = jsonObj.getString("openid");
                if (oConvertUtils.isEmpty(openid)) {
                    return null;
                }
                String subscribe = jsonObj.getString("subscribe");
                //未关注
                if ("0".equals(subscribe)) {
                    WeixinGzuser userInfo = new WeixinGzuser();
                    userInfo.setSubscribe(subscribe);
                    userInfo.setOpenid(openid);
                    infoList.add(userInfo);
                } else {
                    //关注
                    WeixinGzuser userInfo = new WeixinGzuser();
                    if (jsonObj.containsKey("nickname")) {
                        userInfo.setNickname(jsonObj.getString("nickname"));
                    }
                    if(jsonObj.containsKey("sex")) {
                        userInfo.setSex(jsonObj.getString("sex"));
                    }
                    if(jsonObj.containsKey("city")) {
                        userInfo.setCity(jsonObj.getString("city"));
                    }
                    if(jsonObj.containsKey("province")) {
                        userInfo.setProvince(jsonObj.getString("province"));
                    }
                    if(jsonObj.containsKey("country")) {
                        userInfo.setCountry(jsonObj.getString("country"));
                    }
                    if(jsonObj.containsKey("headimgurl")) {
                        userInfo.setHeadimgurl(jsonObj.getString("headimgurl"));
                    }
                    if(jsonObj.containsKey("subscribe_time")) {
                        //update-begin--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
                        String subscribeTime = sdf.format(new Date(jsonObj.getLong("subscribe_time")*1000));
                        //update-end--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
                        userInfo.setSubscribeTime(subscribeTime);
                    }
                    if(jsonObj.containsKey("groupid")) {
                        userInfo.setGroupid(jsonObj.getString("groupid"));
                    }
                    if(jsonObj.containsKey("qr_scene")) {
                        userInfo.setQrScene(jsonObj.getString("qr_scene"));
                    }
                    if(jsonObj.containsKey("qr_scene_str")) {
                        userInfo.setQrSceneStr(jsonObj.getString("qr_scene_str"));
                    }
                    if(jsonObj.containsKey("language")) {
                        userInfo.setLanguage(jsonObj.getString("language"));
                    }
                    if(jsonObj.containsKey("openid")) {
                        userInfo.setOpenid(jsonObj.getString("openid"));
                    }
                    if(jsonObj.containsKey("subscribe")) {
                        userInfo.setSubscribe(jsonObj.getString("subscribe"));
                    }
                    if(jsonObj.containsKey("subscribe_scene")) {
                        userInfo.setSubscribeScene(jsonObj.getString("subscribe_scene"));
                    }
                    if(jsonObj.containsKey("remark")) {
                        userInfo.setBzname(jsonObj.getString("remark"));
                    }
                    if(jsonObj.containsKey("unionid")) {
                        userInfo.setUnionid(jsonObj.getString("unionid"));
                    }
                    if (jsonObj.containsKey("tagid_list")) {
                        JSONArray tagJsonArr = jsonObj.getJSONArray("tagid_list");
                        List<Integer> tagid_list = JSONArray.toList(tagJsonArr);
                        if(tagid_list != null && tagid_list.size() > 0) {
                            String tags = "";
                            for (int i = 0; i < tagid_list.size(); i++) {
                                tags += "," +tagid_list.get(i);
                            }
                            tags = tags.substring(1);
                            userInfo.setTagidList(tags);
                        }
                    }
                    infoList.add(userInfo);
                }
            }
            return infoList;
        } else {
            if (jsonList.containsKey("errcode")) {
                log.info("------------获取粉丝数据接口错误码 errcode :  " + jsonList.get("errcode"));
                log.info("-----------------getGzUserInfo--获取粉丝失败--------------------" + jsonList.toString());
            }
        }
        return null;
    }

    /**
     * 调用微信接口获取关注用户信息，每次获取最多100条
     * @param user_list
     * @param accessToken
     * @return
     * @throws Exception
     */
    public JSONObject batchGetGzUserInfoAPI(List<Map<String,String>> user_list,String accessToken) throws Exception{
        String requestUrl = batch_user_info_url.replace("ACCESS_TOKEN", accessToken);
        JSONObject obj = new JSONObject();
        obj.put("user_list", user_list);
        log.info("------------批量获取粉丝数据接口 调用前参数:  "+obj.toString());
        JSONObject jsonList = WeixinUtil.httpRequest(requestUrl, "POST", obj.toString());
        log.info("------------批量获取粉丝数据接口 调用后参数:  "+jsonList.toString());
        return jsonList;
    }
    /**-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=====================-----------------------===============*/


    public class SyncFansInfo implements Callable<Boolean> {
    //    private static final Logger log = LoggerFactory.getLogger(com.ss.wx.domain.SyncFansInfo.class);
        //private WeixinGzuserMapper weixinGzuserDao = ApplicationContextUtil.getContext().getBean(WeixinGzuserMapper.class);

        //公众号ID
        private String jwid;
        //粉丝列表
        private JSONArray openIdArr;

        public SyncFansInfo() {
        }

        public SyncFansInfo(String jwid, JSONArray openIdArr) {
            this.jwid = jwid;
            this.openIdArr = openIdArr;
        }

        @Override
        public Boolean call() throws Exception {
            boolean flag = false;
            try {
                log.info("--------syncFansInfoTask--------公众号【" + this.jwid + "】-获取新粉丝任务开始");
                log.info("--------SyncUserInfoTask--------公众号【" + this.jwid + "】-获取新粉丝任务从微信取得粉丝数：" + openIdArr.size());
                //1.获取token
                for (int i = 0; i < openIdArr.size(); i++) {
                    //2.将粉丝列表信息存入数据库
                    String openId = openIdArr.get(i).toString();
                    //3.判断当前粉丝在表中是否存在
                    //update-begin--Author:zhangweijian  Date: 20180820 for：添加jwid查询条件
                    WeixinGzuser gzuserInfo = weixinGzuserMapper.queryByOpenId(openId, jwid);
                    //update-end--Author:zhangweijian  Date: 20180820 for：添加jwid查询条件
                    //4.不存在，添加
                    if (gzuserInfo == null) {
                        WeixinGzuser newGzuser = new WeixinGzuser();
                        newGzuser.setId(UUID.randomUUID().toString());
                        newGzuser.setOpenid(openId);
                        //update-begin--Author:zhangweijian  Date: 20180806 for：设置默认关注状态，jwid
                        newGzuser.setJwid(jwid);
                        newGzuser.setSubscribe("1");//默认关注
                        //update-end--Author:zhangweijian  Date: 20180806 for：设置默认关注状态，jwid
                        newGzuser.setCreateTime(new Date());
                        weixinGzuserMapper.insertWeixinGzuser(newGzuser);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.info("--------SyncUserInfoTask--------公众号【" + this.jwid + "】-获取新粉丝任务失败：" + e.toString());
            }
            return flag;
        }
    }

    /**
     * 根据用户id批量给用户添加标签
     * @param ids
     * @return
     */
    @Override
    public int batchUpdateWeixinGzuser(String[] ids) {
        return weixinGzuserMapper.batchUpdateWeixinGzuser(ids);
    }

    @Override
    public List<WeixinGzuser> queryGzuserByTagId(String tagId) {
        return weixinGzuserMapper.queryGzuserByTagId(tagId);
    }

    @Override
    public List<WeixinGzuser> selectGzuserByTagIdAndJwid(String tagId, String jwid) {
        return weixinGzuserMapper.selectGzuserByTagIdAndJwid(tagId,jwid);
    }

    @Override
    public int updateBatchTagidList(List<WeixinGzuser> list) {
        return weixinGzuserMapper.updateBatchTagidList(list);
    }

    /**
     * 根据微信用户openId和微信公众号，获取微信用户的昵称等信息
     * @param openId
     * @param jwid
     * @param accessToken
     * @return
     */
    @Override
    public WeixinGzuser getGzUserInfo(String openId, String jwid, String accessToken) {
        if(accessToken == null){
            return null;
        }
        String requestUrl = user_info_url.replace("ACCESS_TOKEN",accessToken).replace("OPENID",openId);
        JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl, "GET", "");
        if(jsonObject != null && !jsonObject.containsKey("errcode")){
            System.out.println("======jsonObject====="+jsonObject);
            WeixinGzuser userInfo = new WeixinGzuser();
            if (jsonObject.containsKey("nickname")) {
                userInfo.setNickname(jsonObject.getString("nickname"));
            }
            if(jsonObject.containsKey("sex")) {
                userInfo.setSex(jsonObject.getString("sex"));
            }
            if(jsonObject.containsKey("city")) {
                userInfo.setCity(jsonObject.getString("city"));
            }
            if(jsonObject.containsKey("province")) {
                userInfo.setProvince(jsonObject.getString("province"));
            }
            if(jsonObject.containsKey("country")) {
                userInfo.setCountry(jsonObject.getString("country"));
            }
            if(jsonObject.containsKey("headimgurl")) {
                userInfo.setHeadimgurl(jsonObject.getString("headimgurl"));
            }
            if(jsonObject.containsKey("subscribe_time")) {
                //update-begin--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String subscribeTime = sdf.format(new Date(jsonObject.getLong("subscribe_time")*1000));
                userInfo.setSubscribeTime(subscribeTime);
                //update-end--Author:zhangweijian  Date: 20180807 for：关注事件时间修改
            }
            if(jsonObject.containsKey("groupid")) {
                userInfo.setGroupid(jsonObject.getString("groupid"));
            }
            if(jsonObject.containsKey("qr_scene")) {
                userInfo.setQrScene(jsonObject.getString("qr_scene"));
            }
            if(jsonObject.containsKey("qr_scene_str")) {
                userInfo.setQrSceneStr(jsonObject.getString("qr_scene_str"));
            }
            if(jsonObject.containsKey("language")) {
                userInfo.setLanguage(jsonObject.getString("language"));
            }
            if(jsonObject.containsKey("openid")) {
                userInfo.setOpenid(jsonObject.getString("openid"));
            }
            if(jsonObject.containsKey("subscribe")) {
                userInfo.setSubscribe(jsonObject.getString("subscribe"));
            }
            if(jsonObject.containsKey("subscribe_scene")) {
                userInfo.setSubscribeScene(jsonObject.getString("subscribe_scene"));
            }
            if(jsonObject.containsKey("remark")) {
                userInfo.setBzname(jsonObject.getString("remark"));
            }
            if(jsonObject.containsKey("unionid")) {
                userInfo.setUnionid(jsonObject.getString("unionid"));
            }
            if (jsonObject.containsKey("tagid_list")) {
                JSONArray tagJsonArr = jsonObject.getJSONArray("tagid_list");
                List<Integer> tagid_list = JSONArray.toList(tagJsonArr);
                //update-begin-zhangweijian-----Date:20180809---for:tagid_list非空判断
                if(tagid_list != null && tagid_list.size() > 0) {
                    String tags = "";
                    for (int i = 0; i < tagid_list.size(); i++) {
                        tags += "," + tagid_list.get(i);
                    }
                    tags = tags.substring(1);
                    userInfo.setTagidList(tags);
                    //update-end-zhangweijian-----Date:20180809---for:tagid_list非空判断
                }
            }
            return userInfo;
        }else{
            log.info("-------getGzuserInfo--获取粉丝失败---------"+jsonObject.toString());
        }
        return null;
    }
}

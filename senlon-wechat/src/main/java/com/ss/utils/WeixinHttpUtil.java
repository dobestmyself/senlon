package com.ss.utils;

import com.alibaba.fastjson.JSONObject;

import com.jeecg.p3.weixinInterface.entity.WeixinAccount;
import com.ss.wx.domain.JwWebJwid;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.CommonRandomUtil;
import org.apache.commons.lang.StringUtil;
import org.jeecgframework.p3.core.util.HttpUtils;
import org.jeecgframework.p3.core.util.PropertiesUtil;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.jeecgframework.p3.core.utils.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WeixinHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(WeixinHttpUtil.class);
    private static String HD_URL_KEY = "hdUrl";
    public static String TXT_ACTID_KEY = "txtActId";
    public static final String nonceStr = "oDxlNmsjqvV9D29r";
    public static final String timestamp = "1420942347";
    public static final boolean nonceStrflag;
    public static final String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static final String user_info_sns_url = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    public static final String get_access_token = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public WeixinHttpUtil() {
    }

    public static String getOauth2Url(String bussKey, String jwid, String actId) {
        PropertiesUtil p = new PropertiesUtil(bussKey + ".properties");
        String author2_Base_URL = p.readProperty(HD_URL_KEY).trim();
        if (!oConvertUtils.isEmpty(jwid) && !oConvertUtils.isEmpty(actId)) {
            String new_author2_URL = author2_Base_URL + "&jwid=" + jwid.trim() + "&actId=" + actId.trim();
            return new_author2_URL;
        } else {
            return null;
        }
    }

    public static String getRedisWeixinToken(String jwid) {
        long startTime = System.currentTimeMillis();
        boolean var12 = false;

        String var4;
        long endTime;
        label36: {
            try {
                var12 = true;
                WeixinAccount po = JedisPoolUtil.getWxAccount(jwid);
                logger.debug(" --redis--  getRedisWeixinToken -- getAccountappid " + po.getAccountappid());
                logger.debug(" --redis--  getRedisWeixinToken -- getAccountappsecret " + po.getAccountappsecret());
                logger.debug(" --redis--  getRedisWeixinToken -- getAccountname " + po.getAccountname());

                logger.debug(" --redis--  getRedisWeixinToken -- getAccountaccesstoken " + po.getAccountaccesstoken());
                var4 = po.getAccountaccesstoken();
                var12 = false;
                break label36;
            } catch (Exception var13) {
                logger.error("--getRedisWeixinToken--error--" + var13.toString());
                var4 = null;
                var12 = false;
            } finally {
                if (var12) {
                    endTime = System.currentTimeMillis();
                    logger.info("----获取TOKEN----方法：getRedisWeixinToken----耗时：" + (endTime - startTime) + "ms");
                }
            }

            endTime = System.currentTimeMillis();
            logger.info("----获取TOKEN----方法：getRedisWeixinToken----耗时：" + (endTime - startTime) + "ms");
            return var4;
        }

        endTime = System.currentTimeMillis();
        logger.info("----获取TOKEN----方法：getRedisWeixinToken----耗时：" + (endTime - startTime) + "ms");
        return var4;
    }

    public static String getRedisSignature(HttpServletRequest request, String jwid) {
        long startTime = System.currentTimeMillis();
        String signature = null;
        String url = request.getRequestURL() + "?" + request.getQueryString();
        if (url.indexOf("#") != -1) {
            url = url.substring(0, url.indexOf("#"));
        }

        WeixinAccount po = JedisPoolUtil.getWxAccount(jwid);
        String jsapi_ticket = po.getJsapiticket();
        String need_make_string = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + "oDxlNmsjqvV9D29r" + "&timestamp=" + "1420942347" + "&url=" + url;
        logger.debug("----------通过Redis缓存获取公众号，本地生成签名----------------------getRedisSignature-------");
        logger.debug("----------------------need_make_string--------------：" + need_make_string);

        try {
            signature = DigestUtils.shaHex(need_make_string);
        } catch (Exception var11) {
            var11.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        logger.debug("--------生成签名------方法： getRedisSignature-----耗时：" + (endTime - startTime) + "ms");
        return signature;
    }

    public static String getRedisSignature(String url, String jwid) {
        long startTime = System.currentTimeMillis();
        String signature = null;
        WeixinAccount po = JedisPoolUtil.getWxAccount(jwid);
        String jsapi_ticket = po.getJsapiticket();
        String need_make_string = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + "oDxlNmsjqvV9D29r" + "&timestamp=" + "1420942347" + "&url=" + url;
        logger.debug("----------通过Redis缓存获取公众号，本地生成签名----------------------getRedisSignature-------");
        logger.debug("----------------------need_make_string--------------：" + need_make_string);

        try {
            signature = DigestUtils.shaHex(need_make_string);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        logger.debug("--------生成签名------方法： getRedisSignature-----耗时：" + (endTime - startTime) + "ms");
        return signature;
    }

    public static JSONObject getGzUserInfo(String openid, String jwid) {
        long startTime = System.currentTimeMillis();
        String redistoken = getRedisWeixinToken(jwid);
        if (redistoken == null) {
            logger.error("---------------getRedisWeixinToken ---获取缓存token失败----------------");
            return null;
        } else {
            String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN".replace("ACCESS_TOKEN", redistoken);
            requestUrl = requestUrl.replace("OPENID", openid);
            JSONObject jsonObj = sendGet(requestUrl);
            long endTime = System.currentTimeMillis();
            logger.info("----通过微信服务端获取粉丝信息----方法：getGzUserInfo------耗时：" + (endTime - startTime) + "ms");
            if (jsonObj != null && !jsonObj.containsKey("errcode")) {
                return jsonObj;
            } else {
                logger.info("-----------------getGzUserInfo--获取粉丝信息失败--------------------" + jsonObj.toString());
                return null;
            }
        }
    }

    public static String getNickName(String openid, String jwid) {
        JSONObject jsonObj = getGzUserInfo(openid, jwid);
        logger.info("  getGzUserInfo jsonObj={}", new Object[]{jsonObj});
        return jsonObj != null && jsonObj.containsKey("nickname") ? jsonObj.getString("nickname") : null;
    }

    public static JSONObject getGzUserInfo2(String openid, String weixinId, String userAccessToken) {
        long startTime = System.currentTimeMillis();
        String requestUrl = null;
        if (StringUtils.isNotBlank(userAccessToken)) {
            requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN".replace("ACCESS_TOKEN", userAccessToken);
        } else {
            String redistoken = getRedisWeixinToken(weixinId);
            requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN".replace("ACCESS_TOKEN", redistoken);
        }

        requestUrl = requestUrl.replace("OPENID", openid);
        JSONObject jsonObj = sendGet(requestUrl);
        long endTime = System.currentTimeMillis();
        logger.info("----通过微信服务端获取粉丝信息----方法：getGzUserInfo2------耗时：" + (endTime - startTime) + "ms");
        if (jsonObj != null && !jsonObj.containsKey("errcode")) {
            return jsonObj;
        } else {
            logger.info("-----------------getGzUserInfo2--获取粉丝信息失败--------------------" + jsonObj.toString());
            return null;
        }
    }

    public static String getShortUrl(String hdurl, String jwid) {
        logger.info("getShortUrl request jwid={}, hdurl={}.", new Object[]{jwid, hdurl});
        if (!StringUtil.isEmpty(hdurl) && !StringUtil.isEmpty(jwid)) {
            String accessToken = getRedisWeixinToken(jwid);
            if (StringUtil.isEmpty(accessToken)) {
                return null;
            } else {
                Map<String, Object> paramMap = new HashMap();
                paramMap.put("action", "long2short");
                paramMap.put("long_url", hdurl);
                JSONObject obj = new JSONObject(paramMap);
                logger.info("getShortUrl request jsonStr={}.", new Object[]{obj.toString()});
                String str = HttpUtils.doPostJson("https://api.weixin.qq.com/cgi-bin/shorturl?access_token=" + accessToken, obj.toString());
                logger.info("getShortUrl response jsonStr={}.", new Object[]{str});
                if (str != null) {
                    JSONObject jsonStr = JSONObject.parseObject(str);
                    if (jsonStr.containsKey("errcode") && "0".equals(jsonStr.getString("errcode")) && jsonStr.containsKey("short_url")) {
                        String shortUrl = jsonStr.getString("short_url").replaceAll("\\/", "/");
                        logger.info("getShortUrl response short_url={}.", new Object[]{shortUrl});
                        return shortUrl;
                    }
                }

                return null;
            }
        } else {
            return null;
        }
    }

    public static JSONObject sendGet(String url) {
        JSONObject jsonObject = null;
        String result = "";
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(30000);
            connection.connect();
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

            String line;
            for(reader = new BufferedReader(inputStreamReader); (line = reader.readLine()) != null; result = result + line) {
            }
        } catch (Exception var17) {
            logger.info("发送GET请求出现异常！" + var17);
            var17.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            }

        }

        jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }

    public static JSONObject sendPost(String url) {
        JSONObject jsonObject = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(30000);
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

            String line;
            for(reader = new BufferedReader(inputStreamReader); (line = reader.readLine()) != null; result = result + line) {
            }
        } catch (Exception var17) {
            logger.info("发送 POST 请求出现异常！" + var17);
            var17.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }

                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            }

        }

        jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }

    public static void main(String[] args) {
        System.out.println(getRedisWeixinToken("gh_755bffca7aca"));
    }

    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000L);
    }

    public static String encode(byte[] bstr) {
        return Base64.encodeBase64String(bstr);
    }

    public static byte[] decode(String str) {
        byte[] bt = null;

        try {
            bt = Base64.decodeBase64(str);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return bt;
    }

    static {
        nonceStrflag = CommonRandomUtil.TRUE;
    }
}

package com.ss.utils;

import com.jeecg.p3.redis.SerializeUtil;
import com.jeecg.p3.weixinInterface.entity.WeixinAccount;
import com.ss.common.config.Global;
import jdk.internal.dynalink.beans.StaticClass;
import org.jeecgframework.p3.core.util.oConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

public class JedisPoolUtil {
    private static final Logger logger = LoggerFactory.getLogger(JedisPoolUtil.class);
    public static final String REDIS_WX_ACCOUNT_LIST_KEY = "RES_KEY_WX_ACCOUNT_MAP";
    public static final String REDIS_WX_SUCAI_LINK_PRE = "RES_KEY_SUCAI_LINK_PRE";
    public static final String REDIS_WX_USER_KEY_PRE = "RES_KEY_WX_USER_PRE";
    private static final int REDIS_TIMEOUT = 100000;
    private static JedisPool pool;

    public JedisPoolUtil() {
    }

    private static JedisPool getPool() {
        if (pool == null) {
        //    RedisProperties redisProperties = (RedisProperties) ApplicationContextUtil.getBean(RedisProperties.class);
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(Global.getPool_maxTotal()));
            config.setMaxIdle(Integer.parseInt(Global.getPool_maxIdle()));
            config.setMaxWaitMillis(Integer.parseInt(Global.getPool_maxWaitMilli()));
            config.setTestOnBorrow(Global.isPool_testOnBorrow());
            config.setTestOnReturn(Global.isPool_testOnReturn());
            if (Global.getPassword() != null && !Global.getPassword().equals("")) {
                pool = new JedisPool(config, Global.getIp(), Integer.parseInt(Global.getPort()), 100000, Global.getPassword());
            } else {
                pool = new JedisPool(config, Global.getIp(), Integer.parseInt(Global.getPort()), 100000);
            }
        }

        return pool;
    }

    public static Jedis getJedis() {
        return getPool().getResource();
    }

    public static void closeJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }

    }

    public static String setObject(String key, Object value) {
        Jedis jedis = null;

        Object var5;
        try {
            jedis = getPool().getResource();
            String var4 = jedis.set(key.getBytes(), SerializeUtil.serialize(value));
            return var4;
        } catch (Exception var9) {
            logger.error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + var9.getMessage());
            var5 = null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return (String)var5;
    }

    public String setObject(String key, Object value, int expiretime) {
        String result = "";
        Jedis jedis = null;

        try {
            jedis = getPool().getResource();
            result = jedis.set(key.getBytes(), SerializeUtil.serialize(value));
            if (result.equals("OK")) {
                jedis.expire(key.getBytes(), expiretime);
            }

            String var6 = result;
            return var6;
        } catch (Exception var10) {
            logger.error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + var10.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return result;
    }

    public static Long delkeyObject(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = getPool().getResource();
            Long var3 = jedis.del(key.getBytes());
            return var3;
        } catch (Exception var8) {
            var8.printStackTrace();
            var4 = null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return (Long)var4;
    }

    public Boolean existsObject(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = getPool().getResource();
            Boolean var3 = jedis.exists(key.getBytes());
            return var3;
        } catch (Exception var8) {
            var8.printStackTrace();
            var4 = null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }

        }

        return (Boolean)var4;
    }

    public static Object getObject(String key) {
        Jedis jedis = null;

        Object var4;
        try {
            jedis = getPool().getResource();
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes == null) {
                return null;
            }

            var4 = SerializeUtil.unserialize(bytes);
        } catch (Exception var8) {
            logger.error("getObject获取redis键值异常:key=" + key + " cause:" + var8.getMessage());
            return null;
        } finally {
            jedis.close();
        }

        return var4;
    }

    public static void resetAllWxAccount(Map<String, WeixinAccount> newWxAccountMap) {
        Jedis jedis = getPool().getResource();
        jedis.set("RES_KEY_WX_ACCOUNT_MAP".getBytes(), SerializeUtil.serialize(newWxAccountMap));
        jedis.close();
    }

    public static void clearReis() {
        Jedis jedis = getPool().getResource();
        jedis.del("RES_KEY_WX_ACCOUNT_MAP".getBytes());
        Set s = jedis.keys("RES_KEY_SUCAI_LINK_PRE*");
        Iterator it = s.iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            logger.info("---------------------redis-------------key-------------------" + key);
            jedis.del(key);
        }

        jedis.close();
    }

    public static void putWxUser(Map<String, String> userMap) {
        Jedis jedis = getPool().getResource();
        String openid = (String)userMap.get("openid");
        String key = "RES_KEY_WX_USER_PRE" + openid;
        jedis.set(key.getBytes(), SerializeUtil.serialize(userMap));
        jedis.close();
    }

    public static Map<String, String> getWxUser(String openid) {
        Jedis jedis = getPool().getResource();
        String key = "RES_KEY_WX_USER_PRE" + openid;
        Map<String, String> returnMap = (Map)SerializeUtil.unserialize(jedis.get(key.getBytes()));
        jedis.close();
        return returnMap;
    }

    public static void putWeixinLinkOutUrl(String outUrl, String id) {
        if (oConvertUtils.isNotEmpty(outUrl)) {
            Jedis jedis = getPool().getResource();
            String key = "RES_KEY_SUCAI_LINK_PRE" + id;
            jedis.set(key, outUrl);
            jedis.close();
        }

    }

    public static String getWeixinLinkOutUrl(String id) {
        if (oConvertUtils.isNotEmpty(id)) {
            Jedis jedis = getPool().getResource();
            String key = "RES_KEY_SUCAI_LINK_PRE" + id;
            String outurl = jedis.get(key);
            jedis.close();
            return outurl;
        } else {
            return null;
        }
    }

    public static Map<String, WeixinAccount> getAllWxAccount() {
        Jedis jedis = getPool().getResource();
        Map<String, WeixinAccount> wxAccountMap = null;
        wxAccountMap = (Map)SerializeUtil.unserialize(jedis.get("RES_KEY_WX_ACCOUNT_MAP".getBytes()));
        jedis.close();
        return wxAccountMap;
    }

    public static void putWxAccount(WeixinAccount po) {
        Jedis jedis = getPool().getResource();
        Map<String, WeixinAccount> wxAccountMap = (Map)SerializeUtil.unserialize(jedis.get("RES_KEY_WX_ACCOUNT_MAP".getBytes()));
        if (wxAccountMap == null) {
            wxAccountMap = new HashMap();
        }

        ((Map)wxAccountMap).put(po.getWeixinAccountid(), po);
        jedis.set("RES_KEY_WX_ACCOUNT_MAP".getBytes(), SerializeUtil.serialize(wxAccountMap));
        jedis.close();
    }

    public static void batchPutWxAccount(List<WeixinAccount> accountList) {
        Jedis jedis = getPool().getResource();
        Map<String, WeixinAccount> wxAccountMap = (Map)SerializeUtil.unserialize(jedis.get("RES_KEY_WX_ACCOUNT_MAP".getBytes()));
        if (wxAccountMap == null) {
            wxAccountMap = new HashMap();
        }

        Iterator var3 = accountList.iterator();

        while(var3.hasNext()) {
            WeixinAccount po = (WeixinAccount)var3.next();
            ((Map)wxAccountMap).put(po.getWeixinAccountid(), po);
        }

        jedis.set("RES_KEY_WX_ACCOUNT_MAP".getBytes(), SerializeUtil.serialize(wxAccountMap));
        jedis.close();
    }

    public static WeixinAccount getWxAccount(String jwid) {
        WeixinAccount po = null;

        try {
            Jedis jedis = getPool().getResource();
            Map<String, WeixinAccount> wxAccountMap = (Map)SerializeUtil.unserialize(jedis.get("RES_KEY_WX_ACCOUNT_MAP".getBytes()));
            if (wxAccountMap != null) {
                po = (WeixinAccount)wxAccountMap.get(jwid);
            }

            jedis.close();
        } catch (Exception var4) {
            logger.error("--JedisPoolUtil--getWxAccount--error--" + var4.toString());
        }

        return po;
    }

    public static void setList(String key, String... values) {
        Jedis jedis = getPool().getResource();
        jedis.sadd(key, values);
        jedis.close();
    }

    public static boolean exitInList(String key, String value) {
        Jedis jedis = getPool().getResource();
        boolean b = jedis.sismember(key, value);
        jedis.close();
        return b;
    }

    public static Object getList(String key) {
        Jedis jedis = getPool().getResource();
        Set<String> smembers = jedis.smembers(key);
        jedis.close();
        return smembers;
    }

    public static Long clearList(String key) {
        Jedis jedis = getPool().getResource();
        Long i = jedis.del(key);
        jedis.close();
        return i;
    }

    public static void main(String[] args) {
        System.out.println(getList("ip_list"));
    }

    public static void maintest(String[] args) {
        Jedis jedis = getPool().getResource();
        WeixinAccount po = new WeixinAccount();
        po.setId("sdfsdf-sdfsdfsdf");
        po.setAccountaccesstoken("100");
        po.setAccountappsecret("200");
        String keys = "name";
        jedis.set(po.getId().getBytes(), SerializeUtil.serialize(po));
        WeixinAccount tt = (WeixinAccount)SerializeUtil.unserialize(jedis.get(po.getId().getBytes()));
        System.out.println(tt.getAccountaccesstoken());
        jedis.close();
    }
}

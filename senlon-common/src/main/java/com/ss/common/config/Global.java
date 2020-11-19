package com.ss.common.config;

import com.ss.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置类
 * 
 * @author shishuai
 */
@Configuration
public class Global
{
    private static String  name;

    private static String  version;

    private static String  copyrightYear;

    private static String  demoEnabled;

    private static Boolean addressEnabled;

    private static String  profile;
    /*================redis-start==================*/
    private static String ip;

    private static String port;

    private static String  password;

    private static String pool_maxTotal;

    private static String pool_maxIdle;

    private static String pool_maxWaitMilli;

    private static boolean pool_testOnBorrow = true;

    private static boolean pool_testOnReturn = true;

    private static String uploadpath;

    @Value("${jeewx.redis.ip}")
    public static void setIp(String ip) {
        Global.ip = ip;
    }
    @Value("${jeewx.redis.port}")
    public static void setPort(String port) {
        Global.port = port;
    }
    @Value("${jeewx.redis.password}")
    public static void setPassword(String password) {
        Global.password = password;
    }
    @Value("${jeewx.redis.pool.maxTotal}")
    public static void setPool_maxTotal(String pool_maxTotal) {
        Global.pool_maxTotal = pool_maxTotal;
    }
    @Value("${jeewx.redis.pool.maxIdle}")
    public static void setPool_maxIdle(String pool_maxIdle) {
        Global.pool_maxIdle = pool_maxIdle;
    }
    @Value("${jeewx.redis.pool.maxWaitMilli}")
    public static void setPool_maxWaitMilli(String pool_maxWaitMilli) {
        Global.pool_maxWaitMilli = pool_maxWaitMilli;
    }
    @Value("${jeewx.redis.pool.testOnBorrow}")
    public static void setPool_testOnBorrow(boolean pool_testOnBorrow) {
        Global.pool_testOnBorrow = pool_testOnBorrow;
    }
    @Value("${jeewx.redis.pool.testOnReturn}")
    public static void setPool_testOnReturn(boolean pool_testOnReturn) {
        Global.pool_testOnReturn = pool_testOnReturn;
    }
    @Value("${jeewx.path.upload}")
    public static void setUploadpath(String uploadpath) {
        Global.uploadpath = uploadpath;
    }

    public static String getIp() {
        return StringUtils.nvl(ip, "127.0.0.1");
    }

    public static String getPort() {
        return StringUtils.nvl(port,"6379");
    }

    public static String getPassword() {
        return StringUtils.nvl(password, "");
    }

    public static String getPool_maxTotal() {
        return StringUtils.nvl(pool_maxTotal,"1024");
    }

    public static String getPool_maxIdle() {
        return StringUtils.nvl(pool_maxIdle,"200");
    }

    public static String getPool_maxWaitMilli() {
        return StringUtils.nvl(pool_maxWaitMilli,"1000");
    }

    public static boolean isPool_testOnBorrow() {
        return pool_testOnBorrow;
    }

    public static boolean isPool_testOnReturn() {
        return pool_testOnReturn;
    }

    public static String getUploadpath() {
        return uploadpath;
    }
    /*=====================redis-end=======================*/

    //TODO
//    public static Long ROLE_ID_DEFAULT=2L;//新增的用户赋予的默认角色的ID
    public static String ROLE_ID_DEFAULT = "01";

    @Value("${senlon.name}")
    public void setName(String name)
    {
        Global.name = name;
    }

    @Value("${senlon.version}")
    public void setVersion(String version)
    {
        Global.version = version;
    }

    @Value("${senlon.copyrightYear}")
    public void setCopyrightYear(String copyrightYear)
    {
        Global.copyrightYear = copyrightYear;
    }

    @Value("${senlon.demoEnabled}")
    public void setDemoEnabled(String demoEnabled)
    {
        Global.demoEnabled = demoEnabled;
    }

    @Value("${senlon.addressEnabled}")
    public void setAddressEnabled(Boolean addressEnabled)
    {
        Global.addressEnabled = addressEnabled;
    }

    @Value("${senlon.profile}")
    public void setProfile(String profile)
    {
        Global.profile = profile;
    }

    /**
     * 获取项目名称
     */
    public static String getName()
    {
        return StringUtils.nvl(name, "ShiShuai");
    }

    /**
     * 获取项目版本
     */
    public static String getVersion()
    {
        return StringUtils.nvl(version, "4.0.0");
    }

    /**
     * 获取版权年份
     */
    public static String getCopyrightYear()
    {
        return StringUtils.nvl(copyrightYear, "2019");
    }

    /**
     * 实例演示开关
     */
    public static String isDemoEnabled()
    {
        return StringUtils.nvl(demoEnabled, "true");
    }

    /**
     * 获取ip地址开关
     */
    public static Boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    /**
     * 获取文件上传路径
     */
    public static String getProfile()
    {
        return profile;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/profile/upload";
    }

    public static String getUPath(){
        return getProfile() + "/upload";
    }

    /**
     * 获取素材上传路径
     */
    public static String getMaterialPath()
    {
        return getProfile() + "/material";
    }
    /**
     * 获取附件上传路径
     */
    public static String getAttachPath()
    {
        return getProfile() + "/attach";
    }
    /**
     * 获取资源上传路径
     */
    public static String getResourcePath()
    {
        return getProfile() + "/resource";
    }
    /**
     * 获取数据库备份路径
     */
    public static String getDbBackupPath()
    {
        return getProfile() + "/dbbackup";
    }
}

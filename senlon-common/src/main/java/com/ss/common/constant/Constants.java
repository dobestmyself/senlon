package com.ss.common.constant;

/**
 * 通用常量信息
 * 
 * @author shishuai
 */
public class Constants
{
    /**
     * 后台管理系统用户名cookie名
     */
    public static final String ADMIN_COOKIE_USERNAME = "admin_username";
    /**
     * 后台管理系统密码cookie名
     */
    public static final String ADMIN_COOKIE_PASSWORD = "admin_password";
    /**
     * 后台管理系统记住我cookie名
     */
    public static final String ADMIN_COOKIE_REMEMBERME = "admin_rememberMe";
    /**
     * 7天的秒数
     */
    public static final int SECONDS_7_DAYS = 604800;


    /**
     * 登录页面
     */
    public static final String KEY_LOGIN_PAGE="login.page";

    /**
     * 后台首页页面
     */
    public static final String KEY_ADMIN_INDEX="admin.index.type";

    /**
     * 后台首页页面-index_topMenu
     */
    public static final String ADMIN_INDEX_TOP_MENU="index_topMenu";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";
    /**===================================================*/
    /**
     * 当前登录用户
     */
    public static final String OPERATE_WEB_LOGIN_USER = "OPERATE_WEB_LOGIN_USER";

    /**
     * 菜单树
     */
    public static final String OPERATE_WEB_MENU_TREE = "OPERATE_WEB_MENU_TREE";

    /**
     * 正常用户状态
     */
    public static final String USER_NORMAL_STATE = "NORMAL";
    /**
     * jwid
     */
    public static final String SYSTEM_JWID = "jwid";

    /**
     * jwidname
     */
    public static final String SYSTEM_JWIDNAME = "jwidname";
    /**
     * system_userid 系统登录用户名
     */
    public static final String SYSTEM_USERID = "system_userid";

}

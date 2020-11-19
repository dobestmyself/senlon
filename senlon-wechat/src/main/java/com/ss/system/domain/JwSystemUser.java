package com.ss.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 系统用户对象 jw_system_user
 * 
 * @author shishuai
 * @date 2020-07-02
 */
public class JwSystemUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 用户编码 */
    @Excel(name = "用户编码")
    private String userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    /** 密码 */
    private String password;

    /** 用户ERP号 */
    private String userErpNo;

    /** 用户种类 */
    private String userTyp;

    /** 部门编码 */
    private String opeDepId;

    /** 手机电话 */
    private String opePhoneNum;

    /** 注册邮箱 */
    private String email;

    /** 邮箱认证信息 */
    private String opeEmailAuthinfo;

    /** 企业logo */
    private String userIcon;

    /** 手机是否认证 */
    private String opeMobileAuthInd;

    /** 邮箱是否认证 */
    private String opeEmailAuthInd;

    /** 证件号码 */
    private String idNum;

    /** 证件种类 */
    private String idTyp;

    /** 状态 */
    private String state;

    /** 用户状态 NORMAL:正常；INVALID：无效 */
    @Excel(name = "用户状态 NORMAL:正常；INVALID：无效")
    private String userStat;

    /** 上次登录日期 */
    private Date lastLognDttm;

    /** 上次登录IP */
    private String lastLognIp;

    /** 是否保持密码 */
    private String opePasswdInd;

    /** 删除标志 */
    private String delStat;

    /** 建立者 */
    private String creator;

    /** 编辑者 */
    private String editor;

    /** 建立日期 */
    private Date createDt;

    /** 编辑日期 */
    private Date editDt;

    /** 上次修改日期 */
    private Date lastEditDt;

    /** 版本号 */
    private String recordVersion;

    /** null */
    private String openid;

    /** 代理商id */
    private String agentId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setUserErpNo(String userErpNo) 
    {
        this.userErpNo = userErpNo;
    }

    public String getUserErpNo() 
    {
        return userErpNo;
    }
    public void setUserTyp(String userTyp) 
    {
        this.userTyp = userTyp;
    }

    public String getUserTyp() 
    {
        return userTyp;
    }
    public void setOpeDepId(String opeDepId) 
    {
        this.opeDepId = opeDepId;
    }

    public String getOpeDepId() 
    {
        return opeDepId;
    }
    public void setOpePhoneNum(String opePhoneNum) 
    {
        this.opePhoneNum = opePhoneNum;
    }

    public String getOpePhoneNum() 
    {
        return opePhoneNum;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setOpeEmailAuthinfo(String opeEmailAuthinfo) 
    {
        this.opeEmailAuthinfo = opeEmailAuthinfo;
    }

    public String getOpeEmailAuthinfo() 
    {
        return opeEmailAuthinfo;
    }
    public void setUserIcon(String userIcon) 
    {
        this.userIcon = userIcon;
    }

    public String getUserIcon() 
    {
        return userIcon;
    }
    public void setOpeMobileAuthInd(String opeMobileAuthInd) 
    {
        this.opeMobileAuthInd = opeMobileAuthInd;
    }

    public String getOpeMobileAuthInd() 
    {
        return opeMobileAuthInd;
    }
    public void setOpeEmailAuthInd(String opeEmailAuthInd) 
    {
        this.opeEmailAuthInd = opeEmailAuthInd;
    }

    public String getOpeEmailAuthInd() 
    {
        return opeEmailAuthInd;
    }
    public void setIdNum(String idNum) 
    {
        this.idNum = idNum;
    }

    public String getIdNum() 
    {
        return idNum;
    }
    public void setIdTyp(String idTyp) 
    {
        this.idTyp = idTyp;
    }

    public String getIdTyp() 
    {
        return idTyp;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }
    public void setUserStat(String userStat) 
    {
        this.userStat = userStat;
    }

    public String getUserStat() 
    {
        return userStat;
    }
    public void setLastLognDttm(Date lastLognDttm) 
    {
        this.lastLognDttm = lastLognDttm;
    }

    public Date getLastLognDttm() 
    {
        return lastLognDttm;
    }
    public void setLastLognIp(String lastLognIp) 
    {
        this.lastLognIp = lastLognIp;
    }

    public String getLastLognIp() 
    {
        return lastLognIp;
    }
    public void setOpePasswdInd(String opePasswdInd) 
    {
        this.opePasswdInd = opePasswdInd;
    }

    public String getOpePasswdInd() 
    {
        return opePasswdInd;
    }
    public void setDelStat(String delStat) 
    {
        this.delStat = delStat;
    }

    public String getDelStat() 
    {
        return delStat;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setEditor(String editor) 
    {
        this.editor = editor;
    }

    public String getEditor() 
    {
        return editor;
    }
    public void setCreateDt(Date createDt) 
    {
        this.createDt = createDt;
    }

    public Date getCreateDt() 
    {
        return createDt;
    }
    public void setEditDt(Date editDt) 
    {
        this.editDt = editDt;
    }

    public Date getEditDt() 
    {
        return editDt;
    }
    public void setLastEditDt(Date lastEditDt) 
    {
        this.lastEditDt = lastEditDt;
    }

    public Date getLastEditDt() 
    {
        return lastEditDt;
    }
    public void setRecordVersion(String recordVersion) 
    {
        this.recordVersion = recordVersion;
    }

    public String getRecordVersion() 
    {
        return recordVersion;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setAgentId(String agentId) 
    {
        this.agentId = agentId;
    }

    public String getAgentId() 
    {
        return agentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("password", getPassword())
            .append("userErpNo", getUserErpNo())
            .append("userTyp", getUserTyp())
            .append("opeDepId", getOpeDepId())
            .append("opePhoneNum", getOpePhoneNum())
            .append("email", getEmail())
            .append("opeEmailAuthinfo", getOpeEmailAuthinfo())
            .append("userIcon", getUserIcon())
            .append("opeMobileAuthInd", getOpeMobileAuthInd())
            .append("opeEmailAuthInd", getOpeEmailAuthInd())
            .append("idNum", getIdNum())
            .append("idTyp", getIdTyp())
            .append("state", getState())
            .append("userStat", getUserStat())
            .append("lastLognDttm", getLastLognDttm())
            .append("lastLognIp", getLastLognIp())
            .append("opePasswdInd", getOpePasswdInd())
            .append("delStat", getDelStat())
            .append("creator", getCreator())
            .append("editor", getEditor())
            .append("createDt", getCreateDt())
            .append("editDt", getEditDt())
            .append("lastEditDt", getLastEditDt())
            .append("recordVersion", getRecordVersion())
            .append("openid", getOpenid())
            .append("agentId", getAgentId())
            .toString();
    }
}

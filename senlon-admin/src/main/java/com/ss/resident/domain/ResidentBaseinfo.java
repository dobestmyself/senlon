package com.ss.resident.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 住院医师基本信息对象 resident_baseinfo
 * 
 * @author shishuai
 * @date 2020-05-27
 */
public class ResidentBaseinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long residentId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 民族 */
    private String nation;

    /** 出生日期 */
    @Excel(name = "出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date birthdate;

    /** 婚姻状况 */
    private String marries;

    /** 国籍 */
    private String nationality;

    /** 身份证件类型 */
    private String idcardType;

    /** 身份证件号码 */
    @Excel(name = "身份证件号码")
    private String idcard;

    /** 行政区划 */
    @Excel(name = "行政区划")
    private String area;

    /** 电话类型 */
    private String phoneType;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** QQ */
    private String qq;

    /** 邮箱 */
    private String email;

    /** 医师资格考试通过标志 */
    @Excel(name = "医师资格考试通过标志")
    private String isModicalQualifyExamPass;

    /** 通过医师资格日期 */
    @Excel(name = "通过医师资格日期", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date passModicalQualifyDate;

    /** 医师资格证书 */
    @Excel(name = "医师资格证书")
    private String modicalQualifyCertificate;

    /** 取得医师资格证书日期 */
    @Excel(name = "取得医师资格证书日期", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date achieveModicalQualifyCertificateDate;

    /** 医师资格类别 */
    @Excel(name = "医师资格类别")
    private String modicalQualifyLevel;

    /** 医师资格类型 */
    @Excel(name = "医师资格类型")
    private String modicalQualifyType;

    /** 医师资格证书编码 */
    private String modicalQualifyCertificateCode;

    /** 语言级别考试类型 */
    private String languageLevelExamType;

    /** 英语等级 */
    private String englishLevel;

    /** 外语等级 */
    private String languageLevel;

    /** 外语等级考试证书编码 */
    private String languageLevelExamCertificateCode;

    /** 外语等级考试证书取得日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date languageLevelExamCertificateDate;

    public void setResidentId(Long residentId) 
    {
        this.residentId = residentId;
    }

    public Long getResidentId() 
    {
        return residentId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setMarries(String marries)
    {
        this.marries = marries;
    }

    public String getMarries() 
    {
        return marries;
    }
    public void setNationality(String nationality) 
    {
        this.nationality = nationality;
    }

    public String getNationality() 
    {
        return nationality;
    }
    public void setIdcardType(String idcardType) 
    {
        this.idcardType = idcardType;
    }

    public String getIdcardType() 
    {
        return idcardType;
    }
    public void setIdcard(String idcard) 
    {
        this.idcard = idcard;
    }

    public String getIdcard() 
    {
        return idcard;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setPhoneType(String phoneType) 
    {
        this.phoneType = phoneType;
    }

    public String getPhoneType() 
    {
        return phoneType;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setQq(String qq) 
    {
        this.qq = qq;
    }

    public String getQq() 
    {
        return qq;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setIsModicalQualifyExamPass(String isModicalQualifyExamPass) 
    {
        this.isModicalQualifyExamPass = isModicalQualifyExamPass;
    }

    public String getIsModicalQualifyExamPass() 
    {
        return isModicalQualifyExamPass;
    }
    public void setPassModicalQualifyDate(Date passModicalQualifyDate) 
    {
        this.passModicalQualifyDate = passModicalQualifyDate;
    }

    public Date getPassModicalQualifyDate() 
    {
        return passModicalQualifyDate;
    }
    public void setModicalQualifyCertificate(String modicalQualifyCertificate) 
    {
        this.modicalQualifyCertificate = modicalQualifyCertificate;
    }

    public String getModicalQualifyCertificate() 
    {
        return modicalQualifyCertificate;
    }
    public void setAchieveModicalQualifyCertificateDate(Date achieveModicalQualifyCertificateDate) 
    {
        this.achieveModicalQualifyCertificateDate = achieveModicalQualifyCertificateDate;
    }

    public Date getAchieveModicalQualifyCertificateDate() 
    {
        return achieveModicalQualifyCertificateDate;
    }
    public void setModicalQualifyLevel(String modicalQualifyLevel) 
    {
        this.modicalQualifyLevel = modicalQualifyLevel;
    }

    public String getModicalQualifyLevel() 
    {
        return modicalQualifyLevel;
    }
    public void setModicalQualifyType(String modicalQualifyType) 
    {
        this.modicalQualifyType = modicalQualifyType;
    }

    public String getModicalQualifyType() 
    {
        return modicalQualifyType;
    }
    public void setModicalQualifyCertificateCode(String modicalQualifyCertificateCode) 
    {
        this.modicalQualifyCertificateCode = modicalQualifyCertificateCode;
    }

    public String getModicalQualifyCertificateCode() 
    {
        return modicalQualifyCertificateCode;
    }
    public void setLanguageLevelExamType(String languageLevelExamType) 
    {
        this.languageLevelExamType = languageLevelExamType;
    }

    public String getLanguageLevelExamType() 
    {
        return languageLevelExamType;
    }
    public void setEnglishLevel(String englishLevel) 
    {
        this.englishLevel = englishLevel;
    }

    public String getEnglishLevel() 
    {
        return englishLevel;
    }
    public void setLanguageLevel(String languageLevel) 
    {
        this.languageLevel = languageLevel;
    }

    public String getLanguageLevel() 
    {
        return languageLevel;
    }
    public void setLanguageLevelExamCertificateCode(String languageLevelExamCertificateCode) 
    {
        this.languageLevelExamCertificateCode = languageLevelExamCertificateCode;
    }

    public String getLanguageLevelExamCertificateCode() 
    {
        return languageLevelExamCertificateCode;
    }
    public void setLanguageLevelExamCertificateDate(Date languageLevelExamCertificateDate) 
    {
        this.languageLevelExamCertificateDate = languageLevelExamCertificateDate;
    }

    public Date getLanguageLevelExamCertificateDate() 
    {
        return languageLevelExamCertificateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("residentId", getResidentId())
            .append("name", getName())
            .append("gender", getGender())
            .append("nation", getNation())
            .append("birthdate", getBirthdate())
            .append("marries", getMarries())
            .append("nationality", getNationality())
            .append("idcardType", getIdcardType())
            .append("idcard", getIdcard())
            .append("area", getArea())
            .append("phoneType", getPhoneType())
            .append("phoneNumber", getPhoneNumber())
            .append("qq", getQq())
            .append("email", getEmail())
            .append("isModicalQualifyExamPass", getIsModicalQualifyExamPass())
            .append("passModicalQualifyDate", getPassModicalQualifyDate())
            .append("modicalQualifyCertificate", getModicalQualifyCertificate())
            .append("achieveModicalQualifyCertificateDate", getAchieveModicalQualifyCertificateDate())
            .append("modicalQualifyLevel", getModicalQualifyLevel())
            .append("modicalQualifyType", getModicalQualifyType())
            .append("modicalQualifyCertificateCode", getModicalQualifyCertificateCode())
            .append("languageLevelExamType", getLanguageLevelExamType())
            .append("englishLevel", getEnglishLevel())
            .append("languageLevel", getLanguageLevel())
            .append("languageLevelExamCertificateCode", getLanguageLevelExamCertificateCode())
            .append("languageLevelExamCertificateDate", getLanguageLevelExamCertificateDate())
            .toString();
    }
}

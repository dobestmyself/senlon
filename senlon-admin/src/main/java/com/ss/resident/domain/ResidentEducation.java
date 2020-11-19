package com.ss.resident.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.common.annotation.Excel;
import com.ss.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 住院医师教育经历对象 resident_education
 * 
 * @author shishuai
 * @date 2020-05-28
 */
public class ResidentEducation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 身份证件类型 */
    private String idcardType;

    /** 身份证号 */
    private String idcard;

    /** 全日制教育标志 */
    private String isDayEducation;

    /** 在读标志 */
    private String isReading;

    /** 在读学历 */
    private String readingEducationBg;

    /** 预计毕业时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date plainGraduationDate;

    /** 所学专业 */
    private String learnMajor;

    /** 在读专业 */
    private String readingMajor;

    /** 在读学校 */
    private String readingSchool;

    /** 其他在读学校 */
    private String otherReadingSchool;

    /** 学历 */
    @Excel(name = "学历")
    private String educationBg;

    /** 毕业专业 */
    @Excel(name = "毕业专业")
    private String graduationMajor;

    /** 毕业院校 */
    @Excel(name = "毕业院校")
    private String graduationSchool;

    /** 其他毕业院校 */
    private String otherGraduationSchool;

    /** 毕业证书标志 */
    private String isGraduationCertificate;

    /** 毕业证书编号 */
    private String educationCertificateNo;

    /** 取得学历证书日期 */
    @Excel(name = "取得学历证书日期", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date achieveEducationCertificateDate;

    /** 学位证书标志 */
    private String isDegreeCertificate;

    /** 学位 */
    @Excel(name = "学位")
    private String degree;

    /** 学位类型 */
    @Excel(name = "学位类型")
    private String degreeType;

    /** 学位证书编号 */
    private String degreeCertificateNo;

    /** 取得学位证书日期 */
    @Excel(name = "取得学位证书日期", width = 30, dateFormat = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    private Date achiveDegreeCertificateDate;

    private Long residentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
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
    public void setIsDayEducation(String isDayEducation) 
    {
        this.isDayEducation = isDayEducation;
    }

    public String getIsDayEducation() 
    {
        return isDayEducation;
    }
    public void setIsReading(String isReading) 
    {
        this.isReading = isReading;
    }

    public String getIsReading() 
    {
        return isReading;
    }
    public void setReadingEducationBg(String readingEducationBg) 
    {
        this.readingEducationBg = readingEducationBg;
    }

    public String getReadingEducationBg() 
    {
        return readingEducationBg;
    }
    public void setPlainGraduationDate(Date plainGraduationDate) 
    {
        this.plainGraduationDate = plainGraduationDate;
    }

    public Date getPlainGraduationDate() 
    {
        return plainGraduationDate;
    }
    public void setLearnMajor(String learnMajor) 
    {
        this.learnMajor = learnMajor;
    }

    public String getLearnMajor() 
    {
        return learnMajor;
    }
    public void setReadingMajor(String readingMajor) 
    {
        this.readingMajor = readingMajor;
    }

    public String getReadingMajor() 
    {
        return readingMajor;
    }
    public void setReadingSchool(String readingSchool) 
    {
        this.readingSchool = readingSchool;
    }

    public String getReadingSchool() 
    {
        return readingSchool;
    }
    public void setOtherReadingSchool(String otherReadingSchool) 
    {
        this.otherReadingSchool = otherReadingSchool;
    }

    public String getOtherReadingSchool() 
    {
        return otherReadingSchool;
    }
    public void setEducationBg(String educationBg) 
    {
        this.educationBg = educationBg;
    }

    public String getEducationBg() 
    {
        return educationBg;
    }
    public void setGraduationMajor(String graduationMajor) 
    {
        this.graduationMajor = graduationMajor;
    }

    public String getGraduationMajor() 
    {
        return graduationMajor;
    }
    public void setGraduationSchool(String graduationSchool) 
    {
        this.graduationSchool = graduationSchool;
    }

    public String getGraduationSchool() 
    {
        return graduationSchool;
    }
    public void setOtherGraduationSchool(String otherGraduationSchool) 
    {
        this.otherGraduationSchool = otherGraduationSchool;
    }

    public String getOtherGraduationSchool() 
    {
        return otherGraduationSchool;
    }
    public void setIsGraduationCertificate(String isGraduationCertificate) 
    {
        this.isGraduationCertificate = isGraduationCertificate;
    }

    public String getIsGraduationCertificate() 
    {
        return isGraduationCertificate;
    }
    public void setEducationCertificateNo(String educationCertificateNo) 
    {
        this.educationCertificateNo = educationCertificateNo;
    }

    public String getEducationCertificateNo() 
    {
        return educationCertificateNo;
    }
    public void setAchieveEducationCertificateDate(Date achieveEducationCertificateDate) 
    {
        this.achieveEducationCertificateDate = achieveEducationCertificateDate;
    }

    public Date getAchieveEducationCertificateDate() 
    {
        return achieveEducationCertificateDate;
    }
    public void setIsDegreeCertificate(String isDegreeCertificate) 
    {
        this.isDegreeCertificate = isDegreeCertificate;
    }

    public String getIsDegreeCertificate() 
    {
        return isDegreeCertificate;
    }
    public void setDegree(String degree) 
    {
        this.degree = degree;
    }

    public String getDegree() 
    {
        return degree;
    }
    public void setDegreeType(String degreeType) 
    {
        this.degreeType = degreeType;
    }

    public String getDegreeType() 
    {
        return degreeType;
    }
    public void setDegreeCertificateNo(String degreeCertificateNo) 
    {
        this.degreeCertificateNo = degreeCertificateNo;
    }

    public String getDegreeCertificateNo() 
    {
        return degreeCertificateNo;
    }
    public void setAchiveDegreeCertificateDate(Date achiveDegreeCertificateDate) 
    {
        this.achiveDegreeCertificateDate = achiveDegreeCertificateDate;
    }

    public Date getAchiveDegreeCertificateDate() 
    {
        return achiveDegreeCertificateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idcardType", getIdcardType())
            .append("idcard", getIdcard())
            .append("isDayEducation", getIsDayEducation())
            .append("isReading", getIsReading())
            .append("readingEducationBg", getReadingEducationBg())
            .append("plainGraduationDate", getPlainGraduationDate())
            .append("learnMajor", getLearnMajor())
            .append("readingMajor", getReadingMajor())
            .append("readingSchool", getReadingSchool())
            .append("otherReadingSchool", getOtherReadingSchool())
            .append("educationBg", getEducationBg())
            .append("graduationMajor", getGraduationMajor())
            .append("graduationSchool", getGraduationSchool())
            .append("otherGraduationSchool", getOtherGraduationSchool())
            .append("isGraduationCertificate", getIsGraduationCertificate())
            .append("educationCertificateNo", getEducationCertificateNo())
            .append("achieveEducationCertificateDate", getAchieveEducationCertificateDate())
            .append("isDegreeCertificate", getIsDegreeCertificate())
            .append("degree", getDegree())
            .append("degreeType", getDegreeType())
            .append("degreeCertificateNo", getDegreeCertificateNo())
            .append("achiveDegreeCertificateDate", getAchiveDegreeCertificateDate())
            .toString();
    }
}

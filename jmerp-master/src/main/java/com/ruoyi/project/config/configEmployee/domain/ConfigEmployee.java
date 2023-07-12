package com.ruoyi.project.config.configEmployee.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 员工信息对象 config_employee
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String employeeName;

    /** 工号 */
    @Excel(name = "工号")
    private String employeeNo;

    /** 性别 */
    @Excel(name = "性别", dictType = "sys_user_sex")
    private String gender;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyin;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idNumber;

    /** 手机号 */
    @Excel(name = "手机号")
    private String cellPhone;

    /** 部门 */
    @Excel(name = "部门")
    private String deptName;
    private Long deptId;

    /** 岗位 */
    @Excel(name = "岗位", dictType = "job_post")
    private String jobPost;

    /** 职位 */
    @Excel(name = "职位", dictType = "job_position")
    private String jobPosition;

    /** 微信号 */
    @Excel(name = "微信号")
    private String wechat;

    /** 在职状态 */
    @Excel(name = "在职状态", dictType = "job_status")
    private String jobStatus;

    /** 状态 */
    @Excel(name = "状态", dictType = "common_status")
    private String status;

    /** 是否签订劳动合同 */
    @Excel(name = "签订劳动合同", dictType = "sys_yes_no")
    private String isLaborContract;

    /** 劳动合同签订日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "劳动合同起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date laborContractStartDate;

    /** 劳动合同到期日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "劳动合同到期日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date laborContractEndDate;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryDate;

    /** 离职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dimissionDate;

    /** 是否购买社保 */
    @Excel(name = "是否购买社保", dictType = "sys_yes_no")
    private String isSocialSecurity;

    /** 社保开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "社保开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date socialSecurityStartDate;

    /** 社保结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "社保结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date socialSecurityEndDate;

    /** 基本工资 */
    @Excel(name = "基本工资")
    private Integer wage;

    /** 银行账号 */
    @Excel(name = "银行账号")
    private String bankAccount;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况", dictType = "marital_status")
    private String maritalStatus;

    /** 学历 */
    @Excel(name = "学历", dictType = "education_background")
    private String educationBackground;

    /** 宿舍 */
    @Excel(name = "宿舍")
    private String dorm;

    /** 照片 */
    private String photoImg;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }
    public void setPhotoImg(String photoImg)
    {
        this.photoImg = photoImg;
    }

    public String getPhotoImg()
    {
        return photoImg;
    }
    public void setEmployeeNo(String employeeNo)
    {
        this.employeeNo = employeeNo;
    }

    public String getEmployeeNo()
    {
        return employeeNo;
    }
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setPinyin(String pinyin)
    {
        this.pinyin = pinyin;
    }

    public String getPinyin()
    {
        return pinyin;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }
    public void setCellPhone(String cellPhone)
    {
        this.cellPhone = cellPhone;
    }

    public String getCellPhone()
    {
        return cellPhone;
    }
    public void setWechat(String wechat)
    {
        this.wechat = wechat;
    }

    public String getWechat()
    {
        return wechat;
    }
    public void setMaritalStatus(String maritalStatus)
    {
        this.maritalStatus = maritalStatus;
    }

    public String getMaritalStatus()
    {
        return maritalStatus;
    }
    public void setEducationBackground(String educationBackground)
    {
        this.educationBackground = educationBackground;
    }

    public String getEducationBackground()
    {
        return educationBackground;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setJobPost(String jobPost)
    {
        this.jobPost = jobPost;
    }

    public String getJobPost()
    {
        return jobPost;
    }
    public void setJobPosition(String jobPosition)
    {
        this.jobPosition = jobPosition;
    }

    public String getJobPosition()
    {
        return jobPosition;
    }
    public void setJobStatus(String jobStatus)
    {
        this.jobStatus = jobStatus;
    }

    public String getJobStatus()
    {
        return jobStatus;
    }
    public void setWage(Integer wage)
    {
        this.wage = wage;
    }

    public Integer getWage()
    {
        return wage;
    }
    public void setBankAccount(String bankAccount)
    {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount()
    {
        return bankAccount;
    }
    public void setIsLaborContract(String isLaborContract)
    {
        this.isLaborContract = isLaborContract;
    }

    public String getIsLaborContract()
    {
        return isLaborContract;
    }
    public void setIsSocialSecurity(String isSocialSecurity)
    {
        this.isSocialSecurity = isSocialSecurity;
    }

    public String getIsSocialSecurity()
    {
        return isSocialSecurity;
    }
    public void setLaborContractStartDate(Date laborContractStartDate)
    {
        this.laborContractStartDate = laborContractStartDate;
    }

    public Date getLaborContractStartDate()
    {
        return laborContractStartDate;
    }
    public void setLaborContractEndDate(Date laborContractEndDate)
    {
        this.laborContractEndDate = laborContractEndDate;
    }

    public Date getLaborContractEndDate()
    {
        return laborContractEndDate;
    }
    public void setSocialSecurityStartDate(Date socialSecurityStartDate)
    {
        this.socialSecurityStartDate = socialSecurityStartDate;
    }

    public Date getSocialSecurityStartDate()
    {
        return socialSecurityStartDate;
    }
    public void setSocialSecurityEndDate(Date socialSecurityEndDate)
    {
        this.socialSecurityEndDate = socialSecurityEndDate;
    }

    public Date getSocialSecurityEndDate()
    {
        return socialSecurityEndDate;
    }
    public void setEntryDate(Date entryDate)
    {
        this.entryDate = entryDate;
    }

    public Date getEntryDate()
    {
        return entryDate;
    }
    public void setDimissionDate(Date dimissionDate)
    {
        this.dimissionDate = dimissionDate;
    }

    public Date getDimissionDate()
    {
        return dimissionDate;
    }
    public void setDorm(String dorm)
    {
        this.dorm = dorm;
    }

    public String getDorm()
    {
        return dorm;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("employeeName", getEmployeeName())
            .append("photoImg", getPhotoImg())
            .append("employeeNo", getEmployeeNo())
            .append("idNumber", getIdNumber())
            .append("pinyin", getPinyin())
            .append("gender", getGender())
            .append("cellPhone", getCellPhone())
            .append("wechat", getWechat())
            .append("maritalStatus", getMaritalStatus())
            .append("educationBackground", getEducationBackground())
            .append("deptId", getDeptId())
            .append("jobPost", getJobPost())
            .append("jobPosition", getJobPosition())
            .append("jobStatus", getJobStatus())
            .append("wage", getWage())
            .append("bankAccount", getBankAccount())
            .append("isLaborContract", getIsLaborContract())
            .append("isSocialSecurity", getIsSocialSecurity())
            .append("laborContractStartDate", getLaborContractStartDate())
            .append("laborContractEndDate", getLaborContractEndDate())
            .append("socialSecurityStartDate", getSocialSecurityStartDate())
            .append("socialSecurityEndDate", getSocialSecurityEndDate())
            .append("entryDate", getEntryDate())
            .append("dimissionDate", getDimissionDate())
            .append("dorm", getDorm())
            .toString();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

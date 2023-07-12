package com.ruoyi.project.config.configTeamMember.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 班组组员对象 config_team_member
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigTeamMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 班组 */
    @Excel(name = "班组")
    private Long teamId;

    /** 组员 */
    @Excel(name = "组员")
    private String employeeName;
    private Long employeeId;

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

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }
    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("teamId", getTeamId())
            .append("employeeId", getEmployeeId())
            .toString();
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(String employeeNo) {
        this.employeeNo = employeeNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }
}

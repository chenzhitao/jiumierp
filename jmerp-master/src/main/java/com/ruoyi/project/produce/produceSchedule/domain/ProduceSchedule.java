package com.ruoyi.project.produce.produceSchedule.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 生产排程对象 produce_schedule
 * 
 * @author fangzhou
 * @date 2021-04-29
 */
public class ProduceSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;
    private String printType;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 审批人 */
    @Excel(name = "审批人")
    private String approver;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

    /** 审批类型 */
    @Excel(name = "审批类型")
    private String approvalType;

    /** 单号 */
    @Excel(name = "单号")
    private String serialNumber;

    /** 生产状态 */
    @Excel(name = "生产状态")
    private String produceStatus;

    /** 工作中心 */
    @Excel(name = "工作中心")
    private String workCenter;

    /** 设备 */
    @Excel(name = "设备")
    private String equipmentName;
    private Long equipmentId;

    /** 班组 */
    @Excel(name = "班组")
    private String teamName;
    private Long teamId;

    private String pauseInfo;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date produceDate;

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
    public void setApprover(String approver)
    {
        this.approver = approver;
    }

    public String getApprover()
    {
        return approver;
    }
    public void setApprovalTime(Date approvalTime)
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime()
    {
        return approvalTime;
    }
    public void setApprovalType(String approvalType)
    {
        this.approvalType = approvalType;
    }

    public String getApprovalType()
    {
        return approvalType;
    }
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber()
    {
        return serialNumber;
    }
    public void setProduceStatus(String produceStatus)
    {
        this.produceStatus = produceStatus;
    }

    public String getProduceStatus()
    {
        return produceStatus;
    }
    public void setWorkCenter(String workCenter)
    {
        this.workCenter = workCenter;
    }

    public String getWorkCenter()
    {
        return workCenter;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }
    public void setProduceDate(Date produceDate)
    {
        this.produceDate = produceDate;
    }

    public Date getProduceDate()
    {
        return produceDate;
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
            .append("approver", getApprover())
            .append("approvalTime", getApprovalTime())
            .append("approvalType", getApprovalType())
            .append("serialNumber", getSerialNumber())
            .append("produceStatus", getProduceStatus())
            .append("workCenter", getWorkCenter())
            .append("equipmentId", getEquipmentId())
            .append("teamId", getTeamId())
            .append("produceDate", getProduceDate())
            .toString();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPauseInfo() {
        return pauseInfo;
    }

    public void setPauseInfo(String pauseInfo) {
        this.pauseInfo = pauseInfo;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }
}

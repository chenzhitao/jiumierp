package com.ruoyi.project.config.configProcess.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 工序配置对象 config_process
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 工序名称 */
    @Excel(name = "工序名称")
    private String processName;

    /** 工序类型 */
    @Excel(name = "工序类型", dictType = "process_type")
    private String processType;

    /** 设备 */
    @Excel(name = "设备")
    private String equipmentName;
    private Long equipmentId;

    /** 生产最小批量 */
    @Excel(name = "生产最小批量")
    private Integer minBatchQty;

    /** 半成品单位 */
    @Excel(name = "半成品单位", dictType = "common_unit")
    private String semiFinishedUnit;

    /** 生产加工方式 */
    @Excel(name = "加工方式", dictType = "processing_type")
    private String processingType;

    /** 工序要求 */
    @Excel(name = "工序要求")
    private String requirements;

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
    public void setProcessName(String processName)
    {
        this.processName = processName;
    }

    public String getProcessName()
    {
        return processName;
    }
    public void setProcessType(String processType)
    {
        this.processType = processType;
    }

    public String getProcessType()
    {
        return processType;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setMinBatchQty(Integer minBatchQty)
    {
        this.minBatchQty = minBatchQty;
    }

    public Integer getMinBatchQty()
    {
        return minBatchQty;
    }
    public void setSemiFinishedUnit(String semiFinishedUnit)
    {
        this.semiFinishedUnit = semiFinishedUnit;
    }

    public String getSemiFinishedUnit()
    {
        return semiFinishedUnit;
    }
    public void setProcessingType(String processingType)
    {
        this.processingType = processingType;
    }

    public String getProcessingType()
    {
        return processingType;
    }
    public void setRequirements(String requirements)
    {
        this.requirements = requirements;
    }

    public String getRequirements()
    {
        return requirements;
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
            .append("processName", getProcessName())
            .append("processType", getProcessType())
            .append("equipmentId", getEquipmentId())
            .append("minBatchQty", getMinBatchQty())
            .append("semiFinishedUnit", getSemiFinishedUnit())
            .append("processingType", getProcessingType())
            .append("requirements", getRequirements())
            .toString();
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}

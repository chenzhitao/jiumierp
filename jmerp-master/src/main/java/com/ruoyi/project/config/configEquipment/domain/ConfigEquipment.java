package com.ruoyi.project.config.configEquipment.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 设备管理对象 config_equipment
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigEquipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String equipmentName;

    /** 设备型号 */
    @Excel(name = "设备型号")
    private String equipmentModel;

    /** 生产厂商 */
    @Excel(name = "生产厂商")
    private String vendor;

    /** 设备分类 */
    @Excel(name = "设备分类", dictType = "equipment_type")
    private String equipmentType;

    /** 设备用途 */
    @Excel(name = "设备用途", dictType = "equipment_purpose")
    private String equipmentPurpose;

    /** 购买日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "购买日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date purchaseDate;

    /** 设备价格 */
    @Excel(name = "设备价格")
    private BigDecimal price;

    /** 折旧方法 */
    @Excel(name = "折旧方法", dictType = "depreciation_type")
    private String depreciationType;

    /** 折旧年限 */
    @Excel(name = "折旧年限")
    private Integer depreciationLimit;

    /** 使用部门 */
    @Excel(name = "使用部门")
    private String deptName;
    private Long deptId;

    /** 生产批量 */
    @Excel(name = "生产批量")
    private Integer batchQty;

    /** 生产准备时间(H) */
    @Excel(name = "生产准备时间(H)")
    private BigDecimal setupHours;

    /** 最大上机规格长(mm) */
    @Excel(name = "最大上机规格长(mm)")
    private Integer maxWorkLong;

    /** 最大上机规格宽(mm) */
    @Excel(name = "最大上机规格宽(mm)")
    private Integer maxWorkWidth;

    /** 最小上机规格长(mm) */
    @Excel(name = "最小上机规格长(mm)")
    private Integer minWorkLong;

    /** 最小上机规格宽(mm) */
    @Excel(name = "最小上机规格宽(mm)")
    private Integer minWorkWidth;

    /** 最大印色 */
    @Excel(name = "最大印色")
    private Integer maxPrintColor;

    /** 产量上报方式 */
    @Excel(name = "产量上报方式", dictType = "production_report_type")
    private String productionReportType;

    /** 产量上报方式 */
    @Excel(name = "设备开数", dictType = "equipment_open")
    private String equipmentOpen;

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
    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentName()
    {
        return equipmentName;
    }
    public void setEquipmentModel(String equipmentModel)
    {
        this.equipmentModel = equipmentModel;
    }

    public String getEquipmentModel()
    {
        return equipmentModel;
    }
    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    public String getVendor()
    {
        return vendor;
    }
    public void setEquipmentType(String equipmentType)
    {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentType()
    {
        return equipmentType;
    }
    public void setEquipmentPurpose(String equipmentPurpose)
    {
        this.equipmentPurpose = equipmentPurpose;
    }

    public String getEquipmentPurpose()
    {
        return equipmentPurpose;
    }
    public void setPurchaseDate(Date purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    public Date getPurchaseDate()
    {
        return purchaseDate;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setDepreciationType(String depreciationType)
    {
        this.depreciationType = depreciationType;
    }

    public String getDepreciationType()
    {
        return depreciationType;
    }
    public void setDepreciationLimit(Integer depreciationLimit)
    {
        this.depreciationLimit = depreciationLimit;
    }

    public Integer getDepreciationLimit()
    {
        return depreciationLimit;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setBatchQty(Integer batchQty)
    {
        this.batchQty = batchQty;
    }

    public Integer getBatchQty()
    {
        return batchQty;
    }
    public void setSetupHours(BigDecimal setupHours)
    {
        this.setupHours = setupHours;
    }

    public BigDecimal getSetupHours()
    {
        return setupHours;
    }
    public void setMaxWorkLong(Integer maxWorkLong)
    {
        this.maxWorkLong = maxWorkLong;
    }

    public Integer getMaxWorkLong()
    {
        return maxWorkLong;
    }
    public void setMaxWorkWidth(Integer maxWorkWidth)
    {
        this.maxWorkWidth = maxWorkWidth;
    }

    public Integer getMaxWorkWidth()
    {
        return maxWorkWidth;
    }
    public void setMinWorkLong(Integer minWorkLong)
    {
        this.minWorkLong = minWorkLong;
    }

    public Integer getMinWorkLong()
    {
        return minWorkLong;
    }
    public void setMinWorkWidth(Integer minWorkWidth)
    {
        this.minWorkWidth = minWorkWidth;
    }

    public Integer getMinWorkWidth()
    {
        return minWorkWidth;
    }
    public void setMaxPrintColor(Integer maxPrintColor)
    {
        this.maxPrintColor = maxPrintColor;
    }

    public Integer getMaxPrintColor()
    {
        return maxPrintColor;
    }
    public void setProductionReportType(String productionReportType)
    {
        this.productionReportType = productionReportType;
    }

    public String getProductionReportType()
    {
        return productionReportType;
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
            .append("equipmentName", getEquipmentName())
            .append("equipmentModel", getEquipmentModel())
            .append("vendor", getVendor())
            .append("equipmentType", getEquipmentType())
            .append("equipmentPurpose", getEquipmentPurpose())
            .append("purchaseDate", getPurchaseDate())
            .append("price", getPrice())
            .append("depreciationType", getDepreciationType())
            .append("depreciationLimit", getDepreciationLimit())
            .append("deptId", getDeptId())
            .append("batchQty", getBatchQty())
            .append("setupHours", getSetupHours())
            .append("maxWorkLong", getMaxWorkLong())
            .append("maxWorkWidth", getMaxWorkWidth())
            .append("minWorkLong", getMinWorkLong())
            .append("minWorkWidth", getMinWorkWidth())
            .append("maxPrintColor", getMaxPrintColor())
            .append("productionReportType", getProductionReportType())
            .toString();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEquipmentOpen() {
        return equipmentOpen;
    }

    public void setEquipmentOpen(String equipmentOpen) {
        this.equipmentOpen = equipmentOpen;
    }
}

package com.ruoyi.project.config.configMaterials.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 材料配置对象 config_materials
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigMaterials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态 */
    //@Excel(name = "状态")
    private String status;

    /** 材料名称 */
    @Excel(name = "材料名称")
    private String materialsName;
    private String materialsName2;

    /** 材料类型 */
    @Excel(name = "材料类型", dictType = "materials_type")
    private String materialsType;

    /** 主材类型 */
    @Excel(name = "主材类型", dictType = "main_materials_type")
    private String mainMaterialsType;

    /** 材料规格长(mm) */
    @Excel(name = "材料规格长(mm)")
    private Integer sizeLong;

    /** 材料规格宽(mm) */
    @Excel(name = "材料规格宽(mm)")
    private Integer sizeWidth;

    /** 门幅(mm) */
    @Excel(name = "门幅(mm)")
    private Integer width;

    /** 按面积计算 */
    @Excel(name = "按面积计算", dictType = "sys_yes_no")
    private String isAreaCalculate;

    /** 克重(g) */
    @Excel(name = "克重(g)")
    private Integer weight;

    /** 密度(g/mm³) */
    @Excel(name = "密度(g/mm³)")
    private BigDecimal density;

    /** 厚度(mm) */
    @Excel(name = "厚度(mm)")
    private BigDecimal thickness;

    /** 楞型 */
    @Excel(name = "楞型")
    private String corrugatedName;
    private Long corrugatedId;

    /** 纸张配方 */
    @Excel(name = "纸张配方")
    private String paperFormulaName;
    private Long paperFormulaId;

    /** 保质期(天) */
    @Excel(name = "保质期(天)")
    private Integer qualityDays;

    /** 采购周期(天) */
    @Excel(name = "采购周期(天)")
    private Integer purchaseCycle;

    /** 主要供应商 */
    @Excel(name = "主要供应商")
    private String supplierName;
    private Long supplierId;

    /** 免检 */
    @Excel(name = "免检", dictType = "sys_yes_no")
    private String exemption;

    /** 采购单价 */
    @Excel(name = "采购单价")
    private BigDecimal purchasePrice;

    /** 销售单价 */
    @Excel(name = "销售单价")
    private BigDecimal salePrice;

    /** 采购单位 */
    @Excel(name = "采购单位", dictType = "common_unit")
    private String purchaseUnit;

    /** 生产单位 */
    @Excel(name = "生产单位", dictType = "common_unit")
    private String productionUnit;

    /** 销售单位 */
    @Excel(name = "销售单位", dictType = "common_unit")
    private String saleUnit;

    /** 门幅单位 */
    @Excel(name = "门幅单位", dictType = "common_unit")
    private String widthUnit;

    /** 库存单位 */
    @Excel(name = "库存单位", dictType = "common_unit")
    private String inventoryUnit;

    /** 材料要求 */
    @Excel(name = "材料要求")
    private String requirements;

    private String contactName;
    private String cellPhone;

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
    public void setMaterialsName(String materialsName)
    {
        this.materialsName = materialsName;
    }

    public String getMaterialsName()
    {
        return materialsName;
    }
    public void setMaterialsType(String materialsType)
    {
        this.materialsType = materialsType;
    }

    public String getMaterialsType()
    {
        return materialsType;
    }
    public void setMainMaterialsType(String mainMaterialsType)
    {
        this.mainMaterialsType = mainMaterialsType;
    }

    public String getMainMaterialsType()
    {
        return mainMaterialsType;
    }
    public void setSizeLong(Integer sizeLong)
    {
        this.sizeLong = sizeLong;
    }

    public Integer getSizeLong()
    {
        return sizeLong;
    }
    public void setSizeWidth(Integer sizeWidth)
    {
        this.sizeWidth = sizeWidth;
    }

    public Integer getSizeWidth()
    {
        return sizeWidth;
    }
    public void setWidth(Integer width)
    {
        this.width = width;
    }

    public Integer getWidth()
    {
        return width;
    }
    public void setIsAreaCalculate(String isAreaCalculate)
    {
        this.isAreaCalculate = isAreaCalculate;
    }

    public String getIsAreaCalculate()
    {
        return isAreaCalculate;
    }
    public void setWeight(Integer weight)
    {
        this.weight = weight;
    }

    public Integer getWeight()
    {
        return weight;
    }
    public void setDensity(BigDecimal density)
    {
        this.density = density;
    }

    public BigDecimal getDensity()
    {
        return density;
    }
    public void setThickness(BigDecimal thickness)
    {
        this.thickness = thickness;
    }

    public BigDecimal getThickness()
    {
        return thickness;
    }
    public void setCorrugatedId(Long corrugatedId)
    {
        this.corrugatedId = corrugatedId;
    }

    public Long getCorrugatedId()
    {
        return corrugatedId;
    }
    public void setPaperFormulaId(Long paperFormulaId)
    {
        this.paperFormulaId = paperFormulaId;
    }

    public Long getPaperFormulaId()
    {
        return paperFormulaId;
    }
    public void setQualityDays(Integer qualityDays)
    {
        this.qualityDays = qualityDays;
    }

    public Integer getQualityDays()
    {
        return qualityDays;
    }
    public void setPurchaseCycle(Integer purchaseCycle)
    {
        this.purchaseCycle = purchaseCycle;
    }

    public Integer getPurchaseCycle()
    {
        return purchaseCycle;
    }
    public void setSupplierId(Long supplierId)
    {
        this.supplierId = supplierId;
    }

    public Long getSupplierId()
    {
        return supplierId;
    }
    public void setExemption(String exemption)
    {
        this.exemption = exemption;
    }

    public String getExemption()
    {
        return exemption;
    }
    public void setPurchasePrice(BigDecimal purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchasePrice()
    {
        return purchasePrice;
    }
    public void setSalePrice(BigDecimal salePrice)
    {
        this.salePrice = salePrice;
    }

    public BigDecimal getSalePrice()
    {
        return salePrice;
    }
    public void setPurchaseUnit(String purchaseUnit)
    {
        this.purchaseUnit = purchaseUnit;
    }

    public String getPurchaseUnit()
    {
        return purchaseUnit;
    }
    public void setProductionUnit(String productionUnit)
    {
        this.productionUnit = productionUnit;
    }

    public String getProductionUnit()
    {
        return productionUnit;
    }
    public void setSaleUnit(String saleUnit)
    {
        this.saleUnit = saleUnit;
    }

    public String getSaleUnit()
    {
        return saleUnit;
    }
    public void setWidthUnit(String widthUnit)
    {
        this.widthUnit = widthUnit;
    }

    public String getWidthUnit()
    {
        return widthUnit;
    }
    public void setInventoryUnit(String inventoryUnit)
    {
        this.inventoryUnit = inventoryUnit;
    }

    public String getInventoryUnit()
    {
        return inventoryUnit;
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
            .append("materialsName", getMaterialsName())
            .append("materialsType", getMaterialsType())
            .append("mainMaterialsType", getMainMaterialsType())
            .append("sizeLong", getSizeLong())
            .append("sizeWidth", getSizeWidth())
            .append("width", getWidth())
            .append("isAreaCalculate", getIsAreaCalculate())
            .append("weight", getWeight())
            .append("density", getDensity())
            .append("thickness", getThickness())
            .append("corrugatedId", getCorrugatedId())
            .append("paperFormulaId", getPaperFormulaId())
            .append("qualityDays", getQualityDays())
            .append("purchaseCycle", getPurchaseCycle())
            .append("supplierId", getSupplierId())
            .append("exemption", getExemption())
            .append("purchasePrice", getPurchasePrice())
            .append("salePrice", getSalePrice())
            .append("purchaseUnit", getPurchaseUnit())
            .append("productionUnit", getProductionUnit())
            .append("saleUnit", getSaleUnit())
            .append("widthUnit", getWidthUnit())
            .append("inventoryUnit", getInventoryUnit())
            .append("requirements", getRequirements())
            .toString();
    }

    public String getCorrugatedName() {
        return corrugatedName;
    }

    public void setCorrugatedName(String corrugatedName) {
        this.corrugatedName = corrugatedName;
    }

    public String getPaperFormulaName() {
        return paperFormulaName;
    }

    public void setPaperFormulaName(String paperFormulaName) {
        this.paperFormulaName = paperFormulaName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getMaterialsName2() {
        return materialsName2;
    }

    public void setMaterialsName2(String materialsName2) {
        this.materialsName2 = materialsName2;
    }
}

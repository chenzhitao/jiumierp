package com.ruoyi.project.config.configProductionTemplateProcess.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 生产工艺卡工序对象 config_production_template_process
 * 
 * @author fangzhou
 * @date 2021-04-13
 */
public class ConfigProductionTemplateProcess extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 报价工艺卡 */
    @Excel(name = "报价工艺卡")
    private String productionTemplateName;
    private Long productionTemplateId;

    /** 工序 */
    @Excel(name = "工序")
    private String processName;
    private Long processId;

    /** 倍率 */
    @Excel(name = "倍率")
    private BigDecimal times;

    /** 计价方式 */
    @Excel(name = "计价方式", dictType = "valuation_type")
    private String valuationType;

    /** 外发 */
    @Excel(name = "外发", dictType = "sys_yes_no")
    private String isOutsource;

    /** 外发带料 */
    @Excel(name = "外发带料", dictType = "sys_yes_no")
    private String isWithMaterials;

    /** 外发价格 */
    @Excel(name = "外发价格")
    private BigDecimal outsourcePrice;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal price;

    /** 设备 */
    @Excel(name = "设备")
    private String equipmentName;
    private Long equipmentId;

    /** 损耗率(%) */
    @Excel(name = "损耗率(%)")
    private BigDecimal lossRate;

    /** 固定损耗数 */
    @Excel(name = "固定损耗数")
    private Integer lossQty;

    /** 固定损耗数 */
    @Excel(name = "倍率计算", dictType = "sys_yes_no")
    private String isTimeCount;

    /** 顺序 */
    @Excel(name = "顺序")
    private Integer processOrder;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setProductionTemplateId(Long productionTemplateId)
    {
        this.productionTemplateId = productionTemplateId;
    }

    public Long getProductionTemplateId()
    {
        return productionTemplateId;
    }
    public void setProcessId(Long processId)
    {
        this.processId = processId;
    }

    public Long getProcessId()
    {
        return processId;
    }
    public void setTimes(BigDecimal times)
    {
        this.times = times;
    }

    public BigDecimal getTimes()
    {
        return times;
    }
    public void setValuationType(String valuationType)
    {
        this.valuationType = valuationType;
    }

    public String getValuationType()
    {
        return valuationType;
    }
    public void setIsOutsource(String isOutsource)
    {
        this.isOutsource = isOutsource;
    }

    public String getIsOutsource()
    {
        return isOutsource;
    }
    public void setIsWithMaterials(String isWithMaterials)
    {
        this.isWithMaterials = isWithMaterials;
    }

    public String getIsWithMaterials()
    {
        return isWithMaterials;
    }
    public void setOutsourcePrice(BigDecimal outsourcePrice)
    {
        this.outsourcePrice = outsourcePrice;
    }

    public BigDecimal getOutsourcePrice()
    {
        return outsourcePrice;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }
    public void setEquipmentId(Long equipmentId)
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId()
    {
        return equipmentId;
    }
    public void setLossRate(BigDecimal lossRate)
    {
        this.lossRate = lossRate;
    }

    public BigDecimal getLossRate()
    {
        return lossRate;
    }
    public void setLossQty(Integer lossQty)
    {
        this.lossQty = lossQty;
    }

    public Integer getLossQty()
    {
        return lossQty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("productionTemplateId", getProductionTemplateId())
            .append("processId", getProcessId())
            .append("times", getTimes())
            .append("valuationType", getValuationType())
            .append("isOutsource", getIsOutsource())
            .append("isWithMaterials", getIsWithMaterials())
            .append("outsourcePrice", getOutsourcePrice())
            .append("price", getPrice())
            .append("equipmentId", getEquipmentId())
            .append("lossRate", getLossRate())
            .append("lossQty", getLossQty())
            .toString();
    }

    public String getProductionTemplateName() {
        return productionTemplateName;
    }

    public void setProductionTemplateName(String productionTemplateName) {
        this.productionTemplateName = productionTemplateName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public Integer getProcessOrder() {
        return processOrder;
    }

    public void setProcessOrder(Integer processOrder) {
        this.processOrder = processOrder;
    }

    public String getIsTimeCount() {
        return isTimeCount;
    }

    public void setIsTimeCount(String isTimeCount) {
        this.isTimeCount = isTimeCount;
    }
}
